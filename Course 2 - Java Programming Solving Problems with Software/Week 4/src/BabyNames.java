import org.apache.commons.csv.CSVRecord;
import edu.duke.FileResource;
import edu.duke.DirectoryResource;
import java.io.File;

public class BabyNames {
	public void totalBirths(FileResource fr) {
		int totalBirths = 0;
		int totalBoys = 0;
		int totalGirls = 0;
		for (CSVRecord rec : fr.getCSVParser(false)) {
			int numBorn = Integer.parseInt(rec.get(2));
			totalBirths += numBorn;
			if (rec.get(1).equals("M"))
				totalBoys += numBorn;
			else
				totalGirls += numBorn;
			
		}
		System.out.println("Total Births " + totalBirths);
		System.out.println("Total Girls " + totalGirls);
		System.out.println("Total Boys " + totalBoys);
	}
	
	public void totalNames(FileResource fr) {
		int totalBirths = 0;
		int totalBoys = 0;
		int totalGirls = 0;
		for (CSVRecord rec : fr.getCSVParser(false)) {
			totalBirths += 1;
			if (rec.get(1).equals("M"))
				totalBoys += 1;
			else
				totalGirls += 1;
			
		}
		System.out.println("Total Names " + totalBirths);
		System.out.println("Total Girl Names " + totalGirls);
		System.out.println("Total Boy Names " + totalBoys);
	}
	
	public int getRank(int year, String name, String gender) {
		int rank = 0;
		boolean found = false;
		String filePath = String.format("src/data/yob%d.csv", year);
		FileResource fr = new FileResource(filePath);
		for (CSVRecord rec : fr.getCSVParser(false)) {
			if (rec.get(1).equals(gender)) {
				rank += 1;
				if (rec.get(0).equals(name)) {
					found = true;
					break;
				}
			}
		}
		if (found)
			return rank;
		else
			return -1;
	}
	
	public String getName(int year, int rank, String gender) {
		int currIndex = 0;
		String name = "";
		String filePath = String.format("src/data/yob%d.csv", year);
		FileResource fr = new FileResource(filePath);
		for (CSVRecord rec : fr.getCSVParser(false)) {
			if (rec.get(1).equals(gender)) {
				currIndex += 1;
				if (currIndex == rank) {
					name = rec.get(0);
					break;
				}
			}
		}
		if (name.isEmpty())
			return "NO NAME";
		else
			return name;
	}
	
	public void whatIsNameInYear(String name, int year, int newYear, String gender) {
		int rank = getRank(year, name, gender);
		String newName = getName(newYear, rank, gender);
		System.out.println(String.format("%s born in %d would be %s if she was born in %d.", name, year, newName, newYear));
	}
	
	public int yearOfHighestRank(String name, String gender) {
		int highestRank = Integer.MAX_VALUE;
		int highestRankYear = Integer.MAX_VALUE;
		DirectoryResource dr = new DirectoryResource();
		for (File f : dr.selectedFiles()) {
			int year = Integer.parseInt(f.getName().substring(3, 7));
			int currentRank = getRank(year, name, gender);
			if (currentRank < highestRank && currentRank != -1) {
				highestRank = currentRank;
				highestRankYear = year;
			}
		}
		if (highestRankYear == Integer.MAX_VALUE)
			return -1;
		else
			return highestRankYear;
	}
	
	public double getAverageRank(String name, String gender) {
		double sumRank = 0.0;
		int count = 0;
		DirectoryResource dr = new DirectoryResource();
		for (File f : dr.selectedFiles()) {
			int year = Integer.parseInt(f.getName().substring(3, 7));
			int rank = getRank(year, name, gender);
			if (rank != -1) {
				sumRank += rank;
				count += 1;
			}
		}
		if (count == 0)
			return -1.0;
		else
			return sumRank / count;
	}
	
	public int getTotalBirthsRankedHigher(int year, String name, String gender) {
		int higherRankBirths = 0;
		String filePath = String.format("src/data/yob%d.csv", year);
		FileResource fr = new FileResource(filePath);
		for (CSVRecord rec : fr.getCSVParser(false)) {
			if (rec.get(1).equals(gender)) {
				if (rec.get(0).equals(name)) {
					break;
				}
				higherRankBirths += Integer.parseInt(rec.get(2));
			}
		}
		return higherRankBirths;
	}
	
	public static void main(String[] args) {
		BabyNames obj = new BabyNames();
		
		// Assignment Testing
		//int rank = obj.getRank(2012, "Mason", "M");
		//System.out.println(rank);
		//System.out.println(obj.getName(2012, 6, "M"));
		//obj.whatIsNameInYear("Isabella", 2012, 2014, "F");
		//System.out.println(obj.yearOfHighestRank("Mason", "M"));
		//System.out.println(obj.getAverageRank("Jacob", "M"));
		//System.out.println(obj.getTotalBirthsRankedHigher(2012, "Ethan", "M"));
		
		// Quiz Questions
		//FileResource fr = new FileResource("src/data/yob1900.csv");
		//obj.totalNames(fr);
		//FileResource fr = new FileResource("src/data/yob1905.csv");
		//obj.totalNames(fr);
		//System.out.println(obj.getRank(1960, "Emily", "F"));
		//System.out.println(obj.getRank(1971, "Frank", "M"));
		//System.out.println(obj.getName(1980, 350, "F"));
		//System.out.println(obj.getName(1982, 450, "M"));
		//obj.whatIsNameInYear("Susan", 1972, 2014, "F");
		//obj.whatIsNameInYear("Owen", 1974, 2014, "M");
		//System.out.println(obj.yearOfHighestRank("Genevieve", "F"));
		//System.out.println(obj.yearOfHighestRank("Mich", "M"));
		//System.out.println(obj.getAverageRank("Susan", "F"));
		//System.out.println(obj.getAverageRank("Robert", "M"));
		//System.out.println(obj.getTotalBirthsRankedHigher(1990, "Emily", "F"));
		//System.out.println(obj.getTotalBirthsRankedHigher(1990, "Drew", "M"));
	}
}
