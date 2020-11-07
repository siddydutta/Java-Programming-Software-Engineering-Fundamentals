import java.util.ArrayList;
import java.util.HashMap;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVRecord;

public class FirstRatings {
	public ArrayList<Movie> loadMovies(String filename) {
		ArrayList<Movie> movies = new ArrayList<Movie>();
		FileResource dataFile = new FileResource(filename);
		
		for (CSVRecord record : dataFile.getCSVParser()) {
			Movie movie = new Movie(record.get("id"),
									record.get("title"),
									record.get("year"),
									record.get("genre"),
									record.get("director"),
									record.get("country"),
									record.get("poster"),
									Integer.parseInt(record.get("minutes")));
			movies.add(movie);
		}
		return movies;
	}
	
	public void testLoadMovies() {
		ArrayList<Movie> result = loadMovies("src/data/ratedmoviesfull.csv");
		System.out.println("# of Movies: " + result.size());
		
		int comedyMovies = 0;
		int longMovies = 0;
		for (Movie movie : result) {
			if (movie.getGenres().contains("Comedy"))
				comedyMovies += 1;
			if (movie.getMinutes() > 150)
				longMovies += 1;
		}
		System.out.println("# of movies including Comedy genre: " + comedyMovies);
		System.out.println("# of movies > 150 minutes in length: " + longMovies);
		
		HashMap<String, Integer> directorCounts = new HashMap<String, Integer>();
		for (Movie movie : result) {
			String[] directors = movie.getDirector().split(",");
			for (String director : directors) {
				director = director.trim();
				if (directorCounts.containsKey(director)) {
					int currCount = directorCounts.get(director);
					directorCounts.put(director, currCount+1);
				}
				else {
					directorCounts.put(director, 1);
				}
			}
		}
		
		int maxDirected = 0;
		for (String director : directorCounts.keySet()) {
			if (directorCounts.get(director) > maxDirected)
				maxDirected = directorCounts.get(director);
		}
		System.out.println("Maximum number of movies directed: " + maxDirected);
		
		ArrayList<String> dirsWithMax = new ArrayList<String>();
		for (String director : directorCounts.keySet()) {
			if (directorCounts.get(director) == maxDirected)
				dirsWithMax.add(director);
		}
		System.out.println("Directors with maximum directed: " + dirsWithMax.size());
		System.out.println("Directors: " + dirsWithMax);
	}
	
	public ArrayList<Rater> loadRaters(String filename) {
		ArrayList<Rater> raters = new ArrayList<Rater>();
		FileResource dataFile = new FileResource(filename);
		
		HashMap<String, ArrayList<Rating>> raterData = new HashMap<String, ArrayList<Rating>>();
		for (CSVRecord record : dataFile.getCSVParser()) {
			if (raterData.containsKey(record.get("rater_id"))) {
				Rating rating = new Rating(record.get("movie_id"), Double.parseDouble(record.get("rating")));
				ArrayList<Rating> currentRatings = raterData.get(record.get("rater_id"));
				currentRatings.add(rating);
				raterData.put(record.get("rater_id"), currentRatings);
			}
			else {
				Rating rating = new Rating(record.get("movie_id"), Double.parseDouble(record.get("rating")));
				ArrayList<Rating> ratingList = new ArrayList<Rating>();
				ratingList.add(rating);
				raterData.put(record.get("rater_id"), ratingList);
			}
		}
		
		for (String raterID : raterData.keySet()) {
			Rater obj = new EfficientRater(raterID);
			for (Rating rating : raterData.get(raterID))
				obj.addRating(rating.getItem(), rating.getValue());
			raters.add(obj);
		}
		
		return raters;
	}
	
	public void testLoadRaters() {
		ArrayList<Rater> result = loadRaters("src/data/ratings.csv");
		System.out.println("# of Raters: " + result.size());
		/*
		for (Rater rater : result) {
			System.out.println(rater.getID() + " " + rater.numRatings());
		}
		*/
		
		// Find the number of ratings for a particular rater. 
		for (Rater rater : result) {
			if (rater.getID().equals("193"))
				System.out.println(rater.numRatings());
		}
		
		int maxRatings = 0;
		for (Rater rater : result) {
			if (rater.numRatings() > maxRatings)
				maxRatings = rater.numRatings();
		}
		System.out.println("Maximum Number of Ratings by any Rater: " + maxRatings);
		System.out.print("Rater IDs with Max Number of Ratings:");
		for (Rater rater : result) {
			if (rater.numRatings() == maxRatings)
				System.out.print(" " + rater.getID());
		}
		System.out.println();
		
		// Find the number of ratings a particular movie has
		int numRatings = 0;
		for (Rater rater : result) {
			if (rater.hasRating("1798709"))
				numRatings += 1;
		}
		System.out.println("Number of ratings the movie has: " + numRatings);
		
		// How many different movies have been rated
		ArrayList<String> ratedMovieIDs = new ArrayList<String>();
		for (Rater rater : result) {
			ArrayList<String> moviesRated = rater.getItemsRated();
			for (String movieID : moviesRated) {
				if (!ratedMovieIDs.contains(movieID))
					ratedMovieIDs.add(movieID);
			}
		}
		System.out.println("Number of movies rated: " + ratedMovieIDs.size());
	}
	
	public static void main(String[] args) {
		FirstRatings obj = new FirstRatings();
		obj.testLoadMovies();
		obj.testLoadRaters();
	}
}
