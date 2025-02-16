
/** 
 * DE LA SALLE USAP - CLIENT CLASS
 * @author SALAMANTE, Stephen
 * @author TULABOT, Victor
*/

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.event.*;
import javax.swing.text.html.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileFilter;
import java.util.*;
import java.util.Arrays;
import java.util.ArrayList;

public class Client extends Thread {
    private final JFrame frame;
    private final JPanel panel;

    private final JButton client_logout_button;
    private final JTextPane client_active;
    private final JLabel client_active_label;
    private final JTextPane client_chatlog;
    private final JTextField client_name_field;
    private final JLabel client_name_label;
    private final JTextField client_ip_field;
    private final JLabel client_ip_label;
    private final JTextField client_port_field;
    private final JLabel client_port_label;
    private final JButton client_proceed_button;
    private final JScrollPane vertical_log;
    private final JScrollPane vertical_user;
    private final JScrollPane vertical_message;
    private final JButton client_file_button;
    private final JButton client_message_button;
    private final JTextArea client_message_field;
    private String msg = "";

    // For server connection
    private String username;
    private String portnumber;
    private String servername;
    private int PORT;
    private Thread thread;
    BufferedReader input;
    PrintWriter output;
    Socket server;

    public Client() {
        frame = new JFrame("De La Salle Usap");
        panel = new JPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // construct components
        client_logout_button = new JButton("Logout");

        client_active = new JTextPane();
        client_active.setEditable(false);
        client_active.setVisible(true);

        client_active_label = new JLabel("Active Users:");

        client_chatlog = new JTextPane();
        client_chatlog.setEditable(false);
        client_chatlog.setVisible(true);

        client_name_field = new JTextField(5);

        client_name_label = new JLabel("Name");

        client_ip_field = new JTextField(5);

        client_ip_label = new JLabel("IP Address");

        client_port_field = new JTextField(5);

        client_port_label = new JLabel("Port Number");

        client_proceed_button = new JButton("Proceed");

        client_file_button = new JButton("Send File");
        client_message_button = new JButton("Send Message");

        client_message_field = new JTextArea(5, 5);
        client_message_field.setLineWrap(true);
        client_message_field.setEditable(true);
        client_message_field.setVisible(true);

        client_chatlog.setContentType("text/html");
        client_chatlog.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, true);

