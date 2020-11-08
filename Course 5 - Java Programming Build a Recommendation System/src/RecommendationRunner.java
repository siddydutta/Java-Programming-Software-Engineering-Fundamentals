import java.util.ArrayList;
import java.util.Random;

public class RecommendationRunner implements Recommender {
	@Override
	// Returns 5 Random Movie IDs From 2015
	public ArrayList<String> getItemsToRate() {
		Random random = new Random();
		Filter yearFilter = new YearAfterFilter(2015);
		MovieDatabase.initialize("ratedmoviesfull.csv");
		ArrayList<String> allMoviesFromYear = MovieDatabase.filterBy(yearFilter);
		ArrayList<String> selectedMovies = new ArrayList<String>();
		for (int i = 0; i < 5; i++) {
			int index = random.nextInt(allMoviesFromYear.size());
			selectedMovies.add(allMoviesFromYear.get(index));
			allMoviesFromYear.remove(index);
		}
		return selectedMovies;
	}

	@Override
	// Recommends Upto 5 Similar Movies
	public void printRecommendationsFor(String webRaterID) {
		MovieDatabase.initialize("ratedmoviesfull.csv");
		RaterDatabase.initialize("ratings.csv");
		FourthRatings obj = new FourthRatings();
		
		ArrayList<Rating> recommended = obj.getSimilarRatings(webRaterID, 10, 5);
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
