import java.util.*;

public class EfficientMarkovModel extends AbstractMarkovModel {
    private int N;
    private HashMap<String, ArrayList<String>> followMap;
    
    public EfficientMarkovModel(int N) {
        myRandom = new Random();
        this.N = N;
        followMap = new HashMap<String, ArrayList<String>>();
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
        buildMap();
    }
    
    public void buildMap(){
        for (int i = 0; i < myText.length()-N; i++) {
			String newKey = myText.substring(i, i+N);
			if(!followMap.containsKey(newKey)) {
			     ArrayList<String> list = getFollows(newKey);
			     followMap.put(newKey,list);
			} 
        }
    }
    
    public void printHashMapInfo() {
       System.out.println("# Keys in HashMap: "+(followMap.size()+1));
       int maxSize = 0;
       for (String key : followMap.keySet()) {
           if(followMap.get(key).size()> maxSize)
        	   maxSize = followMap.get(key).size();
       }
       System.out.println("Max Size of Follow Set: " + maxSize);
    }
    
    public String getRandomText(int numChars) {
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-N);
        String key = myText.substring(index, index+N);
        sb.append(key);
        
        for(int k = 0; k < numChars;  k++){
            ArrayList<String> follows = followMap.get(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }
        return sb.toString();
    }    
}