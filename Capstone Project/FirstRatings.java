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
}
