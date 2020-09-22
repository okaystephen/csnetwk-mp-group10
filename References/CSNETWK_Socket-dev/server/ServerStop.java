import java.io.*;
import java.net.*;

public class ServerStop extends Thread {

    private ServerSocket ss;
    private BufferedReader in;

    public ServerStop(ServerSocket ss, BufferedReader in) {
        this.ss = ss;
        this.in = in;
    }

    @Override
    public void run() {
        try {
            while (!in.readLine().equalsIgnoreCase("quit"));
            ss.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}