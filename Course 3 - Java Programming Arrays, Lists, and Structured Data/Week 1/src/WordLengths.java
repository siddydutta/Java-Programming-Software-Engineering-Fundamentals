import edu.duke.FileResource;

public class WordLengths {
	public int wordLength(String word) {
		int length = word.length();
		if (!Character.isLetter(word.charAt(length-1)) && length > 1)
			length -= 1;
		if (!Character.isLetter(word.charAt(0)))
			length -= 1;
		return length;
	}
	
	public void countWordLengths(FileResource resource, int counts[]) {
		for (String word : resource.words()) {
			int wordLength = wordLength(word);
			if (wordLength == -1)
				System.out.println(word);
			if (wordLength > counts.length)
				counts[counts.length-1] += 1;
			else
				counts[wordLength] += 1;
		}
		System.out.println(indexOfMax(counts));
	}
	
	public void testCountWordLengths() {
		FileResource fr = new FileResource("src/data/manyWords.txt");
		int counts[] = new int[31];
		countWordLengths(fr, counts);
	}
	
	public int indexOfMax(int values[]) {
		int index = 0;
		int max = values[index];
		for (int i = 1; i < values.length; i++) {
			if (values[i] > max) {
				max = values[i];
				index = i;
			}
		}
		return index;
	}
	
	public static void main(String[] args) {
		WordLengths obj = new WordLengths();
		obj.testCountWordLengths();	
	}

}
