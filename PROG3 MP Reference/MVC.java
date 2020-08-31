/** This is our MVC class
 * @author SALAMANTE, Stephen E.
 * @author CARANDANG, Pauline
*/

public class MVC {

	public static void main(String[] args) {
		Gui guiview = new Gui();
		CustomerInterface model = new CustomerInterface();
		ControllerTest control = new ControllerTest(model,guiview);
	}
}
