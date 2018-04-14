package view;

import java.util.Observable;
import java.util.Observer;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import model.HashTable;

public class ShowView extends BorderPane implements Observer {

	// model variable
	private HashTable theHashTable;
	private ListView<String> list;
	
	// constructor
	public ShowView(HashTable table) {
		theHashTable = table;
		list = new ListView<String>();
		this.setCenter(list);
		initializePane();
	}
	
	public void initializePane() {
		//set list width
		list.setPrefWidth(300);
		// set list height
		list.setPrefHeight(500);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		theHashTable = (HashTable) o;
		System.out.println("Updating list for listView...");
		// function calls?
	}

}
