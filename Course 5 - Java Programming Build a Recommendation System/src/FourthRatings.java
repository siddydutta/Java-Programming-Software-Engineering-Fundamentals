import java.util.ArrayList;
import java.util.Collections;

public class FourthRatings {
    public FourthRatings() {
    }
    
    public double getAverageByID(String id, int minimalRaters) {
    	id = String.format("%07d", Integer.parseInt(id));
    	ArrayList<Double> allRatings = new ArrayList<Double>(); // All ratings for a particular movie
    	for (Rater rater : RaterDatabase.getRaters()) {
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
    
    private double dotProduct(Rater me, Rater r) {
    	double result = 0.0;
    	ArrayList<String> itemsRated = me.getItemsRated();
    	for (String item : itemsRated) {
    		if (r.hasRating(item)) {
    			result += ((me.getRating(item) - 5.0) * (r.getRating(item) - 5.0));
    		}
    	}
    	return result;
    }
    
    private ArrayList<Rating> getSimilarities(String id) {
    	ArrayList<Rating> list = new ArrayList<Rating>();
    	Rater me = RaterDatabase.getRater(id);
    	for (Rater r : RaterDatabase.getRaters()) {
    		double sim = dotProduct(me, r);
    		if (!r.getID().equals(me.getID()) && sim > 0.0)
    			list.add(new Rating(r.getID(), sim));
    	}
    	Collections.sort(list, Collections.reverseOrder());
    	return list;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
    	return getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, new TrueFilter());
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria) {
    	ArrayList<Rating> list = new ArrayList<Rating>();
    	ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);

    	ArrayList<Rating> similarRaters = getSimilarities(id);
    	for (String movieID : movies) {
    		ArrayList<Double> weightedRating = new ArrayList<Double>(); // Stores weighted rating of top numSimilarRaters
    		for (int i = 0; i < numSimilarRaters; i++) {
    			try {
    				Rater currentRater = RaterDatabase.getRater(similarRaters.get(i).getItem());
	    			// Movie already rated by me
	    			if(RaterDatabase.getRater(id).hasRating(movieID)) 
	    				continue;
	    			// Rater similarity score * rater movie rating 
	    			if (currentRater.hasRating(movieID))
	    				weightedRating.add(currentRater.getRating(movieID) * similarRaters.get(i).getValue());
    			}
    			catch (Exception e) {
    				break;
    			}
    		}
    		if (weightedRating.size() >= minimalRaters) {
    			double sum = 0.0;
    			for (Double rate : weightedRating)
    				sum += rate;
    			list.add(new Rating(movieID, sum/weightedRating.size()));
    		}
    		
    	}
    	Collections.sort(list, Collections.reverseOrder());
    	return list;
    }
}
