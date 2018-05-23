/**
 * Skeleton for 'Borders.fxml' Controller Class
 */

package it.polito.tdp.borders;


import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class BordersController {

	private Model model;

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;
	
	@FXML // fx:id="boxStati"
    private ChoiceBox<String> boxStati;

    @FXML // fx:id="txtAnno"
	private TextField txtAnno; // Value injected by FXMLLoader

	@FXML // fx:id="txtResult"
	private TextArea txtResult; // Value injected by FXMLLoader
	
	

	@FXML
	void doCalcolaConfini(ActionEvent event) {
		
		String s = txtAnno.getText();
		try {
		int anno = Integer.parseInt(s);
		if(anno>=1816 && anno<=2016) {
			model.createGraph(anno);
			txtResult.setText(model.stampaGrafo()+"\nIl numero delle componenti connesse è "+model.getNumberOfConnectedComponents());
		}
		else
			txtResult.setText("Inserire un anno compreso nell'intervallo 1816-2016");
		}
		catch (NumberFormatException e) {
			txtResult.setText("Insert a valid number of years");
		}
		
	}
	
	 @FXML
	    void doTrovaVicini(ActionEvent event) {
		
		 

	    }


	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert boxStati != null : "fx:id=\"boxStati\" was not injected: check your FXML file 'Borders.fxml'.";
		assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Borders.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Borders.fxml'.";
		// Utilizzare questo font per incolonnare correttamente i dati
		txtResult.setStyle("-fx-font-family: monospace");
		
		
		
	}
	
	public void setModel(Model model) {

		this.model = model;
		
		for(Country c : model.getCountries()) {
			boxStati.getItems().add(c.getName());
		}
	}
}
