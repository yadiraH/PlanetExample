package app;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.PlanetAttributes;
import javafx.scene.control.Alert.AlertType;

public class UserAlert {
	
	public static boolean keepCurrentPlanet = false;

	public static void createAlert(UserAlertType alertMessage, PlanetAttributes input) {
		UserAlert userAlert = new UserAlert();
		switch(alertMessage) {
			case ReplacePlanet:
				userAlert.createReplaceAlert();
				break;
			case InputError:
				userAlert.createInputAlert(input);
				break;
			case FileError:
				userAlert.createFileAlert();
				break;
			default:
				break;
		}		
	}

	private void createReplaceAlert() {
		Alert alert;
		alert = new Alert(AlertType.CONFIRMATION, "Do you want to load a new planet?"
				+ "\nThis will override your current planet view.", 
				 ButtonType.YES, ButtonType.NO);
		alert.showAndWait();
			
		if (alert.getResult() == ButtonType.YES) {	
			keepCurrentPlanet = false;		
		}else if (alert.getResult() == ButtonType.NO) {
			keepCurrentPlanet = true;
			return;
		}
	}
	private void createInputAlert(PlanetAttributes input) {
		Alert alert;
		alert = new Alert(AlertType.WARNING);
		alert.setTitle("Invalid Input");
		alert.setHeaderText(null);
		alert.setContentText("Invalid " + input);
		alert.showAndWait();
		return;
	}
	private void createFileAlert() {
		Alert alert;
		alert = new Alert(AlertType.ERROR);
		alert.setTitle("File Error");
		alert.setHeaderText(null);
		alert.setContentText("There was an issue with the file");
		alert.showAndWait();		
	}
}
	


