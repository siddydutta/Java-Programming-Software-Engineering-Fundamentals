import java.util.ArrayList;

public class MarkovModel extends AbstractMarkovModel {
	private int N;
	
	public MarkovModel(int N) {
		this.N = N;
	}
	
	@Override
	public String getRandomText(int numChars) {
		if (myText == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length() - N);
		String key = myText.substring(index, index + N);
		sb.append(key);
		
		for(int k=0; k < numChars-N; k++){
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
	
	public String toString() {
		return "Markov Model of Order " + N;
	}

}
