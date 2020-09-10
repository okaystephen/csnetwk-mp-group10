import java.util.*;
import java.io.*;
import java.text.*;

/** This is our Customer Interface class
 * @author SALAMANTE, Stephen E.
 * @author CARANDANG, Pauline
 * 
 * This class handles most of the interaction with
 * the customer (e.g. taking order).
*/
public class CustomerInterface{

	private Scanner scan;
	private ArrayList<Parcel> parcel;
	private Parcel temp_parcel;
	private ArrayList<CustomerItem> customeritem; 
	private CustomerItem item;
	private Recipient customer; 
	private int parcelNo = 0;
	private double dWeight = 0;
	private double dLength = 0;
	private double dHeight = 0;
	private double dWidth = 0;
	private int iSentinelCheck;
	private int parcelPrint;
	private int saveNumber = 0;
	private int sSequence = 1;
	private int parcel_print;
	private String username;
	private String password;

	/** Instantiate Scanner class and Parcel ArrayList */
    public CustomerInterface(){
		scan = new Scanner(System.in);
		parcel = new ArrayList<Parcel>();
		customer = new Recipient();
		customeritem = new ArrayList<CustomerItem>();
	}
	
	/** Getter for username */
    public String getUsername() {
    	return username;
    }

	/** Setter for username */
    public void setUsername(String username) {
    	this.username = username;
    }
	
	/** Getter for password */
    public String getPassword() {
    	return password;
    }
	
	/** Setter for password */
    public void setPassword(String password) {
    	this.password = password;
	}
	
	/** Getter for parcel print */
	public int getParcelPrint(){
		return parcelPrint;
	}
	
	/** Setter for parcel print */
	public void setParcelPrint(int parcel_print) {
		this.parcel_print = parcelPrint;
	}

	/** Setter for save data number */
	public void setSaveNumber(int saveNumber){
		this.saveNumber = saveNumber;
	}

	/** Getter for save data number */
	public int getSaveNumber(){
		return saveNumber;
	}

	/** Setter for shipping sequence */
	public void setShippingSequence(int sSequence){
		this.sSequence = sSequence;
	}

	/** Getter for shipping sequence */
	public int getShippingSequence(){
		return sSequence;
	}

	/** Getter for recipient */
	public Recipient getRecipient(){
		return customer;
	}

	/** Getter for item */
	public CustomerItem getItem(){
		return item;
	}

	/** Getter for customer item arraylist */
	public ArrayList<CustomerItem> getCustomerItem(){
		return customeritem;
	}
	
	/** Getter for parcel arraylist */
	public ArrayList<Parcel> getParcel(){
		return parcel;
	}

	/** Getter for current number of parcel */
	public int getParcelNo(){
		return parcelNo;
	}
	
	/** Setter for current number of parcel */
	public void setParcelNo(int parcel) {
		this.parcelNo = parcel;
	}

	/** Instantiates a document type of item */
	public void instantiateDocument(){
		item = new Document();
	}

	/** Instantiates a product type of item */
	public void instantiateProduct(){
		item = new Product();
	}

	/** Adds item into customer item arraylist */
	public void addItemToArray(CustomerItem item){
		customeritem.add(item);
	}
	
	/** Returns temporary parcel */
	public Parcel getTempParcel() {
		return temp_parcel;
	}

	/** Instantiates a new recipient */
	public void instantiatenewCustomer() {
		customer = new Recipient();
	}

	/** Instantiates a new customer item arraylist */
	public void instantiatenewCustItem() {
		customeritem = new ArrayList<CustomerItem>();
	}
	
	/** A Welcome message for the customer */
	public int Welcome(){
		int nCheck;
		int nLoop = 0;
		int nValue = 0;
		System.out.println("Welcome to Johnny Moves.\n");
		System.out.println("Johnny Moves is a courier company, whose innovation is to allow self-check");
		System.out.println("out of parcels for delivery to their partner sites or branches.\n");
		
		/** Customer will be prompted if they want to proceed in ordering
		 * 1. If customer says yes [1], the program proceeds.
		 * 2. If the customer says no [2], then the program will terminate
		 * 3. If the customer inputs neither of the two, the the program will print an error
		 *    message then loop back into asking the customer for an input.
		 */
		while (nLoop == 0){
			System.out.print("Would you like to proceed? ([1] Yes [2] No): ");
			nCheck = scan.nextInt();
			if (nCheck == 1){
				nLoop++;
				if(Load() == 1){
					LoadFile();
					nValue = 0;
				} else {
					nValue = 1;
				}
			}
			else if (nCheck == 2){
				System.out.println("\nThank you for using Johnny Moves.\n");
				nLoop++;
				nValue = 0;
			}
			else{
				System.out.println("\nInvalid input, try again.\n");
			}
		}
		return nValue;
	}

