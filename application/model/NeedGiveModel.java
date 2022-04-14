
package application.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.Scanner;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class NeedGiveModel {
	/*
	 * Need give model will hold data structures to store info, hashmap for items and quantity, arraylist for usernames
	 * setaddbutton addbutton and will set the add or sub button when you click need or give from the menu
	 * add item checks if item is in hashmap from properties then add if it is, create new item with given quantity if 
	 * it doesnt exist
	 * get number of items returns quantity with respective item 
	 * Overall model class handles the data procressing of the app
	 */
	private static HashMap<String, String> h1 = new HashMap<String,String>();
    private static Properties properties = new Properties();
    private static HashMap<Object, Object> inventario = new HashMap<Object,Object>();
    private static ArrayList<String> users = new ArrayList<String>();
    
    private static boolean addSubbutton;
    
	public static boolean isAddSubbutton() { //set add button if coming from need or give
		return addSubbutton;
	}
	public static void setAddSubbutton(boolean addSubbutton) {
		NeedGiveModel.addSubbutton = addSubbutton;
	}
	public static HashMap<String, String> getH1() { //getter for h1 data struct
		return h1;
	}
	public static void setH1(HashMap<String, String> h1) {
		NeedGiveModel.h1 = h1;
	}
	public static void addItem(String item, String quantity) throws IOException { //function to add item with quantity, adds to if it exists

		if (item==null || item.isEmpty()||quantity==null || quantity.isEmpty() ){
			Alert error= new Alert(AlertType.ERROR);
			//Alert error= new Alert(AlertType.CONFIRMATION);
			error.setTitle("Error Message");
			error.setHeaderText("Missing Information");
			error.setContentText("Please, try again!");
			error.showAndWait();		
		}
		else{
			int oldValue=0;
			int newValue=0;
			properties.load(new FileInputStream("src/data.properties"));  
			for(Object keys: properties.stringPropertyNames()){//get keys from properties storage
				inventario.put(keys, properties.get(keys).toString()); //store keys and values in inventario hashmap
			}			
			if (inventario.containsKey(item)){//if key or item is present 
				oldValue=Integer.parseInt((inventario.get(item).toString())); //get value to key, convert to int
				newValue=oldValue+Integer.parseInt(quantity); //adds old to new 
				h1.put(item,String.valueOf(newValue)); //store back into h1 hashmap
//				 System.out.println(oldValue);
				 System.out.println("newValue: "+newValue);
			}
			else {
				h1.put(item,quantity); //store back into h1 hashmap if it doesnt exist yet with given quantity
			}
			properties.putAll(h1); //store back in properties variable 
			FileOutputStream file=new FileOutputStream("src/data.properties", true);
			properties.store(file, null); //store back to properties file 
			Alert error= new Alert(AlertType.CONFIRMATION); //alert for confirmation
			error.setContentText("Submitted!");
			error.showAndWait();
		    System.out.println(h1);
			
			
		}
	}
	public static String getNumberOfItemsInInventory(String selected) {
		System.out.println(h1.get(selected));
		return h1.get(selected);
	}
	public static void substractItem(String item, String quantity) throws IOException {//subtract item if selected from previous 
		if (item==null || item.isEmpty()||quantity==null || quantity.isEmpty() ){
			Alert error= new Alert(AlertType.ERROR);
			//Alert error= new Alert(AlertType.CONFIRMATION);
			error.setTitle("Error Message");
			error.setHeaderText("Missing Information");
			error.setContentText("Please, try again!");
			error.showAndWait();		
		}
		else{
			int oldValue=0;
			int newValue=0;
			properties.load(new FileInputStream("src/data.properties"));  
			for(Object keys: properties.stringPropertyNames()){//get keys from properties storage
				inventario.put(keys, properties.get(keys).toString()); //store keys and values in inventario hashmap
			}			
			if (inventario.containsKey(item)){//if key or item is present 
				oldValue=Integer.parseInt((inventario.get(item).toString())); //get value to key, convert to int
				newValue=oldValue-Integer.parseInt(quantity); //subtracts old by new 
				h1.put(item,String.valueOf(newValue)); //store back into h1 hashmap
//				 System.out.println(oldValue);
				 System.out.println("new Value: "+newValue);
			}
			else {
				h1.put(item,quantity); //store back into h1 hashmap
			}
			properties.putAll(h1); //store back in properties variable 
			FileOutputStream file=new FileOutputStream("src/data.properties", true);
			properties.store(file, null); //store back to properties file 
			Alert error= new Alert(AlertType.CONFIRMATION);
			error.setContentText("Submitted!");
			error.showAndWait();
		    System.out.println(h1);
			
			
		}
	}
	public static void addUserName(String user) throws IOException {//adds username if not already in list, creates file
		if(!users.contains(user)) {
			
			File file=new File("src/users.txt");
	        FileWriter writer = new FileWriter(file, true);
	        writer.write(user + "\n");
	        writer.close();
	        users.add(user);
	        System.out.println("added user");
	        System.out.println("users:");
	        for (String i: users) {
	        	System.out.println(i);
	        }
		}
		
	}
	public static void loadData() throws FileNotFoundException, IOException {//load data into hashmap and array list at bootup of app
		properties.load(new FileInputStream("src/data.properties"));  
		for(Object keys: properties.stringPropertyNames()){//get keys from properties storage
			inventario.put(keys, properties.get(keys).toString()); //store keys and values in inventario hashmap
			
		}	
		for (Object keys: inventario.keySet()) { //from properties sto inventario to h1
			h1.put(keys.toString(), inventario.get(keys).toString());
		}
		System.out.println(h1);
		File file = new File("src/users.txt");
	    Scanner info = new Scanner(file);
	    
	    while (info.hasNextLine()) {
	    	if(!info.toString().matches("")) {
	    		users.add(info.nextLine().toString());
//	    		System.out.println(info.nextLine().toString());
	    	}
	    }
	}
}


























