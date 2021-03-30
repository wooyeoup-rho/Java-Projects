import java.util.ArrayList;
public class ViewOrderStatusControl {
	private DataManager dataManager;
	private CustomerObject customer;
	
	public ViewOrderStatusControl(DataManager dm, CustomerObject cus) {
		this.dataManager = dm;
		this.customer = cus;
	}
	
	public ArrayList<BookOrderObject> processViewOrderStatus() {
		return dataManager.getOrdersByCustomer(customer.id);
	}
	
	public BookInfoObject getBook(String id) {
		return dataManager.getBooks(id);
	}

}
