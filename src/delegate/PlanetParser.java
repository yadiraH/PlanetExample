package delegate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import app.UserAlert;
import app.UserAlertType;
import model.Planet;



public class PlanetParser {
	
	public static List<String> processFile(String file) {
		PlanetFileReader planetFile = new PlanetFileReader();
		PlanetParser parser = new PlanetParser();
		List<String> planetAttributes = new ArrayList();
		try {
			planetFile = planetFile.openFile(file);
			while (planetFile.hasNext()) {
				planetAttributes = Arrays.asList(parser.parsePlanet(planetFile.next()));				
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
		return planetAttributes;
	}

	public String[] parsePlanet(String line) {
		String[] planetAttributes = line.split(",");
		if(planetAttributes.length < 5) {
			UserAlert.createAlert(UserAlertType.FileError, null);
		}
		return planetAttributes;
	}	
	public static Planet parsePlanetFromInput(List<String> planetAttributes) {
		Planet planet = new Planet();
		planet.setName(planetAttributes.get(0));
		planet.setDiameter(Double.parseDouble(planetAttributes.get(1)));
		planet.setMeanTemperature(Double.parseDouble(planetAttributes.get(2)));
		planet.setNumberOfMoons(Integer.parseInt(planetAttributes.get(3)));
		planet.setImgFileName(planetAttributes.get(4));
		System.out.println(planet);
		return planet;
	}	

}
