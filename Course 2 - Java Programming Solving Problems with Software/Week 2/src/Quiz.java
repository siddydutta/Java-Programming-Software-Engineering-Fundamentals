import edu.duke.StorageResource;
import edu.duke.FileResource;

public class Quiz {
	public int findStopCodon(String dna, int startIndex, String stopCodon) {
		int currIndex = dna.indexOf(stopCodon, startIndex+3);
		while(currIndex != -1) {
			int diff = currIndex - startIndex;
			if (diff % 3 == 0)
				return currIndex;
			else
				currIndex = dna.indexOf(stopCodon, currIndex+1);
		}
		return dna.length();
	}
	
	
	public String findGene(String dna, int where) {
		int startIndex = dna.indexOf("ATG", where);
		if (startIndex == -1)
				return "";
		
		int taaIndex = findStopCodon(dna, startIndex, "TAA");
		int tagIndex = findStopCodon(dna, startIndex, "TAG");
		int tgaIndex = findStopCodon(dna, startIndex, "TGA");
		
		int temp = Math.min(taaIndex,tagIndex);
		int minIndex = Math.min(temp, tgaIndex);
		
		if (minIndex == dna.length())
			return "";

		return dna.substring(startIndex, minIndex+3);		
	}

	
	public StorageResource getAllGenes(String dna) {
		StorageResource geneList = new StorageResource();
		int startIndex = 0;
		while (true) {
			String currentGene = findGene(dna, startIndex);
			if (currentGene.isEmpty())
				break;
			geneList.add(currentGene);
			
			startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
		}
		return geneList;
	}


	public static double cgRatio(String dna) {
		double count = 0.0;
		for (int i = 0; i < dna.length(); i++)
		{
			if (dna.charAt(i) == 'C' || dna.charAt(i) == 'G')
				count += 1;
		}
		return count / dna.length();
	}

	
	public int countCTG(String dna) {
		int count = 0;
		int index = 0;
		while(index < dna.length()) {
			int found = dna.indexOf("CTG", index);
			if (found == -1)
				break;
			else {
				count += 1;
				index = found + 3;
			}
		}
		return count;
	}
	
	
	public void processGenes(StorageResource sr) {
		int moreLength = 0;
		int higherCG = 0;
		String longest = "";
		for (String gene: sr.data()) {
			if (gene.length() > 60) {
				System.out.println("More than 60 characters: " + gene);
				moreLength += 1;
			}
			if (cgRatio(gene) > 0.35) {
				System.out.println("Higher than 0.35 CG Ratio: " + gene);
				higherCG += 1;
			}
			if (gene.length() > longest.length())
				longest = gene;
		}
		System.out.println();
		System.out.println("Number of Strings longer than 60 characters: " + moreLength);
		System.out.println("Number of Strings higher than 0.35 CG ratio: " + higherCG);
		System.out.println("Gene with the longest length: " + longest);
		System.out.println("Longest length: " + longest.length());
	}


	public String mystery(String dna) {
		int pos = dna.indexOf("T");
		int count = 0;
		int startPos = 0;
		String newDna = "";
		if (pos == -1) 
			return dna;
		
		while(count < 3) {
			count += 1;
			newDna += dna.substring(startPos, pos);
			startPos = pos+1;
			pos = dna.indexOf("T", startPos);
			if (pos == -1)
				break;
		}
		newDna += dna.substring(startPos);
		return newDna;
	}
	
	
	public static void main(String args[]) {
		Quiz obj = new Quiz();
		FileResource fr = new FileResource("GRch38dnapart.fa");
		String dna = fr.asString();
		StorageResource genes = obj.getAllGenes(dna);
		int count = 0;
		for (String gene: genes.data())
			count += 1;
		System.out.println("Number of Genes: " + count);
		obj.processGenes(genes);
		System.out.println("Count of CTG Codon: " + obj.countCTG(dna));

		System.out.println(obj.mystery("ATGTAAGATGCCCTAGT"));
	}
}
