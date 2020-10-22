package weather;

import org.apache.commons.csv.CSVRecord;

import java.io.File;

import org.apache.commons.csv.CSVParser;
import edu.duke.FileResource;
import edu.duke.DirectoryResource;

public class lowestHumidity {
	public CSVRecord lowestHumidityInFile(CSVParser parser) {
		CSVRecord lowestHumidityRecord = null;
		for(CSVRecord row : parser) {
			String humidityValue = row.get("Humidity");
			if (humidityValue.equals("N/A"))
				continue;
			
			if (lowestHumidityRecord == null)
				lowestHumidityRecord = row;
			else {
				double currentHumidity = Double.parseDouble(humidityValue);
				double lowestHumidity = Double.parseDouble(lowestHumidityRecord.get("Humidity"));
				if (currentHumidity < lowestHumidity)
					lowestHumidityRecord = row;
			}
		}
		return lowestHumidityRecord;
	}
	
	public void testLowestHumidityInFile() {
		FileResource fr = new FileResource("src/weather/data/2014/weather-2014-07-22.csv");
		CSVRecord lowestHumidityRecord = lowestHumidityInFile(fr.getCSVParser());
		System.out.print("Lowest Humidity was " + lowestHumidityRecord.get("Humidity"));
		System.out.print(" at " + lowestHumidityRecord.get("DateUTC"));
	}
	
	
	public CSVRecord lowestHumidityInManyFiles() {
		CSVRecord lowestHumidityRecord = null;
		DirectoryResource dr = new DirectoryResource();
		for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			CSVRecord currentHumidityRecord = lowestHumidityInFile(fr.getCSVParser());
			if (lowestHumidityRecord == null)
				lowestHumidityRecord = currentHumidityRecord;
			else {
				double currentHumidity = Double.parseDouble(currentHumidityRecord.get("Humidity"));
				double lowestHumidity = Double.parseDouble(lowestHumidityRecord.get("Humidity"));
				if (currentHumidity < lowestHumidity)
					lowestHumidityRecord = currentHumidityRecord;
			}
		}
		return lowestHumidityRecord;
	}
	
	public void testLowestHumidityInManyFiles() {
		CSVRecord lowestHumidityRecord = lowestHumidityInManyFiles();
		System.out.print("Lowest Humidity was " + lowestHumidityRecord.get("Humidity"));
		System.out.print(" at " + lowestHumidityRecord.get("DateUTC"));
	}
	public static void main(String[] args) {
		lowestHumidity obj = new lowestHumidity();
		//obj.testLowestHumidityInFile();
		obj.testLowestHumidityInManyFiles();
	}

}
