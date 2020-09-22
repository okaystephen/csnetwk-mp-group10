import java.util.*;

public class Message {

    private long timestamp;
    private String sender;
    private String msg;

    public Message(String sender, String msg, long timestamp) {
        this.timestamp = timestamp;
        this.sender = sender;
        this.msg = msg;
    }

    public Message(String sender, String msg) {
        this(sender, msg, System.currentTimeMillis());
    }

    public String getSender() {
        return sender;
    }

    public String getMsg() {
        return msg;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getDate() {
        return (new Date(timestamp)).toInstant().toString();
    }

    public String simple(String self) {
        return (sender.equals(self) ? "You" : sender) + ": " + msg;
    }

    public String detailed() {
        return getDate() + "> " + sender + ": " + msg;
    }

}