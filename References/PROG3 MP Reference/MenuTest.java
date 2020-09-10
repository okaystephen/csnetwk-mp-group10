import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

/** This is our GUI View Part 2 class
 * @author SALAMANTE, Stephen E.
 * @author CARANDANG, Pauline
*/

public class MenuTest {
	private JLabel regionLabel;
	private JLabel deliveryLabel;
	private JLabel rateLabel;
	private JLabel AvailRegionLabel1;
	private JLabel ItemLabel;
	private JLabel ItemLabel1;
	private JLabel ItemLabel2;
	private JLabel ItemLabel3;
	private JLabel ItemLabel4;
	private JPanel regionPanel;
	private JPanel deliveryPanel;
	private JPanel ratePanel;
	private JPanel namePanel;
	private JPanel firstPanel;
	private JPanel AvailRegion1;
	private JPanel noItemsPanel;
	private JPanel ItemPanel1;
	private JPanel ItemPanel2;
	private JPanel ItemPanel3;
	private JPanel ItemPanel4;
	private JPanel ItemPanel5;
	private JPanel ItemPanel6;
	private JPanel ItemPanel7;
	private JPanel ItemPanel;
	private JLabel nameLabel;
	private JLabel noItems;
	private JLabel action0;
	private JLabel action1;
	private JLabel action2;
	private JLabel action3;
	private JLabel rotate;
	private JLabel delete;
	private JLabel ItemLabelProd;
	private JLabel ItemLabelProd1;
	private JLabel chooseParcelText;
	private JLabel nextMoveLabel;
	private JLabel pickChoice;
	private JLabel enterNumber;
	private JLabel TrackLabel;
	private JLabel HereItems;
	private JLabel AvailItems;
	private JPanel finalPanel;
	private JPanel actionPanel;
	private JLabel availParcel;
	private JLabel chooseParcel;
	private JLabel breakDown;
	private JLabel breakD;
	private JLabel username;
	private JLabel password;
	
	private JTextField nameTextField;
	private JTextField availRegion;
	private JTextField noItemTextField;
	private JTextField ItemTextField;
	private JTextField ItemTextField1;
	private JTextField ItemTextField2;
	private JTextField ItemTextField3;
	private JTextField ItemTextField4;
	private JTextField ItemTextField5;
	private JTextField ItemTextField6;
	private JTextField rotateField;
	private JTextField deleteField;
	private JTextField chooseParcelField;
	private JTextField pickchoiceField;
	private JTextField actionChoice;
	private JTextField TrackNumField;
	private JTextField usernameField;
	private JTextField passwordField;

	private JButton submitBtn = new JButton("Submit");
	private JButton submitBtn1 = new JButton("Submit");
	private JFrame frame;
	private JScrollPane pane;
	private JButton showTest = new JButton("SHOW");
	private JButton adminButton;
	
	private JPanel panel;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panelRotate;
	private JPanel panelDelete;
	private JPanel panelBrkdown;
	private JPanel panelChooseParcel;
	private JPanel nextMovePanel;
	private JPanel pickChoicePanel;
	private JPanel trackNumPanel;
	private JPanel trackInfo;
	private JPanel AvailItemsPanel;
	private JPanel HereItemsPanel;
	private JPanel breakDPanel;
	private JPanel usernamePanel;
	private JPanel passwordPanel;
	
	private JButton submitBreakdown;
	private JButton submitRotate;
	private JButton submitDelete;
	private JButton submitBtnChoiceParcel;
	private JButton submitChoice;
	private JButton submitTrack;
	
