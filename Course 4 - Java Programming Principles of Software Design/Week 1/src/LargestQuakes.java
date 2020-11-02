import java.util.ArrayList;

public class LargestQuakes {
	public int indexOfLargest(ArrayList<QuakeEntry> data) {
		int maxIndex = 0;
		double maxMagnitude = 0.0;
		for (int i = 0; i < data.size(); i++) {
			QuakeEntry current = data.get(i);
			if (current.getMagnitude() > maxMagnitude) {
				maxMagnitude = current.getMagnitude();
				maxIndex = i;
			}
		}
		return maxIndex;
	}
	
	public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> data, int howMany) {
		ArrayList<QuakeEntry> result = new ArrayList<QuakeEntry>();
		ArrayList<QuakeEntry> list = new ArrayList<QuakeEntry>(data);
		for (int i = 1; i <= howMany; i++) {
			int maxIndex = indexOfLargest(list);
			result.add(list.get(maxIndex));
			list.remove(maxIndex);
		}
		return result;
	}
	
	public void findLargestQuakes() {
		EarthQuakeParser parser = new EarthQuakeParser();
		String source = "src/data/nov20quakedata.atom";
		ArrayList<QuakeEntry> list = parser.read(source);
		System.out.println("# of Earthquake Records Found: " + list.size());
		
		/*
		int maxIndex = indexOfLargest(list);
		System.out.println("Largest magnitude: " + list.get(maxIndex).getMagnitude() + " at index: " + maxIndex);
		*/
		ArrayList<QuakeEntry> answer = getLargest(list, 50);
		for (QuakeEntry qe : answer)
			System.out.println(qe);
	}
	
	public static void main(String[] args) {
		LargestQuakes obj = new LargestQuakes();
		obj.findLargestQuakes();
	}
}
