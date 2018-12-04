package delegate;

/**Delegatee **/
public class PlanetInputValidater {
	public final int minPlanetNameLength = 1;
	public final int maxPlanetNameLength = 255;
	public final double minPlanetDiameter = 0;
	public final double maxPlanetDiameter = 200000;
	public final double minPlanetTemperature = -273.15;
	public final double maxPlanetTemperature = 500.0;
	public final int minNumberOfMoons = 0;
	public final int maxNumberOfMoons = 1000;

	//1 to 255 characters
	//⁃ Valid planet name characters are A..Z, a..z, 0..9, and space, hyphen, and period
	public boolean validNameLength(String name) {
		if(name.length() >= minPlanetNameLength && name.length() <= maxPlanetNameLength) {
			return true;
		}
		return false;
	}
	// TODO add regex
	public boolean validName(String name) {
		if(name.matches("\\w*[ .-]*")) {
			return true;
		}
		return false;
	}

	public boolean validDiameter(double diameter) {
		if(diameter >= minPlanetDiameter && diameter <= maxPlanetDiameter) {
			return true;
		}
		return false;
	}

	public boolean validTemperature(double meanTemperature) {
		if(meanTemperature >= minPlanetTemperature && meanTemperature <= maxPlanetTemperature) {
			return true;
		}
		return false;
	}

	public boolean validNumberOfMoons(int numberOfMoons) {
		if(numberOfMoons >= minNumberOfMoons && numberOfMoons <= maxNumberOfMoons) {
			return true;
		}
		return false;
	}

}