	/** Ask user whether they want to load a save file or not */
	public int Load(){
		int nCheck = 0;
		int nLoop = 0;
		while (nLoop == 0){
			System.out.print("\nWould you like to load a saved progress? ([1] Yes [2] No): ");
			nCheck = scan.nextInt();
			if (nCheck == 1){
				nLoop++;
			}
			else if (nCheck == 2){
				System.out.println("\nOkay then, let's proceed.\n");
				nLoop++;
			}
			else{
				System.out.println("\nInvalid input, try again.\n");
			}
		}
		return nCheck;
	}

	/** Used to load a save file */
	public void LoadFile(){
		String loadFile;
		Scanner scanstatus;
		boolean done = false;
		String[] loadString = new String[4];
		int choiceCheck = 0;
		Date currentdate = new Date();
		SimpleDateFormat trackdateformat = new SimpleDateFormat("MMdd");  
		String sCurrentDate = trackdateformat.format(currentdate);
		String sPrepare = "Preparing";
		String sShipping = "Shipping";
		String sDelivered = "Delivered";
		String sTrackingStatus;

		while (!done){
			int counter = 0;
			boolean checker = false;
			try {
				System.out.print("\nEnter filename with extension of save file: ");
				loadFile = (scan.next() + scan.nextLine());
				File file = new File(loadFile);
				scanstatus = new Scanner(file);
				while (scanstatus.hasNextLine()) {
					String line = scanstatus.nextLine();
					loadString[counter] = line;
					counter++;
				}

				if (sCurrentDate.equals(loadString[0]) == true){
					sTrackingStatus = sPrepare;
				}
				else if (sCurrentDate.equals(loadString[1]) == true){
					sTrackingStatus = sDelivered;
				}
				else{
					sTrackingStatus = sShipping;
				}

				System.out.println("\nReading save file ...\n");
				System.out.println("Hey " + loadString[2] +"! Here's a summary of your delivery.\n");
				System.out.println("Tracking Number: " + loadString[3]);
				System.out.println("Recipient Name: " + loadString[2]);
				System.out.println("Date ordered: " + loadString[0]);
				System.out.println("Current date: " + sCurrentDate);
				System.out.println("Date of arrival: " + loadString[1]);
				System.out.println("Status: " + sTrackingStatus);

				while(!checker){
					System.out.print("\nWould you like to load another save file? ([1] Yes [2] No): ");
					choiceCheck = scan.nextInt();
					if (choiceCheck == 1){
						checker = true;
					} else if (choiceCheck == 2){
						System.out.println("\nThank you for using Johnny Moves!");
						checker = true;
						done = true;
						scanstatus.close();
					} else {
						System.out.println("\nWrong input! Try again.");
					}
				}

				
			} catch (IOException e) {
				System.out.println("\nFile doesn't exist, try again!");
				System.err.format("IOException: %s%n", e);
			}
		}
		
		
	}

	/** Display the regions with their respective date of arrival and rate */
	public void DisplayRates(){
		System.out.printf("\n%-1s%22s%13s\n", "[REGION]", "[DELIVERY]", "[RATE]");
		System.out.printf("%-1s%14s%17s\n", "Metro Manila", "2 Days", "PHP 50");
		System.out.printf("%-1s%21s%18s\n", "Luzon", "3 Days", "PHP 100");
		System.out.printf("%-1s%19s%50s\n", "Visayas", "5 Days", "PHP 1000 or 10% of the weight or volume");
		System.out.printf("%-1s%18s%46s\n\n", "Mindanao", "8 Days", "PHP 3000 or 25% of weight or volume");
	}

	/** Returns string of corresponding destination of parcel */
	public String getDestination(){
		if (customer.getRegion() == 1){
			return "Metro Manila";
		}
		else if (customer.getRegion() == 2){
			return "Luzon";
		}
		else if (customer.getRegion() == 3){
			return "Visayas";
		}
		else{
			return "Mindanao";
		}
	}

