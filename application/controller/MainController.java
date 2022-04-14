//hno775 Mario Alday
package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import application.model.NeedGiveModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainController implements Initializable {
	/*
	 * main controller has three buttons to navigate through app and calls setAddSubbutton to 
	 * have a button selected based off which button is pressed
	 */
	@FXML
    private Button needButton;

    @FXML
    private Button inventoryButton;

    @FXML
    private Button giveButton;
	
    @FXML
    void needPressed(ActionEvent event) throws IOException {
    	NeedGiveModel.setAddSubbutton(true);
    	URL url = new File("src/NeedGive.fxml").toURI().toURL();
    	Pane pane = FXMLLoader.load(url);
        //mainPane = FXMLLoader.load(getClass().getClassLoader().getResource("Classified.fxml"));// pane you are GOING TO
        Scene scene = new Scene(pane);// pane you are GOING TO show
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();// pane you are ON
        window.setScene(scene);
        window.show();
        
    }

    @FXML
    void givePressed(ActionEvent event) throws IOException {
    	NeedGiveModel.setAddSubbutton(false);
    	URL url = new File("src/NeedGive.fxml").toURI().toURL();
    	Pane pane = FXMLLoader.load(url);
        //mainPane = FXMLLoader.load(getClass().getClassLoader().getResource("Classified.fxml"));// pane you are GOING TO
        Scene scene = new Scene(pane);// pane you are GOING TO show
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();// pane you are ON
        window.setScene(scene);
        window.show();
        
    }

    @FXML
    void inventoryPressed(ActionEvent event) throws IOException {
    	URL url = new File("src/Inventory.fxml").toURI().toURL();
    	Pane pane = FXMLLoader.load(url);
        //mainPane = FXMLLoader.load(getClass().getClassLoader().getResource("Classified.fxml"));// pane you are GOING TO
        Scene scene = new Scene(pane);// pane you are GOING TO show
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();// pane you are ON
        window.setScene(scene);
        window.show();
    }
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	try {
			NeedGiveModel.loadData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
