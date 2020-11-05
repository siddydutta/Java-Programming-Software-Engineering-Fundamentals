import edu.duke.*; 

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
			String st= markov.getRandomText(size);
			printOut(st);
		}
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource("src/data/obama.txt");
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int size = 200;
		
        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size, 42);
    }

    public void runEfficientMarkov() {
        
    	FileResource fr = new FileResource("src/data/confucius.txt");
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int size = 50;
		int seed = 792;
		
		/*
		String st = "yes-this-is-a-thin-pretty-pink-thistle";
		int size = 50;
		int seed = 42;
		*/
		EfficientMarkovModel obj = new EfficientMarkovModel(6);
		runModel(obj, st, size, seed);
		obj.printHashMapInfo();
    }
    
	private void printOut(String s){
		String[] words = s.split("\\s+");
		int psize = 0;
		System.out.println("----------------------------------");
		for(int k=0; k < words.length; k++){
			System.out.print(words[k]+ " ");
			psize += words[k].length() + 1;
			if (psize > 60) {
				System.out.println();
				psize = 0;
			}
		}
		System.out.println("\n----------------------------------");
	}
	
	public static void main(String args[]) {
		MarkovRunnerWithInterface obj = new MarkovRunnerWithInterface();
		//obj.runMarkov();
		obj.runEfficientMarkov();
	}
	
}
