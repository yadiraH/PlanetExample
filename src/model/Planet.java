package model;

public class Planet {
	
	private String name;
	private double diameter;
	private double meanTemperature;
	private int numberOfMoons;
	private String imgFileName;
	
	public Planet() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getDiameter() {
		return diameter;
	}

	public void setDiameter(double diameter) {
		this.diameter = diameter;
	}

	public double getMeanTemperature() {
		return meanTemperature;
	}

	public void setMeanTemperature(double meanTemperature) {
		this.meanTemperature = meanTemperature;
	}

	public int getNumberOfMoons() {
		return numberOfMoons;
	}

	public void setNumberOfMoons(int numberOfMoons) {
		this.numberOfMoons = numberOfMoons;
	}

	public String getImgFileName() {
		return imgFileName;
	}

	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}

	@Override
	public String toString() {
		return "Planet [name=" + name + ", diameter=" + diameter + ", meanTemperature=" 
			+ meanTemperature + ", numberOfMoons=" + numberOfMoons + ", imgFileName=" 
			+ imgFileName + "]";
	}	
}
