package delegate;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javafx.stage.FileChooser;
import model.Planet;

public class PlanetFileWriter {
	StringBuffer planetResults = new StringBuffer();
	String filename = "";
	FileWriter fw;

	public void saveToFile(Planet planet) {
		FileChooser fileChooser = new FileChooser();
    	fileChooser.setInitialDirectory(new File("planets"));
    	FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
    		"TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(null);
    				
		try {			
			if (file != null) {
				filename = file.getAbsolutePath();
				writeToFile(planet);
			}	
		} catch (IOException e) {
			e.printStackTrace();
		}				
	}

	private void writeToFile(Planet planet) throws IOException{
		planetResults.append(String.format("%s,%s,%s,%s,%s", planet.getName(), 
			planet.getDiameter(),planet.getMeanTemperature(), planet.getNumberOfMoons(), 
			planet.getImgFileName()));
		fw = new FileWriter(filename);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(planetResults.toString());
		bw.close();	
	}
}
