import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import java.lang.Math;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.CallableStatement;


public class StudentTranscriptGUI extends Application {
	private TextField idField;
	private Text response;

	public void start(Stage primaryStage) {
		primaryStage.setTitle("Student Transcript");

		Label idLabel = new Label ("Student ID: ");

		idField = new TextField();
		idField.setPrefWidth(200);

		Button accept = new Button("Get Transcript");
		accept.setOnAction(this::processStudentTranscript);
		Button quit = new Button("Quit");
		quit.setOnAction(this::quitApp);

		response = new Text("");

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(0, 10, 0, 10));

		// Row 1: Buttons
		grid.add(accept, 0, 0);
		grid.add(quit, 2, 0);
		//Row 2: id
		grid.add(idLabel, 0, 1);
		grid.add(idField, 1, 1);
		//Row 3: response
		grid.add(response, 0, 4, 2, 4);

		Scene scene = new Scene (grid, 350, 200);

		primaryStage.setScene(scene);
		primaryStage.show();
	}


	public void processStudentTranscript(ActionEvent event){
		try{
			//Checks if field is empty
			if (idField.getText().isEmpty()) {
				response.setText("Usage: Enter a number in Student ID Field");
				response.setFill(Color.RED);
			}

			else {
				String tempId = idField.getText();
				int studentId = Integer.parseInt(tempId);
				Connection conn = this.openConnection();
				String query = "{CALL StudentTranscript(?)}";
				CallableStatement stmt = conn.prepareCall(query);
				stmt.setInt(1,studentId);

				response.setFill(Color.BLACK);
				ResultSet rs = stmt.executeQuery();

				String message = ("Grade\tCourse ID    \tCourse Name");
				message += "\n---------------------------------------------------------";

				while( rs.next() ) {
					message += "\n" + rs.getString(3)+ "\t\t" + rs.getString(1)+ "    \t" + rs.getString(2);
				}

				response.setText(message);
				this.closeConnection(conn);
			}
		}//end try

		catch (SQLException e) {
			response.setText(e.getMessage());
			response.setFill(Color.RED);
		}

		//Exception for when a non-numeric data type entered in field
		catch (NumberFormatException e) {
			response.setText("Usage: Enter a number in Student ID Field");
		}
	}

	public void quitApp(ActionEvent event){
		Platform.exit();
		System.exit(0);
	}

	private Connection openConnection() {
		final String url = "jdbc:mysql://database_connection/database_name";
		final String user = "username";
		final String password = "password";
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(url, user, password);
		}

		catch (Exception e) {
			System.err.printf("Couldn't open a connection: (%s)", e.getMessage());
		}

		return conn;
	}//end openConnection

	private void closeConnection(Connection conn) {// closeConnection method
		try {
			conn.close();
		}

		catch (Exception e) {
			System.err.printf("Couldn't close connection: (%s)", e.getMessage());
		}
	}//end closeConnection

}
