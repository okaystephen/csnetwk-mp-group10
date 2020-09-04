
/** Client GUI Class
 * @author SALAMANTE, Stephen
 * @author TULABOT, Victor
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Client extends JPanel {
    private JLabel usap_title;
    private JLabel ip_label;
    private JToggleButton jcomp3;
    private JTextField ip_address;
    private JLabel port_label;
    private JLabel jcomp6;
    private JTextField port_number;

    public Client() {

        // construct components
        usap_title = new JLabel("De La Salle Usap");
        ip_label = new JLabel("IP Address:");
        jcomp3 = new JToggleButton("Proceed", false);
        ip_address = new JTextField(5);
        port_label = new JLabel("Port Number:");
        jcomp6 = new JLabel("To start using the service, please fill-in the following details");
        port_number = new JTextField(5);

        // adjust size and set layout
        setPreferredSize(new Dimension(601, 350));
        setLayout(null);

        // add components
        add(usap_title);
        add(ip_label);
        add(jcomp3);
        add(ip_address);
        add(port_label);
        add(jcomp6);
        add(port_number);

        // set component bounds (only needed by Absolute Positioning)
        usap_title.setBounds(245, 35, 105, 25);
        ip_label.setBounds(45, 125, 100, 25);
        jcomp3.setBounds(254, 280, 100, 25);
        ip_address.setBounds(125, 125, 415, 25);
        port_label.setBounds(45, 175, 100, 25);
        jcomp6.setBounds(110, 75, 385, 20);
        port_number.setBounds(130, 178, 410, 20);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("De La Salle Usap - Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new Client());
        frame.pack();
        frame.setVisible(true);
    }
}
