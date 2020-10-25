
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
	public String file;
	LogAnalyzer obj;
	
	public Tester(String file) {
		obj = new LogAnalyzer();
		obj.readFile(file);
	}
	
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
		obj.readFile("src/data/short-test_log");
    }
    
    public void testUniqIP() {
    	System.out.println("Number of Unique IPs: " + obj.countUniqueIPs());
    }
    
    public void testHigherThanNum() {
    	obj.printAllHigherThanNum(400);
    }
    
    public void testUniqueIPVisits() {
    	ArrayList<String> uniqueIPVisits = obj.uniqueIPVisitsOnDay("Mar 17");
    	System.out.println(uniqueIPVisits.size());
    	for (String IP : uniqueIPVisits) {
    		System.out.println(IP);
    	}
    }
    
    public void testCountUniqueIPsInRange() {
    	System.out.println(obj.countUniqueIPsInRange(200, 299));
    }
    
    
    public static void main(String args[]) {
    	/*
    	Tester obj = new Tester("src/data/weblog-short_log");
    	obj.testLogEntry();
    	obj.testLogAnalyzer();
    	obj.testUniqIP();
    	obj.testHigherThanNum();
    	obj.testUniqueIPVisits();
    	obj.testCountUniqueIPsInRange();
    	*/
    	LogAnalyzer obj = new LogAnalyzer();
    	obj.readFile("src/data/weblog2_log");
    	System.out.println("Number of Unique IPs: " + obj.countUniqueIPs());
    	System.out.println("Unique IP Visits on Sep 27: " + obj.uniqueIPVisitsOnDay("Sep 27").size());
    	System.out.println("Number of IPs in Range (200-299): " + obj.countUniqueIPsInRange(200, 299));
    	HashMap<String, Integer> ipCounts = obj.countVisitsPerIP();
    	System.out.println("Most Number of Visits by a IP: " + obj.mostNumberVisitsByIP(ipCounts));
    	System.out.println("IPs Most Visited: " + obj.iPsMostVisits(ipCounts));
    	HashMap<String, ArrayList<String>> mapIPDay = obj.iPsForDays();
    	System.out.println("Day with Most IP Visits: " + obj.dayWithMostIPVisits(mapIPDay));
    	System.out.println("IPs Most Visited on Sep 30: " + obj.iPsWithMostVisitsOnDay(mapIPDay, "Sep 30"));
    }
}

