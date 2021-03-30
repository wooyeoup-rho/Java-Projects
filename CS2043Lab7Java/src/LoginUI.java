import java.util.*;
public class LoginUI {
	private LoginControl loginControl;
	
	public LoginUI(LoginControl control) {
		this.loginControl = control;
	}
	
	public void displayLoginForm() {
		System.out.println("Enter username and password.");
	}
	
	public void enterUserIdPassword() {
		Scanner sc = new Scanner(System.in);
		
		String userId = sc.next();
		String userPassword = sc.next();
		sc.close();
		
		CustomerObject customer = loginControl.processLogin(userId, userPassword);
		
		displayLoginConfirmation(customer);
	}
	
	public void displayLoginConfirmation(CustomerObject customer) {
		if (customer.id == null)
				System.out.println("Login unsuccessful.");
		else {
			System.out.println("Login successful.");
		}
	}
}
