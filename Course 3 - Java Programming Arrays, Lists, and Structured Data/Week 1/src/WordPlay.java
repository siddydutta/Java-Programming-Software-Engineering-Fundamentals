public class WordPlay {
	public boolean isVowel(char ch) {
		if (ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U')
			return true;
		if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u')
			return true;
		return false;
	}
	public String replaceVowels(String phrase, char ch) {
		String newPhrase = "";
		for (int i = 0; i < phrase.length(); i++) {
			if (isVowel(phrase.charAt(i)))
				newPhrase += ch;
			else
				newPhrase += phrase.charAt(i);
		}
		return newPhrase;
	}
	public String emphasize(String phrase, char ch) {
		String newPhrase = "";
		for (int i = 0; i < phrase.length(); i++) {
			if (phrase.charAt(i) == ch) {
				if (i % 2 == 0)
					newPhrase += "*";
				else
					newPhrase += "+";
			}
			else
				newPhrase += phrase.charAt(i);
		}
		return newPhrase;
	}
	public static void main(String[] args) {
		WordPlay obj = new WordPlay();
		System.out.println(obj.replaceVowels("Hello World", '*'));
		System.out.println(obj.emphasize("dna ctgaaactga", 'a'));
		System.out.println(obj.emphasize("Mary Bella Abracadabra", 'a'));
	}

}
