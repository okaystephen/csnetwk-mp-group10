import java.io.*;
import java.util.*;

public class WriterTxt extends Thread {

    private final String filepath = "tmp/" + System.currentTimeMillis() + ".txt";
    private Queue<String> logs;
    private boolean running = true;

    public WriterTxt() {
        logs = new LinkedList<>();
    }

    public void write(String txt) {
        logs.add(txt);
    }

    @Override
    public void run() {
        try {
            while (running || logs.peek() != null) {
                if (logs.peek() != null) {
                    FileWriter file = new FileWriter(filepath, true);
                    file.write(logs.remove() + "\n");
                    file.close();
                } else {
                    Thread.sleep(1000);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopRunning() {
        running = false;
    }

}