	/** Scans and set inputted data to CustomerItem ArrayList */
	public void scanItem(){

		DisplayRates();
		System.out.println("Parcel Number: " + (this.getParcelNo() + 1) + "\n");

		// Instantiate a Recipient class
		customer = new Recipient();

		// Instantiate CustomerItem ArrayList 
		customeritem = new ArrayList<CustomerItem>();
		
		// Ask customer for recipient's name
		System.out.print("Enter recipient name: ");
		customer.setName(scan.next() + scan.nextLine());
		
		int iChecker = 0;
		int iInsuranceChecker = 0;
		String sRegion1 = "Metro Manila";
		String sRegion2 = "Luzon";
		String sRegion3 = "Visayas";
		String sRegion4 = "Mindanao";
		
		// Prints list of regions and ask customer for input from the list
		while (iChecker == 0){
			System.out.println("\nList of Regions:");
			System.out.println("[1]: " + sRegion1);
			System.out.println("[2]: " + sRegion2);
			System.out.println("[3]: " + sRegion3);
			System.out.println("[4]: " + sRegion4);
			System.out.print("Enter a region: ");
			customer.setRegion(scan.nextInt());

			if(customer.getRegion() == 1 || customer.getRegion() == 2 || customer.getRegion() == 3 || customer.getRegion() == 4){
				iChecker++;
			}

			else{
				System.out.println("Region cannot be found, try again.");
			}
		}

		// Ask customer to input amount of items to be delivered
		iChecker = 0;
        while(iChecker == 0) {
			System.out.print("\nEnter number of items: ");
			customer.setItems(scan.nextInt());
			if(customer.getItems() <= 0) {
				System.out.println("Input cannot be less than or equal to 0, try again.");
			}
			else {
				iChecker++;
			}
		}
		
		for (int i = 0; i < customer.getItems(); i++){
			// Ask customer for inputs regarding item
			System.out.println("\n[Item Number " + (i+1) + "]");

			/** 1. If customer inputs [1], item type will be document
			 * 	2. If customer inputs [2], item type will be product
			 *  3. Else, it will ask user to input again.
			 */
			iChecker = 0;
			while (iChecker == 0){
				int itemChoice;
				System.out.print("Enter choice of item ([1] Document [2] Product): ");	
				itemChoice = scan.nextInt();
				if (itemChoice == 1){
					item = new Document();
					iChecker++;
				} else if (itemChoice == 2){
					item = new Product();
					iChecker++;
				} else {
					System.out.println("Wrong input, try again.\n");
				}
			}

			// For document type of item
			iChecker = 0;
			if(item instanceof Document == true){
				while(iChecker==0) {
				System.out.print("Enter no. of pages: ");
				item.setnoPage(scan.nextInt());		// Customer inputs number of pages of document
				System.out.print("Enter width (in inches): ");
				item.setdWidth(scan.nextDouble());	// Customer inputs width of document
				System.out.print("Enter length (in inches): ");
				item.setdLength(scan.nextDouble());	// Customer inputs length of document
				
				if (item.getnoPage() > 0 && item.getdWidth() > 0 && item.getdLength() > 0) {
					iChecker++;
				}
				else {
					System.out.println("\nThere's something wrong with your input, try again.\n");
				}
			}
				while (iInsuranceChecker == 0){
					// Ask customer if to include insurance or not
					System.out.print("Add insurance ([1] Yes [2] No): ");
					item.setInsurance(scan.nextInt());
					if (item.getInsurance() != 1 && item.getInsurance() != 2){
						System.out.println("\nThere's something wrong with your input, try again.\n");
					}
					else{
						iInsuranceChecker++;
					}
				}
				iInsuranceChecker = 0;
				double dDocumentHeight = Math.ceil(item.getnoPage() / 25);
				item.setdHeight(dDocumentHeight);
				customeritem.add(item);
			}
		
			// For product type of item
			else {
				/** 1. If customer inputs [1], product type will be regular
			 	* 	2. If customer inputs [2], product type will be irregular
			 	*   3. Else, it will ask user to input again.
				 */
				iChecker = 0;
				while (iChecker == 0){
					int itemChoice = 0;
					System.out.print("Enter choice of item ([1] Document [2] Product): ");	
					itemChoice = scan.nextInt() ;

					if (itemChoice == 1){
						item.setProductType(true);
						iChecker++;
					} else if (itemChoice == 2){
						item.setProductType(false);
						iChecker++;
					} else {
						System.out.println("Wrong input, try again.\n");
					}
				}

				// For regular product
				iChecker = 0;
				if(item.getProductType() == true){
					while(iChecker == 0){
					System.out.print("Enter width (in inches): ");
					item.setdWidth(scan.nextDouble());	// Customer inputs width of regular product
					System.out.print("Enter length (in inches): ");
					item.setdLength(scan.nextDouble());	// Customer inputs length of regular product
					System.out.print("Enter height (in inches): ");
					item.setdHeight(scan.nextDouble());	// Customer inputs height of regular product
					System.out.print("Enter weight (in kilos): ");
					item.setdWeight(scan.nextDouble());	// Customer inputs weight of regular product
						if(item.getdWidth() >0 && item.getdLength() >0 && item.getdWeight() >0 && item.getdHeight() >0) {
							iChecker++;
						} else {
						System.out.println("\nThere's something wrong with your input, try again.\n");
					}
				}

					while (iInsuranceChecker == 0){
						// Ask customer if to include insurance or not
						System.out.print("Add insurance ([1] Yes [2] No): ");
						item.setInsurance(scan.nextInt());
						if (item.getInsurance() != 1 && item.getInsurance() != 2){
							System.out.println("\nThere's something wrong with your input, try again.\n");
						} else{
							iInsuranceChecker++;
						}
					}
					iInsuranceChecker = 0;
					customeritem.add(item);
				}
			
				// For irregular product
				else {
					iChecker = 0;
					while(iChecker==0) {
					System.out.print("Enter largest measure for width (in inches): ");
					item.setdWidth(scan.nextDouble());	// Customer inputs width of irregular product
					System.out.print("Enter largest measure for length (in inches): ");
					item.setdLength(scan.nextDouble());	// Customer inputs length of irregular product
					System.out.print("Enter largest measure for height (in inches): ");
					item.setdHeight(scan.nextDouble());	// Customer inputs height of irregular product
					System.out.print("Enter weight (in kilos): ");
					item.setdWeight(scan.nextDouble());	// Customer inputs weight of irregular product

						if(item.getdWidth() >0 && item.getdLength() >0 && item.getdWeight() >0 && item.getdHeight() >0) {
							iChecker++;
						} else {
							System.out.println("\nThere's something wrong with your input, try again.\n");
						}
					}

					while (iInsuranceChecker == 0){
						// Ask customer if to include insurance or not
						System.out.print("Add insurance ([1] Yes [2] No): ");
						item.setInsurance(scan.nextInt());
						if (item.getInsurance() != 1 && item.getInsurance() != 2){
							System.out.println("\nThere's something wrong with your input, try again.\n");
						} else{
							iInsuranceChecker++;
						}
					}
					iInsuranceChecker = 0;
					customeritem.add(item);
				}
			}

		}
		Updater();
	}

