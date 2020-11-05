import java.util.ArrayList;
import java.util.Random;

public class MarkovFour {
    private String myText;
	private Random myRandom;
	
	public MarkovFour() {
		myRandom = new Random();
	}
	
	public void setRandom(int seed){
		myRandom = new Random(seed);
	}
	
	public void setTraining(String s){
		myText = s.trim();
	}
	
	public ArrayList<String> getFollows(String key) {
		ArrayList<String> followSet = new ArrayList<String>();
		int pos = 0;
		while (pos < myText.length()-key.length()) {
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
	
	public String getRandomText(int numChars){
		if (myText == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length()-4);
		String key = myText.substring(index, index+4);
		sb.append(key);
		
		for(int k=0; k < numChars-4; k++){
			ArrayList<String> followSet = getFollows(key);
			if (followSet.size() == 0)
				break;
			index = myRandom.nextInt(followSet.size());
			String next = followSet.get(index);
			sb.append(next);
			key = key.substring(1) + next;
		}
		return sb.toString();
	}
}
