package delegate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

import model.Planet;

public class PlanetFileReader implements Iterator{
	
	private Scanner fileScanner = null;
	protected int recordLength = 0;
	
	protected PlanetFileReader openFile(String file) throws FileNotFoundException {
		PlanetFileReader planetFile = new PlanetFileReader();
		planetFile.fileScanner = new Scanner(new File(file));
		return planetFile;
	}

	@Override
	public boolean hasNext() {
		if (this.fileScanner.hasNextLine()) {
			this.recordLength++;
			return true;
		}
		else {
			if (this.recordLength == 0) {
				this.fileScanner.close();
				throw new NoSuchElementException("No records found in employee file");
			}
			return false;
		}
	}

	@Override
	public String next() {
		String line = null;
		line = this.fileScanner.nextLine();
		return line;
	}

	protected void closeFileScanner() {
		if (this.fileScanner != null)
			this.fileScanner.close();
	}



}