        client_active.setContentType("text/html");
        client_active.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, true);

        appendPane(client_chatlog, "<br><br>" + "<h3>Welcome to De La Salle Usap!</h3>" + "<br>"
                + "Enter your name, IP Address, and Port Number" + "<br>" + "to get started!");

        // adjust size and set layout
        panel.setPreferredSize(new Dimension(601, 350));
        panel.setLayout(null);

        // adjust vertical scrollbar
        vertical_log = new JScrollPane(client_chatlog);
        vertical_log.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        vertical_user = new JScrollPane(client_active);
        vertical_user.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        vertical_message = new JScrollPane(client_message_field);
        vertical_message.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // add components
        panel.add(vertical_user);
        panel.add(client_active_label);
        panel.add(vertical_log);
        panel.add(client_name_field);
        panel.add(client_name_label);
        panel.add(client_ip_field);
        panel.add(client_ip_label);
        panel.add(client_port_field);
        panel.add(client_port_label);
        panel.add(client_proceed_button);

        // set component bounds (only needed by Absolute Positioning)
        client_logout_button.setBounds(480, 20, 100, 25);
        vertical_user.setBounds(410, 80, 165, 180);
        client_active_label.setBounds(410, 55, 100, 25);
        vertical_log.setBounds(25, 60, 375, 200);
        client_name_field.setBounds(20, 295, 125, 25);
        client_name_label.setBounds(25, 275, 100, 25);
        client_ip_field.setBounds(155, 295, 125, 25);
        client_ip_label.setBounds(160, 275, 100, 25);
        client_port_field.setBounds(290, 295, 125, 25);
        client_port_label.setBounds(295, 275, 100, 25);
        client_proceed_button.setBounds(480, 295, 100, 25);

        client_file_button.setBounds(364, 318, 100, 25);
        client_message_button.setBounds(457, 318, 125, 25);
        vertical_message.setBounds(25, 263, 550, 51);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        // On connect
        client_proceed_button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent ae) {
                try {
                    if (client_name_field.getText().equals("")) {
                        JOptionPane.showMessageDialog(panel, "Please enter a name!");
                    } else if (client_ip_field.getText().equals("")) {
                        JOptionPane.showMessageDialog(panel, "Please enter server name!");
                    } else if (client_port_field.getText().equals("")) {
                        JOptionPane.showMessageDialog(panel, "Please enter port number!");
                    } else {
                        // String portnumber = client_port_field.getText();
                        username = client_name_field.getText();
                        portnumber = client_port_field.getText();
                        servername = client_ip_field.getText();
                        PORT = Integer.parseInt(portnumber);

                        appendPane(client_chatlog,
                                "<br><span>Connecting to " + servername + " on port " + PORT + "...</span>");
                        server = new Socket(servername, PORT);

                        appendPane(client_chatlog,
                                "<span>Successfully connected to " + server.getRemoteSocketAddress() + "</span><br>");

                        input = new BufferedReader(new InputStreamReader(server.getInputStream()));
                        output = new PrintWriter(server.getOutputStream(), true);

                        // send nickname to server
                        output.println(username);

                        // create new Read Thread
                        thread = new Read();
                        thread.start();

                        panel.remove(client_name_field);
                        panel.remove(client_port_field);
                        panel.remove(client_ip_field);
                        panel.remove(client_name_label);
                        panel.remove(client_port_label);
                        panel.remove(client_ip_label);
                        panel.remove(client_proceed_button);

                        panel.add(client_logout_button);
                        panel.add(client_file_button);
                        panel.add(client_message_button);
                        panel.add(vertical_message);

                        panel.revalidate();
                        panel.repaint();
                    }

                } catch (final Exception e) {
                    appendPane(client_chatlog, "<br><span>Couldn't connect to the server! :(</span><br>");
                    JOptionPane.showMessageDialog(panel, e.getMessage());
                }
            }

        });

        // Send message button
        client_message_field.addKeyListener(new KeyAdapter() {
            // send message on Enter
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    send();
                }

                // Get last message typed
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    String currentMessage = client_message_field.getText().trim();
                    client_message_field.setText(msg);
                    msg = currentMessage;
                }

                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    String currentMessage = client_message_field.getText().trim();
                    client_message_field.setText(msg);
                    msg = currentMessage;
                }
            }
        });

        // Send message button
        client_message_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                send();
            }
        });

        // Send file button
        client_file_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    JFileChooser file = new JFileChooser();
                    file.addChoosableFileFilter(new ImageFilter());
                    file.setAcceptAllFileFilterUsed(false);
                    int result = file.showOpenDialog(null);
                    int size;
                    if (result == JFileChooser.APPROVE_OPTION) {

                        File selectedFile = file.getSelectedFile();
                        output.println("sent a file:" + selectedFile.getName());

                        // TESTING CODE
                        byte[] mybytearray = new byte[(int) selectedFile.length()];

                        FileInputStream fis = new FileInputStream(selectedFile);

                        String filepath = selectedFile.getName();
                        FileOutputStream fileCopy = new FileOutputStream(filepath);

                        while ((size = fis.read(mybytearray)) > 0) {
                            fileCopy.write(mybytearray, 0, size);
                        }

                        fis.close();
                        fileCopy.close();

                        // handle file send over socket
                        OutputStream os = server.getOutputStream();

                        // Sending file name and file size to the server
                        DataOutputStream dos = new DataOutputStream(os);
                        dos.writeUTF("");

                        System.out.println("File " + selectedFile.getName() + " sent to client.");

                    } else {
                        System.out.println("No Selection");
                    }
                } catch (final Exception e) {
                    JOptionPane.showMessageDialog(panel, e.getMessage());
                }
            }
        });

        // Logout button
        client_logout_button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent ae) {
                try {
                    // clear textfield
                    client_name_field.setText("");
                    client_port_field.setText("");
                    client_ip_field.setText("");
                    client_chatlog.setText("");
                    client_active.setText("");

                    thread.interrupt();
                    output.close();

                    appendPane(client_chatlog, "<br><span>You have logged out!</span><br>");

                    appendPane(client_chatlog, "<h3>Welcome to De La Salle Usap!</h3>" + "<br>"
                            + "Enter your name, IP Address, and Port Number" + "<br>" + "to get started!");

                    panel.remove(client_logout_button);
                    panel.remove(client_file_button);
                    panel.remove(client_message_button);
                    panel.remove(vertical_message);

                    panel.add(client_name_field);
                    panel.add(client_port_field);
                    panel.add(client_ip_field);
                    panel.add(client_name_label);
                    panel.add(client_port_label);
                    panel.add(client_ip_label);
                    panel.add(client_proceed_button);

                    panel.revalidate();
                    panel.repaint();

                } catch (final Exception e) {
                    JOptionPane.showMessageDialog(panel, e.getMessage());
                }
            }

        });
    }

    public void send() {
        try {
            String message = client_message_field.getText().trim();
            if (message.equals("")) {
                return;
            }
            this.msg = message;
            output.println(message);
            client_message_field.requestFocus();
            client_message_field.setText(null);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    class Read extends Thread {
        public void run() {
            String message;
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    message = input.readLine();
                    if (message != null) {
                        if (message.charAt(0) == '[') {
                            message = message.substring(1, message.length() - 1);
                            ArrayList<String> userArray = new ArrayList<String>(Arrays.asList(message.split(", ")));
                            client_active.setText(null);
                            for (String user : userArray) {
                                if (user.replaceAll("(?s)<[^>]*>(\\s*<[^>]*>)*", "").equals(username)) {
                                    appendPane(client_active, user + " (You)");
                                } else {
                                    appendPane(client_active, user);
                                }
                            }
                        } else {
                            appendPane(client_chatlog, message);
                        }
                    }
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
    }

    class ImageFilter extends FileFilter {
        public final static String JPEG = "jpeg";
        public final static String JPG = "jpg";
        public final static String GIF = "gif";
        public final static String TIFF = "tiff";
        public final static String TIF = "tif";
        public final static String PNG = "png";

        @Override
        public boolean accept(File f) {
            if (f.isDirectory()) {
                return true;
            }

            String extension = getExtension(f);
            if (extension != null) {
                if (extension.equals(TIFF) || extension.equals(TIF) || extension.equals(GIF) || extension.equals(JPEG)
                        || extension.equals(JPG) || extension.equals(PNG)) {
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        }

        @Override
        public String getDescription() {
            return "Image Only";
        }

        String getExtension(File f) {
            String ext = null;
            String s = f.getName();
            int i = s.lastIndexOf('.');

            if (i > 0 && i < s.length() - 1) {
                ext = s.substring(i + 1).toLowerCase();
            }
            return ext;
        }
    }

    private void appendPane(final JTextPane pane, final String message) {
        HTMLDocument doc = (HTMLDocument) pane.getDocument();
        HTMLEditorKit editorKit = (HTMLEditorKit) pane.getEditorKit();
        try {
            editorKit.insertHTML(doc, doc.getLength(), message, 0, 0, null);
            pane.setCaretPosition(doc.getLength());
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public String getFilename(String path) {
        File filepath = new File(path);
        String filename = filepath.getName();
        return filename.replace(" ", "_");
    }

    public static void main(final String[] args) throws Exception {
        final Client client = new Client();
    }
}
