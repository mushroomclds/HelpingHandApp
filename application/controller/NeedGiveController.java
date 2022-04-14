//hno775 Mario Alday
package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import application.model.NeedGiveModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class NeedGiveController implements Initializable{
	/*
	 * this controller initializes load data to populate h1 hashmap 
	 * only allows right format and filled boxes to be input when clicking submit 
	 * displays alert box and updates on the transaction in the info box right side 
	 */
	@FXML
    private RadioButton subButton;
	
	@FXML
    private TextArea infoText;
	
    @FXML
    private RadioButton addButton;
    
    @FXML
    private TextArea userText;
    
    @FXML
    private TextArea quantityText;

    @FXML
    private TextArea itemText;

    @FXML
    void donateButtonPressed(ActionEvent event) {
    	
    }

    @FXML
    void giveButtonPressed(ActionEvent event) {

    }

    @FXML
    void amountItemPressed(ActionEvent event) {

    }
    @FXML
    void addSubButtonPressed(ActionEvent event) {
    	
    }
    @FXML
    void addButtonPressed(ActionEvent event) {
    	subButton.setSelected(false);
    }

    @FXML
    void subButtonPressed(ActionEvent event) {
    	addButton.setSelected(false);
    }
    @FXML
    void submitButtonPressed(ActionEvent event) throws IOException {
    	/*
    	 * check if in format abc123 and if all input boxes are filled when 
    	 * pressing submit 
    	 * depending on what button is pressed it will add or subtract from the previous
    	 * store item quantity and update it
    	 */
    	Alert error= new Alert(AlertType.ERROR);
    	HashMap<String, String> localHash = new HashMap<String,String>();
    	localHash = NeedGiveModel.getH1();
    	String user = userText.getText();
    	String item = itemText.getText();
    	String quantity = quantityText.getText();
    	if(!(user.length() == 6 && user.substring(0,3).matches("[a-zA-Z]+") 
    			&& user.substring(3).matches("[0-9]+"))) {
    		error.setContentText("User name invalid!");
    		error.showAndWait();
    		return;
    	}
    	if((!subButton.isSelected() && !addButton.isSelected()) ||
    			(user.equals("") || item.equals("") || quantity.equals(""))) {
    		error.setContentText("Fill in all boxes and choose add/subtract!");
    		error.showAndWait();
    		return;
    	}
    	userText.clear();
    	itemText.clear();
    	quantityText.clear();
    	
    	System.out.println("Debug:"+user + " " + item+ " "+ quantity);
    	
    	if(addButton.isSelected()) {
    		NeedGiveModel.addItem(item, quantity);
    		String newAmount = localHash.get(item);
    		infoText.setText(user+" gave "+ quantity+ " of "+item + "\nNew amount: " + newAmount );
    		
    	}
    	else if (subButton.isSelected()) {
    		NeedGiveModel.substractItem(item, quantity);
    		String newAmount = localHash.get(item);
    		infoText.setText(user+" recieved "+ item+ " of amount "+ quantity + "\nNew amount: " + newAmount );
    	}
    	NeedGiveModel.addUserName(user);
    	
    }

    @FXML
    void homeButtonPressed(ActionEvent event) throws IOException {
    	/*
    	 * goes back to home screen when pressed
    	 */
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
    	/*
    	 * creates file if not alreadt created and then sets add and sub button
    	 * depending on what was pressed from home screen
    	 */
    	File prop = new File("src/data.properties");
		if (!prop.exists()) {
			try {
				prop.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(NeedGiveModel.isAddSubbutton()) { //give is false, need is true
			subButton.setSelected(true);
		}
		else {
			addButton.setSelected(true);
		}
		
	}
}
