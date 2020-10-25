import java.util.ArrayList;
import java.util.HashMap;
import edu.duke.FileResource;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
    	 records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
    	 FileResource fr = new FileResource(filename);
    	 for (String line : fr.lines()) {
    		 records.add(WebLogParser.parseEntry(line));
    	 }
     }
     
     public int countUniqueIPs() {
    	 ArrayList<String> uniqueIPs = new ArrayList<String>();
    	 for (LogEntry le : records) {
    		 String ipAddr = le.getIpAddress();
    		 if (!uniqueIPs.contains(ipAddr)) {
    			 uniqueIPs.add(ipAddr);
    		 }
    	 }
    	 return uniqueIPs.size();
     }
     
     public void printAllHigherThanNum(int num) {
    	 for (LogEntry le : records) {
    		 if (le.getStatusCode() > num)
    			 System.out.println(le);
    	 }
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
    	 ArrayList<String> uniqIPVisits = new ArrayList<String>();
    	 String checkDate[] = someday.split(" ");
    	 String checkMonth = checkDate[0];
    	 String checkDay = checkDate[1];
    	 for (LogEntry le : records) {
    		 String recordDate[] = le.getAccessTime().toString().split(" ");
    		 String recordMonth = recordDate[1];
    		 String recordDay = recordDate[2];
    		 if (checkMonth.equals(recordMonth) && checkDay.equals(recordDay)) {
    			 if (!uniqIPVisits.contains(le.getIpAddress()))
    				 uniqIPVisits.add(le.getIpAddress());
    		 }
    	 }
    	 return uniqIPVisits;
     }
     
     public int countUniqueIPsInRange(int low, int high) {
    	 int count = 0;
    	 ArrayList<String> uniqIPs = new ArrayList<String>();
    	 for (LogEntry le : records) {
    		 if (le.getStatusCode() >= low && le.getStatusCode() <= high && !uniqIPs.contains(le.getIpAddress())) {
    			 count += 1;
    			 uniqIPs.add(le.getIpAddress());
    		 }
    	 }
    	 return count;
     }
     
     public HashMap<String, Integer> countVisitsPerIP() {
    	 HashMap<String, Integer> ipCount = new HashMap<String, Integer>();
    	 for (LogEntry le : records) {
    		 String ipAddress = le.getIpAddress();
    		 if (!ipCount.containsKey(ipAddress))
    			 ipCount.put(ipAddress, 1);
    		 else {
    			 int currCount = ipCount.get(ipAddress);
    			 ipCount.put(ipAddress, currCount+1);
    		 }
    	 }
    	 return ipCount;
     }
     
     public int mostNumberVisitsByIP(HashMap<String, Integer> ipCounts) {
    	 int maxCount = 0;
    	 for (String ip : ipCounts.keySet()) {
    		 if (ipCounts.get(ip) > maxCount)
    			 maxCount = ipCounts.get(ip);
    	 }
    	 return maxCount;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> ipCounts) {
    	 int maxCount = mostNumberVisitsByIP(ipCounts);
    	 ArrayList<String> ipAddresses = new ArrayList<String>();
    	 for (String ip : ipCounts.keySet()) {
    		 if (ipCounts.get(ip) == maxCount)
    			 ipAddresses.add(ip);
    	 }
    	 return ipAddresses;
     }
     
     public HashMap<String, ArrayList<String>> iPsForDays() {
    	 HashMap<String, ArrayList<String>> mapIPDay = new HashMap<String, ArrayList<String>>();
    	 for (LogEntry le : records) {
    		 String recordDate[] = le.getAccessTime().toString().split(" ");
    		 String day = recordDate[1] + " " + recordDate[2];
    		 if (mapIPDay.containsKey(day)) {
    			 ArrayList<String> ipList = mapIPDay.get(day);
    			 ipList.add(le.getIpAddress());
    			 mapIPDay.put(day, ipList);
    		 }
    		 else {
    			 ArrayList <String> ipList = new ArrayList<String>();
    			 ipList.add(le.getIpAddress());
    			 mapIPDay.put(day,  ipList);
    		 }
    	 }
    	 return mapIPDay;
     }
     
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> mapIPDay) {
    	 int maxCount = 0;
    	 String day = "";
    	 for (String key : mapIPDay.keySet()) {
    		 ArrayList<String> ipList = mapIPDay.get(key);
    		 if (ipList.size() > maxCount) {
    			 maxCount = ipList.size();
    			 day = key;
    		 }
    	 }
    	 return day;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> mapIPDay, String day) {
    	 ArrayList<String> ipVisitorsDay = mapIPDay.get(day);
    	 HashMap<String, Integer> ipCounts = new HashMap<String, Integer>();
    	 for (String ip : ipVisitorsDay) {
    		 if (ipCounts.containsKey(ip)) {
    			 int currCount = ipCounts.get(ip);
    			 ipCounts.put(ip, currCount+1);
    		 }
    		 else
    			 ipCounts.put(ip, 1);
    	 }
    	 return iPsMostVisits(ipCounts);
     }
     
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
}
