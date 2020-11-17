import java.util.ArrayList;
import java.util.HashMap;

public class RecommendationRunner implements Recommender {
	@Override
	public ArrayList<String> getItemsToRate() {
		// Initializing Movie & Rater Database
		MovieDatabase.initialize("ratedmoviesfull.csv");
		RaterDatabase.initialize("ratings.csv");
		
		// Counting Number of Ratings per Movie
		HashMap<String, Integer> movieRatingsCount = new HashMap<String, Integer>();
		for (Rater rater : RaterDatabase.getRaters()) {
			ArrayList<String> moviesRated = rater.getItemsRated();
			for (String movieID : moviesRated) {
				if (movieRatingsCount.containsKey(movieID)) {
					int currCount = movieRatingsCount.get(movieID);
					movieRatingsCount.put(movieID, currCount+1);
				}
				else {
					movieRatingsCount.put(movieID, 1);
				}
			}
		}
		
		// Filtering Movies Based on Number of Ratings (> minRatings)
		int minRatings = 40;
		ArrayList<String> movieItems = new ArrayList<String>();
		for (String movieID : movieRatingsCount.keySet()) {
			if (movieRatingsCount.get(movieID) > minRatings)
				movieItems.add(movieID);
		}
		return movieItems;
	}

	@Override
	public void printRecommendationsFor(String webRaterID) {
		// Initializations
		MovieDatabase.initialize("ratedmoviesfull.csv");
		RaterDatabase.initialize("ratings.csv");
		FourthRatings obj = new FourthRatings();
		
		// 20 Similar Raters & 5 Minimal Raters
		ArrayList<Rating> recommended = obj.getSimilarRatings(webRaterID, 20, 5);
		if (recommended.size() == 0)
			System.out.println("<h2>Sorry, you have quite a unique taste!</h2>");
		else {
			System.out.print("<h2>Top Movie(s) to Watch!</h2>");
			System.out.print("<table><tr><th>Movie</th><th>Similarity Score</th></tr>");
			int count = 0;
			for (int i = 0; i < recommended.size(); i++) {
				System.out.print("<tr><td>" + MovieDatabase.getTitle(recommended.get(i).getItem()) + "</td>");
				System.out.print("<td>" + String.format("%.2f", recommended.get(i).getValue()) + "</td></tr>");
				count += 1;
				if (count == 5)
					break;
			}
			System.out.print("</table>");
		}
	}
}
