/** This is our Document class
 * @author SALAMANTE, Stephen E.
 * @author CARANDANG, Pauline
 * 
 * This class represents document type of an item
*/

public class Document extends CustomerItem{

	/** Constructor */
	public Document(){
	}

	/** Constructor */
	public Document(int noPage, double dLength, double dWidth, int iInsurance){
		super(noPage, dLength, dWidth, iInsurance);
	}

	/** Swaps the length and width for rotate */
	public void swap(){
		double temp;
		temp = getdLength();
		setdLength(getdWidth());
		setdWidth(temp);
	}

}