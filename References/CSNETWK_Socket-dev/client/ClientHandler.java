import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class ClientHandler extends Thread {

    private final int MAX_BYTES = 1024 * 16; // 16kb
    private ClientModel cm;
    private Chatbox cb;
    private ClientController cc;

    public ClientHandler(ClientModel cm, Chatbox cb, ClientController cc) {
        this.cm = cm;
        this.cb = cb;
        this.cc = cc;
    }

    @Override
    public void run() {
        try {
            while (cm.getState() == ConnectionState.CONNECTED)
                loop();

        } catch (Exception e) {
            if (cm.getState() != ConnectionState.CLOSED) {
                e.printStackTrace();
                cc.askToSave();
            }
        } finally {
            cm.setState(ConnectionState.INITIALIZING);
            try {
                cm.getSocket().close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void loop() throws SocketException, Exception {
        String str, key, sender, msg, log = "";
        long timestamp;
        switch(cm.getReader().readInt()) {
            case Action.ADD_USER:
                str = cm.getReader().readUTF();
                key = cm.getUsername().compareTo(str) < 1 ?
                                cm.getUsername() + '-' + str :
                                str + '-' + cm.getUsername();

                if (!cm.getMessages().containsKey(key))
                    cm.getMessages().put(key, new ArrayList<>());
                    
                cb.addUser(
                    str,
                    cm.getReader().readUTF(),
                    new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            cm.setReceiver(str);
                            cb.setChat(cm.getUsername(), str, cm.getMessages().get(key));
                        }
                    }
                );
                log = (new Date()).toInstant() + "> CLIENT: User " + str + " has connected to the server";
            break;
            case Action.REMOVE_USER:
                str = cm.getReader().readUTF();
                cb.removeUser(str);
                if (cm.getReceiver() != null && cm.getReceiver().equals(str)) {
                    cm.setReceiver(null);
                    cb.alertDisconnect(str);
                    // cb.clearChat();
                }
                log = (new Date()).toInstant() + "> User " + str + " has disconnected to the server";
            break;
            case Action.RECEIVE_MESSAGE:
                sender = cm.getReader().readUTF();
                msg = cm.getReader().readUTF();
                timestamp = cm.getReader().readLong();
                key = cm.getReader().readUTF();

                if (cm.getReceiver() != null && cm.getReceiver().equals(sender)) {
                    cb.appendChat(sender + ": " + msg, false);
                } else {
                    cb.highlightUser(sender);
                }

                Message message = new Message(sender, msg, timestamp);
                cm.getMessages().get(key).add(message);
                log = message.detailed();
            break;
            case Action.RECEIVE_IMAGE:
                sender = cm.getReader().readUTF();
                msg = cm.getReader().readUTF(); // filename
                timestamp = cm.getReader().readLong();
                key = cm.getReader().readUTF();
                
                try {
                    ImgMessage imgMsg = new ImgMessage(sender, msg, timestamp);
                    String filepath = imgMsg.getFilepath(false);
                    FileOutputStream image = new FileOutputStream(filepath);
                    byte[] b = new byte[MAX_BYTES];
                    int size;

                    while ((size = cm.getReader().readInt()) > 0) {
                        cm.getReader().read(b, 0, size);
                        image.write(b, 0, size);
                    }
                    image.close();

                    // System.out.println("CLIENT: Displaying image");
                    if (cm.getReceiver() != null && cm.getReceiver().equals(sender)) {
                        cb.appendImgChat(imgMsg, false);
                    } else {
                        cb.highlightUser(sender);
                    }
                    cm.getMessages().get(key).add(imgMsg);
                    log = imgMsg.getDate() + "> " + sender + ": Image " + msg + " sent to you";
                } catch (Exception e) {
                    e.printStackTrace();
                }
            break;
        }
        System.out.println(log);
        cm.getLogs().add(log);
    }
}