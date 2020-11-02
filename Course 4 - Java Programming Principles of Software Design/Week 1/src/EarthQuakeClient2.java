import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "src/data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        
        // Filter f = new MinMagFilter(4.0);
        // Filter f = new MagnitudeFilter(4.0, 5.0);
        // Filter f = new DepthFilter(-35000.0, -12000.0);
        // Filter f = new DistanceFilter(new Location(35.42, 139.43), 10000000);
        Filter f = new PhraseFilter("end", "Japan");
        ArrayList<QuakeEntry> m7  = filter(list, f); 
        for (QuakeEntry qe: m7) { 
            System.out.println(qe);
        } 
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }
    
    public void testMatchAllFilter() {
    	EarthQuakeParser parser = new EarthQuakeParser();
    	String source = "src/data/nov20quakedata.atom";
    	ArrayList<QuakeEntry> list  = parser.read(source);
    	System.out.println("Read data for " + list.size() + " records.");
    	
    	MatchAllFilter maf = new MatchAllFilter();
    	maf.addFilter(new MagnitudeFilter(0.0, 2.0));
    	maf.addFilter(new DepthFilter( -100000.0, -10000.0));
    	maf.addFilter(new PhraseFilter("any", "a"));
    	
    	ArrayList<QuakeEntry> result  = filter(list, maf); 
        /*for (QuakeEntry qe: result) { 
            System.out.println(qe);
        }*/
        System.out.println("# of records filtered: " + result.size());
        System.out.println("Filters used are: " + maf.getName());
    }
    
    public void testMatchAllFilter2() {
    	EarthQuakeParser parser = new EarthQuakeParser();
    	String source = "src/data/nov20quakedata.atom";
    	ArrayList<QuakeEntry> list  = parser.read(source);
    	System.out.println("Read data for " + list.size() + " records.");
    	
    	MatchAllFilter maf = new MatchAllFilter();
    	maf.addFilter(new MagnitudeFilter(0.0, 3.0));
    	maf.addFilter(new DistanceFilter(new Location(36.1314, -95.9372), 10000000));
    	maf.addFilter(new PhraseFilter("any", "Ca"));
    	
    	ArrayList<QuakeEntry> result  = filter(list, maf); 
        /*for (QuakeEntry qe: result) { 
        System.out.println(qe);
    	}*/
    	
    	System.out.println("# of records filtered: " + result.size());
        System.out.println("Filters used are: " + maf.getName());
    }
    
    public void testFilter() {
    	EarthQuakeParser parser = new EarthQuakeParser();
    	String source = "src/data/nov20quakedata.atom";
    	ArrayList<QuakeEntry> list  = parser.read(source);
    	System.out.println("# of records read: " + list.size());
    	
    	MatchAllFilter maf = new MatchAllFilter();
    	maf.addFilter(new MagnitudeFilter(0.0, 5.0));
    	maf.addFilter(new DistanceFilter(new Location(55.7308, 9.1153), 3000000));
    	maf.addFilter(new PhraseFilter("any", "e"));
    	
    	ArrayList<QuakeEntry> result  = filter(list, maf); 
        /*for (QuakeEntry qe: result) { 
            System.out.println(qe);
        }*/
        System.out.println("# of records filtered: " + result.size());
        System.out.println("Filters used are: " + maf.getName());
    }
    
    public static void main(String args[]) {
    	EarthQuakeClient2 obj = new EarthQuakeClient2();
    	//obj.quakesWithFilter();
    	//obj.testMatchAllFilter();
    	//obj.testMatchAllFilter2();
    	obj.testFilter();
    }
}
