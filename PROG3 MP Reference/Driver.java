/** This is our Johnny Moves driver
 * @author SALAMANTE, Stephen E.
 * @author CARANDANG, Pauline
*/

public class Driver{
	
	public static void main(String[] args){

		Gui guiview = new Gui();
		CustomerInterface model = new CustomerInterface();
		ControllerTest control = new ControllerTest(model,guiview);
			/*
			int sentinelCheck = 1;
			CustomerInterface customerinterface = new CustomerInterface();

			try {
			sentinelCheck = customerinterface.Welcome();
			while(sentinelCheck == 1){
				customerinterface.scanItem();
				customerinterface.scanParcel();
				customerinterface.FeeBreakdown();
				customerinterface.CreateSaveFile();
				customerinterface.NewTransaction();
				sentinelCheck = customerinterface.sentinelChecker();
			}
			}
			catch(Exception err) {
				System.out.println("\nInvalid input! Error: " + err + "\nGoing back to main menu.\n");
				main(args);
			}
			*/
		}
}