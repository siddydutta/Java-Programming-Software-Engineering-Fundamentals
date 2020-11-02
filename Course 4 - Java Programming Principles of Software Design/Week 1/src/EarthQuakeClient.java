import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData, double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
        	if (qe.getMagnitude() > magMin)
        		answer.add(qe);
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData, double distMax, Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
        	double distance = from.distanceTo(qe.getLocation());
        	if (distance < distMax)
        		answer.add(qe);
        }
        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "src/data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        ArrayList<QuakeEntry> filteredList = filterByMagnitude(list, 5.0);
        for (QuakeEntry qe : filteredList)
        	System.out.println(qe);
        System.out.println("Found " + filteredList.size() + " quakes that match the criteria");
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "src/data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        // Location city = new Location(35.988, -78.907);
        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);
        ArrayList<QuakeEntry> filteredList = filterByDistanceFrom(list, 1000*1000, city);
        for (QuakeEntry qe : filteredList) {
        	System.out.print(city.distanceTo(qe.getLocation())/1000 + " ");
        	System.out.println(qe.getInfo());
        }
        System.out.println("Found " + filteredList.size() + " quakes that match the criteria");
    }
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth) {
    	ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
    	for (QuakeEntry qe : quakeData) {
    		if (qe.getDepth() > minDepth && qe.getDepth() < maxDepth)
    			answer.add(qe);
    	}
    	return answer;
    }
    
    public void quakesOfDepth() {
    	EarthQuakeParser parser = new EarthQuakeParser();
    	String source = "src/data/nov20quakedata.atom";
    	ArrayList<QuakeEntry> list  = parser.read(source);
    	
    	ArrayList<QuakeEntry> filteredList = filterByDepth(list, -4000.0, -2000.0);
    	/*for (QuakeEntry qe : filteredList)
    		System.out.println(qe);*/
    	System.out.println("Found " + filteredList.size() + " quakes that match the criteria");
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase) {
    	ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
    	for (QuakeEntry qe : quakeData) {
    		if (where.equals("start") && qe.getInfo().startsWith(phrase))
    			answer.add(qe);
    		if (where.equals("end") && qe.getInfo().endsWith(phrase))
    			answer.add(qe);
    		if (where.equals("any") && qe.getInfo().contains(phrase))
    			answer.add(qe);
    	}
    	return answer;
    }
    
    public void quakesByPhrase() {
    	EarthQuakeParser parser = new EarthQuakeParser();
    	String source = "src/data/nov20quakedata.atom";
    	ArrayList<QuakeEntry> list  = parser.read(source);
    	
    	ArrayList<QuakeEntry> filteredList = filterByPhrase(list, "any", "Can");
    	/*for (QuakeEntry qe : filteredList)
    		System.out.println(qe);*/
    	System.out.println("Found " + filteredList.size() + " quakes that match the criteria");
    }
    
    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "src/data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        /*for (QuakeEntry qe : list) {
            System.out.println(qe);
        }*/
    }
    
    public static void main(String args[]) {
    	EarthQuakeClient obj = new EarthQuakeClient();
    	//obj.createCSV();
    	//obj.bigQuakes();
    	//obj.closeToMe();
    	//obj.quakesOfDepth();
    	obj.quakesByPhrase();
    }
    
}