	/** Display the available parcels based on the customer's items,
	 *  and will ask customer to input which parcel they want
	 */
	public void scanParcel(){
		int iLoop = 0;
		int actionChoice;
		
		while (iLoop == 0){
			System.out.println("\nHere are the available parcels: ");
			printParcel();
			System.out.println("Note: [X] means unavailable.\n");

			System.out.println("Here are your items:");
			for (int i = 0; i < customeritem.size(); i++){
				if (customeritem.get(i) instanceof Document){
					System.out.println((i+1) +". Document: " + customeritem.get(i).getdLength() + " x " + customeritem.get(i).getdWidth());
				} else{
					System.out.println((i+1) +". Product: " + customeritem.get(i).getdLength() + " x " + customeritem.get(i).getdWidth() + " x " + customeritem.get(i).getdHeight());
				}
			}
			System.out.println();

			System.out.println("Actions: ");
			System.out.println("[1] Rotate an item");
			System.out.println("[2] Delete an item");
			System.out.println("[3] Choose a parcel\n");
			System.out.print("Choose an action: ");
			actionChoice = scan.nextInt();
			
			if (actionChoice == 1){
				RotateItem();
			} else if(actionChoice == 2){
				DeleteItem();
			} else if (actionChoice == 3){
				VerifyParcel();
				iLoop++;
			} else{
				System.out.println("Wrong input, try again.\n");
			}
		}
	}

	/** Delete an item from customer item arraylist */
	public void DeleteItem(){
		int iLoop = 0;
		int deleteChoice;

		while (iLoop == 0){
			System.out.print("Which item would you like to delete: ");
			deleteChoice = scan.nextInt();
			if (customeritem.size() == 1){
				System.out.println("\nYou only have one item, you cannot delete!");
				iLoop++;
			} else if (deleteChoice >= 1 && deleteChoice <= customeritem.size()){
				int deleteItem = deleteChoice - 1;
				DeleteUpdate(deleteItem);
				customeritem.remove(deleteItem);
				iLoop++;
			} else{
				System.out.println("Wrong input, try again.\n");
			}
			
		}
		
	}

	/** Updater when an item is deleted */
	public void DeleteUpdate(int deleteItem){
		dLength -= customeritem.get(deleteItem).getdLength();
		dWidth -= customeritem.get(deleteItem).getdWidth();
		dHeight -= customeritem.get(deleteItem).getdHeight();
		dWeight -= customeritem.get(deleteItem).getdWeight();
	}

	/** Rotate an item's dimension */
	public void RotateItem(){
		int iLoop = 0;
		int itemChoice;
		while (iLoop == 0){
			System.out.print("Which item are you going to rotate: ");
			itemChoice = scan.nextInt();

			if (itemChoice >= 1 && itemChoice <= customeritem.size()){
				int item = itemChoice - 1;
				customeritem.get(item).swap();
				Updater();
				iLoop++;
			} else{
				System.out.println("\nWrong input, try again.\n");
			}
		}
	}

	/** Updater for new item's dimension */
	public void Updater(){
		dLength = 0;
		dWidth = 0;
		dHeight = 0;
		dWeight = 0;

		for (int i = 0; i < customeritem.size(); i++){
			dLength += customeritem.get(i).getdLength();
			dWidth += customeritem.get(i).getdWidth();
			dHeight += customeritem.get(i).getdHeight();
			dWeight += customeritem.get(i).getdWeight();
		}
	}

