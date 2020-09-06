
/** Client GUI Class
 * @author SALAMANTE, Stephen
 * @author TULABOT, Victor
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Client extends JPanel {
    private JLabel client_usap_label;
    private JLabel client_ip_label;
    private JToggleButton client_proceed_button;
    private JTextField client_ip_textfield;
    private JLabel client_port_label;
    private JLabel client_desc_label;
    private JTextField client_port_textfield;

    public Client() {

        // construct components
        client_usap_label = new JLabel("De La Salle Usap");
        client_ip_label = new JLabel("IP Address:");
        client_proceed_button = new JToggleButton("Proceed", false);
        client_ip_textfield = new JTextField(5);
        client_port_label = new JLabel("Port Number:");
        client_desc_label = new JLabel("To start using the service, please fill-in the following details");
        client_port_textfield = new JTextField(5);

        // adjust size and set layout
        setPreferredSize(new Dimension(601, 350));
        setLayout(null);

        // add components
        add(client_usap_label);
        add(client_ip_label);
        add(client_proceed_button);
        add(client_ip_textfield);
        add(client_port_label);
        add(client_desc_label);
        add(client_port_textfield);

        // set component bounds (only needed by Absolute Positioning)
        client_usap_label.setBounds(245, 35, 105, 25);
        client_ip_label.setBounds(45, 125, 100, 25);
        client_proceed_button.setBounds(254, 280, 100, 25);
        client_ip_textfield.setBounds(125, 125, 415, 25);
        client_port_label.setBounds(45, 175, 100, 25);
        client_desc_label.setBounds(110, 75, 385, 20);
        client_port_textfield.setBounds(130, 178, 410, 20);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("De La Salle Usap - Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new Client());
        frame.pack();
        frame.setVisible(true);
    }
}
