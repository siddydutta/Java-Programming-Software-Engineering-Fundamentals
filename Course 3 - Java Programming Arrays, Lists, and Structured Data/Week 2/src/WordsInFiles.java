import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;
import edu.duke.FileResource;
import edu.duke.DirectoryResource;

public class WordsInFiles {
	private HashMap<String, ArrayList<String>> mapWordFile;
	
	public WordsInFiles() {
		mapWordFile = new HashMap<String, ArrayList<String>>();
	}
	
	private void addWordsFromFile(File f) {
		FileResource fr = new FileResource(f);
		for (String word : fr.words()) {
			if (mapWordFile.containsKey(word)) {
				ArrayList<String> wordFiles = mapWordFile.get(word);
				if (!wordFiles.contains(f.getName())) {
						wordFiles.add(f.getName());
						mapWordFile.put(word, wordFiles);
				}
			}
			else {
				ArrayList<String> wordFiles = new ArrayList<String>();
				wordFiles.add(f.getName());
				mapWordFile.put(word, wordFiles);
			}
		}
	}
	
	public void buildWordFileMap() {
		mapWordFile.clear();
		DirectoryResource dr = new DirectoryResource();
		for(File f : dr.selectedFiles()) {
			addWordsFromFile(f);
		}
	}
	
	public int maxNumber() {
		int maxNumberFiles = 0;
		for(String word : mapWordFile.keySet()) {
			ArrayList<String> fileList = mapWordFile.get(word);
			if (fileList.size() > maxNumberFiles)
				maxNumberFiles = fileList.size();
		}
		return maxNumberFiles;
	}
	
	public ArrayList<String> wordsInNumFiles(int number) {
		ArrayList<String> words = new ArrayList<String>();
		for(String word : mapWordFile.keySet()) {
			ArrayList<String> fileList = mapWordFile.get(word);
			if (fileList.size() == number)
				words.add(word);
		}
		return words;
	}
	
	public void printFilesIn(String word) {
		ArrayList<String> files = mapWordFile.get(word);
		for(String file : files)
			System.out.println(file);
	}
	
	public void tester() {
		buildWordFileMap();
		int maxNumberFiles = maxNumber();
		System.out.println("Maximum number of files any word is in: " + maxNumberFiles);
		ArrayList<String> words = wordsInNumFiles(maxNumberFiles);
		for(String word : words) {
			System.out.println("File List for Word: " + word);
			//printFilesIn(word);
		}
		
	}
	
	public static void main(String[] args) {
		WordsInFiles obj = new WordsInFiles();
		obj.tester();
		
	}
}
