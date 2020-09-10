/** This is our Flat class
 * @author SALAMANTE, Stephen E.
 * @author CARANDANG, Pauline
 * 
 * This class represents flat type of a parcel
*/

public class Flat extends Parcel{
	private boolean parcelType;

	/** Constructor */
	public Flat(){
		// True for flat, false for box
		parcelType = true; 
	}

	/** Constructor */
	public Flat(int parcelPrint, int iParcel, int parcelType){
		super(parcelPrint, iParcel, parcelType);
	}
}