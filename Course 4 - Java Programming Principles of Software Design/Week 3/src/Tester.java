import java.util.ArrayList;

import edu.duke.FileResource;

public class Tester {
	public void testGetFollows() {
		MarkovOne markov = new MarkovOne();
		markov.setTraining("this is a test yes this is a test.");
		ArrayList<String> followSet = markov.getFollows("t");
		for(String ch : followSet)
			System.out.print(ch + ",");
	}
	
	public void testGetFollowsWithFile() {
		FileResource fr = new FileResource("src/data/confucius.txt");
		String st = fr.asString();
		st = st.replace('\n', ' ');
		MarkovOne markov = new MarkovOne();
		markov.setTraining(st);
		
		ArrayList<String> followSet = markov.getFollows("he");
		System.out.println("# Characters Found: " + followSet.size());
	}
	
	public static void main(String args[]) {
		Tester obj = new Tester();
		//obj.testGetFollows();
		obj.testGetFollowsWithFile();
	}
}
