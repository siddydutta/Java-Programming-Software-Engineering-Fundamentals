package weather;

import org.apache.commons.csv.CSVRecord;

import edu.duke.FileResource;

import org.apache.commons.csv.CSVParser;

public class averageTemperature {
	public double averageTemperatureInFile(CSVParser parser) {
		double tempSum = 0.0;
		int count = 0;
		for (CSVRecord row : parser) {
			double temp = Double.parseDouble(row.get("TemperatureF"));
			tempSum += temp;
			count += 1;
		}
		return tempSum/count;
	}
	
	public void testAverageTemperatureInFile() {
		FileResource fr = new FileResource("src/weather/data/2013/weather-2013-08-10.csv");
		double avgTemp = averageTemperatureInFile(fr.getCSVParser());
		System.out.println("Average temperature in file is " + avgTemp);
	}
	
	public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
		double tempSum = 0.0;
		int count = 0;
		for (CSVRecord row : parser) {
			String humidityValue = row.get("Humidity");
			if (humidityValue.equals("N/A"))
				continue;
			
			if (Double.parseDouble(humidityValue) >= value) {
				double temp = Double.parseDouble(row.get("TemperatureF"));
				tempSum += temp;
				count += 1;
			}
		}
		if (count == 0)
			return 0.0;
		else
			return tempSum/count;
	}
	
	public void testAverageTemperatureWithHumidityInFile() {
		FileResource fr = new FileResource("src/weather/data/2013/weather-2013-09-02.csv");
		double avgTempWithHum = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(), 80);
		if (avgTempWithHum == 0.0)
			System.out.println("No temperatures with that humidity");
		else
			System.out.println("Average Temp with high Humidity is " + avgTempWithHum);
	}
	
	public static void main(String[] args) {
		averageTemperature obj = new averageTemperature();
		//obj.testAverageTemperatureInFile();
		obj.testAverageTemperatureWithHumidityInFile();
	}

}
