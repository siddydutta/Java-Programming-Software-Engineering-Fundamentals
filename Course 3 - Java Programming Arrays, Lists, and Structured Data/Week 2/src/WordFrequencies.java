import edu.duke.*;
import java.util.ArrayList;

public class WordFrequencies
{
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique() {
    	myWords.clear();
    	myFreqs.clear();
    	
        FileResource resource = new FileResource();
        
        for(String s : resource.words()) {
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            if (index == -1){
                myWords.add(s);
                myFreqs.add(1);
            }
            else {
                int freq = myFreqs.get(index);
                myFreqs.set(index, freq+1);
            }
        }
    }
    
    public int findIndexOfMax(){
        int max = myFreqs.get(0);
        int maxIndex = 0;
        for(int i = 1; i < myFreqs.size(); i++) {
            if (myFreqs.get(i) > max) {
                max = myFreqs.get(i);
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    public void tester() {
        findUnique();
        System.out.println("Number of unique words: " + myWords.size());
        
        for(int i = 0; i < myFreqs.size(); i++) 
        	System.out.println(myFreqs.get(i) + " " + myWords.get(i));
        
        int index = findIndexOfMax();
        System.out.print("The word that occurs most often and its count are: ");
        System.out.println(myWords.get(index) + " " +myFreqs.get(index));
    }
    
    public static void main(String args[]) {
    	WordFrequencies obj = new WordFrequencies();
    	obj.tester();
    }
}
