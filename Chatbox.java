
/** Client Chatbox GUI Class
 * @author SALAMANTE, Stephen
 * @author TULABOT, Victor
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Chatbox extends JPanel {
    private JButton jcomp1; // Attach File Button
    private JTextArea jcomp2; // Input Message textbox
    private JButton jcomp3; // Logout Button
    private JButton jcomp4; // Send Message Button
    private JLabel jcomp5; // "You are now connected!" Label
    private JTextArea jcomp6; // Chat log textbox
    private JScrollPane vertical_message;
    private JScrollPane vertical_body;

    public Chatbox() {
        // construct components
        jcomp1 = new JButton("Attach File");

        jcomp2 = new JTextArea(5, 5);
        jcomp2.setLineWrap(true);
        jcomp2.setEditable(true);
        jcomp2.setVisible(true);

        jcomp3 = new JButton("Logout");

        jcomp4 = new JButton("Send");

        jcomp5 = new JLabel("You are now connected!");

        jcomp6 = new JTextArea(5, 5);
        jcomp6.setLineWrap(true);
        jcomp6.setEditable(false);
        jcomp6.setVisible(true);

        // add vertical scrollbar
        vertical_message = new JScrollPane(jcomp2);
        vertical_message.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        vertical_body = new JScrollPane(jcomp6);
        vertical_body.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // adjust size and set layout
        setPreferredSize(new Dimension(601, 350));
        setLayout(null);

        // add components
        add(jcomp1);
        add(vertical_message); // add(jcomp2);
        add(jcomp3);
        add(jcomp4);
        add(jcomp5);
        add(vertical_body); // add(jcomp6);

        // set component bounds (only needed by Absolute Positioning)
        jcomp1.setBounds(465, 275, 115, 30);
        vertical_message.setBounds(30, 278, 425, 54); // jcomp2
        jcomp3.setBounds(465, 20, 115, 26);
        jcomp4.setBounds(465, 305, 115, 30);
        jcomp5.setBounds(30, 19, 155, 25);
        vertical_body.setBounds(27, 54, 550, 215); // jcomp6
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("De La Salle Usap - Chat");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new Chatbox());
        frame.pack();
        frame.setVisible(true);
    }
}
