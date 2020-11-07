import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerAverage {
	public void printAverageRatings() {
		SecondRatings obj = new SecondRatings();
		ArrayList<Rating> averageRatings = obj.getAverageRatings(12);
		Collections.sort(averageRatings, Collections.reverseOrder());
		
		for (Rating r : averageRatings) {
			System.out.println(String.format("%.2f", r.getValue()) + "\t" + obj.getTitle(r.getItem()));
		}
		
		System.out.println("\nNumber of Movies: " + averageRatings.size());
	}
	
	public void getAverageRatingOneMovie() {
		SecondRatings obj = new SecondRatings();
		String id = obj.getID("Vacation");
		System.out.println("Average Rating: " + obj.getAverageByID(id, 0));
	}
	
	public static void main(String[] args) {
		MovieRunnerAverage obj = new MovieRunnerAverage();
		obj.printAverageRatings();
		obj.getAverageRatingOneMovie();
	}
}