	/** Error-checking when a customer chooses a parcel */
	public void VerifyParcel(){
		int iLoop = 0;
		int getParcel;
		while (iLoop == 0){
			System.out.print("Choose a parcel: ");
			getParcel = scan.nextInt();
			System.out.println();
			
			if (getParcelPrint() == 1){
				if (getParcel > 0 && getParcel < 8){
					if (getParcel > 0 && getParcel < 4){
						temp_parcel = new Flat();
						parcel.add(temp_parcel);
						parcel.get(parcelNo).setParcelType(getParcel);
					} else{
						temp_parcel = new Box();
						parcel.add(temp_parcel);
						parcel.get(parcelNo).setParcelType(getParcel);
					}
					iLoop++;
				}
				else{
					System.out.println("That parcel cannot be used, choose again.");
				}
			}

			else if (getParcelPrint() == 2){
				if (getParcel > 1 && getParcel < 8){
					if (getParcel > 1 && getParcel < 4){
						temp_parcel = new Flat();
						parcel.add(temp_parcel);
						parcel.get(parcelNo).setParcelType(getParcel);
					} else{
						temp_parcel = new Box();
						parcel.add(temp_parcel);
						parcel.get(parcelNo).setParcelType(getParcel);
					}
					iLoop++;
				}
				else{
					System.out.println("That parcel cannot be used, choose again.");
				}
			}

			else if (getParcelPrint() == 3){
				if (getParcel > 2 && getParcel < 8){
					if (getParcel == 3){
						temp_parcel = new Flat();
						parcel.add(temp_parcel);
						parcel.get(parcelNo).setParcelType(getParcel);
					} else{
						temp_parcel = new Box();
						parcel.add(temp_parcel);
						parcel.get(parcelNo).setParcelType(getParcel);
					}
					iLoop++;
				}
				else{
					System.out.println("That parcel cannot be used, choose again.");
				}
			}

			else if (getParcelPrint() == 4){
				if (getParcel > 3 && getParcel < 8){
					temp_parcel = new Box();
					parcel.add(temp_parcel);
					parcel.get(parcelNo).setParcelType(getParcel);
					iLoop++;
				}
				else{
					System.out.println("That parcel cannot be used, choose again.");
				}
			}

			else if (getParcelPrint() == 5){
				if (getParcel > 4 && getParcel < 8){
					temp_parcel = new Box();
					parcel.add(temp_parcel);
					parcel.get(parcelNo).setParcelType(getParcel);
					iLoop++;
				}
				else{
					System.out.println("That parcel cannot be used, choose again.");
				}
			}

			else if (getParcelPrint() == 6){
				if (getParcel > 5 && getParcel < 8){
					temp_parcel = new Box();
					parcel.add(temp_parcel);
					parcel.get(parcelNo).setParcelType(getParcel);
					iLoop++;
				}
				else{
					System.out.println("That parcel cannot be used, choose again.");
				}
			}

			else if (getParcelPrint() == 7){
				if (getParcel > 6 && getParcel < 8){
					temp_parcel = new Box();
					parcel.add(temp_parcel);
					parcel.get(parcelNo).setParcelType(getParcel);
					iLoop++;
				}
				else{
					System.out.println("That parcel cannot be used, choose again.");
				}
			}

			else{
				System.out.println("You'll need to remove an item.");
				DeleteItem();
				iLoop++;
			}
		}
	}

	/** Print list of parcels that's available for the customer */
	public void printParcel(){
		if (computeParcel() == 1) {
			System.out.println("[1] Flat - 4 x 6 inches");
			System.out.println("[2] Flat - 9 x 14 inches");
			System.out.println("[3] Flat - 12 x 18 inches");
			System.out.println("[4] Box - 12 x 10 x 5 inches");
			System.out.println("[5] Box - 14 x 11 x 7 inches");
			System.out.println("[6] Box - 18 x 12 x 9 inches");
			System.out.println("[7] Box - 20 x 16 x 12 inches");
			parcelPrint = 1;
		}

		else if (computeParcel() == 2){
			System.out.println("[X] Flat - 9 x 14 inches");
			System.out.println("[2] Flat - 12 x 18 inches");
			System.out.println("[3] Box - 12 x 10 x 5 inches");
			System.out.println("[4] Box - 14 x 11 x 7 inches");
			System.out.println("[5] Box - 18 x 12 x 9 inches");
			System.out.println("[6] Box - 20 x 16 x 12 inches");
			parcelPrint = 2;
		}

		else if (computeParcel() == 3){
			System.out.println("[X] Flat - 9 x 14 inches");
			System.out.println("[X] Flat - 12 x 18 inches");
			System.out.println("[3] Box - 12 x 10 x 5 inches");
			System.out.println("[4] Box - 14 x 11 x 7 inches");
			System.out.println("[5] Box - 18 x 12 x 9 inches");
			System.out.println("[6] Box - 20 x 16 x 12 inches");
			parcelPrint = 3;
		}

		else if (computeParcel() == 4){
			System.out.println("[X] Flat - 9 x 14 inches");
			System.out.println("[X] Flat - 12 x 18 inches");
			System.out.println("[X] Box - 12 x 10 x 5 inches");
			System.out.println("[4] Box - 14 x 11 x 7 inches");
			System.out.println("[5] Box - 18 x 12 x 9 inches");
			System.out.println("[6] Box - 20 x 16 x 12 inches");
			parcelPrint = 4;
		}

		else if (computeParcel() == 5){
			System.out.println("[X] Flat - 9 x 14 inches");
			System.out.println("[X] Flat - 12 x 18 inches");
			System.out.println("[X] Box - 12 x 10 x 5 inches");
			System.out.println("[X] Box - 14 x 11 x 7 inches");
			System.out.println("[5] Box - 18 x 12 x 9 inches");
			System.out.println("[6] Box - 20 x 16 x 12 inches");
			parcelPrint = 5;
		}

		else if (computeParcel() == 6){
			System.out.println("[X] Flat - 9 x 14 inches");
			System.out.println("[X] Flat - 12 x 18 inches");
			System.out.println("[X] Box - 12 x 10 x 5 inches");
			System.out.println("[X] Box - 14 x 11 x 7 inches");
			System.out.println("[X] Box - 18 x 12 x 9 inches");
			System.out.println("[6] Box - 20 x 16 x 12 inches");
			System.out.println("[7] Flat - 4 x 6 inches");
			parcelPrint = 6;
		}

		else if (computeParcel() == 7){
			System.out.println("[X] Flat - 9 x 14 inches");
			System.out.println("[X] Flat - 12 x 18 inches");
			System.out.println("[X] Box - 12 x 10 x 5 inches");
			System.out.println("[X] Box - 14 x 11 x 7 inches");
			System.out.println("[X] Box - 18 x 12 x 9 inches");
			System.out.println("[X] Box - 20 x 16 x 12 inches");
			System.out.println("[7] Flat - 4 x 6 inches");
			parcelPrint = 7;
		}

		else{
			System.out.println("[X] Flat - 9 x 14 inches");
			System.out.println("[X] Flat - 12 x 18 inches");
			System.out.println("[X] Box - 12 x 10 x 5 inches");
			System.out.println("[X] Box - 14 x 11 x 7 inches");
			System.out.println("[X] Box - 18 x 12 x 9 inches");
			System.out.println("[X] Box - 20 x 16 x 12 inches");
			System.out.println("[X] Flat - 4 x 6 inches");
			parcelPrint = 8;
		}
	}

