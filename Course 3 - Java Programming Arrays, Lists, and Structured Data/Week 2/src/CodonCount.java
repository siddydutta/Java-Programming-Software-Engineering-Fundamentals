import java.util.HashMap;
import edu.duke.FileResource;

public class CodonCount {
	private HashMap<String, Integer> codonCounts;
	
	public CodonCount() {
		codonCounts = new HashMap<String, Integer>();
	}
	
	public void buildCodonMap(int start, String dna) {
		codonCounts.clear();
		for(int i = start; i < dna.length(); i += 3) {
			try {
				String codon = dna.substring(i, i+3);
				if (codonCounts.containsKey(codon)) {
					int value = codonCounts.get(codon);
					codonCounts.put(codon, value+1);
				}
				else {
					codonCounts.put(codon, 1);
				}
			}
			catch(Exception e) {
				
			}
		}
	}
	
	public String getMostCommonCodon() {
		int maxCount = 0;
		String mostCommonCodon = "";
		for (String key : codonCounts.keySet()) {
			int value = codonCounts.get(key);
			if (value > maxCount) {
				maxCount = value;
				mostCommonCodon = key;
			}
		}
		return mostCommonCodon;
	}
	
	public void printCodonCounts(int start, int end) {
		for (String codon : codonCounts.keySet()) {
			int count = codonCounts.get(codon);
			if (count >= start && count <= end)
				System.out.println(codon + " " + count);
		}
	}
	
	public void testerMethod() {
		FileResource fr = new FileResource("src/data/dnaMystery2.txt");
		String dna = fr.asString().trim().toUpperCase();
		int start = 3, end = 7;
		for (int rf = 0; rf <= 2; rf += 1) {
			buildCodonMap(rf, dna);
			System.out.print("Reading frame starting with " + rf);
			System.out.println(" results in " + codonCounts.size() + " unique codons");
			String mostCommonCodon = getMostCommonCodon();
			System.out.print("and most common codon is " + mostCommonCodon);
			System.out.println(" with count " + codonCounts.get(mostCommonCodon));
			System.out.print("Counts of codons between " + start);
			System.out.println(" and " + end + " inclusive are:");
			printCodonCounts(start, end);
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		CodonCount obj = new CodonCount();
		obj.testerMethod();
	}

}
