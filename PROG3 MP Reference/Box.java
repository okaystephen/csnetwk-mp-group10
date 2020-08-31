/** This is our Box class
 * @author SALAMANTE, Stephen E.
 * @author CARANDANG, Pauline
 * 
 * This class represents box type of a parcel
*/

public class Box extends Parcel{
	private boolean parcelType;

	/** Constructor */
	public Box(){
		// True for flat, false for box
		parcelType = false; 
	}

	/** Constructor */
	public Box(int parcelPrint, int iParcel, int parcelType){
		super(parcelPrint, iParcel, parcelType);
	}

	
	
}