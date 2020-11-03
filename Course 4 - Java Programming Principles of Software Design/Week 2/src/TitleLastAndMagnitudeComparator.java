import java.util.Comparator;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
	@Override
	public int compare(QuakeEntry o1, QuakeEntry o2) {
		String q1LastWord = o1.getInfo().substring(o1.getInfo().lastIndexOf(" ")+1);
		String q2LastWord = o2.getInfo().substring(o2.getInfo().lastIndexOf(" ")+1);
		if (q1LastWord.compareTo(q2LastWord) < 0)
			return -1;
		else if (q1LastWord.compareTo(q2LastWord) > 0)
			return 1;
		else {
			if (o1.getMagnitude() < o2.getMagnitude())
				return -1;
			else if (o1.getMagnitude() > o2.getMagnitude())
				return 1;
			else
				return 0;
		}
	}
}