	/** Constructor for MenuTest class */
	public MenuTest() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout());
		finalPanel = new JPanel();
		finalPanel.setLayout(new BoxLayout(finalPanel, BoxLayout.Y_AXIS));
		RegionPanel();
		ItemInfo();
		BreakdownGUI();
		finalPanel.add(firstPanel);
		finalPanel.add(ItemPanel);
		finalPanel.add(panel2);
		panel2.setVisible(false);
		panelRotate.setVisible(false);
		panelDelete.setVisible(false);
		breakDPanel.setVisible(false);
		panelChooseParcel.setVisible(false);
		nextMovePanel.setVisible(false);
		pickChoicePanel.setVisible(false);
		trackNumPanel.setVisible(false);
		trackInfo.setVisible(false);
		usernamePanel.setVisible(false);
		passwordPanel.setVisible(false);
		pane=new JScrollPane(finalPanel);
		frame.add(pane);
		frame.setSize(400,400);
		frame.pack();
	}
	
	/** For region panel */
	public void RegionPanel() {
		regionPanel = new JPanel();
		deliveryPanel = new JPanel();
		ratePanel = new JPanel();
		firstPanel = new JPanel();
	
		regionPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		deliveryPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		ratePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		firstPanel.setLayout(new BoxLayout(firstPanel, BoxLayout.X_AXIS));
		
		// First Panel
		regionLabel = new JLabel("<html>[REGION]<br></br>"
				+ "<br>Metro Manila</b>"
				+ "<br>Luzon</br>"
				+ "<br>Visayas</br>"
				+ "<br>Mindanao</br>"
				+ "<br><br><br>List of Regions: </br></br></br>"
				+ "<br>[1] Metro Manila</br>"
				+ "<br>[2] Luzon</br>"
				+ "<br>[3] Visayas</br>"
				+ "<br>[4] Mindanao</br></html>");
		deliveryLabel = new JLabel("<html>[DELIVERY]<br></br>"
				+ "<br>2 Days</br>"
				+ "<br>3 Days</br>"
				+ "<br>5 Days</br>"
				+ "<br>8 Days</br></html>");
		rateLabel = new JLabel("<html>[RATE]<br></br>"
				+ "<br>PHP 50</br>"
				+ "<br>PHP 100</br>"
				+ "<br>PHP 1000 or 10% of the weight or volume</br>"
				+ "<br>PHP 3000 or 25% of the weight or volume</br><html>");
		
		regionPanel.add(regionLabel);
		deliveryPanel.add(deliveryLabel);
		ratePanel.add(rateLabel);
		firstPanel.add(regionPanel);
		firstPanel.add(deliveryPanel);
		firstPanel.add(ratePanel);	
	}

