
/** Client GUI Class
 * @author SALAMANTE, Stephen
 * @author TULABOT, Victor
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Client extends JPanel {
    // Login
    private JFrame client_frame;
    private JPanel client_panel;
    private JButton client_proceed_button;
    private JLabel client_usap_label;
    private JLabel client_ip_label;
    private JLabel client_port_label;
    private JLabel client_desc_label;
    private JTextField client_ip_textfield;
    private JTextField client_port_textfield;

    // Chatbox
    private JFrame chatbox_frame;
    private JPanel chatbox_panel;
    private JButton chatbox_file_button; // Attach File Button
    private JButton chatbox_logout_button; // Logout Button
    private JButton chatbox_send_message_button; // Send Message Button
    private JLabel chatbox_connected_label; // "You are now connected!" Label
    private JTextArea chatbox_input_textarea; // Input Message textbox
    private JTextArea chatbox_chatlog_textarea; // Chat log textbox
    private JScrollPane vertical_message;
    private JScrollPane vertical_body;

    /** Client Login Window */
    public Client() {
        client_frame = new JFrame("De La Salle Usap - Login");
        client_panel = new JPanel();
        client_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // construct components
        client_usap_label = new JLabel("De La Salle Usap");
        client_ip_label = new JLabel("IP Address:");
        client_proceed_button = new JButton("Proceed");
        client_ip_textfield = new JTextField(5);
        client_port_label = new JLabel("Port Number:");
        client_desc_label = new JLabel("To start using the service, please fill-in the following details");
        client_port_textfield = new JTextField(5);

        // adjust size and set layout
        client_panel.setPreferredSize(new Dimension(601, 350));
        client_panel.setLayout(null);

        // add components
        client_panel.add(client_usap_label);
        client_panel.add(client_ip_label);
        client_panel.add(client_proceed_button);
        client_panel.add(client_ip_textfield);
        client_panel.add(client_port_label);
        client_panel.add(client_desc_label);
        client_panel.add(client_port_textfield);

        // set component bounds (only needed by Absolute Positioning)
        client_usap_label.setBounds(245, 35, 105, 25);
        client_ip_label.setBounds(45, 125, 100, 25);
        client_proceed_button.setBounds(254, 280, 100, 25);
        client_ip_textfield.setBounds(125, 125, 415, 25);
        client_port_label.setBounds(45, 175, 100, 25);
        client_desc_label.setBounds(110, 75, 385, 20);
        client_port_textfield.setBounds(130, 178, 410, 20);

        client_frame.getContentPane().add(client_panel);
        client_frame.pack();
        client_frame.setVisible(true);
    }

    /** Chatbox Window */
    void loadChatbox() {
        client_frame.setVisible(false);

        chatbox_frame = new JFrame("De La Salle Usap - Chat");
        chatbox_panel = new JPanel();
        chatbox_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
        chatbox_panel.setPreferredSize(new Dimension(601, 350));
        chatbox_panel.setLayout(null);

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

        chatbox_frame.getContentPane().add(chatbox_panel);
        chatbox_frame.pack();
        chatbox_frame.setVisible(true);
    }

    /** Listener for Client Login Window - Proceed Button */
    void client_proceedBtnListener(ActionListener client_proceed) {
        client_proceed_button.addActionListener(client_proceed);
    }
}
