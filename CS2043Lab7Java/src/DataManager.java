import java.sql.*;
import java.util.*;
public class DataManager {
	Connection connection = null;
	
	public DataManager() {
		try {
	        	Class.forName("com.mysql.jdbc.Driver").newInstance();
	     	}
		catch (Exception e) {
	      		System.err.println(e.toString());
	     	}
		
		String url = "jdbc:mysql://database_connection/database_name";
		
		try {
			connection = DriverManager.getConnection(url, "username", "password");
		}
		catch (SQLException e) {
			System.err.println("Database connection error.");
		}
	}
	
	
	public CustomerObject getCustomer(String userId, String userPassword) {
		CustomerObject customer = new CustomerObject();
		try {
			Statement st = connection.createStatement();
			
			//create query String
			String sqlQuery = "select * from CustomerAccount where Password = sha1('" + userPassword + "') AND Id = '" + userId + "';";
			
			//execute SQL query
			ResultSet rs = st.executeQuery(sqlQuery);
			
			//convert retrieved row to CustomerObject
			while (rs.next()) {
				customer.id = rs.getString(1);
				customer.password = rs.getString(2);
				customer.firstName = rs.getString(3);
				customer.lastName = rs.getString(4);
				customer.address = rs.getString(5);
				customer.contact = rs.getString(6);
			}
		}
		catch (SQLException e) {
			System.err.println("SQLerror: getCustomer");
		}
		return customer;
	}
	
	public BookInfoObject getBooks(String id) {
		BookInfoObject book = new BookInfoObject();
		try {
			Statement st = connection.createStatement();
			
			String sqlQuery = "select * from BookInfo where Id = '" + id + "';";
			
			ResultSet rs = st.executeQuery(sqlQuery);
			
			while (rs.next()) {
				book.id = rs.getString(1);
				book.title = rs.getString(2);
				book.description = rs.getString(3);
				book.author = rs.getString(4);
				book.isbn = rs.getString(5);
				book.publisher = rs.getString(6);
				book.year = rs.getInt(7);
				book.inventory = rs.getInt(8);
			}				
		}
		catch (SQLException e) {
			System.err.println("SQL error: getBooks");
		}
		return book;
	}
	
	public ArrayList<BookOrderObject> getOrdersByCustomer(String cusId) {
		ArrayList<BookOrderObject> orderList = new ArrayList<BookOrderObject>();
		try {
			Statement st = connection.createStatement();
			
			String sqlQuery = "select * from BookOrder where CustomerId = '" + cusId + "';";
			
			ResultSet rs = st.executeQuery(sqlQuery);
			
			while (rs.next()) {
				BookOrderObject bookOrder = new BookOrderObject();
				bookOrder.orderId = rs.getString(1);
				bookOrder.customerId = rs.getString(2);
				bookOrder.bookId = rs.getString(3);
				bookOrder.date = rs.getString(4);
				bookOrder.address = rs.getString(5);
				bookOrder.status = rs.getString(6);
				orderList.add(bookOrder);
			}
		}
		catch (SQLException e) {
			System.err.println("SQL error: getOrdersByCustomer");
		}
		return orderList;
	}
	
	
	
	public ArrayList<BookInfoObject> getBooksByKeywords(ArrayList<String> keywords) {
		ArrayList<BookInfoObject> bookList = new ArrayList<BookInfoObject>();
		try {
			Statement st = connection.createStatement();
			
			//create query string
			String sqlQuery = "select * from BookInfo where ";
			for (int i=0; i<keywords.size(); i++) {
				if (i < keywords.size() - 1)
					sqlQuery = sqlQuery + "Title like '%" + keywords.get(i) + "%' or ";
				else sqlQuery = sqlQuery + "Title like '%" + keywords.get(i) + "%';";
			}
			
			//execute SQL query
			ResultSet rs = st.executeQuery(sqlQuery);
			
			//convert retrieved rows to BookInfoObject[]
			while (rs.next()) {
				BookInfoObject book = new BookInfoObject();
				book.id = rs.getString(1);
				book.title = rs.getString(2);
				book.description = rs.getString(3);
				book.author = rs.getString(4);
				book.isbn = rs.getString(5);
				book.publisher = rs.getString(6);
				book.year = rs.getInt(7);
				book.inventory = rs.getInt(8);
				bookList.add(book);
			}
		} catch (SQLException e) {
			System.err.println("SQL error: getBooksByKeywords");
		}
		
		return bookList;
	}
}