/** Panel for item information */
public void ItemInfo() {
	ItemPanel = new JPanel();
	ItemPanel1 = new JPanel();
	ItemPanel2 = new JPanel();
	ItemPanel3 = new JPanel();
	ItemPanel4 = new JPanel();
	ItemPanel5 = new JPanel();
	ItemPanel6 = new JPanel();
	ItemPanel7 = new JPanel();
	ItemTextField = new JTextField(2);
	ItemTextField1 = new JTextField(2);
	ItemTextField2 = new JTextField(2);
	ItemTextField3 = new JTextField(2);
	ItemTextField4 = new JTextField(2);
	ItemTextField5 = new JTextField(2);
	ItemTextField6 = new JTextField(2);
	ItemLabel = new JLabel("Choice of item ([1] Document [2] Product): ");
	ItemLabel1 = new JLabel("Number of pages: ");
	ItemLabel2 = new JLabel("Width of item: ");
	ItemLabel3 = new JLabel("Length of item: ");
	ItemLabelProd = new JLabel("Height of item: ");
	ItemLabelProd1 = new JLabel("Weight of item: ");
	ItemLabel4 = new JLabel("Add insurance ([1] Yes [2] No): ");
	ItemPanel.setLayout(new BoxLayout(ItemPanel, BoxLayout.Y_AXIS));
	ItemPanel1.setLayout(new FlowLayout(FlowLayout.LEFT));
	ItemPanel2.setLayout(new FlowLayout(FlowLayout.LEFT));
	ItemPanel3.setLayout(new FlowLayout(FlowLayout.LEFT));
	ItemPanel4.setLayout(new FlowLayout(FlowLayout.LEFT));
	ItemPanel5.setLayout(new FlowLayout(FlowLayout.LEFT));
	ItemPanel6.setLayout(new FlowLayout(FlowLayout.LEFT));
	ItemPanel7.setLayout(new FlowLayout(FlowLayout.LEFT));
	
	ItemPanel1.add(ItemLabel);
	ItemPanel1.add(ItemTextField);
	ItemPanel1.add(submitBtn1);
	ItemPanel2.add(ItemLabel1);
	ItemPanel2.add(ItemTextField1);
	ItemPanel3.add(ItemLabel2);
	ItemPanel3.add(ItemTextField2);
	ItemPanel4.add(ItemLabel3);
	ItemPanel4.add(ItemTextField3);
	ItemPanel5.add(ItemLabel4);
	ItemPanel5.add(ItemTextField4);
	ItemPanel5.add(submitBtn);
	ItemPanel6.add(ItemLabelProd);
	ItemPanel6.add(ItemTextField5);
	ItemPanel7.add(ItemLabelProd1);
	ItemPanel7.add(ItemTextField6);
	
	namePanel = new JPanel();
	AvailRegion1 = new JPanel();
	noItemsPanel = new JPanel();
	
	nameTextField = new JTextField(30);
	
	availRegion = new JTextField(2);
	
	noItemTextField = new JTextField(2);
	
	nameLabel = new JLabel("Recipient name:");
	AvailRegionLabel1 = new JLabel("Region: ");
	noItems = new JLabel("Number of items: ");
	namePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
	AvailRegion1.setLayout(new FlowLayout(FlowLayout.LEFT));
	noItemsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

	namePanel.add(nameLabel);
	namePanel.add(nameTextField);
	AvailRegion1.add(AvailRegionLabel1);
	AvailRegion1.add(availRegion);
	noItemsPanel.add(noItems);
	noItemsPanel.add(noItemTextField);
	
	ItemTextField1.setEditable(false);
	ItemTextField2.setEditable(false);
	ItemTextField3.setEditable(false);
	ItemTextField4.setEditable(false);
	ItemTextField5.setEditable(false);
	ItemTextField6.setEditable(false);
	
	ItemPanel.add(namePanel);
	ItemPanel.add(AvailRegion1);
	ItemPanel.add(noItemsPanel);
	ItemPanel.add(ItemPanel1);
	ItemPanel.add(ItemPanel2);
	ItemPanel.add(ItemPanel3);
	ItemPanel.add(ItemPanel4);
	ItemPanel.add(ItemPanel6);
	ItemPanel.add(ItemPanel7);
	ItemPanel.add(ItemPanel5);
}

