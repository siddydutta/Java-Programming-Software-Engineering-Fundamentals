import edu.duke.FileResource;

public class Tester {
	public void testCaesarCipher() {
		// Testing CaesarCipher Class with Key 17
		CaesarCipher obj = new CaesarCipher(17);
		FileResource fr = new FileResource("src/data/titus-small.txt");
		String message = fr.asString();
		
		StringBuilder encryptedMessage = new StringBuilder(message);
		for (int i = 0; i < message.length(); i++) {
			char ch = obj.encryptLetter(encryptedMessage.charAt(i));
			encryptedMessage.setCharAt(i, ch);
		}
		System.out.println("Encrypted Message\n" + encryptedMessage.toString());
		
		StringBuilder decryptedMessage = new StringBuilder(encryptedMessage);
		for (int i = 0; i < message.length(); i++) {
			char ch = obj.decryptLetter(decryptedMessage.charAt(i));
			decryptedMessage.setCharAt(i, ch);
		}
		System.out.println("\nDecrypted Message\n" + decryptedMessage.toString());
	}
	
	public void testCaesarCracker() {
		// Testing CaesarCracker with default most common character 'e'
		CaesarCracker obj = new CaesarCracker();
		FileResource fr = new FileResource("src/data/titus-small_key5.txt");
		//CaesarCracker obj = new CaesarCracker('a');
		//FileResource fr = new FileResource("src/data/oslusiadas_key17.txt");
		String encryptedMessage = fr.asString();
		System.out.println("Encrypted Message\n" + encryptedMessage);
		String decryptedMessage = obj.decrypt(encryptedMessage);
		System.out.println("\nDecrypted Message\n" + decryptedMessage);
	}
	
	public void testVigenereCipher() {
		// Testing VigenereCipher with key 'rome'
		int key[] = {17, 14, 12, 4};
		VigenereCipher obj = new VigenereCipher(key);
		FileResource fr = new FileResource("src/data/titus-small.txt");
		String message = fr.asString();
		
		String encryptedMessage = obj.encrypt(message);
		System.out.println("Encrypted Message\n" + encryptedMessage);
		String decryptedMessage = obj.decrypt(encryptedMessage);
		System.out.println("\nDecrypted Message\n" + decryptedMessage);
	}
	
	public static void main(String[] args) {
		Tester obj = new Tester();
		obj.testVigenereCipher();
	}
}
