package controller;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;

import java.util.Observer;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.HashTable;
import view.AddView;
import view.DeleteView;
import view.ShowView;

public class Main extends Application {

	// model variable
	private HashTable theHashTable;			// the Hash Table
	// observer variable(s)
	private Observer currentView;
	private Observer addView;
	private Observer deleteView;
	private Observer listView;
	// stage variables
	private BorderPane window;				// main window of GUI
	private MenuBar menuBar;					// main window Menu Bar for GUI
	// static variables
	public static final int width = 300;		// width of the window
	public static final int height = 500;	// height of the window
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// set stage title
		primaryStage.setTitle("Hashin' Stuff");
		//create new window to put stuff in
		window = new BorderPane();
		// create new scene to put window in
		Scene scene = new Scene(window, width, height);
		
		//setup sub menus
		setupMenus();
		
		// set the menu bar to the top of the window
		window.setTop(menuBar);
		
		// create a new Model 
		theHashTable = new HashTable();
		
		// create views
		addView = new AddView(theHashTable);
		deleteView = new DeleteView(theHashTable);
		listView = new ShowView(theHashTable);
		
		// add the views as observers of the model
		theHashTable.addObserver(addView);
		theHashTable.addObserver(deleteView);
		theHashTable.addObserver(listView);
		
		// set initial view 
		setViewTo(listView);
		
		// set the stage scene 
		primaryStage.setScene(scene);
		
		// show the stage
		primaryStage.show();
	}
	
	private void setupMenus() {
		// create menu items
		MenuItem add_MI = new MenuItem("Add");
		MenuItem delete_MI = new MenuItem("Delete");
		MenuItem list_MI = new MenuItem("Show");
		MenuItem exit_MI = new MenuItem("Exit");
		// create menu
		Menu menu = new Menu("Options");
		// add menu items to menu
		menu.getItems().addAll(add_MI, delete_MI, list_MI, exit_MI);
		// create menu bar
		menuBar = new MenuBar();
		// add menu to menu bar
		menuBar.getMenus().addAll(menu);
		// create menu listener
		MenuItemListener menuListener = new MenuItemListener();
		add_MI.setOnAction(menuListener);
		delete_MI.setOnAction(menuListener);
		list_MI.setOnAction(menuListener);
		exit_MI.setOnAction(menuListener);
	}
	
	private void setViewTo(Observer newView) {
		// clear the window
		window.setCenter(null);
		//set current view to new view
		currentView = newView;
		// set the window to the new view
		window.setCenter((Node) currentView);
	}
	
	private class MenuItemListener implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			// create text for menu item text
			String text = ((MenuItem) event.getSource()).getText();
			// check which menu item was selected
			if (text.equals("Add"))
				setViewTo(addView);
			else if (text.equals("Delete"))
				setViewTo(deleteView);
			else if (text.equals("Show"))
				setViewTo(listView);
			else if (text.equals("Exit"))
				System.exit(0);
		}
		
	}
}
