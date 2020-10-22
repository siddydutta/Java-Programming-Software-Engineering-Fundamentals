package weather;

import java.io.File;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

public class coldestTemperature {
	public CSVRecord coldestHourInFile(CSVParser parser) {
		CSVRecord coldestRecord = null;
		for (CSVRecord currentRow : parser) { 
			double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
			if (coldestRecord == null && currentTemp != -9999)
				coldestRecord = currentRow;
			else {
				double coldestTemp = Double.parseDouble(coldestRecord.get("TemperatureF"));
				if (currentTemp < coldestTemp && currentTemp != -9999)
					coldestRecord = currentRow;
			}
		}
		return coldestRecord;
	}
	
	
	public void testColdestHourInFile() {
		FileResource fr = new FileResource("src/weather/data/2014/weather-2014-05-01.csv");
		CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
		System.out.println("Coldest Temperature: " + coldest.get("TemperatureF"));
	}
	
	
	public File fileWithColdestTemperature() {
		File coldestFile = null;
		CSVRecord coldestRecord = null;
		DirectoryResource dr = new DirectoryResource();
		for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			CSVRecord currentRecord = coldestHourInFile(fr.getCSVParser());
			if (currentRecord == null)
				continue;
			
			if (coldestRecord == null) {
				coldestRecord = currentRecord;
				coldestFile = f;
			}
			else {
				double currentTemp = Double.parseDouble(currentRecord.get("TemperatureF"));
				double coldestTemp = Double.parseDouble(coldestRecord.get("TemperatureF"));
				if (currentTemp < coldestTemp) {
					coldestRecord = currentRecord;
					coldestFile = f;
				}
			}
		}
		return coldestFile;
	}

	
	public void testFileWithColdestTemperature() {
		File coldestFileName = fileWithColdestTemperature();
		FileResource fr = new FileResource(coldestFileName);
		System.out.println("Coldest day was in file: " + coldestFileName.getName());
		CSVRecord coldestRecord = coldestHourInFile(fr.getCSVParser());
		System.out.println("Coldest temperature on that day was: " + coldestRecord.get("TemperatureF"));
		System.out.println("All the temperatures on the coldest day were: ");
		for (CSVRecord row : fr.getCSVParser()) {
			System.out.println(row.get("DateUTC") + " " + row.get("TemperatureF"));
		}
	}
	
	public static void main(String args[]) {
		coldestTemperature obj = new coldestTemperature();
		//obj.testColdestHourInFile();
		obj.testFileWithColdestTemperature();
		
	}
}
