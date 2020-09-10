/** This is our Recipient class
 * @author SALAMANTE, Stephen E.
 * @author CARANDANG, Pauline
 * 
 * This class stores details of a recipient by the customer.
*/

public class Recipient{
	
	/** Variables */
	private String sName;
	private int nRegion;
	private int nItems;
	
	/** Constructor */
	public Recipient(){

	}

	/** Constructor with Setters */
	public Recipient(String sName, int nRegion, int nItems){
		this.sName = sName;
		this.nRegion = nRegion;
		this.nItems = nItems;
	}
	
	/** Setter for Recipient Name */
	public void setName(String sName){
		this.sName = sName;
	}
	
	/** Getter for Recipient Name */
	public String getName(){
		return this.sName;
	}
	
	/** Setter for Region */
	public void setRegion(int nRegion){
		this.nRegion = nRegion;
	}
	
	/** Getter for Region */
	public int getRegion(){
		return this.nRegion;
	}
	
	/** Setter for number of items */
	public void setItems(int nItems){
		this.nItems = nItems;
	}

	/** Getter for number of items */
	public int getItems(){
		return this.nItems;
	}
	
}