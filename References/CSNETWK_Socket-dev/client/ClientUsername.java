import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.*;

public class ClientUsername {

    private JTextField username;
    private JButton loginBtn;
    private JFrame frame;
    private JRadioButton[] avatars;
    private ButtonGroup group;
    private Font font, smallfont;

    public ClientUsername() {
        // Adjust size and set layout
        frame = new JFrame("De La Salle Usap");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(350, 400);
        frame.setLayout(null);

        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/MilkyNice.ttf")).deriveFont((float) 26);
            smallfont = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/MilkyNice.ttf")).deriveFont((float) 20);
        } catch (Exception e) {
            font = new Font("Arial", Font.BOLD, 30);
        }

        // Background
        ImageIcon background_image = new ImageIcon("assets/images/background5.png");
        Image img = background_image.getImage();
        Image temp_img = img.getScaledInstance(600, 500, Image.SCALE_REPLICATE);
        background_image = new ImageIcon(temp_img);
        JLabel background = new JLabel(background_image);

        background.setBounds(0, 0, 350, 400);

        JLabel usernameLabel = new JLabel("ENTER USERNAME", SwingConstants.CENTER);
        usernameLabel.setFont(font);
        usernameLabel.setBounds(0, 20, 350, 30);
        usernameLabel.setForeground(Color.WHITE);
        background.add(usernameLabel);
        
        username = new JTextField();
        username.setHorizontalAlignment(JTextField.CENTER);
        username.setFont(smallfont);
        username.setBounds(30, 60, 290, 30);
        background.add(username);
        
        JLabel avatarLabel = new JLabel("CHOOSE AN AVATAR", SwingConstants.CENTER);
        avatarLabel.setFont(font);
        avatarLabel.setBounds(0, 110, 350, 30);
        avatarLabel.setForeground(Color.WHITE);
        background.add(avatarLabel);

        loginBtn = new JButton("LOGIN");
        loginBtn.setFont(smallfont);
        loginBtn.setBounds(30, 310, 290, 30);
        background.add(loginBtn);

        avatars = new JRadioButton[] {
            new JRadioButton("1", new ImageIcon("assets/images/1.png"), true),
            new JRadioButton("2", new ImageIcon("assets/images/2.png")),
            new JRadioButton("3", new ImageIcon("assets/images/3.png")),
            new JRadioButton("4", new ImageIcon("assets/images/4.png")),
            new JRadioButton("5", new ImageIcon("assets/images/5.png")),
            new JRadioButton("6", new ImageIcon("assets/images/6.png")),
            new JRadioButton("7", new ImageIcon("assets/images/7.png")),
            new JRadioButton("8", new ImageIcon("assets/images/8.png"))
        };
        group = new ButtonGroup();

        for (int i = 0; i < 8; i++)
            avatars[i].setBounds(30 + i % 4 * 80, 160 + i / 4 * 70, 50, 50);
        
        for (JRadioButton avatar: avatars) {
            avatar.setBackground(Color.WHITE);
            avatar.setOpaque(true);
            group.add(avatar);
            background.add(avatar);
        }

        avatars[0].setBackground(Color.BLUE);

        frame.add(background);
        frame.setVisible(false);
        frame.setResizable(false);

        for (JRadioButton avatar: avatars) {
            avatar.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    if(avatar.isSelected()) {
                        avatar.setBackground(Color.BLUE);
                    } else {
                        avatar.setBackground(Color.WHITE);
                    }
                }
            });
        }

        frame.revalidate();
        frame.repaint();
    
    }

    // GETTERS
    public JFrame getFrame() {
        return frame;
    }

    public String getUsername(){
        return username.getText();
    }

    public String getSelected() {
        for (JRadioButton btn: avatars) {
            if (btn.isSelected())
                return btn.getText();
        }
        return null;
    }

    public Point getLocation() {
        return frame.getLocation();
    }

    public Dimension getSize() {
        return frame.getSize();
    }

    // Action Listeners
    public void addCloseListener(WindowAdapter exit) {
        frame.addWindowListener(exit);
    }

    public void addLoginListener(ActionListener login) {
		loginBtn.addActionListener(login);
    }

    public void setVisibility(boolean show){
        frame.setVisible(show);
    }

    public void setLocation(Point p) {
        frame.setLocation(p);
    }

    
}