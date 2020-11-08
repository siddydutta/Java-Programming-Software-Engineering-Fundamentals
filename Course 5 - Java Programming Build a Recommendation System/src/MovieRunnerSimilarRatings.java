import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerSimilarRatings {
	public void printAverageRatings() {
		FourthRatings obj = new FourthRatings();
		
		RaterDatabase.initialize("ratings.csv");
		System.out.println("# of Raters: " + RaterDatabase.size());
		
		MovieDatabase.initialize("ratedmoviesfull.csv");
		System.out.println("# of Movies: " + MovieDatabase.size());
		
		ArrayList<Rating> averageRatings = obj.getAverageRatings(35);
		Collections.sort(averageRatings, Collections.reverseOrder());
		
		for (Rating r : averageRatings) {
			System.out.println(String.format("%.2f", r.getValue()) + "\t" + MovieDatabase.getTitle(r.getItem()));
		}
		
		System.out.println("# of Movies Found: " + averageRatings.size());
		
	}
	
	public void printAverageRatingsByYearAfterAndGenre() {
		Filter yearFilter = new YearAfterFilter(1990);
		Filter genreFilter = new GenreFilter("Drama");
		
		AllFilters filters = new AllFilters();
		filters.addFilter(yearFilter);
		filters.addFilter(genreFilter);
		
		FourthRatings obj = new FourthRatings();
		RaterDatabase.initialize("ratings.csv");
		MovieDatabase.initialize("ratedmoviesfull.csv");
		System.out.println("# of Movies: " + MovieDatabase.size());
		
		ArrayList<Rating> averageRatings = obj.getAverageRatingsByFilter(8, filters);
		Collections.sort(averageRatings, Collections.reverseOrder());
		/*
		for (Rating r : averageRatings) {
			System.out.println(String.format("%.2f", r.getValue()) + "\t" + MovieDatabase.getTitle(r.getItem()));
			System.out.println("\t" + MovieDatabase.getYear(r.getItem()) + "-" + MovieDatabase.getGenres(r.getItem()));
		}
		*/
		System.out.println("# of Movies Found: " + averageRatings.size());				
	}
	
	public void printSimilarRatings() {
    	RaterDatabase.initialize("ratings.csv");
    	MovieDatabase.initialize("ratedmoviesfull.csv");
    	FourthRatings obj = new FourthRatings();
    	
    	ArrayList<Rating> similarMovies = obj.getSimilarRatings("65", 10, 5);
    	for (Rating movie : similarMovies) {
    		System.out.println(MovieDatabase.getTitle(movie.getItem()) + " " + movie.getValue());
    	}
	}
	
	public void printSimilarRatingsByGenre() {
    	RaterDatabase.initialize("ratings.csv");
    	MovieDatabase.initialize("ratedmoviesfull.csv");
    	FourthRatings obj = new FourthRatings();
    	
    	Filter genreFilter = new GenreFilter("Mystery");
    	ArrayList<Rating> similarMovies = obj.getSimilarRatingsByFilter("964", 20, 5, genreFilter);
    	for (Rating movie : similarMovies)
    		System.out.println(MovieDatabase.getTitle(movie.getItem()) + " " + movie.getValue());
	}
	
	public void printSimilarRatingsByDirector() {
    	RaterDatabase.initialize("ratings.csv");
    	MovieDatabase.initialize("ratedmoviesfull.csv");
    	FourthRatings obj = new FourthRatings();
    	
    	Filter directorsFilter = new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
    	ArrayList<Rating> similarMovies = obj.getSimilarRatingsByFilter("120", 10, 2, directorsFilter);
    	for (Rating movie : similarMovies)
    		System.out.println(MovieDatabase.getTitle(movie.getItem()) + " " + movie.getValue());
	}
	
	public void printSimilarRatingsByGenreAndMinutes() {
    	RaterDatabase.initialize("ratings.csv");
    	MovieDatabase.initialize("ratedmoviesfull.csv");
    	FourthRatings obj = new FourthRatings();
    	
    	Filter genreFilter = new GenreFilter("Drama");
		Filter timeFilter = new MinutesFilter(80, 160);
		
		AllFilters filters = new AllFilters();
		filters.addFilter(genreFilter);
		filters.addFilter(timeFilter);
		
    	ArrayList<Rating> similarMovies = obj.getSimilarRatingsByFilter("168", 10, 3, filters);
    	for (Rating movie : similarMovies)
    		System.out.println(MovieDatabase.getTitle(movie.getItem()) + " " + movie.getValue());
	}
	
	public void printSimilarRatingsByYearAfterAndMinutes() {
    	RaterDatabase.initialize("ratings.csv");
    	MovieDatabase.initialize("ratedmoviesfull.csv");
    	FourthRatings obj = new FourthRatings();
    	
    	Filter genreFilter = new YearAfterFilter(1975);
		Filter timeFilter = new MinutesFilter(70, 200);
		
		AllFilters filters = new AllFilters();
		filters.addFilter(genreFilter);
		filters.addFilter(timeFilter);
		
    	ArrayList<Rating> similarMovies = obj.getSimilarRatingsByFilter("314", 10, 5, filters);
    	for (Rating movie : similarMovies)
    		System.out.println(MovieDatabase.getTitle(movie.getItem()) + " " + movie.getValue());
	}
	
	public static void main(String[] args) {
		MovieRunnerSimilarRatings obj = new MovieRunnerSimilarRatings();
		obj.printSimilarRatings();
		//obj.printSimilarRatingsByGenre();
		//obj.printSimilarRatingsByDirector();
		//obj.printSimilarRatingsByGenreAndMinutes();
		//obj.printSimilarRatingsByYearAfterAndMinutes();
	}
}
