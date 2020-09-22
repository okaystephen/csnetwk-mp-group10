import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import javax.swing.filechooser.*;

public class Chatbox {

    private final String e0dee0 = "#e0dee0";

    private JFrame frameSave;
    private JFrame frame;
    private JPanel usersPanel;
    // private JPanel chatPanel;

    private JButton sendBtn;
    private JButton imgBtn;
    private JButton logoutBtn;
    private JButton saveFileBtn;
    private HashMap<String, JLabel> users;

    private JLabel currentUser;
    private JLabel chatUser;
    
    private JTextArea chatTextArea;
    
    private JTextPane chatBoxArea;
    private SimpleAttributeSet attributeSetSelf;
    private SimpleAttributeSet attributeSetOther;  
    private StyledDocument doc;

    private Font largeFont, mediumFont, smallFont;

    public Chatbox() {
        users = new HashMap<>();
        try {
            largeFont = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/MilkyNice.ttf")).deriveFont((float) 30);
            mediumFont = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/MilkyNice.ttf")).deriveFont((float) 24);
            smallFont = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/MilkyNice.ttf")).deriveFont((float) 18);
        } catch (Exception e) {
            largeFont = new Font("Arial", Font.BOLD, 30);
            mediumFont = new Font("Arial", Font.BOLD, 24);
            smallFont = new Font("Arial", Font.BOLD, 18);
        }   

        // MAIN FRAME
        frame = new JFrame("De La Salle Usap");
        frame.setSize(800, 600);
        frame.setLayout(null);
        frame.setVisible(false);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);        
        ImageIcon background_image1 = new ImageIcon("assets/images/background5.png");
        Image img = background_image1.getImage();
        Image temp_img = img.getScaledInstance(800, 600, Image.SCALE_REPLICATE);
        background_image1 = new ImageIcon(temp_img);
        JLabel background = new JLabel (background_image1);

        // PANEL
        usersPanel = new JPanel();
        usersPanel.setLayout(new BoxLayout(usersPanel, BoxLayout.Y_AXIS));
        JScrollPane usersScrollPanel = new JScrollPane(usersPanel);
        usersPanel.setBounds(600, 95, 175, 350);
        usersScrollPanel.setBounds(600, 95, 175, 350);
        background.add(usersScrollPanel);

