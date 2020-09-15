import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
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

public class Server {

  private int port;
  private List<User> clients;
  private ServerSocket server;

  public static void main(String[] args) throws IOException {
    new Server(12345).run();
  }

  public Server(int port) {
    this.port = port;
    this.clients = new ArrayList<User>();
  }

  public void run() throws IOException {
    server = new ServerSocket(port) {
      protected void finalize() throws IOException {
        this.close();
      }
    };
    Date date = new Date();  
    Timestamp ts=new Timestamp(date.getTime());  
    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");  
    System.out.println(formatter.format(ts) + ": " + "Listening at Port 12345");

    while (true) {
      // accepts a new client
      Socket client = server.accept();

      // get nickname of newUser
      String nickname = (new Scanner(client.getInputStream())).nextLine();
      nickname = nickname.replace(",", ""); // ',' use for serialisation
      nickname = nickname.replace(" ", "_");
      Date clientdate = new Date();  
      Timestamp clientts=new Timestamp(clientdate.getTime());  
      SimpleDateFormat clientformatter = new SimpleDateFormat("HH:mm:ss");  
      System.out.println("\n" + clientformatter.format(clientts) + ": " + nickname + " connected" + 
                        "\n\t" + client.getRemoteSocketAddress());
                        // "\n\t  IP Address: " + client.getInetAddress().getHostAddress() +
                        // "\n\t  Port: " + client.getRemoteSocketAddress());

      // create new User
      User newUser = new User(client, nickname);

      // add newUser message to list
      this.clients.add(newUser);

      // Welcome msg
      newUser.getOutStream()
          .println("<br><b>Welcome</b> " + newUser.toString() + "! You may start chatting now.</span><br><br>");

      // create a new thread for newUser incoming messages handling
      new Thread(new UserHandler(this, newUser)).start();
    }
  }

  // delete a user from the list
  public void removeUser(User user) {
    this.clients.remove(user);
    Date date = new Date();  
    Timestamp ts=new Timestamp(date.getTime());  
    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");  
    System.out.println("\n" + formatter.format(ts) + ": " + user.getNickname() + " disconnected...");
  }

  // send incoming msg to all Users
  public void broadcastMessages(String msg, User userSender) {
    boolean success = false;
    String receiver = "";
    for (User client : this.clients) {
      if(this.clients.size() == 2){
        client.getOutStream().println(userSender.toString() + "<span>: " + msg + "</span>");
        success = true;
        if(client != userSender){
          receiver = client.getNickname();
        }
      }
      else{
        userSender.getOutStream().println("Message sending failed... No other client online");
        Date date = new Date();
        Timestamp ts=new Timestamp(date.getTime());
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        System.out.println("\n" + formatter.format(ts) + ": " + "Message sending failed...");
      }
    }
    if(success){
      Date date = new Date();  
      Timestamp ts=new Timestamp(date.getTime());  
      SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
      System.out.println("\n" + formatter.format(ts) + ": " + userSender.getNickname() + " sent a message to " + receiver);
    }
  }

  // send list of clients to all Users
  public void broadcastAllUsers() {
    for (User client : this.clients) {
      client.getOutStream().println(this.clients);
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

    // when there is a new message, broadcast to all
    Scanner sc = new Scanner(this.user.getInputStream());
    while (sc.hasNextLine()) {
      message = sc.nextLine();

        // Gestion du changement
      if (message.charAt(0) == '#') {
        user.changeColor(message);
        // update color for all other users
        this.server.broadcastAllUsers();
      } else {
        // update user list
        server.broadcastMessages(message, user);
      }
    }
    // end of Thread
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
    this.color = ColorInt.getColor(this.userId);
    nbUser += 1;
  }

  // change color user
  public void changeColor(String hexColor) {
    // check if it's a valid hexColor
    Pattern colorPattern = Pattern.compile("#([0-9a-f]{3}|[0-9a-f]{6}|[0-9a-f]{8})");
    Matcher m = colorPattern.matcher(hexColor);
    if (m.matches()) {
      Color c = Color.decode(hexColor);
      // if the Color is too Bright don't change
      double luma = 0.2126 * c.getRed() + 0.7152 * c.getGreen() + 0.0722 * c.getBlue(); // per ITU-R BT.709
      if (luma > 160) {
        this.getOutStream().println("<b>Color Too Bright</b>");
        return;
      }
      this.color = hexColor;
      this.getOutStream().println("<b>Color changed successfully</b> " + this.toString());
      return;
    }
    this.getOutStream().println("<b>Failed to change color</b>");
  }

  // getteur
  public PrintStream getOutStream() {
    return this.streamOut;
  }

  public InputStream getInputStream() {
    return this.streamIn;
  }

  public String getNickname() {
    return this.nickname;
  }

  // print user with his color
  public String toString() {

    return "<u><span style='color:" + this.color + "'>" + this.getNickname() + "</span></u>";

  }
}

class ColorInt {
  public static String[] mColors = { "#3079ab", // dark blue
      "#e15258", // red
      "#f9845b", // orange
      "#7d669e", // purple
      "#53bbb4", // aqua
      "#51b46d", // green
      "#e0ab18", // mustard
      "#f092b0", // pink
      "#e8d174", // yellow
      "#e39e54", // orange
      "#d64d4d", // red
      "#4d7358", // green
  };

  public static String getColor(int i) {
    return mColors[i % mColors.length];
  }
}
