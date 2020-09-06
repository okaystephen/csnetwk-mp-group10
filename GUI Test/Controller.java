
/**
 * Client Controller Class
 * 
 * @author SALAMANTE, Stephen
 * @author TULABOT, Victor
 */

import java.awt.event.*;
import java.io.*;
import java.text.*;
import java.util.*;
import javax.swing.JOptionPane;

public class Controller {
	private Client client;
	private Backend model;

	/** Constructor for Controller Class */
	Controller(Backend model, Client client) {

		client = this.client;
		model = this.model;

		client.client_proceedBtnListener(new client_proceedBtnListener());
		// client.answerListener(new AnswerListener());
		// client.addcloseListener(new CloseBtnListener());
	}

	/** Listener for the yes button in Gui class */
	class client_proceedBtnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			client.showMessage("Success!");
			client.loadChatbox();
		}
	}

	/** Listener for the no button in Gui class */
	class CloseBtnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			m_gui.showMessage("Thank you for using Johnny Moves!");
			System.exit(0);
		}
	}

	/** This is button that asks if the user wants to load a saved file or not */
	class AnswerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int choice = 0;
			try {
				choice = Integer.parseInt(m_gui.getAnswer());

				/*
				 * if user chooses 1 - Yes
				 */
				if (choice == 1) {
					m_gui.loadFrameView();
					m_gui.addSubmitLoadListener(new SubmitLoadListener());
				}

				/*
				 * if user chooses 2 - No
				 */
				else if (choice == 2) {
					MenuTest menuview = new MenuTest();
					m_menu = menuview;
					m_gui.closeFrame();
					m_menu.addSubmitListener(new SubmitListener());
					m_menu.addSubmitFinalListener(new SubmitListener1());
					m_menu.BreakDownButtonListener(new BreakDownListener());
					m_menu.DeleteItemListener(new DeleteListener());
					m_menu.RotateItemListener(new RotateListener());
					m_menu.submitChoiceParcel(new ChoiceParcelListener1());
					m_menu.submitChoice(new ChoiceListener());
					m_menu.submitTrack(new TrackListener());
					m_menu.AdminListener(new AdminListener());
				} else {
					m_gui.showMessage("There's an error in your input.");
				}
			} catch (Exception err) {
				String error = "Something's wrong: " + err;
				m_gui.showMessage(error);
			}
		}
	}

	/** Listener for the submit button in load method of Gui class */
	class SubmitLoadListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				Date currentdate = new Date();
				SimpleDateFormat trackdateformat = new SimpleDateFormat("MMdd");
				String sCurrentDate = trackdateformat.format(currentdate);
				String sPrepare = "Preparing";
				String sShipping = "Shipping";
				String sDelivered = "Delivered";
				String sTrackingStatus;
				File file = new File(m_gui.getLoadAnswer());
				Scanner scanstatus = new Scanner(file);
				String[] loadString = new String[4];
				int counter = 0;

				while (scanstatus.hasNextLine()) {
					String line = scanstatus.nextLine();
					loadString[counter] = line;
					counter++;
				}

				if (sCurrentDate.equals(loadString[0]) == true) {
					sTrackingStatus = sPrepare;
				} else if (sCurrentDate.equals(loadString[1]) == true) {
					sTrackingStatus = sDelivered;
				} else {
					sTrackingStatus = sShipping;
				}

				m_gui.setLoadLabel("<html>Hey " + loadString[2] + "! Here's a summary of your delivery."
						+ "<br>Tracking Number: " + loadString[3] + "<br>Recipient Name: " + loadString[2]
						+ "<br>Date ordered: " + loadString[0] + "<br>Current date: " + sCurrentDate
						+ "<br>Date of arrival: " + loadString[1] + "<br>Status: " + sTrackingStatus + "</html>");
				scanstatus.close();
				m_gui.showLoadSummary();
				m_gui.getLoadFrame().pack();
				m_gui.addProceedLoadListener(new ProceedLoadListener());
			} catch (IOException err) {
				String error = "File doesn't exist! " + err;
				m_gui.showMessage(error);
			}
		}
	}

	/** Listener for the proceed button in load method of Gui class */
	class ProceedLoadListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			m_gui.proceedButton();
		}
	}

	/**
	 * Listener for the submit button that would check if user chooses document or
	 * product
	 */
	class SubmitListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				int items = Integer.parseInt(m_menu.getItems());
				int region = Integer.parseInt(m_menu.getRegion());
				int choice = Integer.parseInt(m_menu.getUserInput());
				m_model.getRecipient().setRegion(region);
				m_model.getRecipient().setItems(items);

				if (choice == 1) {
					m_model.instantiateDocument();
					m_model.getItem().setProductType(false);
					m_menu.setTrue();
					m_menu.Hide();
				} else if (choice == 2) {
					m_model.instantiateProduct();
					m_model.getItem().setProductType(true);
					m_menu.setTrue1();
					m_menu.Hide1();
				} else {
					m_menu.showMessage("Wrong input, try again.");
				}
			} catch (Exception err) {
				String error = "Something's wrong: " + err;
				m_menu.showMessage(error);
			}
		}
	}

	/**
	 * Listener for the submit button that the action is equivalent to scanItem
	 * method in CustomerInterface
	 */
	class SubmitListener1 implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			Checker++;
			String name = m_menu.getName();
			m_model.getRecipient().setName(name);
			int region = Integer.parseInt(m_menu.getRegion());
			int items = Integer.parseInt(m_menu.getItems());
			m_model.getRecipient().setRegion(region);
			m_model.getRecipient().setItems(items);

			int iChecker = 0;
			while (iChecker == 0) {

				if (m_model.getRecipient().getRegion() == 1 || m_model.getRecipient().getRegion() == 2
						|| m_model.getRecipient().getRegion() == 3 || m_model.getRecipient().getRegion() == 4) {
					iChecker++;
				}

				else {
					System.out.println("Region cannot be found, try again.");
				}
			}

			if (m_model.getRecipient().getItems() <= 0) {
				m_menu.showMessage("Input cannot be less than or equal to 0, try again.");
			}

			// For Document
			if (m_model.getItem() instanceof Document == true) {

				int pageNo = Integer.parseInt(m_menu.getPageNo());
				m_model.getItem().setnoPage(pageNo);
				int width = Integer.parseInt(m_menu.getWidth());
				m_model.getItem().setdWidth(width);
				int length = Integer.parseInt(m_menu.getLength());
				m_model.getItem().setdLength(length);

				if (m_model.getItem().getnoPage() < 0 && m_model.getItem().getdWidth() < 0
						&& m_model.getItem().getdLength() < 0) {
					m_menu.showMessage("Wrong input, try again.");
				}

				// Ask customer if to include insurance or not
				int insurance = Integer.parseInt(m_menu.getInsurance());
				m_model.getItem().setInsurance(insurance);

				if (m_model.getItem().getInsurance() != 1 && m_model.getItem().getInsurance() != 2) {
					m_menu.showMessage("Wrong input, try again.");
				}

				double dDocumentHeight = Math.ceil(m_model.getItem().getnoPage() / 25);
				m_model.getItem().setdHeight(dDocumentHeight);
				m_model.addItemToArray(m_model.getItem());
			}

			// For product type of item
			else {
				if (m_model.getItem().getProductType() == true) {
					int width = Integer.parseInt(m_menu.getWidth());
					m_model.getItem().setdWidth(width);
					int length = Integer.parseInt(m_menu.getLength());
					m_model.getItem().setdLength(length);
					int height = Integer.parseInt(m_menu.getHeight());
					m_model.getItem().setdHeight(height); // Customer inputs height of regular product
					int weight = Integer.parseInt(m_menu.getWeight());
					m_model.getItem().setdWeight(weight); // Customer inputs weight of regular product
					if (m_model.getItem().getdWidth() < 0 && m_model.getItem().getdLength() < 0
							&& m_model.getItem().getdWeight() < 0 && m_model.getItem().getdHeight() < 0) {
						m_menu.showMessage("Wrong input, try again.\n");
					}

					// Ask customer if to include insurance or not
					int insurance = Integer.parseInt(m_menu.getInsurance());
					m_model.getItem().setInsurance(insurance);
					if (m_model.getItem().getInsurance() != 1 && m_model.getItem().getInsurance() != 2) {
						m_menu.showMessage("Wrong input, try again.\n");
					}
					m_model.addItemToArray(m_model.getItem());
				}
			}

			m_model.Updater();
			m_model.computeHeight();
			m_model.computeWidth();
			m_model.computeLength();

			if (m_model.computeParcel() == 1) {
				m_menu.setText1();
				parcelPrint = 1;

			} else if (m_model.computeParcel() == 2) {
				m_menu.setText2();
				parcelPrint = 2;

			} else if (m_model.computeParcel() == 3) {
				m_menu.setText3();
				parcelPrint = 3;

			} else if (m_model.computeParcel() == 4) {
				m_menu.setText4();
				parcelPrint = 4;

			} else if (m_model.computeParcel() == 5) {
				m_menu.setText5();
				parcelPrint = 5;

			} else if (m_model.computeParcel() == 6) {
				m_menu.setText6();
				parcelPrint = 6;

			} else if (m_model.computeParcel() == 7) {
				m_menu.setText7();
				parcelPrint = 7;

			} else {
				m_menu.setText8();
				parcelPrint = 8;
			}
			m_menu.UneditInfo();
			m_menu.revertValues1();

			/*
			 * Show content of Item ArrayList
			 */
			if (Checker == m_model.getRecipient().getItems()) {
				String[] itemNames = new String[100];
				String itemName = "<html>";
				for (int i = 0; i < m_model.getCustomerItem().size(); i++) {
					if (m_model.getCustomerItem().get(i) instanceof Document) {
						itemNames[i] = ((i + 1) + ". Document: " + m_model.getCustomerItem().get(i).getdLength() + " x "
								+ m_model.getCustomerItem().get(i).getdWidth());
					} else {
						itemNames[i] = ((i + 1) + ". Product: " + m_model.getCustomerItem().get(i).getdLength() + " x "
								+ m_model.getCustomerItem().get(i).getdWidth() + " x "
								+ m_model.getCustomerItem().get(i).getdHeight());
					}
				}

				for (int i = 0; i < m_model.getCustomerItem().size(); i++) {
					itemName += itemNames[i] + "<br>";
					m_menu.setAvailItems(itemName + "\n");
				}
				itemName += "</html>";
				m_menu.setAvailItems(itemName);
				m_menu.showPanel2();
			}

		}
	}

	/** Listener to show fee breakdown */
	class BreakDownListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int actionChoice = Integer.parseInt(m_menu.getActionChoice());

			/*
			 * This is to choose an action if [1] Rotate [2] Delete [3] Choose a parcel
			 */
			if (actionChoice == 1) {
				m_menu.showRotatePanel();
				m_menu.HideDeletePanel();
				m_menu.HideParcelPanel();
			}
			if (actionChoice == 2) {
				m_menu.showDeletePanel();
				m_menu.HideRotatePanel();
				m_menu.HideParcelPanel();
			}

			if (actionChoice == 3) {
				m_menu.showParcelchoice();
				m_menu.HideDeletePanel();
				m_menu.HideRotatePanel();
			}
		}
	}

	/** Listener for delete action */
	class DeleteListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int iLoop = 0;
			int deleteChoice;

			while (iLoop == 0) {
				deleteChoice = Integer.parseInt(m_menu.getDeleteItem());
				if (m_model.getCustomerItem().size() == 1) {
					m_menu.showMessage("You only have one item, you cannot delete!");
					iLoop++;
				} else if (deleteChoice >= 1 && deleteChoice <= m_model.getCustomerItem().size()) {
					int deleteItem = deleteChoice - 1;
					m_model.DeleteUpdate(deleteItem);
					m_model.getCustomerItem().remove(deleteItem);
					iLoop++;
				} else {
					m_menu.showMessage("Wrong input, try again.");
				}
			}

			m_model.Updater();
			String[] itemNames = new String[100];
			String itemName = "<html>";
			for (int i = 0; i < m_model.getCustomerItem().size(); i++) {
				if (m_model.getCustomerItem().get(i) instanceof Document) {
					itemNames[i] = ((i + 1) + ". Document: " + m_model.getCustomerItem().get(i).getdLength() + " x "
							+ m_model.getCustomerItem().get(i).getdWidth());
				} else {
					itemNames[i] = ((i + 1) + ". Product: " + m_model.getCustomerItem().get(i).getdLength() + " x "
							+ m_model.getCustomerItem().get(i).getdWidth() + " x "
							+ m_model.getCustomerItem().get(i).getdHeight());
				}
			}

			for (int i = 0; i < m_model.getCustomerItem().size(); i++) {
				itemName += itemNames[i] + "<br>";
				m_menu.setAvailItems(itemName + "\n");
			}
			itemName += "</html>";
			m_menu.setAvailItems(itemName);

			m_model.computeLength();
			m_model.computeWidth();
			m_model.computeHeight();
			if (m_model.computeParcel() == 1) {
				m_menu.setText1();
				parcelPrint = 1;

			} else if (m_model.computeParcel() == 2) {
				m_menu.setText2();
				parcelPrint = 2;

			} else if (m_model.computeParcel() == 3) {
				m_menu.setText3();
				parcelPrint = 3;

			} else if (m_model.computeParcel() == 4) {
				m_menu.setText4();
				parcelPrint = 4;

			} else if (m_model.computeParcel() == 5) {
				m_menu.setText5();
				parcelPrint = 5;

			} else if (m_model.computeParcel() == 6) {
				m_menu.setText6();
				parcelPrint = 6;
			} else if (m_model.computeParcel() == 7) {
				m_menu.setText7();
				parcelPrint = 7;
			} else {
				m_menu.setText8();
				parcelPrint = 8;
			}

			itemNames = new String[100];
			itemName = "<html>";
			for (int i = 0; i < m_model.getCustomerItem().size(); i++) {
				if (m_model.getCustomerItem().get(i) instanceof Document) {
					itemNames[i] = ((i + 1) + ". Document: " + m_model.getCustomerItem().get(i).getdLength() + " x "
							+ m_model.getCustomerItem().get(i).getdWidth());
				} else {
					itemNames[i] = ((i + 1) + ". Product: " + m_model.getCustomerItem().get(i).getdLength() + " x "
							+ m_model.getCustomerItem().get(i).getdWidth() + " x "
							+ m_model.getCustomerItem().get(i).getdHeight());
				}
			}

			for (int i = 0; i < m_model.getCustomerItem().size(); i++) {
				itemName += itemNames[i] + "<br>";
				m_menu.setAvailItems(itemName + "\n");
			}
			itemName += "</html>";
			m_menu.setAvailItems(itemName);
			m_menu.HideDeletePanel();
		}
	}

	/** Listener for rotate action */
	class RotateListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			int iLoop = 0;
			int itemChoice;
			while (iLoop == 0) {

				itemChoice = Integer.parseInt(m_menu.getRotateItem());

				if (itemChoice >= 1 && itemChoice <= m_model.getCustomerItem().size()) {
					int item = itemChoice - 1;
					m_model.getCustomerItem().get(item).swap();
					// Call updater method of model
					m_model.Updater();
					iLoop++;
				} else {
					m_menu.showMessage("Wrong input, try again.");
				}
			}

			String[] itemNames = new String[100];
			String itemName = "<html>";
			for (int i = 0; i < m_model.getCustomerItem().size(); i++) {
				if (m_model.getCustomerItem().get(i) instanceof Document) {
					itemNames[i] = ((i + 1) + ". Document: " + m_model.getCustomerItem().get(i).getdLength() + " x "
							+ m_model.getCustomerItem().get(i).getdWidth());
				} else {
					itemNames[i] = ((i + 1) + ". Product: " + m_model.getCustomerItem().get(i).getdLength() + " x "
							+ m_model.getCustomerItem().get(i).getdWidth() + " x "
							+ m_model.getCustomerItem().get(i).getdHeight());
				}
			}

			for (int i = 0; i < m_model.getCustomerItem().size(); i++) {
				itemName += itemNames[i] + "<br>";
				m_menu.setAvailItems(itemName + "\n");
			}
			itemName += "</html>";
			m_menu.setAvailItems(itemName);

			// Recalculating of dimensions
			m_model.computeLength();
			m_model.computeWidth();
			m_model.computeHeight();
			if (m_model.computeParcel() == 1) {
				m_menu.setText1();
				parcelPrint = 1;

			} else if (m_model.computeParcel() == 2) {
				m_menu.setText2();
				parcelPrint = 2;

			} else if (m_model.computeParcel() == 3) {
				m_menu.setText3();
				parcelPrint = 3;

			} else if (m_model.computeParcel() == 4) {
				m_menu.setText4();
				parcelPrint = 4;

			} else if (m_model.computeParcel() == 5) {
				m_menu.setText5();
				parcelPrint = 5;

			} else if (m_model.computeParcel() == 6) {
				m_menu.setText6();
				parcelPrint = 6;
			} else if (m_model.computeParcel() == 7) {
				m_menu.setText7();
				parcelPrint = 7;
			} else {
				m_menu.setText8();
				parcelPrint = 8;
			}

			m_model.Updater();
			itemNames = new String[100];
			itemName = "<html>";
			for (int i = 0; i < m_model.getCustomerItem().size(); i++) {
				if (m_model.getCustomerItem().get(i) instanceof Document) {
					itemNames[i] = ((i + 1) + ". Document: " + m_model.getCustomerItem().get(i).getdLength() + " x "
							+ m_model.getCustomerItem().get(i).getdWidth());
				} else {
					itemNames[i] = ((i + 1) + ". Product: " + m_model.getCustomerItem().get(i).getdLength() + " x "
							+ m_model.getCustomerItem().get(i).getdWidth() + " x "
							+ m_model.getCustomerItem().get(i).getdHeight());
				}
			}

			for (int i = 0; i < m_model.getCustomerItem().size(); i++) {
				itemName += itemNames[i] + "<br>";
				m_menu.setAvailItems(itemName + "\n");
			}
			itemName += "</html>";
			m_menu.setAvailItems(itemName);

			m_menu.HideRotatePanel();
		}

	}

	/** Listener for choosing of parcel */
	class ChoiceParcelListener1 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int iLoop = 0;
			int getParcel;
			getParcel = Integer.parseInt(m_menu.getParcelChoice());

			if (parcelPrint == 1) {
				if (getParcel > 0 && getParcel < 7) {
					if (getParcel > 0 && getParcel < 3) {
						temp_parcel = new Flat();
						m_model.getParcel().add(temp_parcel);
						m_model.getParcel().get(m_model.getParcelNo()).setParcelType(getParcel);
					} else {
						temp_parcel = new Box();
						m_model.getParcel().add(temp_parcel);
						m_model.getParcel().get(m_model.getParcelNo()).setParcelType(getParcel);
					}
					iLoop++;
				} else {
					m_menu.showMessage("That parcel cannot be used, choose again.");
				}
			}

			else if (parcelPrint == 2) {
				if (getParcel > 1 && getParcel < 7) {
					if (getParcel == 2) {
						temp_parcel = new Flat();
						m_model.getParcel().add(temp_parcel);
						m_model.getParcel().get(m_model.getParcelNo()).setParcelType(getParcel);
					} else {
						temp_parcel = new Box();
						m_model.getParcel().add(temp_parcel);
						m_model.getParcel().get(m_model.getParcelNo()).setParcelType(getParcel);
					}
					iLoop++;
				} else {
					m_menu.showMessage("That parcel cannot be used, choose again.");

				}
			}

			else if (parcelPrint == 3) {
				if (getParcel > 2 && getParcel < 7) {
					temp_parcel = new Box();
					m_model.getParcel().add(temp_parcel);
					m_model.getParcel().get(m_model.getParcelNo()).setParcelType(getParcel);
					iLoop++;
				} else {
					m_menu.showMessage("That parcel cannot be used, choose again.");
				}
			}

			else if (parcelPrint == 4) {
				if (getParcel > 3 && getParcel < 7) {
					temp_parcel = new Box();
					m_model.getParcel().add(temp_parcel);
					m_model.getParcel().get(m_model.getParcelNo()).setParcelType(getParcel);
					iLoop++;
				} else {
					m_menu.showMessage("That parcel cannot be used, choose again.");
				}
			}

			else if (parcelPrint == 5) {
				if (getParcel > 4 && getParcel < 7) {
					temp_parcel = new Box();
					m_model.getParcel().add(temp_parcel);
					m_model.getParcel().get(m_model.getParcelNo()).setParcelType(getParcel);
					iLoop++;
				} else {
					m_menu.showMessage("That parcel cannot be used, choose again.");
				}
			}

			else if (parcelPrint == 6) {
				if (getParcel > 5 && getParcel < 7) {
					temp_parcel = new Box();
					m_model.getParcel().add(temp_parcel);
					m_model.getParcel().get(m_model.getParcelNo()).setParcelType(getParcel);
					iLoop++;
				} else {
					m_menu.showMessage("That parcel cannot be used, choose again.");
				}
			}

			m_menu.HideParcelPanel();
			if (iLoop > 0) {
				m_model.getParcel().get(m_model.getParcelNo()).getTracker().TrackingNumber(m_model.getRecipient(),
						m_model.getShippingSequence());
				m_menu.setbreakD("<html><br>[Fee Breakdown]" + "<br>Destination (" + m_model.getDestination()
						+ "): PHP " + m_model.computeDestFee() + "<br>Parcel and Weight: PHP " + m_model.computeFee()
						+ "<br>Insurance: PHP " + m_model.computeInsurace() + "<br>Total: PHP "
						+ (m_model.computeDestFee() + m_model.computeFee() + m_model.computeInsurace())
						+ "<br>Tracking Number: "
						+ (m_model.getParcel().get(m_model.getParcelNo()).getTracker().getTrackingNumber())
						+ "<br><br> Save file automatically created!");
				try {
					m_model.CreateSaveFile();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				m_menu.showbreakDPanel();
				m_menu.showBreakdown();
				m_menu.shownextMovePanel();
				m_menu.showPickChoice();
			}
		}
	}

	/** Listener for tracking action */
	class TrackListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String trackNum = m_menu.getTrackNum();
			int trackCheck = 0;
			for (int i = 0; i < m_model.getParcel().size(); i++) {
				if (trackNum.equals(m_model.getParcel().get(i).getTracker().getTrackingNumber())) {
					m_model.getParcel().get(i).getTracker().TrackingNumberStatus(i);
					m_menu.setTrackInfo("<html>[Tracking Number: " + trackNum + "]" + "<br>Recipient Name: "
							+ m_model.getParcel().get(i).getTracker().getDateEssentials(2) + "<br>Date Ordered: "
							+ m_model.getParcel().get(i).getTracker().getDateEssentials(0) + "<br>Current Date: "
							+ m_model.getParcel().get(i).getTracker().getCurrentDate() + "<br>Date of Arrival: "
							+ m_model.getParcel().get(i).getTracker().getDateEssentials(1) + "<br>Shipping Status: "
							+ m_model.getParcel().get(i).getTracker().getShippingStat());
					m_menu.showtrackInfo();
					trackCheck++;
				}
			}

			if (trackCheck == 0) {
				JOptionPane.showMessageDialog(null, "Tracking number cannot be found");
				m_menu.TrackInfoReset();
			}
		}
	}

	/** Class for next action [1] Have new transaction [2] Track Parcel [3] Exit */
	class ChoiceListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int choice = Integer.parseInt(m_menu.getNextChoice());

			if (choice == 1) {
				Checker = 0;
				m_model.setSaveNumber(m_model.getSaveNumber() + 1);
				m_model.setParcelNo(m_model.getParcelNo() + 1);
				m_model.setShippingSequence(m_model.getShippingSequence() + 1);
				m_menu.HidePanel2();
				m_menu.revertValues2();
				m_model.instantiatenewCustomer();
				m_model.instantiatenewCustItem();
			}

			else if (choice == 2) {
				m_menu.showtrackNumPanel();
				m_menu.HideExitPanel();
			}

			else if (choice == 3) {
				m_menu.showUserPanel();
				m_menu.HidetrackNumPanel();
				m_menu.hidetrackInfo();
				m_menu.showPasswordPanel();
			}
		}
	}

	/** Listener for verifying inputted username and password */
	class AdminListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			/*
			 * You can change username and password here
			 */
			String user = "user";
			String pass = "pass";
			String username = m_menu.getUsername();
			String password = m_menu.getPassword();
			m_model.setUsername(username);
			m_model.setPassword(password);

			System.out.println(username);
			System.out.println(password);

			if (user.equals(m_model.getUsername()) == true && pass.equals(m_model.getPassword()) == true) {
				m_menu.showMessage("Thank you for using Johnny Moves!");
				m_menu.returnFrame().setVisible(false);
				System.exit(0);
			} else {
				m_menu.showMessage("Wrong input!");
				m_menu.HideAdmin();
			}
		}
	}
}
