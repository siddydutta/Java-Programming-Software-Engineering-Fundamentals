import edu.duke.FileResource;
import edu.duke.DirectoryResource;
import java.util.HashSet;
import java.util.HashMap;
import java.io.File;

public class VigenereBreaker {
	public FileResource fr;
	
	public VigenereBreaker() {
		fr = new FileResource();
	}
	
	public VigenereBreaker(String filePath) {
		fr = new FileResource(filePath);
	}
	
    public String sliceString(String message, int whichSlice, int totalSlices) {
        String slicedString = "";
        for (int i = whichSlice; i < message.length(); i += totalSlices)
        	slicedString += message.charAt(i);
        return slicedString;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker obj = new CaesarCracker(mostCommon);
        for (int i = 0; i < klength; i++) {
        	String encryptedSlicedString = sliceString(encrypted, i, klength);
        	key[i] = obj.getKey(encryptedSlicedString);
        }
        return key;
    }
    
    public HashSet<String> readDictionary(FileResource fr) {
    	HashSet<String> words = new HashSet<String>();
    	for (String word : fr.lines()) // One Word per Line
    		words.add(word.toLowerCase());
    	return words;
    }
    
    public int countWords(String message, HashSet<String> dictionary) {
    	int validWords = 0;
    	String messageWords[] = message.split("\\W+");
    	for (String messageWord : messageWords) {
    		if (dictionary.contains(messageWord.toLowerCase()))
    			validWords += 1;
    	}
    	return validWords;
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary) {
    	HashMap<Character, Integer> charFreqs = new HashMap<Character, Integer>();
    	for(String word : dictionary) {
    		for(int i = 0; i < word.length(); i++) {
    			char ch = word.charAt(i);
    			if (charFreqs.containsKey(ch)) {
    				int freq = charFreqs.get(ch);
    				charFreqs.put(ch, freq+1);
    			}
    			else
    				charFreqs.put(ch, 1);
    		}
    	}
    	
    	char mostCommon = ' ';
    	int maxCount = 0;
    	for (char ch : charFreqs.keySet()) {
    		if (charFreqs.get(ch) > maxCount) {
    			maxCount = charFreqs.get(ch);
    			mostCommon = ch;
    		}
    	}
    	return mostCommon;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
    	char mostCommon = mostCommonCharIn(dictionary);
    	int[] bestKey = new int[0];
    	int maxValidWords = 0;
    	String decrypted = "";
    	
    	for (int kl = 1; kl <= 100; kl++) { // encrypted.length() instead of 100.
    		int key[] = tryKeyLength(encrypted, kl, mostCommon);
    		VigenereCipher obj = new VigenereCipher(key);
    		String possibleDecrypted = obj.decrypt(encrypted);
    		if (countWords(possibleDecrypted, dictionary) > maxValidWords) {
    			maxValidWords = countWords(possibleDecrypted, dictionary);
    			decrypted = possibleDecrypted;
    			bestKey = key;
    		}
    	}
    	/*
    	System.out.print("Key is: ");
    	for (int shift : bestKey)
    		System.out.print(" " + shift);
    	System.out.println("\nKey Length: " + bestKey.length);
    	System.out.println("Valid Words: " + maxValidWords);
    	System.out.println("Total Word Count: " + decrypted.split("\\W+").length);
    	*/
    	return decrypted;
    }
    
    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
    	int maxValidWords = 0;
    	String decryptedMessage = "";
    	String bestLanguage = "";
    	
    	for (String language : languages.keySet()) {
    		String possibleDecrypted = breakForLanguage(encrypted, languages.get(language));
    		int validWords = countWords(possibleDecrypted, languages.get(language));
    		if (validWords > maxValidWords) {
    			maxValidWords = validWords;
    			decryptedMessage = possibleDecrypted;
    			bestLanguage = language;
    		}
    	}
    	System.out.println("\nDecrypted Message First Line\n" + decryptedMessage.substring(0, decryptedMessage.indexOf('\n')));
    	System.out.println("Language Dictionary Used: " + bestLanguage);
    }
    
    public void breakVigenere () {
    	DirectoryResource dr = new DirectoryResource();
    	HashMap<String, HashSet<String>> languages = new HashMap<String, HashSet<String>>();
    	
    	System.out.println("Loading Dictionaries");
    	for (File dictionary : dr.selectedFiles()) {
    		FileResource dict = new FileResource(dictionary);
    		HashSet<String> dictWords = readDictionary(dict);
    		languages.put(dictionary.getName(), dictWords);
    		System.out.println("Language: " + dictionary.getName() + " Words: " + dictWords.size());
    	}
    	System.out.println("Dictionaries Loaded\n");
    	
    	String encryptedMessage = fr.asString();
    	//System.out.println("Encrypted Message\n" + encryptedMessage);
    	breakForAllLangs(encryptedMessage, languages);
    	
    	/*
    	String decryptedMessage = breakForLanguage(encryptedMessage, dictWords);
    	System.out.println("\nDecrypted Message First Line\n" + decryptedMessage.substring(0, decryptedMessage.indexOf('\n')));
    	*/
    	
    }
}
