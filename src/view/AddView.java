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
import model.HashResult;
import model.HashTable;

public class AddView extends BorderPane implements Observer {

	private HashTable theHashTable;
	private GridPane gp;
	
	private TextField textField;
	private Button addButton;
	private Label responseText;
	
	// constructor
	public AddView(HashTable table) {
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
		// create add button
		addButton = new Button("Add");
		// create input text field
		textField = new TextField();
		// make the input text field editable
		textField.setEditable(true);
		// create response text
		responseText = new Label("Enter a state to add");
		// set grid pane width & height
		gp.setPrefSize(300, 400);
		// add button listener
		ButtonListener handler = new ButtonListener();
		// set the listener to the add button
		addButton.setOnAction(handler);
		// set the position of the text field
		GridPane.setConstraints(textField, 1, 1);
		// set the position of the add button
		GridPane.setConstraints(addButton, 2, 1);
		// set the position of the response text
		GridPane.setConstraints(responseText, 1, 2);
		// set the horizontal gap between columns
		gp.setHgap(10);
		// set the vertical gap between rows
		gp.setVgap(30);
		// add children to grid pane
		gp.getChildren().addAll(textField, addButton, responseText);
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
				System.out.println("Adding -> " + text + " to the table...");
				// add the state to the table
				HashResult add_result = theHashTable.add(text);
				// set the response text
				if (add_result.getResult() == true) // success
					responseText.setText("'" + text + "' added successfully");
				else if (add_result.getResult() == false && add_result.getKey().equals("")) { // duplicate found
					responseText.setText("'" + text + "' was ignored. Duplicate found.");
				}
				else {
					responseText.setText("'" + text + "' collided at index " + add_result.getIndex());
				}
			} else {
				responseText.setText("Please enter a state");
			}
			// clear the text field
			textField.setText("");
		}
		
	}

}
