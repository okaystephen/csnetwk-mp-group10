
/** This is our Parcel class
 * @author SALAMANTE, Stephen E.
 * @author CARANDANG, Pauline
 * 
 * This class stores data needed for the parcel
*/

public abstract class Parcel{
	
	/** Variables */
	private int parcelPrint;
	private int iParcel;
	private int parcelType;
	private Tracking tracker;
	
	
	/** Constructor */
	public Parcel(){
		tracker = new Tracking();
	}

	/** Constructor */
	public Parcel(int parcelPrint, int iParcel, int parcelType){
		this.parcelPrint = parcelPrint;
		this.iParcel = iParcel;
		this.parcelType = parcelType;
	}

	/** Setter for parcel type (flat or box) */
	public void setParcel(int iParcel){
		this.iParcel = iParcel;
	}
	
	/** Setter for parcel type (flat or box) */
	public int getParcel(){
		return iParcel;
	}

	/** Setter for parcel print */
	public void setParcelPrint(int parcelPrint){
		this.parcelPrint = parcelPrint;
	}

	/** Getter for parcel print */
	public int getParcelPrint(){
		return parcelPrint;
	}

	/** Setter for parcel type */
	public void setParcelType(int parcelType){
		this.parcelType = parcelType;
	}

	/** Getter for parcel type */
	public int getParcelType(){
		return parcelType;
	}

	/** Setter for tracking class*/
	public void setTracker(Tracking tracker){
		this.tracker = tracker;
	}

	/** Getter for tracking class */
	public Tracking getTracker(){
		return tracker;
	}
}
