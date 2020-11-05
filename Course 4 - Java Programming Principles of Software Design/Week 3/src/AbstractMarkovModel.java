import java.util.ArrayList;
import java.util.Random;

public abstract class AbstractMarkovModel implements IMarkovModel {
	protected String myText;
	protected Random myRandom;
	
	public AbstractMarkovModel() {
		myRandom = new Random();
	}
	
	public void setTraining(String s) {
		myText = s.trim();
	}
	
	public void setRandom(int seed) {
		myRandom = new Random(seed);
	}
	
	abstract public String getRandomText(int numChars);
	
	protected ArrayList<String> getFollows(String key) {
		ArrayList<String> followSet = new ArrayList<String>();
		int pos = 0;
		while (pos < myText.length()) {
			int index = myText.indexOf(key, pos);
			if (index == -1)
				break;
			int nextIndex = index + key.length();
			if (nextIndex >= myText.length())
				break;
			followSet.add(myText.substring(nextIndex, nextIndex+1));
			pos = index + 1;
		}
		return followSet;
	}
}
