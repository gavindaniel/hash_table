package view;

import java.util.Observable;
import java.util.Observer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import model.HashTable;

public class ShowView extends BorderPane implements Observer {

	// model variable
	private HashTable theHashTable;
	private ListView<String> list;
	private ObservableList<String> states = FXCollections.observableArrayList();
	
	// constructor
	public ShowView(HashTable table) {
		theHashTable = table;
		list = new ListView<String>(states);
//		list.setItems(states);
//		states = new FXCollections.observableArrayList("","");
		this.setCenter(list);
		initializePane();
		updateStates();
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
		updateStates();
	}

	private void updateStates() {
		
		String[] table = theHashTable.getTable();
		int i = 0;
		states.clear();
		while (i != table.length) {
			if (table[i] == null)
				states.add("");
			else
				states.add(table[i]);
			i++;
		}
		list.setItems(states);
	}
}