/** Panel for fee breakdown and actions */
public void BreakdownGUI() {
	panel = new JPanel();
	panel1 = new JPanel();
	panel2 = new JPanel();
	actionPanel = new JPanel();
	panelRotate = new JPanel();
	panelDelete = new JPanel();
	panelBrkdown = new JPanel();
	nextMovePanel = new JPanel();
	pickChoicePanel = new JPanel();
	panelChooseParcel = new JPanel();
	breakDPanel = new JPanel();
	trackNumPanel = new JPanel();
	trackInfo = new JPanel();
	AvailItemsPanel = new JPanel();
	HereItemsPanel = new JPanel();
	usernamePanel = new JPanel();
	passwordPanel = new JPanel();
	
	action0 = new JLabel("Actions: ");
	action1 = new JLabel("[1] Rotate an item");
	action2 = new JLabel("[2] Delete an item");
	action3 = new JLabel("[3] Choose a parcel");
	chooseParcelText = new JLabel("Choose a parcel: ");
	breakD = new JLabel("FEE BREAKDOWN");
	rotate = new JLabel("Which item are you going to rotate: ");
	delete = new JLabel("Which item are you going to delete: ");
	nextMoveLabel = new JLabel("<html>What would you like to do next?<br></br>"
			+ "<br>[1] Have another parcel delivered</br>"
			+ "<br>[2] Track your parcel/s</br>"
			+ "<br>[3] Exit program</br></html>");
	availParcel = new JLabel("");
	breakDown = new JLabel("HELLOOOOO");
	TrackLabel = new JLabel();
	username = new JLabel("Username: ");
	password = new JLabel("Password: ");
	
	submitBreakdown = new JButton("Submit");
	submitBtnChoiceParcel = new JButton("Submit");
	submitChoice = new JButton("Submit");
	submitTrack = new JButton("Submit");
	chooseParcel = new JLabel("Choose an action: ");
	pickChoice = new JLabel("Pick a choice");
	enterNumber = new JLabel("Tracking number: ");
	HereItems = new JLabel("Here are your available items: ");
	AvailItems = new JLabel("");
	submitRotate = new JButton("Submit");
	submitDelete = new JButton("Submit");
	adminButton = new JButton("Submit");
	
	actionChoice = new JTextField(1);
	deleteField = new JTextField(2);
	rotateField = new JTextField(2);
	chooseParcelField = new JTextField(2);
	pickchoiceField = new JTextField(2);
	TrackNumField = new JTextField(10);
	usernameField = new JTextField(10);
	passwordField = new JTextField(10);
	
	panelRotate.setLayout(new FlowLayout(FlowLayout.LEFT));
	panelDelete.setLayout(new FlowLayout(FlowLayout.LEFT));
	panel.setLayout(new FlowLayout(FlowLayout.LEFT));
	panel1.setLayout(new FlowLayout(FlowLayout.LEFT));
	actionPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
	panelBrkdown.setLayout(new FlowLayout(FlowLayout.LEFT));
	panelChooseParcel.setLayout(new FlowLayout(FlowLayout.LEFT));
	nextMovePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
	pickChoicePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
	trackNumPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
	trackInfo.setLayout(new FlowLayout(FlowLayout.LEFT));
	HereItemsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
	breakDPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
	usernamePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
	passwordPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
	AvailItemsPanel.setLayout(new BoxLayout(AvailItemsPanel, BoxLayout.Y_AXIS));
	panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
	
	panel.add(availParcel);
	AvailItemsPanel.add(HereItems);
	AvailItemsPanel.add(AvailItems);
	HereItemsPanel.add(AvailItemsPanel);
	
	panel1.add(chooseParcel);
	panel1.add(actionChoice);
	panel1.add(submitBreakdown);
	actionPanel.add(action0);
	actionPanel.add(action1);
	actionPanel.add(action2);
	actionPanel.add(action3);
	panelRotate.add(rotate);
	panelRotate.add(rotateField);
	panelRotate.add(submitRotate);
	panelDelete.add(delete);
	panelDelete.add(deleteField);
	panelDelete.add(submitDelete);
	panelChooseParcel.add(chooseParcelText);
	panelChooseParcel.add(chooseParcelField);
	panelChooseParcel.add(submitBtnChoiceParcel);
	nextMovePanel.add(nextMoveLabel);
	panelBrkdown.add(breakDown);
	pickChoicePanel.add(pickChoice);
	pickChoicePanel.add(pickchoiceField);
	pickChoicePanel.add(submitChoice);
	trackNumPanel.add(enterNumber);
	trackNumPanel.add(TrackNumField);
	trackNumPanel.add(submitTrack);
	breakDPanel.add(breakD);
	trackInfo.add(TrackLabel);
	usernamePanel.add(username);
	usernamePanel.add(usernameField);
	passwordPanel.add(password);
	passwordPanel.add(passwordField);
	passwordPanel.add(adminButton);
	
	panel2.add(panel);
	panel2.add(HereItemsPanel);
	panel2.add(actionPanel);
	panel2.add(panel1);
	panel2.add(panelRotate);
	panel2.add(panelDelete);
	panel2.add(panelChooseParcel);
	panel2.add(panelBrkdown);
	panel2.add(breakDPanel);
	panel2.add(nextMovePanel);
	panel2.add(pickChoicePanel);
	panel2.add(trackNumPanel);
	panel2.add(trackInfo);
	panel2.add(usernamePanel);
	panel2.add(passwordPanel);
}

/** Getter for recipient name */
String getName() {
	return nameTextField.getText();
}

/** Getter for region */
String getRegion() {
	return availRegion.getText();
}

