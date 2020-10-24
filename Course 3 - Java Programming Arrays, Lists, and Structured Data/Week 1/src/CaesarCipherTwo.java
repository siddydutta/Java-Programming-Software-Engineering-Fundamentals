public class CaesarCipherTwo {
	private String alphabet;
	private String shiftedAlphabet1;
	private String shiftedAlphabet2;
	private int key1;
	private int key2;
	
	public CaesarCipherTwo(int key1, int key2) {
		alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0,key1);
		shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0,key2);
		this.key1 = key1;
		this.key2 = key2;
	}
	
	public String encrypt(String input) {
    	String encryptedString = "";
    	for (int i = 0; i < input.length(); i++) {
            char currChar = input.charAt(i);
    		int idx = alphabet.indexOf(Character.toUpperCase(currChar));
    		if (idx != -1) {
	    		if (i % 2 == 0) {
	    			if (Character.isUpperCase(currChar))
	    				encryptedString += shiftedAlphabet1.charAt(idx);
	    			else
	    				encryptedString += Character.toLowerCase(shiftedAlphabet1.charAt(idx));
	    		}
	    		else {
	    			if (Character.isUpperCase(currChar))
	    				encryptedString += shiftedAlphabet2.charAt(idx);
	    			else
	    				encryptedString += Character.toLowerCase(shiftedAlphabet2.charAt(idx));
	    		}
    		}
    		else {
    			encryptedString += currChar;
    		}
    	}
    	return encryptedString;
	}
	
	public String decrypt(String input) {
		CaesarCipherTwo obj = new CaesarCipherTwo(26-key1, 26-key2);
		return obj.encrypt(input);
	}
}
