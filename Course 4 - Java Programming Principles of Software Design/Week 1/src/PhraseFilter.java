public class PhraseFilter implements Filter {
	private String where;
	private String phrase;
	
	public PhraseFilter(String where, String phrase) {
		this.where = where;
		this.phrase = phrase;
	}

	@Override
	public boolean satisfies(QuakeEntry qe) {
		if (where.equals("start") && qe.getInfo().startsWith(phrase))
			return true;
		if (where.equals("end") && qe.getInfo().endsWith(phrase))
			return true;
		if (where.equals("any") && qe.getInfo().contains(phrase))
			return true;
		return false;
	}
	
	@Override
	public String getName() {
		return "Phrase Filter";
	}
}
