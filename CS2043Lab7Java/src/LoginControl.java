public class LoginControl {
	private DataManager dataManager;
	private CustomerObject loginCustomer;
	
	public LoginControl(DataManager dm) {
		this.dataManager = dm;
		this.loginCustomer = null;
	}
	
	public CustomerObject processLogin(String userId, String userPassword) {
		CustomerObject temp = dataManager.getCustomer(userId, userPassword);
		saveCustomerObject(temp);
		return temp;
	}
	
	public void saveCustomerObject(CustomerObject temp) {
		this.loginCustomer = temp;
	}
	
	public CustomerObject getCustomerObject() {
		return loginCustomer;
	}
}
