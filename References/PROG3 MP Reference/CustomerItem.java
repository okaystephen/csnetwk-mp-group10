/** This is our Customer Item class
 * @author SALAMANTE, Stephen E.
 * @author CARANDANG, Pauline
 * 
 * This class stores data regarding an item
*/

public abstract class CustomerItem{

	/** Variables */
	private int noPage;
	private double dWeight;
	private double dLength;
	private double dHeight;
	private double dWidth;
	private int iInsurance;
	private boolean productType;
	
	/** Constructor */
	public CustomerItem() {

	}

	/**	Constructor for documents */
	public CustomerItem(int noPage, double dLength, double dWidth, int iInsurance){
		this.noPage = noPage;
		this.dLength = dLength;
		this.dWidth = dWidth;
		this.iInsurance = iInsurance;
	}
	
	/*	Constructor for product */
	public CustomerItem(double dLength, double dWidth, double dHeight, double dWeight, int iInsurance) {
		this.dLength = dLength;
		this.dWidth = dWidth;
		this.dHeight = dHeight;
		this.dWeight = dWeight;
		this.iInsurance = iInsurance;
	}

	/** Setter for number of pages for document */
	public void setnoPage(int noPage) {
		this.noPage = noPage;
	}
	
	/** Getter for number of pages for document */
	public int getnoPage() {
		return noPage;
	}

	/** Setter for length of document or product */
	public void setdLength(double dLength) {
		this.dLength = dLength;
	}

	/** Getter for length of document or product */
	public double getdLength() {
		return dLength;
	}
	
	/** Setter for width of document or product */
	public void setdWidth(double dWidth) {
		this.dWidth = dWidth;
	}
	
	/** Getter for width of document or product */
	public double getdWidth() {
		return dWidth;
	}

	/** Setter for height of document or product */
	public void setdHeight(double dHeight) {
		this.dHeight = dHeight;
	}

	/** Getter for height of document or product */
	public double getdHeight() {
		return dHeight;
	}

	/** Setter for weight of product */
	public void setdWeight(double dWeight) {
		this.dWeight = dWeight;
	}

	/** Getter for weight of product */
	public double getdWeight() {
		return dWeight;
	}

	/** Setter for insurance choice */
	public void setInsurance(int iInsurance) {
		this.iInsurance = iInsurance;
	}

	/** Getter for insurance choice */
	public int getInsurance() {
		return iInsurance;
	}


	/** Swaps the length and width for rotate */
	public abstract void swap();

	/** Setter for product if regular or irregular */
	public void setProductType(boolean bInput){
		// True for regular product, false for irregular product
		if (bInput == true){
			productType = true;
		} else {
			productType = false;
		}
	}

	/** Getter for product type */
	public boolean getProductType(){
		return productType;
	}

}
