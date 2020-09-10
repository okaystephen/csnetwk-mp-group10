import java.text.*;
import java.util.*;
import java.io.*;

/** This is our Tracking class
 * @author SALAMANTE, Stephen E.
 * @author CARANDANG, Pauline
 * 
 * This class handles the tracking of a parcel
*/

public class Tracking{

	private String sTrackingStatus;
	private String sTrackingBeginning;
	private String sTrackingDelivery;
	private String sTrackingNumber;
	private String[] dateName;
	private String sCurrentDate;
	private Scanner scan;

	/** Constructor */
	public Tracking() {
		scan = new Scanner(System.in);
	}

	/** Getter for string of date essentials */
	public String getDateEssentials(int i){
		return dateName[i];
	}

	/** Getter for current date */
	public String getCurrentDate(){
		return sCurrentDate;
	}

	/** Getter for current stats of shipping */
	public String getShippingStat(){
		return sTrackingStatus;
	}

	/** Creates the string for tracking number */
	public void TrackingNumber(Recipient customer, int sSequence) {
		Date begindate = new Date();
		SimpleDateFormat trackdateformat = new SimpleDateFormat("MMdd");  
		sTrackingBeginning = trackdateformat.format(begindate);
		Calendar c = Calendar.getInstance();
        c.setTime(begindate);
		String sRegionShortcut;
		String itemPattern = "00";
		String seqPattern = "000";
		DecimalFormat itemFormat = new DecimalFormat(itemPattern);
		DecimalFormat seqFormat = new DecimalFormat(seqPattern);
		String itemformat = itemFormat.format(customer.getItems());
		String seqformat = seqFormat.format(sSequence);
		
		// Get how many days of delivery
		if (customer.getRegion() == 1){
			sRegionShortcut = "MML";
			c.add(Calendar.DATE, 2);
		}
		else if (customer.getRegion() == 2){
			sRegionShortcut = "LUZ";
			c.add(Calendar.DATE, 3);
		}
		else if (customer.getRegion() == 3){
			sRegionShortcut = "VIS";
			c.add(Calendar.DATE, 5);
		}
		else{
			sRegionShortcut = "MIN";
			c.add(Calendar.DATE, 8);
		}

		Date deliverydate = c.getTime();
		sTrackingDelivery = trackdateformat.format(deliverydate);
		
		sTrackingNumber = sTrackingBeginning + sRegionShortcut + itemformat + seqformat;
	}

	/** Getter for beginning date of shipping */
	public String getBeginDate(){
		return sTrackingBeginning;
	}

	/** Getter for delivery date of shipping */
	public String getDeliveryDate(){
		return sTrackingDelivery;
	}

	/** Getter for tracking number */
	public String getTrackingNumber(){
		return sTrackingNumber;
	}

	/** Interface for inputting a tracking number,
	 * 	when successful, it will reveal info about the parcel.
	 */
	public int TrackingCheck(ArrayList<Parcel> parcel){
		int nCheck = 0;
		System.out.print("\nEnter Tracking Number: ");
		String sTrackInput = scan.next() + scan.nextLine();
		for (int i = 0; i < parcel.size(); i++){
			if (parcel.get(i).getTracker().getTrackingNumber().equals(sTrackInput) == true){
				System.out.println();
				parcel.get(i).getTracker().TrackingNumberStatus(i);
				nCheck = 1;
			}
		}
		return nCheck;
	}

	/** Display tracking info of a parcel */
	public void TrackingNumberStatus(int parcelNumber){
		Scanner scanstatus;
		Date currentdate = new Date();
		SimpleDateFormat trackdateformat = new SimpleDateFormat("MMdd");  
		sCurrentDate = trackdateformat.format(currentdate);

		String sPrepare = "Preparing";
		String sShipping = "Shipping";
		String sDelivered = "Delivered";
		dateName = new String[4];
		int counter = 0;

		try {
            String fileName = "savefile" + parcelNumber + ".txt";
            File file = new File(fileName);
            scanstatus = new Scanner(file);

            while (scanstatus.hasNextLine()) {
				String line = scanstatus.nextLine();
				dateName[counter] = line;
				counter++;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
		}

		// Get status of tracking
		if (sCurrentDate.equals(dateName[0]) == true){
			sTrackingStatus = sPrepare;
		}
		else if (sCurrentDate.equals(dateName[1]) == true){
			sTrackingStatus = sDelivered;
		}
		else{
			sTrackingStatus = sShipping;
		}

		System.out.println("[Tracking Number: " + sTrackingNumber + "]");
		System.out.println("Recipient Name: " + dateName[2]);
		System.out.println("Date ordered: " + dateName[0]);
		System.out.println("Current date: " + sCurrentDate);
		System.out.println("Date of arrival: " + dateName[1]);
		System.out.println("Status: " + sTrackingStatus);
	
	}
}