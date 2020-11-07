import java.util.ArrayList;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        //this("src/data/ratings_short.csv");
        this("src/data/ratings.csv");
    }
    
    public ThirdRatings(String ratingsfile) {
    	FirstRatings obj = new FirstRatings();
    	myRaters = obj.loadRaters("src/data/" + ratingsfile);
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
    	ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
    	for (String movieID : movies) {
    		double rating = getAverageByID(movieID, minimalRaters);
    		if (rating != 0.0)
    			avgRatings.add(new Rating(movieID, rating));
    	}
    	return avgRatings;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
    	ArrayList<String> filteredMovieIds = MovieDatabase.filterBy(filterCriteria);
    	ArrayList<Rating> filAvgRatings = new ArrayList<Rating>();
    	
    	for (String movieID: filteredMovieIds) {
    		double rating = getAverageByID(movieID, minimalRaters);
    		if (rating != 0.0)
    			filAvgRatings.add(new Rating(movieID, rating));
    	}
    	return filAvgRatings;
    	
    }
}
