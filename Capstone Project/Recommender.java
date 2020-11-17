import java.util.*;


public interface Recommender {
    /**
     * This method returns a list of movie IDs that will be used to look up 
     * the movies in the MovieDatabase and present them to users to rate. 
     */
    public ArrayList<String> getItemsToRate();

    /**
     * This method returns nothing, but prints out an HTML table of the 
     * movies recommended for the given rater.
     * 
     * @param webRaterID the ID of a new Rater that has been already added to 
     *        the RaterDatabase with ratings for the movies returned by the 
     *        method getItemsToRate
     */
    public void printRecommendationsFor(String webRaterID);
}