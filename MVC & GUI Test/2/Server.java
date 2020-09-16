import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.awt.Color;
import java.sql.Timestamp;
import java.util.Date;
import java.text.SimpleDateFormat;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.event.*;
import javax.swing.text.html.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Server {
  private final JFrame frame;
  private final JPanel panel;
  private final JScrollPane vertical_log;
  private final JTextPane client_chatlog;
  private final JButton server_savelog_button;

  private int port;
  private List<User> clients;
  private ServerSocket server;

  public String console_log;

  public static void main(String[] args) throws IOException {
    new Server(12345).run();
  }

  public Server(int port) {
    frame = new JFrame("De La Salle Usap Server");
    panel = new JPanel();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    client_chatlog = new JTextPane();
    client_chatlog.setEditable(false);
    client_chatlog.setVisible(true);

    server_savelog_button = new JButton("Save Log");

    client_chatlog.setContentType("text/html");
    client_chatlog.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, true);

    panel.setPreferredSize(new Dimension(601, 350));
    panel.setLayout(null);

    vertical_log = new JScrollPane(client_chatlog);
    vertical_log.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    panel.add(vertical_log);
    panel.add(server_savelog_button);

    server_savelog_button.setBounds(245, 295, 100, 20);
    vertical_log.setBounds(30, 40, 540, 225);

    frame.getContentPane().add(panel);
    frame.pack();
    frame.setResizable(false);
    frame.setVisible(true);

    this.port = port;
    this.clients = new ArrayList<User>();

    // Save log button
    server_savelog_button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        try {
          String log = client_chatlog.getText().replaceAll("(?s)<[^>]*>(\\s*<[^>]*>)*", " ");

          JFileChooser chooser = new JFileChooser();
          chooser.setCurrentDirectory(new java.io.File("."));
          chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
          // disable the "All files" option.
          chooser.setAcceptAllFileFilterUsed(false);
          //
          if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
            System.out.println("getSelectedFile() : " + chooser.getSelectedFile());

            File logfile = new File(chooser.getSelectedFile() + "/chatlog.txt");
            if (logfile.createNewFile()) {
              System.out.println("File created: " + logfile.getName());
              FileWriter myWriter = new FileWriter("chatlog.txt");
              myWriter.write(log);
              myWriter.close();
              System.out.println("Successfully wrote to the file.");
            } else {
              System.out.println("File already exists.");
            }
          } else {
            System.out.println("No Selection ");
          }
        } catch (final Exception e) {
          JOptionPane.showMessageDialog(panel, e.getMessage());
        }
      }
    });
  }

  public void run() throws IOException {
    server = new ServerSocket(port) {
      protected void finalize() throws IOException {
        this.close();
      }
    };
    Date date = new Date();
    Timestamp ts = new Timestamp(date.getTime());
    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
    console_log = "\n" + formatter.format(ts) + ": " + "Listening at Port 12345";
    System.out.println(console_log);
    appendPane(client_chatlog, console_log);

    while (true) {
      // accepts a new client
      Socket client = server.accept();

      // get nickname of newUser
      String nickname = (new Scanner(client.getInputStream())).nextLine();
      nickname = nickname.replace(",", ""); // ',' use for serialisation
      nickname = nickname.replace(" ", "_");
      Date clientdate = new Date();
      Timestamp clientts = new Timestamp(clientdate.getTime());
      SimpleDateFormat clientformatter = new SimpleDateFormat("HH:mm:ss");
      console_log = "\n" + clientformatter.format(clientts) + ": " + nickname + " connected" + "\n\t"
          + client.getRemoteSocketAddress();
      // "\n\t IP Address: " + client.getInetAddress().getHostAddress() +
      // "\n\t Port: " + client.getRemoteSocketAddress());
      System.out.println(console_log);
      appendPane(client_chatlog, console_log);

      // create a new user
      User newUser = new User(client, nickname);

      // add newUser message to list
      this.clients.add(newUser);

      // welcome message
      newUser.getOutStream()
          .println("<br><b>Welcome</b> " + newUser.toString() + "! You may start chatting now.</span><br><br>");

      for (User clients : this.clients) {
        if (clients != newUser) {
          clients.getOutStream().println(newUser.toString() + " <b>has connected.</b>");
        }
      }

      // create a new thread for newUser incoming messages handling
      new Thread(new UserHandler(this, newUser)).start();
    }
  }

  // delete a user from the list
  public void removeUser(User user) {
    this.clients.remove(user);
    Date date = new Date();
    Timestamp ts = new Timestamp(date.getTime());
    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
    console_log = "\n" + formatter.format(ts) + ": " + user.getNickname() + " disconnected...";
    System.out.println(console_log);
    appendPane(client_chatlog, console_log);

    for (User clients : this.clients) {
      if (clients != user) {
        clients.getOutStream().println(user.toString() + " <b>has logged out.</b>");
      }
    }
  }

  // send incoming messages to all users
  public void broadcastMessages(String msg, User userSender) {
    boolean success = false;
    String receiver = "";
    for (User client : this.clients) {
      if (this.clients.size() == 2) {
        client.getOutStream().println(userSender.toString() + "<span>: " + msg + "</span>");
        success = true;
        if (client != userSender) {
          receiver = client.getNickname();
        }
      } else {
        userSender.getOutStream().println("Message sending failed... No other client online");
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        console_log = "\n" + formatter.format(ts) + ": " + "Message sending failed...";
        System.out.println(console_log);
        appendPane(client_chatlog, console_log);
      }
    }
    if (success) {
      Date date = new Date();
      Timestamp ts = new Timestamp(date.getTime());
      SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
      console_log = "\n" + formatter.format(ts) + ": " + userSender.getNickname() + " sent a message to " + receiver;
      System.out.println(console_log);
      appendPane(client_chatlog, console_log);
    }
  }

  // send incoming messages to all users
  public void broadcastFile(String msg, User userSender) {
    boolean success = false;
    String receiver = "";
    for (User client : this.clients) {
      if (this.clients.size() == 2) {
        client.getOutStream().println(userSender.toString() + "<span>: " + msg + "</span>");
        success = true;
        if (client != userSender) {
          receiver = client.getNickname();
        }
      } else {
        userSender.getOutStream().println("File sending failed... No other client online");
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        console_log = "\n" + formatter.format(ts) + ": " + "File sending failed...";
        System.out.println(console_log);
        appendPane(client_chatlog, console_log);
      }
    }
    if (success) {
      Date date = new Date();
      Timestamp ts = new Timestamp(date.getTime());
      SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
      console_log = "\n" + formatter.format(ts) + ": " + userSender.getNickname() + " sent a file to " + receiver;
      System.out.println(console_log);
      appendPane(client_chatlog, console_log);
    }
  }

  // send list of clients to all users
  public void broadcastAllUsers() {
    for (User client : this.clients) {
      client.getOutStream().println(this.clients);
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
}

class UserHandler implements Runnable {

  private Server server;
  private User user;

  public UserHandler(Server server, User user) {
    this.server = server;
    this.user = user;
    this.server.broadcastAllUsers();
  }

  public void run() {
    String message;

    // broadcast to all when there is incoming message
    Scanner sc = new Scanner(this.user.getInputStream());
    while (sc.hasNextLine()) {
      message = sc.nextLine();

      if (message.charAt(0) == '(') {
        // user.changeColor(message);
        // update color for all other users
        this.server.broadcastFile(message, user);
      } else {
        // update user list
        server.broadcastMessages(message, user);
      }
    }
    // end thread
    server.removeUser(user);
    this.server.broadcastAllUsers();
    sc.close();
  }
}

class User {
  private static int nbUser = 0;
  private int userId;
  private PrintStream streamOut;
  private InputStream streamIn;
  private String nickname;
  private Socket client;
  private String color;

  // constructor
  public User(Socket client, String name) throws IOException {
    this.streamOut = new PrintStream(client.getOutputStream());
    this.streamIn = client.getInputStream();
    this.client = client;
    this.nickname = name;
    this.userId = nbUser;
    this.color = ColorFont.getColor(this.userId);
    nbUser += 1;
  }

  // getters
  public PrintStream getOutStream() {
    return this.streamOut;
  }

  public InputStream getInputStream() {
    return this.streamIn;
  }

  public String getNickname() {
    return this.nickname;
  }

  // print user with respective color
  public String toString() {

    return "<u><span style='color:" + this.color + "'>" + this.getNickname() + "</span></u>";

  }
}

class ColorFont {
  public static String[] mColors = { "#3079ab", "#e15258", "#f9845b", "#7d669e", "#53bbb4", "#51b46d", "#e0ab18",
      "#f092b0", "#e8d174", "#e39e54", "#d64d4d", "#4d7358", };

  public static String getColor(int i) {
    return mColors[i % mColors.length];
  }
}