/** Getter for number of items */
String getItems() {
	return noItemTextField.getText();
}

/** Getter for page number */
String getPageNo() {
	return ItemTextField1.getText();
}

/** Getter for width */
String getWidth() {
	return ItemTextField2.getText();
}

/** Getter for length */
String getLength() {
	return ItemTextField3.getText();
}

/** Getter for insurance */
String getInsurance() {
	return ItemTextField4.getText();
}

/** Getter for user input */
String getUserInput() 
{
    return ItemTextField.getText();
}

/** Getter for height */
String getHeight() {
	return ItemTextField5.getText();
}

/** Getter for height */
String getWeight() {
	return ItemTextField6.getText();
}

/** Getter for action choice */
String getActionChoice() {
	return actionChoice.getText();
	
}

/** Getter for next choice */
String getNextChoice() {
	return pickchoiceField.getText();
}

/** Getter for delete item */
String getDeleteItem() {
	return deleteField.getText();
}

/** Getter for rotate item */
String getRotateItem() {
	return rotateField.getText();
}

/** Getter for tracking number */
String getTrackNum() {
	return TrackNumField.getText();
}

/** Getter for parcel choice */
String getParcelChoice() {
	return chooseParcelField.getText();
}

/** Getter for username */
String getUsername() {
	return usernameField.getText();
}

/** Getter for password */
String getPassword() {
	return passwordField.getText();
}

/** Getter for return frame */
public JFrame returnFrame() {
	return frame;
}

/** Show whole breakdown panel */
public void showPanel2() {
	panel2.setVisible(true);
}

/** Show explicit breakdown panel */
public void showBreakdown() {
	breakDown.setVisible(true);
}

/** Show next move panel */
public void shownextMovePanel() {
	nextMovePanel.setVisible(true);
}

/** Show parcel choice panel */
public void showParcelchoice() {
	panelChooseParcel.setVisible(true);
}

/** Show track number panel */
public void showtrackNumPanel() {
	trackNumPanel.setVisible(true);
}

/** Show tracking label panel */
public void setTrackInfo(String message) {
	TrackLabel.setText(message);
}

/** Show tracking information panel*/
public void showtrackInfo() {
	trackInfo.setVisible(true);
}

/** Reset tracking number field */
public void TrackInfoReset() {
	TrackNumField.setText("");
}

/** Show weight panel */
public void showWeight() {
	ItemPanel7.setVisible(true);
}

/** Show number of pages panel */
public void showNoPage() {
	ItemPanel2.setVisible(true);
}

/** Show rotate panel */
void showRotatePanel() {
	panelRotate.setVisible(true);
}

/** Show delete panel */
void showDeletePanel() {
	panelDelete.setVisible(true);
}

/** Show pick choice panel */
void showPickChoice() {
	pickChoicePanel.setVisible(true);
}

/** Show breakdown panel */
void showbreakDPanel() {
	breakDPanel.setVisible(true);
}

/** Setter for breakdown text */
void setbreakD(String message) {
	breakD.setText(message);
}

/** Hiding submit button */
void submitBtnremove() {
	submitBtn1.setVisible(false);
}

/** Hiding labels about customer input version 1 */
void Hide() {
	ItemLabel1.setVisible(true);
	ItemTextField1.setVisible(true);
	ItemLabelProd.setVisible(false);
	ItemLabelProd1.setVisible(false);
	ItemTextField5.setVisible(false);
	ItemTextField6.setVisible(false);
}

/** Hiding labels about customer input version 2 */
void Hide1() {
	ItemLabel1.setVisible(false);
	ItemTextField1.setVisible(false);
	ItemTextField5.setVisible(true);
	ItemTextField6.setVisible(true);
	ItemLabelProd.setVisible(true);
	ItemLabelProd1.setVisible(true);
}

/** Show labels about customer input version 1 */
void show() {
	ItemLabelProd.setVisible(true);
	ItemLabelProd1.setVisible(true);
	ItemTextField5.setVisible(true);
	ItemTextField6.setVisible(true);
	
}

