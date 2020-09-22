import java.io.*;
import java.net.*;
import java.util.*;

public class ConnectionHandler extends Thread {

    private Socket s;
    private ConnectionState state;
    private String username; // TODO: make this an object if needed
    private String avatar;
    private DataInputStream reader;
    private DataOutputStream writer;
    private static LinkedList<ConnectionHandler> noUsername = new LinkedList<>();
    private static HashMap<String, ConnectionHandler> connections = new HashMap<>();
    private static HashMap<String, ArrayList<Message>> messages = new HashMap<>();
    private static final int MAX_BYTES = 1024 * 16; // 16kb
    private WriterTxt fileWriter;

    public ConnectionHandler(Socket s, WriterTxt fileWriter) {
        this.s = s;
        this.fileWriter = fileWriter;
        state = ConnectionState.CONNECTED;
    }

    public String getUsername() {
        return username;
    }

    public String getAvatar() {
        return avatar;
    }

    @Override
    public void run() {
        try {
            reader = new DataInputStream(s.getInputStream());
            writer = new DataOutputStream(s.getOutputStream());

            noUsername.add(this);

            while (state == ConnectionState.CONNECTED)
                loop();

        } catch (InterruptedException e) {
            // good interrupt
        } catch (Exception e) {
            if (state != ConnectionState.CLOSED) {
                e.printStackTrace();
            }
        } finally {
            if (username != null) {
                connections.remove(username);
                // connections.put(username, null);
                for (ConnectionHandler ch: connections.values()) {
                    try {
                        ch.removeUser(username);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            
            try {
                s.close();
                log("Client " + s.getRemoteSocketAddress() + (username != null ? " of user " + username : "") + " was closed");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void loop() throws InterruptedException, SocketException, Exception {
        String receiver, msg;
        long timestamp;
        switch(reader.readInt()) {
            case Action.SEND_USERNAME:
                acceptUsername();
            break;
            case Action.SEND_MESSAGE:
                receiver = reader.readUTF();

                // if (!connections.containsKey(receiver)) { // TODO: User does not exists
                //     return
                // }

                msg = reader.readUTF();
                timestamp = reader.readLong();
                // add to the list of messages
                String key = getKeyMsg(username, receiver);
                addMsg(key, msg, timestamp);
                connections.get(receiver).sendMessage(key, username, msg, timestamp);
            break;
            case Action.SEND_IMAGE:
                receiver = reader.readUTF();

                // if (!connections.containsKey(receiver)) { // TODO: User does not exists
                //     return;
                // }

                String filename = reader.readUTF();
                timestamp = reader.readLong();
                ImgMessage imgMsg = new ImgMessage(username, filename, timestamp);

                try {
                    ConnectionHandler other = connections.get(receiver);
                    key = getKeyMsg(username, receiver);
                    String filepath = imgMsg.getFilepath();
                    if (!messages.containsKey(key))
                        messages.put(key, new ArrayList<>());
                    messages.get(key).add(imgMsg);

                    FileOutputStream image = new FileOutputStream(filepath);
                    byte[] b = new byte[MAX_BYTES];
                    int size; // ctr = 0;
                    
                    // alert other user that image is sending
                    other.sendImgMessage(key, username, filename, timestamp);

                    // System.out.println("Server: Sending and Receiving Image");
                    while ((size = reader.readInt()) > 0) {
                        reader.read(b, 0, size);
                        image.write(b, 0, size);
                        other.sendImgBytes(b, size);

                        // ctr += size;
                        // System.out.println("Server: File uploaded " + ctr + " bytes");
                    }
                    // System.out.println("Server: File was uploaded and sent");
                    other.stopSendImgBytes();
                    image.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            break;
            case Action.LOGOUT:
                state = ConnectionState.CLOSED;
            break;
        }
    }

    private void addMsg(String key, String msg, long timestamp) {
        if (!messages.containsKey(key))
            messages.put(key, new ArrayList<>());
        
        messages.get(key).add(new Message(username, msg, timestamp));
    }

    private String getKeyMsg(String sender, String receiver) {
        return sender.compareTo(receiver) < 1 ?
                    sender + "-" + receiver :
                    receiver + "-" + sender;
    }

    public void acceptUsername() throws SocketException, Exception {
        if (state != ConnectionState.CONNECTED)
            return;
            
        if (connections.containsKey(username = reader.readUTF())) {
            log("Client " + s.getRemoteSocketAddress() + " with passed " + username + " was not accepted");
            writer.writeBoolean(false);
            return;
        }
        log("Client " + s.getRemoteSocketAddress() + " with passed " + username + " was accepted");
        writer.writeBoolean(true);

        avatar = reader.readUTF();

        // System.out.println("SERVER: Sending usernames to " + username);
        // Send all usernames to the user
        for (ConnectionHandler c: connections.values()) {
            writer.writeBoolean(true);
            writer.writeUTF(c.getUsername());
            writer.writeUTF(c.getAvatar());
        }

        writer.writeBoolean(false);

        // System.out.println("SERVER: Sending " + username + " to all other users");
        for (ConnectionHandler c: connections.values()) {
            c.addUser(username, avatar);
        }

        noUsername.remove(this);
        connections.put(username, this);
        
        // TODO: Not anymore boi
        // if (connections.containsKey(username)) {
        //     // throw all messages
        //     for (String str: connections.keySet()) {
        //         if (str.startsWith(username) || str.endsWith(username)) {
        //             // Fetch all messages from that
        //
        //         }
        //     }
        // }
    }

    public void addUser(String user, String avatar) throws SocketException, Exception {
        if (state != ConnectionState.CONNECTED)
            return;

        // System.out.println("SERVER: Adding user " + user);
        writer.writeInt(Action.ADD_USER);
        writer.writeUTF(user);
        writer.writeUTF(avatar);
    }

    public void removeUser(String user) throws SocketException, Exception {
        if (state != ConnectionState.CONNECTED)
            return;

        writer.writeInt(Action.REMOVE_USER);
        writer.writeUTF(user);
    }

    public void sendMessage(String key, String sender, String msg, long timestamp) throws SocketException, Exception {
        if (state != ConnectionState.CONNECTED)
            return;

        log(sender + " is sending a message to " + username, timestamp);
        writer.writeInt(Action.RECEIVE_MESSAGE);
        writer.writeUTF(sender);
        writer.writeUTF(msg);
        writer.writeLong(timestamp);
        writer.writeUTF(key);
    }

    public void sendImgMessage(String key, String sender, String filename, long timestamp) throws SocketException, Exception {
        if (state != ConnectionState.CONNECTED)
            return;

        log(sender + " is sending an image to " + username, timestamp);
        writer.writeInt(Action.RECEIVE_IMAGE);
        writer.writeUTF(sender);
        writer.writeUTF(filename);
        writer.writeLong(timestamp);
        writer.writeUTF(key);
    }

    public void sendImgBytes(byte[] b, int size) throws IOException {
        writer.writeInt(size);
        writer.write(b, 0, size);
    }

    public void stopSendImgBytes() throws IOException {
        writer.writeInt(-1);
    }

    public void log(String text) {
        text = (new Date()).toInstant() + "> SERVER: " + text;
        System.out.println(text);
        fileWriter.write(text);
    }

    public void log(String text, long timestamp) {
        text = (new Date(timestamp)).toInstant() + "> SERVER: " + text;
        System.out.println(text);
        fileWriter.write(text);
    }

    public static void stopAll() throws Exception {
        for (ConnectionHandler ch: noUsername) {
            ch.closeSocket();
        }

        for (ConnectionHandler ch: connections.values()) {
            ch.closeSocket();
        }
    }

    public void closeSocket() throws Exception {
        System.out.println("Closing socket");
        s.close();
        interrupt();
    }

}