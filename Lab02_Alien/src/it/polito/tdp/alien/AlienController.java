package it.polito.tdp.alien;

/**
 * Sample Skeleton for 'Alien.fxml' Controller Class
 */



import java.net.URL;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AlienController {
	
	private alienDictionary alienDictionary = new alienDictionary();
	
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private TextField txtWord;
    @FXML
    private TextArea txtResult;
    @FXML
    private Button btnTranslate;
    @FXML
    private Button btnReset;
        
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	assert txtWord != null : "fx:id=\"txtWord\" was not injected: check your FXML file 'Alien.fxml'.";
    	assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Alien.fxml'.";
    	assert btnTranslate != null : "fx:id=\"bntTranslate\" was not injected: check your FXML file 'Alien.fxml'.";
    	assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Alien.fxml'.";
    	
    }
  
    
    @FXML
    void doTranslate(ActionEvent event) {
    	String input = txtWord.getText().toLowerCase();
    	StringTokenizer st = new StringTokenizer(input, " ");
    	String alien = st.nextToken();
    	if (st.hasMoreTokens()) {
    		String traduzione = st.nextToken();
    		if (!alien.matches("[a-zA-Z]*") || !traduzione.matches("[a-zA-Z]*")) {
				txtResult.setText("Inserire solo caratteri alfabetici.");
				return;
			}
    		alienDictionary.addWord(alien, traduzione);
    		txtResult.setText("La parola: \"" + alien + "\", con traduzione: \"" + traduzione + "\", è stata inserita nel dizionario.");
    		txtWord.clear();
    	}
    	else{
    		if (!alien.matches("[a-zA-Z]*")) {
				txtResult.setText("Inserire solo caratteri alfabetici.");
				return;
			}
    		String translation = alienDictionary.translateWord(alien);
    		if (translation != null) {
    			translation = translation.replaceAll(" \n", System.getProperty("line.separator"));
				txtResult.setText(translation);
			} else {
				txtResult.setText("La parola cercata non esiste nel dizionario.");
			}
    		txtWord.clear();
    	}
    	    	
    }
    
    
    @FXML
    void doReset(ActionEvent event) {
    	txtResult.clear();
    	alienDictionary.resetDictionary();

    }
    
}