	/** Computes total actual weight of all items */
	public double computeWeight(){
		double dWeight = 0;
		double dGram = 0;
		double dSheets = 25;
		double dComputedPage;
		for (int i = 0; i < customeritem.size(); i++){
			if (customeritem.get(i) instanceof Document == true){
				dComputedPage = customeritem.get(i).getnoPage();
				dGram = Math.ceil((dComputedPage / dSheets) * 0.2); 
				dWeight += dGram;
			}

			else{
				dWeight += customeritem.get(i).getdWeight();
			}
		}
		return Math.ceil(dWeight);
	}

	/** Computes volume of items that are of product type */
	public double computeVolume(){
		double dVolume = 0;
		for (int i = 0; i < customeritem.size(); i++){
			if (customeritem.get(i) instanceof Product == true){
				dVolume += (customeritem.get(i).getdLength() * customeritem.get(i).getdWidth() * customeritem.get(i).getdHeight() / 305);
			}
		}
		return Math.ceil(dVolume);
	}

	/** Computes volume of items using formula for destination */
	public double computeVolumeDest(){
		double dVolume = 0;
		for (int i = 0; i < customeritem.size(); i++){
			if (customeritem.get(i) instanceof Product == true){
				dVolume += (customeritem.get(i).getdLength() * customeritem.get(i).getdWidth() * customeritem.get(i).getdHeight());
			}
		}
		return Math.ceil(dVolume);
	}

	/** Looks for the longest length in all the items,
	 * 	then returns the possible corresponding parcel
	 */
	public int computeLength(){
		int nLength = 0;
		for (int i = 0; i < customeritem.size(); i++) {
			if (dLength <= 4) {
				nLength = 1; // Flat Parcel 1
			} 
			else if (dLength <= 9) {
				nLength = 2; // Flat Parcel 1
			} 
			else if (dLength <= 12) {
				nLength = 3; // Flat Parcel 2 
			} 
			else if (dLength <= 12) {
				nLength = 4; // Box Parcel 1
			} 
			else if (dLength <= 14) {
				nLength = 5; // Box Parcel 2
			} 
			else if (dLength <= 18) {
				nLength = 6; // Box Parcel 3
			} 
			else if (dLength <= 20) {
				nLength = 7; // Box Parcel 4
			}
		}
		return nLength;
	}

	/** Looks for the longest width in all the items,
	 * 	then returns the possible corresponding parcel
	 */
	public int computeWidth(){
		int nWidth = 0;
		for (int i = 0; i < customeritem.size(); i++){
			if (dWidth <= 6){
				nWidth =  1; // Flat Parcel 1
			}
			else if (dWidth <= 14){
				nWidth =  2; // Flat Parcel 1
			}
			else if (dWidth <= 10){
				nWidth =  4; // Box Parcel 1
			}
			else if (dWidth <= 18){
				nWidth =  3; // Flat Parcel 2
			}
			else if (dWidth <= 11){
				nWidth =  5; // Box Parcel 2
			}
			else if (dWidth <= 12){
				nWidth =  6; // Box Parcel 3
			}
			else if (dWidth <= 16){
				nWidth =  7; // Box Parcel 4
			}
		}
		return nWidth;
	}

