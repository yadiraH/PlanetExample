package controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

import app.*;
import delegate.PlanetBuilder;
import delegate.PlanetFileWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Planet;

public class PlanetController implements Initializable {

    @FXML
    private ImageView planetImage;

    @FXML
    private Button selectImageButton;

    @FXML
    private TextField planetName;

    @FXML
    private TextField planetDiameterKM;

    @FXML
    private TextField planetDiameterM;

    @FXML
    private TextField planetMeanSurfaceTempC;

    @FXML
    private TextField planetMeanSurfaceTempF;

    @FXML
    private TextField planetNumberOfMoons;

    @FXML
    private Label fancyPlanetName;
    
    private List<TextField>textFields;
    private PlanetBuilder planetBuilder;
    private Planet planet;
    boolean fieldChanged = false;
    
    public PlanetController(){
    	textFields = new ArrayList<TextField>();
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		planetBuilder = new PlanetBuilder();
		planet = new Planet();
		planet = planetBuilder.instatiateDefaultValues();
		textFields.add(planetName);
		textFields.add(planetDiameterKM);
		textFields.add(planetMeanSurfaceTempC);
		textFields.add(planetNumberOfMoons);
		
		addListener();	
	}

	private void addListener() {
    	for(TextField textField: textFields) {
    		textField.textProperty().addListener((observable, oldValue, newValue) -> {
    	    fieldChanged = true;
    	    checkForChange(textField.getId());      	
    		});
    	} 
    }
    
	private void checkForChange(String field) {
    	if(field.equals("planetDiameterKM")) {
    		planetDiameterM.setText("" + planetBuilder.calculateDiameterMi(
            Integer.parseInt(planetDiameterKM.getText()))); 
    	}
    	if(field.equals("planetMeanSurfaceTempC")) {
    		planetMeanSurfaceTempF.setText("" + planetBuilder.calculateTemperatureF(
            Integer.parseInt(planetMeanSurfaceTempC.getText()))); 
    	}  	
	}

	@FXML
    private void selectImage(ActionEvent event) {
    	final FileChooser fileChooser = new FileChooser();
    	fileChooser.setInitialDirectory(new File("images"));

    	try {   
    		File file = fileChooser.showOpenDialog(null);
    		if (file != null) {
        	   file.getName();
    		 }
           
    		setImage(file);
    		planet.setImgFileName("images/" + file.getName());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
           }           
    }
	
    private void setImage(File file) throws FileNotFoundException {
    	InputStream targetStream = new FileInputStream(file);
        Image image = new Image(targetStream);
        planetImage.setImage(image);
        
    }
    	
    @FXML
	private void loadPlanet(ActionEvent event) {
    	if(fieldChanged) {
    		loadPopup(UserAlertType.ReplacePlanet);
    	}
    	if(!UserAlert.keepCurrentPlanet) {
    		loadFromFile();
    	}   	
    }
    
    private void loadPopup(UserAlertType alertMessage) {
    	UserAlert.createAlert(alertMessage, null);	
	}
	
    private void loadFromFile() {
    	UserAlert.keepCurrentPlanet = false; 
		FileChooser fileChooser = new FileChooser();
		fileChooser.setInitialDirectory(new File("planets"));
		File file = fileChooser.showOpenDialog(null);
		try {   			
			setPlanetFields(file);	        
		} catch (FileNotFoundException e) {	
				e.printStackTrace();
		}		
	}

    private void setPlanetFields(File file) throws FileNotFoundException{
	   file.getName();
	   DecimalFormat formatter  = new DecimalFormat(",###.00");
	   planet = planetBuilder.buildPlanetFromFile(file.getAbsolutePath());
	   planetName.setText(planet.getName());
	   fancyPlanetName.setText(planet.getName());
	   planetDiameterKM.setText("" + formatter.format(planet.getDiameter()));
	   planetMeanSurfaceTempC.setText("" + formatter.format(planet.getMeanTemperature()));
	   planetNumberOfMoons.setText("" + planet.getNumberOfMoons());   	  
	   planetDiameterM.setText("" + formatter.format(
            planetBuilder.calculateDiameterMi(planet.getDiameter())));
	   planetMeanSurfaceTempF.setText("" + formatter.format(
            planetBuilder.calculateTemperatureF(planet.getMeanTemperature())));
	   if(planet.getImgFileName() != "") {
		   setImage(new File(planet.getImgFileName()));
	   }   	
	}
	
	@FXML
    void savePlanet(ActionEvent event) {
    	List<String> planetAttributes = new ArrayList<String>();
    	planetAttributes.add(planetName.getText());
    	planetAttributes.add(planetDiameterKM.getText());
    	planetAttributes.add(planetMeanSurfaceTempC.getText());
    	planetAttributes.add(planetNumberOfMoons.getText());
    	planetAttributes.add(planet.getImgFileName());
    	planet = planetBuilder.buildPlanetFromInput(planetAttributes);
    	PlanetFileWriter writer = new PlanetFileWriter();
    	writer.saveToFile(planet);
    }
}