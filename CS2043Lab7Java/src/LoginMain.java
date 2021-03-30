public class LoginMain {

	public static void main(String[] args) {
		DataManager dm = new DataManager();
		LoginControl control = new LoginControl(dm);
		LoginUI ui = new LoginUI(control);
		
		ui.displayLoginForm();
		ui.enterUserIdPassword();
	};
}