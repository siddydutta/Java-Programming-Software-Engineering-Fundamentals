import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MovieRunnerWithFilters {
	public void printAverageRatings() {
		ThirdRatings obj = new ThirdRatings("ratings.csv");
		System.out.println("# of Raters: " + obj.getRaterSize());
		
		MovieDatabase.initialize("ratedmoviesfull.csv");
		System.out.println("# of Movies: " + MovieDatabase.size());
		
		ArrayList<Rating> averageRatings = obj.getAverageRatings(35);
		Collections.sort(averageRatings, Collections.reverseOrder());
		
		for (Rating r : averageRatings) {
			System.out.println(String.format("%.2f", r.getValue()) + "\t" + MovieDatabase.getTitle(r.getItem()));
		}
		System.out.println("# of Movies Found: " + averageRatings.size());
	}
	
	public void printAverageRatingsByYear() {
		Filter filter = new YearAfterFilter(2000);
		ThirdRatings obj = new ThirdRatings("ratings.csv");
		System.out.println("# of Raters: " + obj.getRaterSize());
		MovieDatabase.initialize("ratedmoviesfull.csv");
		System.out.println("# of Movies: " + MovieDatabase.size());
		
		ArrayList<Rating> averageRatings = obj.getAverageRatingsByFilter(20, filter);
		Collections.sort(averageRatings, Collections.reverseOrder());
		
		for (Rating r : averageRatings) {
			System.out.println(String.format("%.2f", r.getValue()) + "\t" + MovieDatabase.getTitle(r.getItem()));
		}
		System.out.println("# of Movies Found: " + averageRatings.size());
	}
	
	public void printAverageRatingsByGenre() {
		Filter filter = new GenreFilter("Comedy");
		ThirdRatings obj = new ThirdRatings("ratings.csv");
		System.out.println("# of Raters: " + obj.getRaterSize());
		MovieDatabase.initialize("ratedmoviesfull.csv");
		System.out.println("# of Movies: " + MovieDatabase.size());
		
		ArrayList<Rating> averageRatings = obj.getAverageRatingsByFilter(20, filter);
		Collections.sort(averageRatings, Collections.reverseOrder());
		
		for (Rating r : averageRatings) {
			System.out.println(String.format("%.2f", r.getValue()) + "\t" + MovieDatabase.getTitle(r.getItem()));
			System.out.println("\t" + MovieDatabase.getGenres(r.getItem()));
		}
		System.out.println("# of Movies Found: " + averageRatings.size());
	}
	
	public void printAverageRatingsByMinutes() {
		Filter filter = new MinutesFilter(105, 135);
		ThirdRatings obj = new ThirdRatings("ratings.csv");
		System.out.println("# of Raters: " + obj.getRaterSize());
		MovieDatabase.initialize("ratedmoviesfull.csv");
		System.out.println("# of Movies: " + MovieDatabase.size());
		
		ArrayList<Rating> averageRatings = obj.getAverageRatingsByFilter(5, filter);
		Collections.sort(averageRatings, Collections.reverseOrder());
		
		for (Rating r : averageRatings) {
			System.out.println(String.format("%.2f", r.getValue()) + "\t" + MovieDatabase.getTitle(r.getItem()));
			System.out.println("\tTime: " + MovieDatabase.getMinutes(r.getItem()));
		}
		System.out.println("# of Movies Found: " + averageRatings.size());
	}
	
	public void printAverageRatingsByDirectors() {
		String directors = "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack";
		Filter filter = new DirectorsFilter(directors);
		
		ThirdRatings obj = new ThirdRatings("ratings.csv");
		System.out.println("# of Raters: " + obj.getRaterSize());
		MovieDatabase.initialize("ratedmoviesfull.csv");
		System.out.println("# of Movies: " + MovieDatabase.size());
		
		ArrayList<Rating> averageRatings = obj.getAverageRatingsByFilter(4, filter);
		Collections.sort(averageRatings, Collections.reverseOrder());
		
		for (Rating r : averageRatings) {
			System.out.println(String.format("%.2f", r.getValue()) + "\t" + MovieDatabase.getTitle(r.getItem()));
			System.out.println("\t" + MovieDatabase.getDirector(r.getItem()));
		}
		System.out.println("# of Movies Found: " + averageRatings.size());		
	}
	
	public void printAverageRatingsByYearAfterAndGenre() {
		Filter yearFilter = new YearAfterFilter(1990);
		Filter genreFilter = new GenreFilter("Drama");
		
		AllFilters filters = new AllFilters();
		filters.addFilter(yearFilter);
		filters.addFilter(genreFilter);
		
		ThirdRatings obj = new ThirdRatings("ratings.csv");
		System.out.println("# of Raters: " + obj.getRaterSize());
		MovieDatabase.initialize("ratedmoviesfull.csv");
		System.out.println("# of Movies: " + MovieDatabase.size());
		
		ArrayList<Rating> averageRatings = obj.getAverageRatingsByFilter(8, filters);
		Collections.sort(averageRatings, Collections.reverseOrder());
		
		for (Rating r : averageRatings) {
			System.out.println(String.format("%.2f", r.getValue()) + "\t" + MovieDatabase.getTitle(r.getItem()));
			System.out.println("\t" + MovieDatabase.getYear(r.getItem()) + "-" + MovieDatabase.getGenres(r.getItem()));
		}
		System.out.println("# of Movies Found: " + averageRatings.size());				
	}
	
	public void printAverageRatingsByDirectorsAndMinutes() {
		Filter timeFilter = new MinutesFilter(90, 180);
		String directors = "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack";
		Filter dirFilter = new DirectorsFilter(directors);
		
		AllFilters filters = new AllFilters();
		filters.addFilter(timeFilter);
		filters.addFilter(dirFilter);
		
		ThirdRatings obj = new ThirdRatings("ratings.csv");
		System.out.println("# of Raters: " + obj.getRaterSize());
		MovieDatabase.initialize("ratedmoviesfull.csv");
		System.out.println("# of Movies: " + MovieDatabase.size());
		
		ArrayList<Rating> averageRatings = obj.getAverageRatingsByFilter(3, filters);
		Collections.sort(averageRatings, Collections.reverseOrder());
		
		for (Rating r : averageRatings) {
			System.out.println(String.format("%.2f", r.getValue()) + "\t" + MovieDatabase.getTitle(r.getItem()));
			System.out.println("\t" + MovieDatabase.getMinutes(r.getItem()) + "-" + MovieDatabase.getDirector(r.getItem()));
		}
		System.out.println("# of Movies Found: " + averageRatings.size());	
	}
	
	public static void main(String[] args) {
		MovieRunnerWithFilters obj = new MovieRunnerWithFilters();
		//obj.printAverageRatings();
		//obj.printAverageRatingsByYear();
		//obj.printAverageRatingsByGenre();
		//obj.printAverageRatingsByMinutes();
		//obj.printAverageRatingsByDirectors();
		//obj.printAverageRatingsByYearAfterAndGenre();
		obj.printAverageRatingsByDirectorsAndMinutes();
	}
}
