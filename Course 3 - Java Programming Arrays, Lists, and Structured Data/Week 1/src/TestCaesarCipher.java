import edu.duke.FileResource;

public class TestCaesarCipher {
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
	
	public String breakCaesarCipher(String input) {
		int key = getKey(input);
		CaesarCipher obj = new CaesarCipher(key);
		return obj.decrypt(input);
	}
	
	public void simpleTests() {
		FileResource fr = new FileResource("src/data/message1.txt");
		String message = fr.asString();
		CaesarCipher obj = new CaesarCipher(18);
		String encrypted = obj.encrypt(message);
		System.out.println("Encrypted Message\n" + encrypted);
		String decrypted = obj.decrypt(encrypted);
		System.out.println("\nDecryptedMessage\n" + decrypted);	
		System.out.println("\nAutomatic Decryption\n" + breakCaesarCipher(encrypted));
	}
	
	public static void main(String[] args) {
		/*
		TestCaesarCipher testObj = new TestCaesarCipher();
		testObj.simpleTests();
		*/
		String str1 = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
		int key1 = 15;
		CaesarCipher obj1 = new CaesarCipher(key1);
		System.out.println(obj1.encrypt(str1));
	}

}
