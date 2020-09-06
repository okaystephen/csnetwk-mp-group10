
/** Client Chatbox GUI Class
 * @author SALAMANTE, Stephen
 * @author TULABOT, Victor
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Chatbox extends JPanel {
    private JButton chatbox_file_button; // Attach File Button
    private JTextArea chatbox_input_textarea; // Input Message textbox
    private JButton chatbox_logout_button; // Logout Button
    private JButton chatbox_send_message_button; // Send Message Button
    private JLabel chatbox_connected_label; // "You are now connected!" Label
    private JTextArea chatbox_chatlog_textarea; // Chat log textbox
    private JScrollPane vertical_message;
    private JScrollPane vertical_body;

    public Chatbox() {
        // construct components
        chatbox_file_button = new JButton("Send File");

        chatbox_input_textarea = new JTextArea(5, 5);
        chatbox_input_textarea.setLineWrap(true);
        chatbox_input_textarea.setEditable(true);
        chatbox_input_textarea.setVisible(true);

        chatbox_logout_button = new JButton("Logout");

        chatbox_send_message_button = new JButton("Send Message");

        chatbox_connected_label = new JLabel("You are now connected!");

        chatbox_chatlog_textarea = new JTextArea(5, 5);
        chatbox_chatlog_textarea.setLineWrap(true);
        chatbox_chatlog_textarea.setEditable(false);
        chatbox_chatlog_textarea.setVisible(true);

        // add vertical scrollbar
        vertical_message = new JScrollPane(chatbox_input_textarea);
        vertical_message.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        vertical_body = new JScrollPane(chatbox_chatlog_textarea);
        vertical_body.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // adjust size and set layout
        setPreferredSize(new Dimension(601, 350));
        setLayout(null);

        // add components
        add(chatbox_file_button);
        add(vertical_message); // add(chatbox_input_textarea);
        add(chatbox_logout_button);
        add(chatbox_send_message_button);
        add(chatbox_connected_label);
        add(vertical_body); // add(chatbox_chatlog_textarea);

        // set component bounds (only needed by Absolute Positioning)
        chatbox_file_button.setBounds(465, 275, 115, 30);
        vertical_message.setBounds(30, 278, 425, 54); // chatbox_input_textarea
        chatbox_logout_button.setBounds(465, 20, 115, 26);
        chatbox_send_message_button.setBounds(465, 305, 115, 30);
        chatbox_connected_label.setBounds(30, 19, 155, 25);
        vertical_body.setBounds(27, 54, 550, 215); // chatbox_chatlog_textarea
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("De La Salle Usap - Chat");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new Chatbox());
        frame.pack();
        frame.setVisible(true);
    }
}
