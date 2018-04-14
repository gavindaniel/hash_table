package controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

	// stage variables
	private BorderPane window;				// main window of GUI
	// static variables
	public static final int width = 300;		// width of the window
	public static final int height = 500;	// height of the window
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		// set stage title
		primaryStage.setTitle("Hashin' Stuff");
		//create new window to put stuff in
		window = new BorderPane();
		// create new scene to put window in
		Scene scene = new Scene(window, width, height);
	}
	
}
