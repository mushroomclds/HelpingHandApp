
package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import application.model.NeedGiveModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class InventoryController implements Initializable{
	/*
	 * invetory controller that uses list for invetory selection and text area to 
	 * display the amount per item selected. includes home button to go back
	 */
	@FXML
    private ListView<String> listView;
	
	@FXML
    private TextArea itemQuantityText;
	
	@FXML
    private Button lookupButton;
    @FXML
    void lookupButtonPressed(ActionEvent event) {
    	ObservableList listOfItems=listView.getSelectionModel().getSelectedItems();
    	if(listOfItems.isEmpty()){
    		return;
    	}
    	System.out.println(listOfItems.get(0));
    	String num = NeedGiveModel.getNumberOfItemsInInventory(listOfItems.get(0).toString());
    	itemQuantityText.setText(num);
    }
    
    @FXML
    void homeButtonPressed(ActionEvent event) throws IOException {
    	URL url = new File("src/Main.fxml").toURI().toURL();
    	Pane pane = FXMLLoader.load(url);
        //mainPane = FXMLLoader.load(getClass().getClassLoader().getResource("Classified.fxml"));// pane you are GOING TO
        Scene scene = new Scene(pane);// pane you are GOING TO show
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();// pane you are ON
        window.setScene(scene);
        window.show();
    }
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	HashMap<String, String> localHash = new HashMap<String,String>();
    	localHash = NeedGiveModel.getH1();
    	System.out.println(localHash);
    	List<String> list = new ArrayList<String>(localHash.keySet());
//    	NeedGiveModel local = new NeedGiveModel();
    	
    	listView.getItems().addAll(list);
    	
	}
    
    
}
