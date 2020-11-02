import java.util.ArrayList;

public class MatchAllFilter implements Filter {
	private ArrayList<Filter> filterList;
	private ArrayList<String> filterNames;
	
	public MatchAllFilter() {
		filterList = new ArrayList<Filter>();
		filterNames = new ArrayList<String>();
	}
	
	public void addFilter(Filter f) {
		filterList.add(f);
		filterNames.add(f.getName());
	}
	
	@Override
	public boolean satisfies(QuakeEntry qe) {
		for (Filter f : filterList) {
			if (!f.satisfies(qe))
				return false;
		}
		return true;
	}

	@Override
	public String getName() {
		String filterNamesString = "";
		for (String filterName : filterNames) {
			filterNamesString += filterName;
			filterNamesString += " ";
		}
		return filterNamesString;
	}
}
