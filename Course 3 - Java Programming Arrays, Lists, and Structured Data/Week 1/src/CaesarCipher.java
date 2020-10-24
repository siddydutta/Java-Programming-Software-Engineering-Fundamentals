public class CaesarCipher {
	private String alphabet;
	private String shiftedAlphabet;
	private int mainKey;
	
	public CaesarCipher(int key) {
		alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
		mainKey = key;
	}
	
    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder(input);
        for(int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            int idx = alphabet.indexOf(Character.toUpperCase(currChar));
            if(idx != -1) {
            	if (Character.isUpperCase(currChar)) {
            		char newChar = shiftedAlphabet.charAt(idx);
            		encrypted.setCharAt(i, newChar);
            	}
            	else {
            		char newChar = Character.toLowerCase(shiftedAlphabet.charAt(idx));
            		encrypted.setCharAt(i, newChar);
            	}
            }
        }
        return encrypted.toString();
    }
    
    public String decrypt(String input) {
    	CaesarCipher obj = new CaesarCipher(26-mainKey);
    	return obj.encrypt(input);
    }
}