	/** Looks for the longest height in all the items,
	 * 	then returns the possible corresponding parcel
	 */
	public int computeHeight(){
		int nHeight = 0;
		// customeritem.get(i).getdHeight()
		for (int i = 0; i < customeritem.size(); i++){
			if(dHeight <= 3){
				nHeight = 1; // Flat Parcel 2
			}
			else if (dHeight <= 1){
				nHeight = 2; // Flat Parcel 1
			}
			else if(dHeight <= 3){
				nHeight = 3; // Flat Parcel 2
			}
			else if(dHeight <= 5){
				nHeight = 4; // Box Parcel 1
			}
			else if(dHeight <= 7){
				nHeight = 5; // Box Parcel 2
			}
			else if(dHeight <= 9){
				nHeight = 6; // Box Parcel 3
			}
			else if(dHeight <= 12){
				nHeight = 7; // Box Parcel 4
			} 
		}
		return nHeight;
	}
	
	/** Computes for the total insurance of all items */
	public double computeInsurace(){	
		double dInsurance = 0;
		for (int i = 0; i < customeritem.size(); i++){
			if (customeritem.get(i).getInsurance() == 1){
				dInsurance += 5;
			}
		}
		return dInsurance;
	}

	/** Computes for the possible parcels for the customer */
	public int computeParcel(){
		int parcelType = 0;
		for(int i = 0 ; i < customer.getItems(); i ++){
			if (computeLength() >= computeWidth() && computeLength() >= computeHeight()){
				parcelType = computeLength();
			}
			else if (computeWidth() >= computeLength() && computeWidth() >= computeHeight()){
				parcelType = computeWidth();
			}
			else if (computeHeight() >= computeLength() && computeHeight() >= computeWidth()){
				parcelType = computeHeight();
			}
		}
		return parcelType;
	}

	/** Computes the total fee for the destination */
	public double computeDestFee(){
		double dDestPrice;
		if (customer.getRegion() == 1){
			dDestPrice = 50;
		}
		else if (customer.getRegion() == 2){
			dDestPrice = 100;
		}
		else if (customer.getRegion() == 3){
			// If 10% of the total volume of items is greater than 1000,
			// return 10% of the total volume
			if ((computeVolume() * 0.10) > 1000){
				dDestPrice = computeVolume() * 0.10;
			}

			// If 10% of the total actual weight of items is greater than 1000,
			// return 10% of the total actual weight
			else if((computeWeight() * 0.10) > 1000){
				dDestPrice = computeWeight() * 0.10;
			}
			else{
				dDestPrice = 1000;
			}
		}
		else{
			// If 25% of the total volume of items is greater than 3000,
			// return 25% of the total volume
			if ((computeVolumeDest() * 0.25) > 3000){
				dDestPrice = computeVolumeDest() * 0.25;
			}
			
			// If 25% of the total actual weight of items is greater than 3000,
			// return 25% of the total actual weight
			else if((computeWeight() * 0.25) > 3000){
				dDestPrice = computeWeight() * 0.10;
			}
			else{
				dDestPrice = 3000;
			}
		}
		return dDestPrice;
	}

	/** Compute for the parcel and weight fee */
	public double computeFee(){
		double dWeightPrice = 0;
		
		// For smaller flat parcel
		if (parcel.get(parcelNo).getParcelType() == 1){
			dWeightPrice = 20;
		}

		else if (parcel.get(parcelNo).getParcelType() == 2){
			dWeightPrice = 20;
		}
		
		// For bigger flat parcel
		else if (parcel.get(parcelNo).getParcelType() == 3){
			dWeightPrice = 50;
		}
		
		// For box parcels
		else if (parcel.get(parcelNo).getParcelType() == 4 || parcel.get(parcelNo).getParcelType() == 5 || parcel.get(parcelNo).getParcelType() == 6 || parcel.get(parcelNo).getParcelType() == 7){
			
			double max1 = customeritem.get(0).getdLength(); // Length
			double max2 = customeritem.get(0).getdWidth(); // Width
			double max3 = customeritem.get(0).getdHeight(); // Height
			for (int i = 0; i < customer.getItems(); i++){
				if (customeritem.get(i).getdLength() > max1){
					max1 = customeritem.get(i).getdLength();
				}
				if (customeritem.get(i).getdWidth() > max2){
					max2 = customeritem.get(i).getdWidth();
				}
				if (customeritem.get(i).getdHeight() > max3){
					max3 = customeritem.get(i).getdHeight();
				}
			}

			double dVolume = Math.ceil((max1 * max2 * max3) / 305);
			double dActualWeight = computeWeight();
			double dBaseActual;
			double dBaseVolume;
			
			for (int i = 0; i < customer.getItems(); i++){
				// If the item type is a document or regular product
				if (customeritem.get(i) instanceof Document == true || customeritem.get(i).getProductType() == true){
					dWeightPrice = dActualWeight * 40;
				}

				// If the item type is an irregular product
				else{
					dBaseActual = dActualWeight * 40;
					dBaseVolume = dVolume * 30;
	
					if (dBaseActual >= dBaseVolume){
						dWeightPrice = dBaseActual;
					}
					else{
						dWeightPrice = dBaseVolume;
					}
				}
			}
		}
		return dWeightPrice;
	}

