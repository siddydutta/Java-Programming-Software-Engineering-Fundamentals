import edu.duke.*;

public class MarkovRunner {
    public void runMarkovZero() {
		FileResource fr = new FileResource("src/data/confucius.txt");
		String st = fr.asString();
		st = st.replace('\n', ' ');
		MarkovZero markov = new MarkovZero();
		markov.setRandom(1024);
		markov.setTraining(st);
		for(int k=0; k < 3; k++){
			String text = markov.getRandomText(500);
			printOut(text);
		}
	}
	
    public void runMarkovOne() {
		FileResource fr = new FileResource("src/data/romeo.txt");
		String st = fr.asString();
		st = st.replace('\n', ' ');
		MarkovOne markov = new MarkovOne();
		markov.setTraining(st);
		markov.setRandom(365);
		String text = markov.getRandomText(500);
		printOut(text);
	}
    
    public void runMarkovFour() {
		FileResource fr = new FileResource("src/data/romeo.txt");
		String st = fr.asString();
		st = st.replace('\n', ' ');
		MarkovFour markov = new MarkovFour();
		markov.setTraining(st);
		markov.setRandom(715);
		String text = markov.getRandomText(500);
		printOut(text);  	
    }
    
    public void runMarkovModel() {
		FileResource fr = new FileResource("src/data/romeo.txt");
		String st = fr.asString();
		st = st.replace('\n', ' ');
		
		MarkovModel markovObj = new MarkovModel(7);
		markovObj.setTraining(st);
		markovObj.setRandom(953);
		String text = markovObj.getRandomText(500);
		printOut(text);
    }
    
    public void runMarkov() { 
        FileResource fr = new FileResource("src/data/confucius.txt"); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        MarkovWordOne markovWord = new MarkovWordOne(); 
        runModel(markovWord, st, 120, 139); 
    } 
    
    public void runMarkovTwo() {
        FileResource fr = new FileResource("src/data/confucius.txt"); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        MarkovWordTwo markovWordTwo = new MarkovWordTwo(); 
        runModel(markovWordTwo, st, 100, 832); 
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
		MarkovRunner obj = new MarkovRunner();
		//obj.runMarkovZero();
		//obj.runMarkovOne();
		//obj.runMarkovFour();
		obj.runMarkovModel();
		//obj.runMarkov();
    	//obj.runMarkovTwo();
	}
}
