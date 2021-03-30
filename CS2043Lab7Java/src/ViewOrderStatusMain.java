
public class ViewOrderStatusMain {
	public static void main(String[] args) {
		DataManager dm = new DataManager();
		
		LoginControl loginControl = new LoginControl(dm);
		if (loginControl.getCustomerObject() == null) {
			LoginUI loginUI = new LoginUI(loginControl);
			
			loginUI.displayLoginForm();
			loginUI.enterUserIdPassword();
		}
		
		ViewOrderStatusControl control = new ViewOrderStatusControl(dm, loginControl.getCustomerObject());
		ViewOrderStatusUI ui = new ViewOrderStatusUI(control);
		
		ui.displayBookOrders();
	}
}
