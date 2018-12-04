package delegate;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import app.UserAlert;
import app.UserAlertType;
import model.*;



public class PlanetBuilder {
	
	protected Planet planet;
	protected PlanetInputValidater planetInput;
	
	public static final String DEFAULT_PLANET_NAME = "";
	public static final double DEFAULT_PLANET_DIAMETER = -1;
	public static final double DEFAULT_PLANET_MEAN_TEMPERATURE = -500;
	public static final int DEFAULT_PLANET_NUMBER_OF_MOONS = -1;
	public static final String DEFAULT_PLANET_IMG_FILENAME = "images/no_image.png";
	private final double KM_TO_MI_AMOUNT = 0.621371;
	private final double CELSIUS_TO_FAHRENHEIT_AMOUNT =  (9/5);
	private final double CELSIUS_TO_FAHRENHEIT_ADD_AMOUNT = 32;
	
	public PlanetBuilder () {
		planet = new Planet();
		planetInput = new PlanetInputValidater();
		planet = instatiateDefaultValues();
	}
	
	public Planet instatiateDefaultValues() {
		planet.setName(DEFAULT_PLANET_NAME);
		planet.setDiameter(DEFAULT_PLANET_DIAMETER);
		planet.setMeanTemperature(DEFAULT_PLANET_MEAN_TEMPERATURE);
		planet.setNumberOfMoons(DEFAULT_PLANET_NUMBER_OF_MOONS);
		planet.setImgFileName(DEFAULT_PLANET_IMG_FILENAME);	
		return planet;
	}

	public Planet buildPlanetFromFile(String planetFile) {
		List<String> planetAttributes = new ArrayList(PlanetParser.processFile(planetFile));
		setName(planetAttributes.get(0));
		setDiameter(Double.parseDouble(planetAttributes.get(1)));
		setTemperature(Double.parseDouble(planetAttributes.get(2)));
		setNumberOfMoons(Integer.parseInt(planetAttributes.get(3)));
		setImage(planetAttributes.get(4));
		return this.planet;
	}
	public Planet buildPlanetFromInput(List<String> planetAttributes) {
		try {
			setName(planetAttributes.get(0));
			setDiameter(Double.parseDouble(planetAttributes.get(1)));
			setTemperature(Double.parseDouble(planetAttributes.get(2)));
			setNumberOfMoons(Integer.parseInt(planetAttributes.get(3)));
			setImage(planetAttributes.get(4));
			System.out.println(planet);
			}catch(NumberFormatException e) {
				e.printStackTrace();
			}
		return this.planet;
	}
	
	public Planet getPlanet() {
		return this.planet;
	}
	
	public void setName(String name) {
		if(planetInput.validName(name) && planetInput.validNameLength(name))
			planet.setName(name);
		else {
			UserAlert.createAlert(UserAlertType.InputError, PlanetAttributes.name);
		}
	}
	
	public void setDiameter(double diameter) {
		if(planetInput.validDiameter(diameter))
			planet.setDiameter(diameter);
		else {
			planet.setDiameter(diameter);
			UserAlert.createAlert(UserAlertType.InputError, PlanetAttributes.diameter);
		}
	}
	
	public void setTemperature(double meanTemperature) {
		if(planetInput.validTemperature(meanTemperature))
			planet.setMeanTemperature(meanTemperature);
		else {
			UserAlert.createAlert(UserAlertType.InputError, PlanetAttributes.temperature);
		}
	}
	
	public void setNumberOfMoons(int numberOfMoons) {
		if(planetInput.validNumberOfMoons(numberOfMoons))
			planet.setNumberOfMoons(numberOfMoons);
		else {
			UserAlert.createAlert(UserAlertType.InputError, PlanetAttributes.moons);
		}
	}
	public double calculateTemperatureF(double celsiusTemperature) {
		double meanTemperatureF;
		meanTemperatureF = CELSIUS_TO_FAHRENHEIT_AMOUNT * celsiusTemperature 
			+ CELSIUS_TO_FAHRENHEIT_ADD_AMOUNT;
		return meanTemperatureF;
	}
	
	public double calculateDiameterMi(double diameterKM) {
		double diameterMi;
		diameterMi = diameterKM * KM_TO_MI_AMOUNT;
		return diameterMi;
	}
	
	public void setImage(String fileName) {
		File file = new File(fileName);
		if(file != null) {
			planet.setImgFileName(fileName);
			System.out.println("File Exists");
		}
		else {
			System.out.println("File does not Exists");
			planet.setImgFileName(DEFAULT_PLANET_IMG_FILENAME);
		}			
	}
	
}
