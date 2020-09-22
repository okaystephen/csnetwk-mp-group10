import java.io.*;
import java.net.*;
import java.util.*;

public class Server {

    private static final int DEFAULT_PORT = 5000;

    public static void main(String[] args) {
        int port;
        String log;
        
        ServerStop stop;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        WriterTxt fileWriter = new WriterTxt();

        if (args[0] == null) {
            port = DEFAULT_PORT;
        } else {
            port = Integer.parseInt(args[0]);
        }
        
        try {
            ((Thread) fileWriter).start();
            ServerSocket ss = new ServerSocket(port);

            stop = new ServerStop(ss, in);
            ((Thread) stop).start();

            log = (new Date()).toInstant() + "> SERVER: Listening to port " + port;
            System.out.println(log);
            fileWriter.write(log);

            while (true) {
                Socket socket = ss.accept();

                log = (new Date()).toInstant() + "> SERVER: Client " + socket.getRemoteSocketAddress() + " has connected";
                System.out.println(log);
                fileWriter.write(log);

                ConnectionHandler connection = new ConnectionHandler(socket, fileWriter);
                connection.start();
            }
        } catch (SocketException e) {
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        log = (new Date()).toInstant() + "> SERVER: Closed Connection";
        System.out.println(log);
        fileWriter.write(log);

        try {
            log = (new Date()).toInstant() + "> SERVER: Closing all sockets";
            System.out.println(log);
            fileWriter.write(log);
            ConnectionHandler.stopAll();

            log = (new Date()).toInstant() + "> SERVER: Closing Thread File Writer";
            System.out.println(log);
            fileWriter.write(log);
            fileWriter.stopRunning();
            fileWriter.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Threads have been terminated.");
        System.out.println("Successful Application Termination.");
        System.out.println("Press any key to exit...");
        try {
            in.read();
            in.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}