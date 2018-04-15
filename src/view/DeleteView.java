package view;

import java.util.Observable;
import java.util.Observer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import model.HashTable;

public class DeleteView extends BorderPane implements Observer {

	private HashTable theHashTable;
	private GridPane gp;
	
	private TextField textField;
	private Button deleteButton;
	private Label responseText;
	
	// constructor
	public DeleteView(HashTable table) {
		// set the hash table
		theHashTable = table;
		// init grid pane
		gp = new GridPane();
		// set grid pane to center
		this.setCenter(gp);
		// initialize the pane
		initializePane();
	}
	
	// Grid Pane initializer
	private void initializePane() {
		// create delete button
		deleteButton = new Button("Delete");
		// create input text field
		textField = new TextField();
		// make the input text field editable
		textField.setEditable(true);
		// create response text
		responseText = new Label("Enter a state to delete");
		// set grid pane width & height
		gp.setPrefSize(300, 400);
		// add button listener
		ButtonListener handler = new ButtonListener();
		// set the listener to the add button
		deleteButton.setOnAction(handler);
		// set the position of the text field
		GridPane.setConstraints(textField, 1, 1);
		// set the position of the add button
		GridPane.setConstraints(deleteButton, 2, 1);
		// set the position of the response text
		GridPane.setConstraints(responseText, 1, 2);
		// set the horizontal gap between columns
		gp.setHgap(10);
		// set the vertical gap between rows
		gp.setVgap(30);
		// add children to grid pane
		gp.getChildren().addAll(textField, deleteButton, responseText);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// udpate the hash table
		theHashTable = (HashTable) o;
	}
	
	public class ButtonListener implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			// read the input text field
			String text = textField.getText();
			// check for empty field
			if (!text.isEmpty()) {
				// TODO: delete/comment out debug statement
				System.out.println("Deleting -> " + text + " from the table...");
				// add the state to the table
				// FIXME: implement delete method for HashTable
//				theHashTable.delete(text);
				// set the response text
				responseText.setText("'" + text + "' deleted successfully");
			} else {
				responseText.setText("Please enter a state");
			}
			// clear the text field
			textField.setText("");
		}
		
	}

}
