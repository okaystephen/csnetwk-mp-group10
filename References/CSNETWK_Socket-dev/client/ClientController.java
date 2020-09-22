import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import javax.swing.filechooser.*;

public class ClientController {

    private final int MAX_BYTES = 1024 * 16; // 16kb
    private final int MAX_IMAGE_SIZE = 1024 * 1024 * 5; // 5mb
    private final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    private ClientGui cg;
    private ClientUsername cu;
    private Chatbox cb;
    private ClientModel cm;
    private ClientHandler ch;

    public ClientController(ClientGui cg, ClientUsername cu, Chatbox cb, ClientModel cm) {
        this.cg = cg;
        this.cu = cu;
        this.cb = cb;
        this.cm = cm;

        // Set the current state of the user to connecting
        this.cm.setState(ConnectionState.INITIALIZING);
        cg.playAnimations();

        this.cg.setLocation(new Point(100, 100));

        this.cu.addCloseListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                int confirmed = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to exit?",
                    "User Confirmation",
                    JOptionPane.YES_NO_OPTION
                );

                if (confirmed == JOptionPane.YES_OPTION) {
                    cm.setState(ConnectionState.CLOSED);
                    try {
                        cm.getWriter().writeInt(Action.LOGOUT);
                        cm.getSocket().close();
                    } catch (Exception err) {
                        err.printStackTrace();
                    }
                    cu.getFrame().setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                }
            }
        });

        this.cb.addCloseListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                Object[] options = {
                    "Yes",
                    "No",
                    "Cancel"
                };

                int confirmed = JOptionPane.showOptionDialog(
                    null,
                    "Do you want to save your logs before you exit?",
                    "User Confirmation",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    options,
                    null
                );

                try {
                    if (confirmed == JOptionPane.YES_OPTION) {
                        if (!saveToFile(true))
                            return;

                        cm.setState(ConnectionState.CLOSED);
                        cm.getWriter().writeInt(Action.LOGOUT);
                        cm.getSocket().close();
                        cb.getFrame().setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                    } else if (confirmed == JOptionPane.NO_OPTION) {
                        cm.setState(ConnectionState.CLOSED);
                        cm.getWriter().writeInt(Action.LOGOUT);
                        cm.getSocket().close();
                        cb.getFrame().setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                    }
                } catch (Exception err) {
                    err.printStackTrace();
                }
            }
        });

        cg.addLoginListener((ActionEvent event) -> {
            if (cm.getState() != ConnectionState.INITIALIZING) {
                cm.setState(ConnectionState.INITIALIZING);
            }

            String error = "";
            if (cg.getIPAddress().equals("")) {
                error += "Host field shouldn't be empty\n";
            }

            if (cg.getPortNumber().equals("")) {
                error += "Port field shouldn't be empty\n";
            } else if (!cg.getPortNumber().matches("\\d+")) {
                error += "Port field should be a number";
            } else if (Integer.parseInt(cg.getPortNumber()) < 1) {
                error += "Port field should be a positive number";
            }

            if (!error.equals("")) {
                JOptionPane.showMessageDialog(null, error);   
                return;
            }

            cm.setHost(cg.getIPAddress());
            cm.setPort(Integer.parseInt(cg.getPortNumber()));

            cm.setState(ConnectionState.CONNECTING);

            try {
                Socket socket = new Socket(this.cm.getHost(), this.cm.getPort());
                String log = (new Date()).toInstant() + "> CLIENT: Connected to server at " + socket.getRemoteSocketAddress();
                System.out.println(log);
                cm.getLogs().add(log);
                cm.setState(ConnectionState.CONNECTED);
                cm.setSocket(socket);
                cm.setReader(new DataInputStream(socket.getInputStream()));
                cm.setWriter(new DataOutputStream(socket.getOutputStream()));

                cu.setLocation(calculateCenter(cg.getLocation(), cg.getSize(), cu.getSize()));

                cg.setVisibility(false);
                cg.stopAnimations();
                cu.setVisibility(true);
            } catch (Exception err) {
                err.printStackTrace();
                JOptionPane.showMessageDialog(null, "Couldn't connect to the server");
                cm.setState(ConnectionState.INITIALIZING);
            }
        });

        cu.addLoginListener((ActionEvent event) -> {
            if (cm.getState() != ConnectionState.CONNECTED) {
                JOptionPane.showMessageDialog(null, "You are not connected to the server");

                cg.setLocation(calculateCenter(cu.getLocation(), cu.getSize(), cg.getSize()));

                cu.setVisibility(false);
                cg.playAnimations();
                cg.setVisibility(true);
                return;
            }

            if (cu.getUsername().equals("")) {
                JOptionPane.showMessageDialog(null, "Username shouldn't be empty");
                return;
            } else if (cu.getUsername().length() > 25) {
                JOptionPane.showMessageDialog(null, "Username too long (Max 25 characters).");
                return;
            }

            String avatar = cu.getSelected();

            if (avatar == null) {
                JOptionPane.showMessageDialog(null, "Select a profile pic be empty");
                return;
            }

            try {
                // Input Username
                String username, log;
                username = cu.getUsername();
                log = (new Date()).toInstant() + "> CLIENT: Sending username " + username + " to the server";
                System.out.println(log);
                cm.getLogs().add(log);

                cm.getWriter().writeInt(Action.SEND_USERNAME);
                cm.getWriter().writeUTF(username);
                if (!cm.getReader().readBoolean()) {
                    log = (new Date()).toInstant() + "> CLIENT: Username " + username + " was not accepted";
                    System.out.println(log);
                    cm.getLogs().add(log);

                    JOptionPane.showMessageDialog(null, "Username is already used. Please use another one.");
                    return;
                }

                cm.setUsername(username);
                log = (new Date()).toInstant() + "> CLIENT: " + username + " has been accepted";
                System.out.println(log);
                cm.getLogs().add(log);

                cm.getWriter().writeUTF(avatar);
                cm.setAvatar(avatar);

                // Get user list
                while (cm.getReader().readBoolean()) {
                    String temp = cm.getReader().readUTF();
                    log = (new Date()).toInstant() + "> CLIENT: Adding user " + temp;
                    System.out.println(log);
                    cm.getLogs().add(log);

                    String key = username.compareTo(temp) < 1 ?
                                    username + "-" + temp :
                                    temp + "-" + username;

                    if (!cm.getMessages().containsKey(key))
                        cm.getMessages().put(key, new ArrayList<>());

                    avatar = cm.getReader().readUTF();
                    cb.addUser(
                        temp,
                        avatar,
                        new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                cm.setReceiver(temp);
                                cb.setChat(username, temp, cm.getMessages().get(key));
                            }
                        }
                    );
                }
                // System.out.println("CLIENT: Done getting users");

                ch = new ClientHandler(cm, cb, this);
                ch.start();

                cb.setLocation(calculateCenter(cu.getLocation(), cu.getSize(), cb.getSize()));
                cb.setCurrentUser(username);

                cu.setVisibility(false);
                cb.setVisibility(true);
            } catch (SocketException err) {
                // No more connection
                System.out.println((new Date()).toInstant() + "> CLIENT: Lost Connection");
                JOptionPane.showMessageDialog(null, "Connection has been lost");

                cg.setLocation(calculateCenter(cu.getLocation(), cu.getSize(), cg.getSize()));

                cg.playAnimations();
                cg.setVisibility(true);
                cu.setVisibility(false);
            } catch (Exception err) {
                err.printStackTrace();
            }
        });

        cb.addLogoutListener((ActionEvent event) -> {
            if (cm.getState() != ConnectionState.CONNECTED)
                return;

            Object[] options = {
                "Yes",
                "No",
                "Cancel"
            };

            int confirmed = JOptionPane.showOptionDialog(
                null,
                "Do you want to save your logs before you exit?",
                "User Confirmation",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                null
            );

            try {
                if (confirmed == JOptionPane.YES_OPTION) {
                    if (!saveToFile(true))
                        return;

                    cm.getWriter().writeInt(Action.LOGOUT);
                    cm.setState(ConnectionState.CLOSED);
                    cb.removeAllUsers();

                    cg.setLocation(calculateCenter(cb.getLocation(), cb.getSize(), cg.getSize()));

                    cb.setVisibility(false);
                    cg.playAnimations();
                    cg.setVisibility(true);

                    cm.setLogs(new LinkedList<>());
                } else if (confirmed == JOptionPane.NO_OPTION) {
                    cm.getWriter().writeInt(Action.LOGOUT);
                    cm.setState(ConnectionState.CLOSED);
                    cb.removeAllUsers();

                    cg.setLocation(calculateCenter(cb.getLocation(), cb.getSize(), cg.getSize()));

                    cb.setVisibility(false);
                    cg.playAnimations();
                    cg.setVisibility(true);

                    cm.setLogs(new LinkedList<>());
                }
            } catch (Exception err) {
                err.printStackTrace();
            }
        });

        cb.addSendBtnListener((ActionEvent event) -> {
            sendMessage();
        });

        cb.addImgBtnListener((ActionEvent event) -> {
            if (cm.getState() != ConnectionState.CONNECTED)
                return;
            
            if (cm.getReceiver() == null) {
                JOptionPane.showMessageDialog(null, "No user to send to");
                return;
            }
            
            JFileChooser chooser = new JFileChooser();
            chooser.addChoosableFileFilter(new FileNameExtensionFilter("Image Type", "jpg", "jpeg", "png", "gif", "tiff", "bmp"));
            chooser.addChoosableFileFilter(new FileNameExtensionFilter("JPEG", "jpg", "jpeg"));
            chooser.addChoosableFileFilter(new FileNameExtensionFilter("PNG", "png"));
            chooser.addChoosableFileFilter(new FileNameExtensionFilter("GIF", "gif"));
            chooser.addChoosableFileFilter(new FileNameExtensionFilter("TIFF", "tiff"));
            chooser.addChoosableFileFilter(new FileNameExtensionFilter("Bitmap", "bmp"));
            chooser.setCurrentDirectory(new java.io.File("."));
            chooser.setDialogTitle("Choose a File");
            // chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            chooser.setAcceptAllFileFilterUsed(false);

            if (chooser.showOpenDialog(cb.getSaveFrame()) == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                byte[] b = new byte[MAX_BYTES];
                int size;
                
                try {
                    FileInputStream image = new FileInputStream(file);
                    cm.getWriter().writeInt(Action.SEND_IMAGE);
                    cm.getWriter().writeUTF(cm.getReceiver());

                    // if (cm.getReader().readBoolean()) {
                    //     // user is not in the server
                    //     JOptionPane.showMessageDialog(null, "User was not found in the server");
                    //     return;
                    // }

                    String filename = file.getName();
                    ImgMessage imgMsg = new ImgMessage(cm.getUsername(), filename);
                    
                    String filepath = imgMsg.getFilepath(true);
                    FileOutputStream imageCopy = new FileOutputStream(filepath);
                    cm.getWriter().writeUTF(filename);
                    cm.getWriter().writeLong(imgMsg.getTimestamp());
                    
                    while ((size = image.read(b)) > 0) {
                        imageCopy.write(b, 0, size);

                        cm.getWriter().writeInt(size);
                        cm.getWriter().write(b, 0, size);
                    }
                    cm.getWriter().writeInt(-1);
                    // System.out.println("CLIENT: File sent to server");
                    image.close();
                    imageCopy.close();
                    
                    // add the image to the chatbox
                    cb.appendImgChat(imgMsg, true);
                    String key = getKey();
                    cm.getMessages().get(key).add(imgMsg);
                    cm.getLogs().add(imgMsg.getDate() + "> You: Image " + filename + " to " + cm.getReceiver());
                } catch (Exception err) {
                    err.printStackTrace();
                }
            } else {
                System.out.println("No Selection");
            }
        });

        cb.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.VK_ENTER) {
                    if ((event.getModifiers() & KeyEvent.CTRL_MASK) != 0) {
                        cb.setChatArea(cb.getChatArea() + "\n");
                    } else {
                        sendMessage();
                    }
                }
            }
        });

        cb.addSaveFileListener((ActionEvent event) -> {
            if (cm.getState() != ConnectionState.CONNECTED)
                return;
            
            // if (cm.getReceiver() == null)
            //     return;

            try {
                saveToFile(false);
            } catch (Exception err) {
                err.printStackTrace();
            }
        });
    }

    private void sendMessage() {
        if (cm.getState() != ConnectionState.CONNECTED)
            return;

        if (cm.getReceiver() == null) {
            JOptionPane.showMessageDialog(null, "No user to send to");
            return;
        }

        if (cb.getChatArea().equals("")) {
            return;
        }

        try {
            String msg = cb.getChatArea();
            cb.setChatArea("");
            Message message = new Message(cm.getUsername(), msg);
            cm.getWriter().writeInt(Action.SEND_MESSAGE);
            cm.getWriter().writeUTF(cm.getReceiver());
            cm.getWriter().writeUTF(msg);
            cm.getWriter().writeLong(message.getTimestamp());
            cb.appendChat("You: " + msg, true);

            String key = getKey();
            cm.getMessages().get(key).add(message);
            cm.getLogs().add((new Date()).toInstant() + "> You: " + msg);
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    private boolean saveToFile(boolean isExiting) throws IOException, SocketException, Exception {
        String now = System.currentTimeMillis() + ".txt";
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("Text File", "txt"));
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Save File To...");
        chooser.setSelectedFile(new File(now));
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showSaveDialog(cb.getSaveFrame()) == JFileChooser.APPROVE_OPTION) {
            String filepath = chooser.getSelectedFile().equals(chooser.getCurrentDirectory()) ?
                chooser.getCurrentDirectory() + "/" + now :
                chooser.getSelectedFile().toString();
            
            if (!filepath.endsWith(".txt")) {
                filepath += ".txt";
            }

            if (isExiting)
                cm.getLogs().add((new Date()).toInstant() + "> CLIENT: You disconnected from the server");

            FileWriter fw = new FileWriter(filepath);
            // write the local in the saved messages
            for (String logs: cm.getLogs())
                fw.write(logs + "\n");
            fw.close();
            // cm.setState(ConnectionState.CONNECTED);
            return true;
        } else {
            return false;
        }
    }

    private String getKey() {
        return cm.getUsername().compareTo(cm.getReceiver()) < 1 ?
                cm.getUsername() + "-" + cm.getReceiver() :
                cm.getReceiver() + "-" + cm.getUsername();
    }

    private Point calculateCenter(Point position, Dimension size1, Dimension size2) {
        int x = (int) (position.getX() + (size1.getWidth() - size2.getWidth()) / 2);
        int y = (int) (position.getY() + (size1.getHeight() - size2.getHeight()) / 2.0);
        x = (x < 0) ? 0 : (x > SCREEN_SIZE.getWidth() - size2.getWidth()) ? (int) (SCREEN_SIZE.getWidth() - size2.getWidth()) : x;
        y = (y < 0) ? 0 : (y > SCREEN_SIZE.getHeight() - size2.getHeight()) ? (int) (SCREEN_SIZE.getHeight() - size2.getHeight()) : y;

        return new Point(x, y);
    }

    public void askToSave() {
        int confirmed = JOptionPane.showConfirmDialog(
            null,
            "Connection has been lost. Do you want to save your current logs?",
            "Connection Lost",
            JOptionPane.YES_NO_OPTION
        );

        if (confirmed == JOptionPane.YES_OPTION) {
            try {
                saveToFile(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        cg.setLocation(calculateCenter(cb.getLocation(), cb.getSize(), cg.getSize()));

        cg.playAnimations();
        cg.setVisibility(true);
        cb.setVisibility(false);
    }

}