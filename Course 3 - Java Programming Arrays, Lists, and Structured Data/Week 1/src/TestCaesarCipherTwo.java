import edu.duke.FileResource;

public class TestCaesarCipherTwo {
	public int[] countLetters(String message) {
		String alph = "abcdefghijklmnopqrstuvwxyz";
		int[] counts = new int[26];
		for (int k = 0; k < message.length(); k++) {
			char ch = Character.toLowerCase(message.charAt(k));
			int dex = alph.indexOf(ch);
			if (dex != -1) {
				counts[dex] += 1;
			}
		}
		return counts;
	}

	public int maxIndex(int[] values) {
        int maxDex = 0;
        for (int k=0; k < values.length; k++) {
            if (values[k] > values[maxDex]) {
                maxDex = k;
            }
        }
        return maxDex;
    }
	
	public int getKey(String s) {
		int[] letterFreq = countLetters(s);
		int maxDex = maxIndex(letterFreq);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4-maxDex);
        }
        return dkey;
	}
	
	public String halfOfString(String message, int start) {
		String half = "";
		for (int i = start; i < message.length(); i += 2)
			half += message.charAt(i);
		return half;
	}

	public String breakCaesarCipher(String input) {
		String firstHalf = halfOfString(input, 0);
		String secondHalf = halfOfString(input, 1);
		int firstKey = getKey(firstHalf);
		int secondKey = getKey(secondHalf);
		System.out.println("Key1: " + firstKey + "\nKey2: " + secondKey);
		CaesarCipherTwo obj = new CaesarCipherTwo(firstKey, secondKey);
		return obj.decrypt(input);
	}
	
	public void simpleTests() {
		FileResource fr = new FileResource("data/message1.txt");
		String message = fr.asString();
		CaesarCipherTwo obj = new CaesarCipherTwo(17, 3);
		String encrypted = obj.encrypt(message);
		System.out.println("Encrypted Message\n" + encrypted);
		String decrypted = obj.decrypt(encrypted);
		System.out.println("\nDecryptedMessage\n" + decrypted);	
		System.out.println("\nAutomatic Decryption\n" + breakCaesarCipher(encrypted));
	}
	
	public static void main(String[] args) {
		/*
		TestCaesarCipherTwo testObj = new TestCaesarCipherTwo();
		testObj.simpleTests();
		 */
		String str1 = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
		int key11 = 21, key12 = 8;
		CaesarCipherTwo obj1 = new CaesarCipherTwo(key11, key12);
		System.out.println(obj1.encrypt(str1));
		
		String str2 = "Hfs cpwewloj loks cd Hoto kyg Cyy.";
		int key21 = 14, key22 = 24;
		CaesarCipherTwo obj2 = new CaesarCipherTwo(key21, key22);
		System.out.println(obj2.decrypt(str2));
		
		String str3 = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
		TestCaesarCipherTwo testObj1 = new TestCaesarCipherTwo();
		System.out.println(testObj1.breakCaesarCipher(str3));
		
		FileResource fr1 = new FileResource("src/data/mysteryTwoKeysQuiz.txt");
		String str4 = fr1.asString();
		TestCaesarCipherTwo testObj2 = new TestCaesarCipherTwo();
		System.out.println(testObj2.breakCaesarCipher(str4));

	}

}
