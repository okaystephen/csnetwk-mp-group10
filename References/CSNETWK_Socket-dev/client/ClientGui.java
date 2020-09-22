import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.event.*;


public class ClientGui {
    private JTextField hostTxt;
    private JTextField portTxt;
    private JButton loginbtn;
    private JFrame frame;
    private Font smallFont, font;

    private Sprite header;
    private Thread thread;
    private boolean loop;

    public ClientGui() {
        // Frame
        frame = new JFrame("De La Salle Usap");
        frame.setSize(1000, 600);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        ImageIcon background_image = new ImageIcon("assets/images/background5.png");
        Image img = background_image.getImage();
        Image temp_img = img.getScaledInstance(1000, 600, Image.SCALE_REPLICATE);
        background_image = new ImageIcon(temp_img);
        JLabel background = new JLabel(background_image);
        background.setBounds(0, 0, 1000, 600);
        frame.add(background);

        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/MilkyNice.ttf")).deriveFont((float) 30);
            smallFont = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/MilkyNice.ttf")).deriveFont((float) 20);
        } catch (Exception e) {
            font = new Font("Arial", Font.BOLD, 30);
            smallFont = new Font("Arial", Font.BOLD, 15);
        }

        // Header
        try {
            header = new Sprite(new Image[] {
                ImageIO.read(getClass().getResource("assets/images/header0.png")),
                ImageIO.read(getClass().getResource("assets/images/header1.png")),
                ImageIO.read(getClass().getResource("assets/images/header2.png"))
            });
            JLabel headerLbl = new JLabel(header, SwingConstants.CENTER);
            header.setContainer(headerLbl);
            headerLbl.setBounds(0, 30, 1000, 150);
            background.add(headerLbl);
        } catch (Exception e) {
            JPanel heading = new JPanel();
            heading.setBounds(0, 50, 1000, 100);
            heading.setBackground(new Color(0, 0, 0, 0));
            JLabel DLSUText = new JLabel("DE LA SALLE USAP");
            DLSUText.setForeground(Color.WHITE);
            DLSUText.setBounds(25, 25, 400, 50);
            heading.add(DLSUText);
            background.add(heading);
        }

        // Login panel
        JPanel login = new JPanel();
        login.setLayout(null);
        login.setSize(400,350);
        login.setBackground(Color.WHITE);
        login.setBounds(300, 175, 400, 350);

        // Host
        JLabel hostLbl = new JLabel("Host");
        hostLbl.setFont(font);
        hostLbl.setBounds(50, 0, 300, 50);
        login.add(hostLbl);

        hostTxt = new JTextField("localhost");
        hostTxt.setBorder(null);
        hostTxt.setBounds(50, 50, 300, 50);
        hostTxt.setBackground(new Color(242, 242, 242));
        hostTxt.setFont(font);
        login.add(hostTxt);

        // Port
        JLabel portLbl = new JLabel("Port");
        portLbl.setFont(font);
        portLbl.setBounds(50, 100, 300, 50);
        login.add(portLbl);

        portTxt = new JTextField("5000");
        portTxt.setBounds(50, 150, 300, 50);
        portTxt.setBorder(null);
        portTxt.setFont(font);
        portTxt.setBackground(new Color(242, 242, 242));
        login.add(portTxt);

        //login button
        loginbtn = new JButton("LOGIN");
        loginbtn.setFont(smallFont);
        loginbtn.setBounds(140, 250, 100, 60);
        login.add(loginbtn);
        background.add(login);

        //close window event
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int confirmed = JOptionPane.showConfirmDialog(
                    null,
                    "Are you sure you want to exit?", "User Confirmation",
                    JOptionPane.YES_NO_OPTION
                );
                
                if (confirmed == JOptionPane.YES_OPTION)
                    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                }
        });

        frame.revalidate();
        frame.repaint();
    }

    public String getIPAddress(){
        return hostTxt.getText();
    }

    public String getPortNumber(){
        return portTxt.getText();
    }
    
    public JFrame getFrame(){
        return frame;
    }

    public Point getLocation() {
        return frame.getLocation();
    }

    public Dimension getSize() {
        return frame.getSize();
    }

    public void addLoginListener(ActionListener login) {
		loginbtn.addActionListener(login);
    }

    public void setVisibility(boolean show){
        frame.setVisible(show);
    }

    public void setLocation(Point p) {
        frame.setLocation(p);
    }

    public void playAnimations() {
        header.play();
    }

    public void stopAnimations() {
        header.stop();
    }

}
