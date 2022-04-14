
package application;
	
import java.io.File;
import java.net.URL;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	/*
	 * main function to start java app
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
//			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("src/Sample.fxml"));
			URL url = new File("src/Main.fxml").toURI().toURL();//use url type to store path to main fxml file
			AnchorPane root = FXMLLoader.load(url); //initialize anchor pane 
			Scene scene = new Scene(root,800,800);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