	/** Display fee breakdown and tracking number */
	public void FeeBreakdown(){
		System.out.println("\n[Fee Breakdown]");
		System.out.println("Destination [" + getDestination() + "]: PHP " + computeDestFee());
		System.out.println("Parcel and Weight: PHP " + computeFee());
		System.out.println("Insurance: PHP " + computeInsurace());
		System.out.println("Total: PHP " + (computeDestFee() + computeFee() + computeInsurace()) +"\n");
		System.out.println("Tracking Number: " + parcel.get(parcelNo).getTracker().getTrackingNumber());
	}

	public void CreateSaveFile() throws IOException {
		FileWriter save = null;
		PrintWriter savewrite = null;
		String saveName = ("savefile" + saveNumber + ".txt");
		System.out.println("Creating a save file ...\n");
		try {
			save = new FileWriter(saveName);
			savewrite = new PrintWriter(save);
			savewrite.write(parcel.get(parcelNo).getTracker().getBeginDate());
			savewrite.println();
			savewrite.write(parcel.get(parcelNo).getTracker().getDeliveryDate());
			savewrite.println();
			savewrite.write(customer.getName());
			savewrite.println();
			savewrite.write(parcel.get(parcelNo).getTracker().getTrackingNumber());
		 } finally {
			if (savewrite != null) {
				savewrite.flush();
				savewrite.close();
			}
		 }
	}

	/** Ask customer to input whether:
	 * 	To have another transaction,
	 * 	Track a parcel,
	 * 	Or to exit the program.
	 */
	public void NewTransaction(){
		int iChecker = 0;
		int iChoice;
		while (iChecker == 0){
			System.out.println("What would you like to do next?");
			System.out.println("[1] Have another parcel delivered");
			System.out.println("[2] Track your parcel/s");
			System.out.println("[3] Exit the program");
			System.out.print("Pick a choice: ");
			iChoice = scan.nextInt();
			
			/** The customer will be given three choices
			 * 	1. If the customer picks [1], the program will start from the beginning
			 *     for another transaction.
			 * 	2. If the customer picks [2], the program will direct the customer to enter
			 * 	   the tracking number of a parcel to see its info.
			 * 	3. If the customer picks [3], the program will exit the program, but it will
			 * 	   be needing a username and password from the administrator.
			 */
			if (iChoice == 1){
				System.out.println("\nCreating new order form...\n");
				parcelNo++;
				saveNumber++;
				iChecker = 1;
				iSentinelCheck = 1;
				scan.nextLine();
			}
		
			else if (iChoice == 2){
				int iLoop = 0;
				// If the tracking number can't be found, it will ask the customer to input again.
				while (iLoop == 0){
					if (parcel.get(parcelNo).getTracker().TrackingCheck(parcel) == 1){
						System.out.println();
						iLoop++;
					}
					else{
						System.out.println("Inputted tracking number cannot be found, try again.");
					}
				}
				iLoop++;
			}
		
			else if (iChoice == 3){
				AdminPermission();
				iChecker = 1;
				iSentinelCheck =  0;
			}

			else{
				System.out.println("Invalid input, try again.");
			}
		}
	}

	/** Asks user/administrator to input username and password
	 * 	to successfully exit the program.
	 */
	public void AdminPermission(){
		String username;
		String userinput;
		String password;
		String passinput;
		username = "user";
		password = "pass";
		int iLoop = 0;
		System.out.println("\nAdmin permission required!");
		scan.nextLine();
		while (iLoop == 0){
			System.out.print("\nEnter username: ");
			userinput = scan.nextLine();
			System.out.print("Enter password: ");
			passinput = scan.nextLine();
			if (username.equals(userinput) == true && password.equals(passinput) == true){
				System.out.println("'\nThank you for using Johnny Moves.");
				iLoop++;
			}
			else{
				System.out.println("\nIncorrect credentials, try again.");
			}
		}
	}

	/** To check whether the inputted username and password matches the system username and password */
	public int AdminPermission1(String username, String password,String userinput, String passinput) {
		int iLoop = 0;
		int check = 0;
		while (iLoop == 0){
			if (username.equals(userinput) == true && password.equals(passinput) == true){
				check = 1;
			}
			else{
				check = 2;
			}
		}
		return check;
	}
	
	
	/** Exits the program when it returns -1 */
	public int sentinelChecker(){
		if (iSentinelCheck == 1){
			return 1;
		}

		else{
			return -1;
		}
	}

} 