        // BUTTONS
        sendBtn = new JButton();
        sendBtn.setBounds(725, 460, 65, 50);
        try {
            Sprite sendSprite = new Sprite(new Image[] {
                ImageIO.read(getClass().getResource("assets/images/send0.png")),
                ImageIO.read(getClass().getResource("assets/images/send1.png"))
            });
            sendSprite.setContainer(sendBtn);
            sendBtn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    sendSprite.play();
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    sendSprite.stop();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    sendSprite.stop();
                }
            });
            sendBtn.setIcon(sendSprite);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        sendBtn.setOpaque(false);
        sendBtn.setContentAreaFilled(false);
        sendBtn.setBorderPainted(false);
        background.add(sendBtn);

        imgBtn = new JButton();
        imgBtn.setBounds(25, 460, 50, 50);
        try {
            Sprite imgSprite = new Sprite(new Image[] {
                ImageIO.read(getClass().getResource("assets/images/img0.png")),
                ImageIO.read(getClass().getResource("assets/images/img1.png"))
            });
            imgSprite.setContainer(imgBtn);
            imgBtn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    imgSprite.play();
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    imgSprite.stop();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    imgSprite.stop();
                }
            });
            imgBtn.setIcon(imgSprite);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        imgBtn.setOpaque(false);
        imgBtn.setContentAreaFilled(false);
        imgBtn.setBorderPainted(false);
        background.add(imgBtn);

        saveFileBtn = new JButton();
        saveFileBtn.setBounds(85, 460, 50, 50);
        try {
            Sprite saveSprite = new Sprite(new Image[] {
                ImageIO.read(getClass().getResource("assets/images/save0.png")),
                ImageIO.read(getClass().getResource("assets/images/save1.png"))
            });
            saveSprite.setContainer(saveFileBtn);
            saveFileBtn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    saveSprite.play();
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    saveSprite.stop();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    saveSprite.stop();
                }
            });
            saveFileBtn.setIcon(saveSprite);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        saveFileBtn.setOpaque(false);
        saveFileBtn.setContentAreaFilled(false);
        saveFileBtn.setBorderPainted(false);
        background.add(saveFileBtn);

        logoutBtn = new JButton();
        logoutBtn.setBounds(725, 20, 50, 50);
        try {
            Sprite logoutSprite = new Sprite(new Image[] {
                ImageIO.read(getClass().getResource("assets/images/logout0.png")),
                ImageIO.read(getClass().getResource("assets/images/logout1.png"))
            });
            logoutSprite.setContainer(logoutBtn);
            logoutBtn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    logoutSprite.play();
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    logoutSprite.stop();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    logoutSprite.stop();
                }
            });
            logoutBtn.setIcon(logoutSprite);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        logoutBtn.setOpaque(false);
        logoutBtn.setContentAreaFilled(false);
        logoutBtn.setBorderPainted(false);
        background.add(logoutBtn);

        // TEXT BOX
        chatTextArea = new JTextArea();
        chatTextArea.setFont(mediumFont);
        chatTextArea.setWrapStyleWord(true);
        chatTextArea.setLineWrap(true);
        JScrollPane chatTextScrollPanel = new JScrollPane(chatTextArea);
        chatTextArea.setBounds(145, 460, 370, 50);
        chatTextScrollPanel.setBounds(145, 460, 570, 50);
        chatTextScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        background.add(chatTextScrollPanel);
        chatTextArea.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "none");

        // TODO: does not support long string wrapping
        chatBoxArea = new JTextPane();
        chatBoxArea.setFont(mediumFont);
        chatBoxArea.setEditable(false);
        
        doc = chatBoxArea.getStyledDocument();  
        
        attributeSetSelf = new SimpleAttributeSet();
        StyleConstants.setForeground(attributeSetSelf, Color.RED);

        attributeSetOther = new SimpleAttributeSet();
        StyleConstants.setForeground(attributeSetOther, Color.BLACK);

        JScrollPane chatScrollPanel = new JScrollPane(chatBoxArea);
        chatBoxArea.setBounds(25, 95, 550, 350);
        chatScrollPanel.setBounds(25, 95, 550, 350);
        background.add(chatScrollPanel);

        // LABELS
        currentUser = new JLabel();
        currentUser.setFont(smallFont);
        currentUser.setBounds(600, 20, 125, 50);
        currentUser.setForeground(Color.WHITE);
        background.add(currentUser);
        
        chatUser = new JLabel();
        chatUser.setFont(largeFont);
        chatUser.setBounds(25, 20, 550, 50);
        chatUser.setForeground(Color.WHITE);
        background.add(chatUser);

        background.setBounds(0, 0, 800, 600);
        frame.add(background);
        frame.setResizable(false);
    }

    // GETTERS
    public JFrame getSaveFrame() {
        return frameSave;
    }

    public JFrame getFrame() {
        return frame;
    }

    public String getChatArea() {
        return chatTextArea.getText();
    }

    public Point getLocation() {
        return frame.getLocation();
    }

    public Dimension getSize() {
        return frame.getSize();
    }

    // ACTION LISTENERS
    public void addCloseListener(WindowAdapter exit) {
        frame.addWindowListener(exit);
    }

    public void addSendBtnListener(ActionListener send) {
		sendBtn.addActionListener(send);
    }

    public void addImgBtnListener(ActionListener img) {
        imgBtn.addActionListener(img);
    }
    
    public void addLogoutListener(ActionListener logout) {
		logoutBtn.addActionListener(logout);
    }

    public void addSaveFileListener(ActionListener save) {
        saveFileBtn.addActionListener(save);
    }

    public void addKeyListener(KeyListener listener) {
        chatTextArea.addKeyListener(listener);
    }

    // METHODS
    public void setCurrentUser(String user) {
        currentUser.setText(user);
    }

    public void setLocation(Point p) {
        frame.setLocation(p);
    }

    public void appendChat(String text, boolean isSender) {
        try {
            doc.insertString(doc.getLength(), text + "\n", isSender ? attributeSetSelf : attributeSetOther);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // chatPanel.revalidate();
        // chatPanel.repaint();
    }

    public void appendImgChat(ImgMessage msg, boolean isSender) {
        try {
            // System.out.println(msg.getFilepath(isSender));
            JLabel img = new JLabel(
                isSender ? "You: " : msg.getSender() + ": ",
                new ImageIcon(msg.getFilepath(isSender)), // TODO: Resize to proper
                SwingConstants.LEFT
            );
            img.setHorizontalTextPosition(SwingConstants.LEFT);
            img.setForeground(isSender ? Color.RED : Color.BLACK);
            img.setFont(mediumFont);

            img.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent event) {
                    JFileChooser chooser = new JFileChooser();
                    chooser.addChoosableFileFilter(new FileNameExtensionFilter("." + msg.getFiletype(), msg.getFiletype()));
                    chooser.setCurrentDirectory(new java.io.File("."));
                    chooser.setDialogTitle("Save File To...");
                    chooser.setSelectedFile(new File(msg.getMsg()));
                    chooser.setAcceptAllFileFilterUsed(false);

                    if (chooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
                        String filepath = chooser.getSelectedFile().equals(chooser.getCurrentDirectory()) ?
                            chooser.getCurrentDirectory() + "/" + System.currentTimeMillis() + ".txt" :
                            chooser.getSelectedFile().toString();
                        
                        if (!filepath.endsWith("." + msg.getFiletype())) {
                            filepath += "." + msg.getFiletype();
                        }

                        try {
                            FileInputStream fReader = new FileInputStream(msg.getFilepath());
                            FileOutputStream fWriter = new FileOutputStream(filepath);
                            byte[] b = new byte[1024 * 16];
                            int size;

                            while ((size = fReader.read(b)) > 0) {
                                fWriter.write(b, 0, size);
                            }

                            fWriter.close();
                            fReader.close();

                            JOptionPane.showMessageDialog(null, "File was saved to " + chooser.getCurrentDirectory());
                        } catch (Exception err) {
                            err.printStackTrace();
                        }
                    }
                }
            });

            chatBoxArea.setCaretPosition(doc.getLength());
            chatBoxArea.insertComponent(img);
            doc.insertString(doc.getLength(), "\n", attributeSetOther);
            // chatBoxArea.revalidate();
            // chatBoxArea.repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setVisibility(boolean show) {
        this.frame.setVisible(show);
    }

    public void setChatArea(String text) {
        this.chatTextArea.setText(text);
    }

    public void saveFile() {
        frameSave = new JFrame("");
        frameSave.addWindowListener(
            new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        frameSave.setVisible(true);
    }

    public void addUser(String user, String avatar, MouseListener listener) {
        JLabel btn = new JLabel(user, new ImageIcon("assets/images/" + avatar + ".png"), SwingConstants.RIGHT);
        btn.setFont(smallFont);
        btn.addMouseListener(listener);
        usersPanel.add(btn);
        users.put(user, btn);

        usersPanel.revalidate();
        usersPanel.repaint();
    }

    public void removeUser(String user) {
        if (users.containsKey(user)) {
            JLabel btn = users.get(user);
            users.remove(user);
            usersPanel.remove(btn);
            usersPanel.revalidate();
            usersPanel.repaint();
        }
    }

    public void alertDisconnect(String user) {
        JOptionPane.showMessageDialog(null, "User " + user + " has disconnected from the server.");
    }

    public void removeAllUsers() {
        usersPanel.removeAll();
        usersPanel.revalidate();
        usersPanel.repaint();
    }

    public void setChat(String self, String other, ArrayList<Message> messages) {
        chatBoxArea.setText("");
        chatUser.setText(other);
        if (users.get(other).getForeground().equals(Color.RED)) {
            users.get(other).setForeground(Color.BLACK);
        }

        for (Message message: messages) {
            if (message instanceof ImgMessage) {
                appendImgChat((ImgMessage) message, self.equals(message.getSender()));
            } else {
                appendChat(message.simple(self), self.equals(message.getSender()));
            }
        }
        
        // chatPanel.revalidate();
        // chatPanel.repaint();
    }

    public void highlightUser(String sender) {
        users.get(sender).setForeground(Color.RED);
        usersPanel.revalidate();
        usersPanel.repaint();
    }
}