/** Show labels about customer input version 2 */
void show1() {
	ItemLabel1.setVisible(true);
	ItemTextField1.setVisible(true);
}

/** Show username panel */
void showUserPanel() {
	usernamePanel.setVisible(true);
}

/** Show password panel */
void showPasswordPanel() {
	passwordPanel.setVisible(true);
}

/** Hide breakdown pabel */
public void HidePanel2() {
	panel2.setVisible(false);
}

/** Hide delete panel */
public void HideDeletePanel() {
	panelDelete.setVisible(false);
	actionChoice.setText("");
	deleteField.setText("");
	
}

/** Hide rotate panel */
public void HideRotatePanel() {
	panelRotate.setVisible(false);
	actionChoice.setText("");
	rotateField.setText("");
}

/** Hide parcel panel */
public void HideParcelPanel() {
	panelChooseParcel.setVisible(false);
	actionChoice.setText("");
	chooseParcelField.setText("");
	
}

/** Hide admin panel */
public void HideAdmin() {
	usernamePanel.setVisible(false);
	usernameField.setText("");
	passwordPanel.setVisible(false);
	passwordField.setText("");
	pickchoiceField.setText("");
	
}

/** Hide tracking number panel */
public void HidetrackNumPanel() {
	pickchoiceField.setText("");
	trackNumPanel.setVisible(false);
	TrackNumField.setText("");
}

/** Hide parcel panel */
public void hidetrackInfo() {
	trackInfo.setVisible(false);
}

/** Hide exit panel */
public void HideExitPanel() {
	pickchoiceField.setText("");
	usernamePanel.setVisible(false);
	usernameField.setText("");
	passwordPanel.setVisible(false);
	passwordField.setText("");
}

/** Setter for parcel panel */
void setTxtBrkdown(String message) {
	breakDown.setText(message);
}

/** Setter for list of available items label */
void setAvailItems(String message) {
	AvailItems.setText(message);
}

/** Listener for submit button of breakdown */
void submitBreakdownListener(ActionListener sub) {
	submitBreakdown.addActionListener(sub);
}

/** Listener for submit button of parcel choice */
void submitChoiceParcel(ActionListener sub) {
	submitBtnChoiceParcel.addActionListener(sub);
}
	
/** Listener for submit button of choice */
void submitChoice(ActionListener sub) {
	submitChoice.addActionListener(sub);
}

/** Listener for submit button of tracking */
void submitTrack(ActionListener sub) {
	submitTrack.addActionListener(sub);
}

/** Listener for show test button */
void ShowListener(ActionListener sub) {
	showTest.addActionListener(sub);
}

/** Listener for add action button */
void addSubmitFinalListener(ActionListener sub) 
{
	submitBtn.addActionListener(sub); 
}

/** Listener for submit button */
void addSubmitListener(ActionListener sub1) {
	submitBtn1.addActionListener(sub1);
}

/** Listener for submit breakdown button */
void BreakDownButtonListener(ActionListener sub) {
	submitBreakdown.addActionListener(sub);
}

/** Listener for submit delete item button */
void DeleteItemListener(ActionListener sub) {
	submitDelete.addActionListener(sub);
}

/** Listener for submit rotate item button */
void RotateItemListener(ActionListener sub) {
	submitRotate.addActionListener(sub);
}

/** Listener for submit admin button */
void AdminListener(ActionListener sub) {
	adminButton.addActionListener(sub);
}

/** Reverting fields and panels version 1 */
void revertValues() {
	noItemTextField.setText("");
	nameTextField.setText("");
	availRegion.setText("");
	ItemTextField.setText("");
	ItemTextField1.setText("");
	ItemTextField2.setText("");
	ItemTextField3.setText("");
	ItemTextField4.setText("");
	ItemTextField5.setText("");
	ItemTextField6.setText("");
	chooseParcelField.setText("");
	chooseParcelField.setText("");
	actionChoice.setText("");
	deleteField.setText("");
	pickchoiceField.setText("");
	TrackNumField.setText("");
	panelRotate.setVisible(false);
	panelDelete.setVisible(false);
	panelChooseParcel.setVisible(false);
	nextMovePanel.setVisible(false);
	panelBrkdown.setVisible(false);
	pickChoicePanel.setVisible(false);
	
}

