import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/** This is our GUI View Part 1 class
 * @author SALAMANTE, Stephen E.
 * @author CARANDANG, Pauline
*/

/** Frame for welcome window */
public class Gui {
	private JButton yesBtn;
	private JButton noBtn;
	private JButton submit;
	private JButton submitLoad;
	private JButton proceedLoad;
	private JLabel label;
	private JLabel label2;
	private JLabel loadLabel;
	private JLabel loadSummaryLabel;
	private JPanel panel;
	private JPanel panel2;
	private JPanel panel3;
	private JPanel panel4;
	private JPanel loadPanel;
	private JPanel loadBox;
	private JPanel loadSummaryPanel;
	private JPanel loadButtonPanel;
	private JFrame frame;
	private JFrame loadFrame;
	private JTextField loadAnswer;
	private JTextField answer;

	public Gui() {
		frame = new JFrame();

		frame.setVisible(true);
		frame.setLayout(new FlowLayout(FlowLayout.LEFT));
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		panel = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		panel4 = new JPanel();
		answer = new JTextField(2);
		submit = new JButton("Submit");

		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel2.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
		panel4.setLayout(new FlowLayout(FlowLayout.CENTER));

		label = new JLabel("<html><center>Welcome to Johnny Moves!<br></br>"
				+ "<br>Johnny Moves is a courier company, whose innovation is to allow self-check<br/>"
				+ "<br>out of parcels for delivery to their partner sites or branches!<br/>"
				+ "<br>Do you want to proceed?</br></center></html>", SwingConstants.CENTER);
		label2 = new JLabel("Would you like to load a saved progress? ([1] Yes [2] No): ");
		yesBtn = new JButton("Yes");
		noBtn = new JButton("No");
		yesBtn.setVisible(true);
		panel4.add(label2);
		panel4.add(answer);
		panel4.add(submit);
		panel.add(yesBtn);
		panel.add(noBtn);
		panel2.add(label);

		panel3.add(panel2);
		panel3.add(panel);
		panel3.add(panel4);

		panel4.setVisible(false);
		frame.add(panel3);

		frame.setSize(500, 500);
		frame.pack();
	}

	/** Frame for loading of save file */
	void loadFrameView() {
		frame.setVisible(false);
		loadFrame = new JFrame();
		loadFrame.setLayout(new FlowLayout(FlowLayout.LEFT));
		loadFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		loadPanel = new JPanel();
		loadSummaryPanel = new JPanel();
		loadBox = new JPanel();
		loadButtonPanel = new JPanel();
		loadSummaryLabel = new JLabel();
		loadPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		loadSummaryPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		loadButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		loadBox.setLayout(new BoxLayout(loadBox, BoxLayout.Y_AXIS));
		loadLabel = new JLabel("Enter filename with extension: ", SwingConstants.LEFT);
		loadAnswer = new JTextField(10);
		submitLoad = new JButton("Submit");
		proceedLoad = new JButton("Proceed");
		loadPanel.add(loadLabel);
		loadPanel.add(loadAnswer);
		loadPanel.add(submitLoad);
		loadSummaryPanel.add(loadSummaryLabel);
		loadButtonPanel.add(proceedLoad);

		loadBox.add(loadPanel);
		loadBox.add(loadSummaryPanel);
		loadBox.add(loadButtonPanel);

		loadSummaryPanel.setVisible(false);
		loadButtonPanel.setVisible(false);
		loadFrame.add(loadBox);
		loadFrame.setVisible(true);
		loadFrame.pack();
	}

	/** Setter for load summary */
	void setLoadLabel(String message) {
		loadSummaryLabel.setText(message);
	}

	/** To show summary panel */
	void showLoadSummary() {
		loadSummaryPanel.setVisible(true);
		loadButtonPanel.setVisible(true);
	}

	/** Actions when proceed button is clicked */
	void proceedButton() {
		loadAnswer.setText("");
		loadFrame.setVisible(false);

		frame.setVisible(true);
		panel4.setVisible(false);
		answer.setText("");
	}

	/** Getter for answer in load panel */
	String getLoadAnswer() {
		return loadAnswer.getText();
	}

	/** Getter for load frame */
	JFrame getLoadFrame() {
		return loadFrame;
	}

	/** Listener for yes button */
	void addyesBtnListener(ActionListener yes) {
		yesBtn.addActionListener(yes);
	}

	/** Listener for submit button */
	void answerListener(ActionListener sub) {
		submit.addActionListener(sub);
	}

	/** Listener for no button */
	void addcloseListener(ActionListener sub) {
		noBtn.addActionListener(sub);
	}

	/** Listener for submit load buttom */
	void addSubmitLoadListener(ActionListener sub) {
		submitLoad.addActionListener(sub);
	}

	/** Listener for proceed load button */
	void addProceedLoadListener(ActionListener pro) {
		proceedLoad.addActionListener(pro);
	}

	/** To show panel about load a save file */
	void showPanel4() {
		panel4.setVisible(true);
	}

	/** To close welcome frame */
	void closeFrame() {
		frame.setVisible(false);
	}

	/** Getter for user input about load */
	String getAnswer() {
		return answer.getText();
	}

	/** Getter for welcome frame */
	JFrame getFrame() {
		return frame;
	}

	/** For dialog box */
	void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}
}
