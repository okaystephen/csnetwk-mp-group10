/** This is our Product class
 * @author SALAMANTE, Stephen E.
 * @author CARANDANG, Pauline
 * 
 * This class represents product type of an item
*/

public class Product extends CustomerItem{
	
	/** Constructor */
	public Product(){
	}

	/** Constructor */
	public Product(double dLength, double dWidth, double dHeight, double dWeight, int iInsurance){
		super(dLength, dWidth, dHeight, dWeight, iInsurance);
	}

	/** Swaps the length and width for rotate */
	public void swap(){
		double temp;
		temp = getdLength();
		setdLength(getdWidth());
		setdWidth(temp);
		setdHeight(getdWidth());
	}

}