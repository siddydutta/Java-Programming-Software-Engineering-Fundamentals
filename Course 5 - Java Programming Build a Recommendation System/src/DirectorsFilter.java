
public class DirectorsFilter implements Filter {
	private String directors;
	
	public DirectorsFilter(String directors) {
		this.directors = directors;
	}	
	
	@Override
	public boolean satisfies(String id) {
		String[] allDirectors = directors.split(",");
		for (String director : allDirectors) {
			if (MovieDatabase.getDirector(id).contains(director))
				return true;
		}
		return false;
	}
}
