import edu.duke.*;
import java.util.*;

public class GladLibMap {
	private HashMap<String, ArrayList<String>> myMap;

	private Random myRandom;
	
	//private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
	private static String dataSourceDirectory = "src/data";
	
	private ArrayList<String> usedWordList;
	private ArrayList<String> usedCategories;

	public GladLibMap(){
		myMap = new HashMap<String, ArrayList<String>>();
		initializeFromSource(dataSourceDirectory);
		myRandom = new Random();
		usedWordList = new ArrayList<String>();
		usedCategories = new ArrayList<String>();
	}
	
	public GladLibMap(String source){
		myMap = new HashMap<String, ArrayList<String>>();
		initializeFromSource(source);
		myRandom = new Random();
		usedWordList = new ArrayList<String>();
		usedCategories = new ArrayList<String>();
	}
	
	private void initializeFromSource(String source) {
		String categories [] = {"adjective", "noun", "color", "country",
								"name", "animal", "timeframe", "verb", "fruit"};
		for (String category : categories) {
			ArrayList<String> categoryList = readIt(source + "/" + category + ".txt");
			myMap.put(category, categoryList);
		}
	}
	
	private String randomFrom(ArrayList<String> source){
		int index = myRandom.nextInt(source.size());
		return source.get(index);
	}
	
	private String getSubstitute(String label) {
		if (!usedCategories.contains(label))
			usedCategories.add(label);
		if (label.equals("number")){
			return ""+myRandom.nextInt(50)+5;
		}
		return randomFrom(myMap.get(label));
	}
	
	private String processWord(String w){
		int first = w.indexOf("<");
		int last = w.indexOf(">",first);
		if (first == -1 || last == -1){
			return w;
		}
		String prefix = w.substring(0,first);
		String suffix = w.substring(last+1);

		String sub = "";
		do {
			sub = getSubstitute(w.substring(first+1,last));
		} while (usedWordList.contains(sub));
		usedWordList.add(sub);
		
		return prefix+sub+suffix;
	}
	
	private void printOut(String s, int lineWidth){
		int charsWritten = 0;
		for(String w : s.split("\\s+")){
			if (charsWritten + w.length() > lineWidth){
				System.out.println();
				charsWritten = 0;
			}
			System.out.print(w+" ");
			charsWritten += w.length() + 1;
		}
	}
	
	private String fromTemplate(String source){
		String story = "";
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		return story;
	}
	
	private ArrayList<String> readIt(String source){
		ArrayList<String> list = new ArrayList<String>();
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		return list;
	}
	
	public int totalWordsInMap() {
		int totalWords = 0;
		for(String category : myMap.keySet()) {
			totalWords += myMap.get(category).size();
		}
		return totalWords;
	}
	
	public int totalWordsConsidered() {
		int totalWords = 0;
		for (String category : usedCategories) {
			if (myMap.containsKey(category))
				totalWords += myMap.get(category).size();
		}
		return totalWords;
	}
	
	public void makeStory(){
	    System.out.println("\n");
		String story = fromTemplate("src/data/madtemplate2.txt");
		printOut(story, 60);
		System.out.println("\n\nTotal Number of Words Replaced: " + usedWordList.size());
		System.out.println("Total Possible Number of Words: " + totalWordsInMap());
		System.out.println("Total Considered Words: " + totalWordsConsidered());
	}
	
	
	public static void main(String args[]) {
		GladLibMap obj = new GladLibMap();
		obj.makeStory();
	}

}
