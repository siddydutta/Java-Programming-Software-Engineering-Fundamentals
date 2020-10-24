import java.util.ArrayList;
import edu.duke.FileResource;

public class CharactersInPlay {
	private ArrayList<String> chars;
	private ArrayList<Integer> counts;
	
	public CharactersInPlay () {
		chars = new ArrayList<String>();
		counts = new ArrayList<Integer>();
	}
	private void update(String person) {
		int index = chars.indexOf(person);
		if (index == -1) {
			chars.add(person);
			counts.add(1);
		}
		else {
			int currCount = counts.get(index);
			counts.set(index, currCount+1);
		}
	}
	
	private void findAllCharacters() {
		chars.clear();
		counts.clear();
		
		FileResource fr = new FileResource("src/data/errors.txt");
		for (String line: fr.lines()) {
			while(true) {
				int startIndex = 0;
				int periodIndex = line.substring(startIndex).indexOf('.');
				if (periodIndex == -1)
					break;
				String possibleChar = line.substring(startIndex, periodIndex);
				update(possibleChar.stripLeading());
				line = line.substring(periodIndex+1);
			}
			
		}
	}
	
	public void charactersWithNumParts(int num1, int num2) {
		findAllCharacters();
		for (int i = 0; i < chars.size(); i++) {
			if (counts.get(i) >= num1 && counts.get(i) <= num2)
				System.out.println(chars.get(i) + " " + counts.get(i));
		}
	}
	
	public void tester() {
		findAllCharacters();
		//for (int i = 0; i < chars.size(); i++)
		//	System.out.println(chars.get(i) + " " + counts.get(i));
		charactersWithNumParts(10, 15);
	}
	
	public static void main(String[] args) {
		CharactersInPlay obj = new CharactersInPlay();
		obj.tester();
	}
}
