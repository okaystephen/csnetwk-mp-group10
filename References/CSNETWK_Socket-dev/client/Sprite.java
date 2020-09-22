import java.awt.*;
import javax.swing.*;

public class Sprite extends ImageIcon implements Runnable {

    private Image[] images;
    private int frame;
    private int fps;
    private double speed;
    private boolean loop;
    private Thread thread;
    private Component container;

    public Sprite(Image[] images) {
        super(images[0]);
        this.images = images;
        frame = 0;
        fps = 4;
        loop = false;
        speed = 1000 / 4; // 1s / 4 frames
    }

    public void setContainer(Component container) {
        this.container = container;
    }

    @Override
    public void run() {
        try {
            while (loop) {
                frame = (frame + 1) % images.length;
                setImage(images[frame]);
                container.revalidate();
                container.repaint();
                Thread.sleep((long) speed);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (!loop) {
            loop = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    public void stop() {
        loop = false;
        frame = 0;
        setImage(images[0]);
    }

}