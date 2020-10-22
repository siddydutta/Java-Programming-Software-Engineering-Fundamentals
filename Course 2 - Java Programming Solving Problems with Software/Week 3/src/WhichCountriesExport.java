/**
 * Reads a chosen CSV file of country exports and prints each country that exports coffee.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class WhichCountriesExport {
	public void listExporters(CSVParser parser, String exportOfInterest) {
		//for each row in the CSV File
		for (CSVRecord record : parser) {
			//Look at the "Exports" column
			String export = record.get("Exports");
			//Check if it contains exportOfInterest
			if (export.contains(exportOfInterest)) {
				//If so, write down the "Country" from that row
				String country = record.get("Country");
				System.out.println(country);
			}
		}
	}

	
	public void whoExportsCoffee() {
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		listExporters(parser, "coffee");
	}
	
	
	public void countryInfo(CSVParser parser, String country) {
		for (CSVRecord record : parser) {
			String recordCountry = record.get("Country");
			if (country.equals(recordCountry)) {
				System.out.print(recordCountry + ": ");
				System.out.print(record.get("Exports") + ": ");
				System.out.println(record.get("Value (dollars)"));
				break;
			}
		}
	}
	
	
	public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
		for (CSVRecord record : parser) {
			String exports = record.get("Exports");
			if (exports.contains(exportItem1) && exports.contains(exportItem2))
				System.out.println(record.get("Country"));
		}
	}
	
	
	public int numberOfExporters(CSVParser parser, String exportItem) {
		int count = 0;
		for (CSVRecord record : parser) {
			String exports = record.get("Exports");
			if (exports.contains(exportItem))
				count += 1;
		}
		return count;
	}
	
	
	public void bigExporters(CSVParser parser, String amount) {
		for (CSVRecord record : parser) {
			String value = record.get("Value (dollars)");
			if (value.length() > amount.length())
				System.out.println(record.get("Country") + " " + value);
		}
	}
	
	
	public void tester() {
		FileResource fr = new FileResource("exportdata.csv");
		CSVParser parser = fr.getCSVParser();
		countryInfo(parser, "Nauru");
		parser = fr.getCSVParser();
		listExportersTwoProducts(parser, "cotton", "flowers");
		parser = fr.getCSVParser();
		System.out.println(numberOfExporters(parser, "cocoa"));
		parser = fr.getCSVParser();
		bigExporters(parser, "$999,999,999,999");
	}
	
	
	public static void main(String args[]) {
		WhichCountriesExport obj = new WhichCountriesExport();
		obj.tester();
	}
}