/** Reverting fields and panels version 2 */
void revertValues1() {
	ItemTextField.setText("");
	ItemTextField1.setText("");
	ItemTextField2.setText("");
	ItemTextField3.setText("");
	ItemTextField4.setText("");
	ItemTextField5.setText("");
	ItemTextField6.setText("");
	chooseParcelField.setText("");
	chooseParcelField.setText("");
	actionChoice.setText("");
	deleteField.setText("");
	pickchoiceField.setText("");
	TrackNumField.setText("");
	panelRotate.setVisible(false);
	panelDelete.setVisible(false);
	panelChooseParcel.setVisible(false);
	nextMovePanel.setVisible(false);
	panelBrkdown.setVisible(false);
	pickChoicePanel.setVisible(false);
	nameTextField.setEditable(false);
	availRegion.setEditable(false);
	noItemTextField.setEditable(false);
}

/** Reverting of fields and panels version 3 */
void revertValues2() {
	nameTextField.setEditable(true);
	nameTextField.setEditable(true);
	availRegion.setEditable(true);
	noItemTextField.setEditable(true);
	noItemTextField.setText("");
	breakD.setText("");
	nameTextField.setText("");
	availRegion.setText("");
	ItemTextField.setText("");
	ItemTextField1.setText("");
	ItemTextField2.setText("");
	ItemTextField3.setText("");
	ItemTextField4.setText("");
	ItemTextField5.setText("");
	ItemTextField6.setText("");
	chooseParcelField.setText("");
	chooseParcelField.setText("");
	actionChoice.setText("");
	deleteField.setText("");
	pickchoiceField.setText("");
	TrackNumField.setText("");
	
	panelRotate.setVisible(false);
	panelDelete.setVisible(false);
	panelChooseParcel.setVisible(false);
	nextMovePanel.setVisible(false);
	panelBrkdown.setVisible(false);
	pickChoicePanel.setVisible(false);
	
}

/** Sets fields to true version 1 */
void setTrue() {
	ItemTextField1.setEditable(true);
	ItemTextField2.setEditable(true);
	ItemTextField3.setEditable(true);
	ItemTextField4.setEditable(true);
}

/** Sets fields to false */
void UneditInfo() {
	nameTextField.setEditable(false);
	availRegion.setEditable(false);
	noItemTextField.setEditable(false);
}

/** Set fields to true version 2 */
void setTrue1() {
	ItemTextField1.setEditable(false);
	ItemTextField2.setEditable(true);
	ItemTextField3.setEditable(true);
	ItemTextField4.setEditable(true);
	ItemTextField5.setEditable(true);
	ItemTextField6.setEditable(true);
}

/** Setter for list of available parcels version 1 */
void setText1() {
	availParcel.setText("<html>[1] Flat - 4 x 6 inches"
			+ "<br>[2] Flat - 9 x 14 inches"
			+ "<br>[3] Flat - 12 x 18 inches</br>"
			+ "<br>[4] Box - 12 x 10 x 5 inches</br>"
			+ "<br>[5] Box - 14 x 11 x 7 inches</br>"
			+ "<br>[6] Box - 18 x 12 x 9 inches</br>"
			+ "<br>[7] Box - 20 x 16 x 12 inches</br>"
			+ "<br>Note: [x] means unavailable</br></html>");
}

/** Setter for list of available parcels version 2 */
void setText2() {
	availParcel.setText("<html>[X] Flat - 4 x 6 inches"
			+ "<br>[2] Flat - 9 x 14 inches"
			+ "<br>[3] Flat - 12 x 18 inches</br>"
			+ "<br>[4] Box - 12 x 10 x 5 inches</br>"
			+ "<br>[5] Box - 14 x 11 x 7 inches</br>"
			+ "<br>[6] Box - 18 x 12 x 9 inches</br>"
			+ "<br>[7] Box - 20 x 16 x 12 inches</br>"
			+ "<br>Note: [x] means unavailable</br></html>");
}

