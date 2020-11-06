import java.util.ArrayList;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        //this("src/data/ratedmovies_short.csv", "src/data/ratings_short.csv");
        this("src/data/ratedmoviesfull.csv", "src/data/ratings.csv");
    }
    
    public SecondRatings(String moviefile , String ratingsfile) {
    	FirstRatings obj = new FirstRatings();
    	myMovies = obj.loadMovies(moviefile);
    	myRaters = obj.loadRaters(ratingsfile);
    }
    
    public int getMovieSize() {
    	return myMovies.size();
    }
    
    public int getRaterSize() {
    	return myRaters.size();
    }
    
    public double getAverageByID(String id, int minimalRaters) {
    	id = String.format("%07d", Integer.parseInt(id));
    	ArrayList<Double> allRatings = new ArrayList<Double>(); // All ratings for a particular movie
    	for (Rater rater : myRaters) {
    		if (rater.hasRating(id)) {
    			allRatings.add(rater.getRating(id));
    		}
    	}

    	if (allRatings.size() < minimalRaters)
    		return 0.0;
    	else {
    		double sum = 0.0;
    		for (double rate : allRatings)
    			sum += rate;
    		return sum / allRatings.size();
    	}
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
    	ArrayList<Rating> avgRatings = new ArrayList<Rating>();
    	for (Movie movie : myMovies) {
    		double rating = getAverageByID(movie.getID(), minimalRaters);
    		if (rating != 0.0)
    			avgRatings.add(new Rating(movie.getID(), rating));
    	}
    	return avgRatings;
    }
    
    public String getTitle(String id) {
    	for (Movie movie : myMovies) {
    		if (movie.getID().equals(id))
    			return movie.getTitle();
    	}
    	return "NO SUCH MOVIE.";
    }
    
    public String getID(String title) {
    	for (Movie movie : myMovies) {
    		if (movie.getTitle().equals(title))
    			return movie.getID();
    	}
    	return "NO SUCH MOVIE.";
    }
}
