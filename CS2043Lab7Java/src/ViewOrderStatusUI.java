import java.util.*;
public class ViewOrderStatusUI {
	private ViewOrderStatusControl viewOrderStatusControl;
	
	public ViewOrderStatusUI(ViewOrderStatusControl control) {
		this.viewOrderStatusControl = control;
	}
	
	public void displayBookOrders() {
		ArrayList<BookOrderObject> orders = viewOrderStatusControl.processViewOrderStatus();
		
		if (orders.size() == 0)
			System.out.println("No orders found.");
		else {
			System.out.println("Order Status Results: ");
			for (int i=0; i < orders.size(); i++) {
				BookInfoObject book = viewOrderStatusControl.getBook(orders.get(i).bookId);
				System.out.println("Title: " + book.title);
				System.out.println("Date: " + orders.get(i).date);
				System.out.println("Address: " + orders.get(i).address);
				System.out.println("Status: " + orders.get(i).status);
				System.out.println();
			}
		}
	}
}