/** Setter for list of available parcels version 3 */
void setText3() {
	availParcel.setText("<html>[X] Flat - 4 x 6 inches"
			+ "<br>[X] Flat - 9 x 14 inches"
			+ "<br>[3] Flat - 12 x 18 inches</br>"
			+ "<br>[4] Box - 12 x 10 x 5 inches</br>"
			+ "<br>[5] Box - 14 x 11 x 7 inches</br>"
			+ "<br>[6] Box - 18 x 12 x 9 inches</br>"
			+ "<br>[7] Box - 20 x 16 x 12 inches</br>"
			+ "<br>Note: [x] means unavailable</br></html>");
}

/** Setter for list of available parcels version 4 */
void setText4() {
	availParcel.setText("<html>[X] Flat - 4 x 6 inches"
			+ "<br>[X] Flat - 9 x 14 inches"
			+ "<br>[X] Flat - 12 x 18 inches</br>"
			+ "<br>[4] Box - 12 x 10 x 5 inches</br>"
			+ "<br>[5] Box - 14 x 11 x 7 inches</br>"
			+ "<br>[6] Box - 18 x 12 x 9 inches</br>"
			+ "<br>[7] Box - 20 x 16 x 12 inches</br>"
			+ "<br>Note: [x] means unavailable</br></html>");
}

/** Setter for list of available parcels version 5 */
void setText5() {
	availParcel.setText("<html>[X] Flat - 4 x 6 inches"
			+ "<br>[X] Flat - 9 x 14 inches"
			+ "<br>[X] Flat - 12 x 18 inches</br>"
			+ "<br>[X] Box - 12 x 10 x 5 inches</br>"
			+ "<br>[5] Box - 14 x 11 x 7 inches</br>"
			+ "<br>[6] Box - 18 x 12 x 9 inches</br>"
			+ "<br>[7] Box - 20 x 16 x 12 inches</br>"
			+ "<br>Note: [x] means unavailable</br></html>");
}

/** Setter for list of available parcels version 6 */
void setText6() {
	availParcel.setText("<html>[X] Flat - 4 x 6 inches"
			+ "<br>[X] Flat - 9 x 14 inches"
			+ "<br>[X] Flat - 12 x 18 inches</br>"
			+ "<br>[X] Box - 12 x 10 x 5 inches</br>"
			+ "<br>[X] Box - 14 x 11 x 7 inches</br>"
			+ "<br>[6] Box - 18 x 12 x 9 inches</br>"
			+ "<br>[7] Box - 20 x 16 x 12 inches</br>"
			+ "<br>Note: [x] means unavailable</br></html>");
}

/** Setter for list of available parcels version 7 */
void setText7() {
	availParcel.setText("<html>[X] Flat - 4 x 6 inches"
			+ "<br>[X] Flat - 9 x 14 inches"
			+ "<br>[X] Flat - 12 x 18 inches</br>"
			+ "<br>[X] Box - 12 x 10 x 5 inches</br>"
			+ "<br>[X] Box - 14 x 11 x 7 inches</br>"
			+ "<br>[X] Box - 18 x 12 x 9 inches</br>"
			+ "<br>[7] Box - 20 x 16 x 12 inches</br>"
			+ "<br>Note: [x] means unavailable</br></html>");
}

void setText8() {
	availParcel.setText("<html>[X] Flat - 4 x 6 inches"
			+ "<br>[X] Flat - 9 x 14 inches"
			+ "<br>[X] Flat - 12 x 18 inches</br>"
			+ "<br>[X] Box - 12 x 10 x 5 inches</br>"
			+ "<br>[X] Box - 14 x 11 x 7 inches</br>"
			+ "<br>[X] Box - 18 x 12 x 9 inches</br>"
			+ "<br>[X] Box - 20 x 16 x 12 inches</br>"
			+ "<br>Note: [x] means unavailable</br></html>");
}

/** Shows dialog box */
void showMessage(String message){
	JOptionPane.showMessageDialog(null, message);
}
}
