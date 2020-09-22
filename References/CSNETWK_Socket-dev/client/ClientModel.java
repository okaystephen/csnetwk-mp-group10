import java.io.*;
import java.net.*;
import java.util.*;

public class ClientModel {
    
    private ConnectionState state;
    private Socket socket;
    private String host;
    private int port;
    private String username;
    private String avatar;
    private String receiver;
    private DataInputStream reader;
    private DataOutputStream writer;
    private HashMap<String, ArrayList<Message>> messages;
    private LinkedList<String> logs;

    public ClientModel() {
        messages = new HashMap<>();
        logs = new LinkedList<>();
    }

    public ConnectionState getState() {
        return state;
    }

    public void setState(ConnectionState state) {
        this.state = state;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public DataInputStream getReader() {
        return reader;
    }

    public void setReader(DataInputStream reader) {
        this.reader = reader;
    }

    public DataOutputStream getWriter() {
        return writer;
    }

    public void setWriter(DataOutputStream writer) {
        this.writer = writer;
    }

    public HashMap<String, ArrayList<Message>> getMessages() {
        return messages;
    }

    public void setLogs(LinkedList<String> logs) {
        this.logs = logs;
    }

    public LinkedList<String> getLogs() {
        return logs;
    }
}