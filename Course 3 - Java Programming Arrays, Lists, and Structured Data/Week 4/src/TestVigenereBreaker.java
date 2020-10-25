import java.util.HashSet;
import edu.duke.FileResource; 

public class TestVigenereBreaker {
	public void testSliceString() {
		VigenereBreaker obj = new VigenereBreaker("src/data/athens_keyflute.txt");
		String slicedString = obj.sliceString("abcdefghijklm", 4, 5);
		System.out.println(slicedString);
	}
	
	public void testTryKeyLength() {
		VigenereBreaker obj = new VigenereBreaker("src/data/athens_keyflute.txt");
		FileResource fr = new FileResource("src/data/athens_keyflute.txt");
		int key[] = obj.tryKeyLength(fr.asString(), "flute".length(), 'e');
		for (int i = 0; i < key.length; i++)
			System.out.print(key[i] + " ");
	}
	
	public void testReadDictionary() {
		VigenereBreaker obj = new VigenereBreaker("src/data/athens_keyflute.txt");
		FileResource engDict = new FileResource("src/dictionaries/English");
		HashSet<String> engWords = obj.readDictionary(engDict);
		System.out.println("Number of English Words: " + engWords.size());
	}
	
	public void testBreakVigenere() {
		VigenereBreaker obj = new VigenereBreaker("src/data/athens_keyflute.txt");
		obj.breakVigenere();
	}
	
	public void testMostCommonCharIn() {
		VigenereBreaker obj = new VigenereBreaker("src/data/athens_keyflute.txt");
		FileResource engDict = new FileResource("src/dictionaries/English");
		HashSet<String> engWords = obj.readDictionary(engDict);
		System.out.println("Most Common Character in English Dictionary: " + obj.mostCommonCharIn(engWords));
	}
	
	public static void main(String[] args) {
		TestVigenereBreaker obj = new TestVigenereBreaker();
		obj.testSliceString();
		obj.testTryKeyLength();
		obj.testBreakVigenere();
		obj.testMostCommonCharIn();
		
		/* Practice Quiz - 1 
		VigenereBreaker obj = new VigenereBreaker("src/data/secretmessage1.txt");
		FileResource fr = new FileResource("src/data/secretmessage1.txt");
		int key[] = obj.tryKeyLength(fr.asString(), 4, 'e');
		for (int i = 0; i < key.length; i++)
			System.out.print(key[i] + " ");
		obj.breakVigenere();
		*/
		
		/* Practice Quiz - 2
		VigenereBreaker obj = new VigenereBreaker("src/data/secretmessage2.txt");
		FileResource fr = new FileResource("src/data/secretmessage2.txt");
		FileResource engDict = new FileResource("src/dictionaries/English");
		HashSet<String> engWords = obj.readDictionary(engDict);
		obj.breakVigenere();
		int key[] = obj.tryKeyLength(fr.asString(), 38, 'e');
		VigenereCipher vc = new VigenereCipher(key);
		String decrypted = vc.decrypt(fr.asString());
		System.out.println("\nTotal Valid Words: " + obj.countWords(decrypted, engWords));
		*/
		
		/* Practice Quiz - 3
		VigenereBreaker obj = new VigenereBreaker("src/data/secretmessage3.txt");
		obj.breakVigenere();
		VigenereBreaker obj = new VigenereBreaker("src/data/secretmessage4.txt");
		obj.breakVigenere();
		*/
	}
}
