package WebLogProgram;
/**
 * Write a description of class LogAnalyzer here. In the LogAnalyzer class you will need to complete the constructor to
 * initialize records to an empty ArrayList and complete the readFile method to create a FileResource and to iterate
 * over all the lines in the file. For each line, create a LogEntry and store it in the records ArrayList. (Refer to the
 * lecture on parsing log files for help.)
 *
 * @author IvyHu
 * @version 08212021
 */

import java.util.*;

import WebLogProgram.LogEntry;
import edu.duke.*;

public class LogAnalyzer {
    private ArrayList<LogEntry> records;

    public LogAnalyzer() {
        // complete constructor
        records = new ArrayList<>();
    }

    public void readFile() {
        // complete method
        FileResource ff = new FileResource();
        for (String line : ff.lines()) {
            WebLogParser parser = new WebLogParser();
            records.add(WebLogParser.parseEntry(line));
        }
    }

    /**
     * In the LogAnalyzer class, write the method countUniqueIPs that has no parameters. This method should return an
     * integer representing the number of unique IP addresses. It should also assume that the instance variable records
     * already has its ArrayList of Strings read in from a file, and should access records in computing this value. For
     * help, refer to the lectures in this lesson on the unique IP algorithm and code.
     */

    public int countUniqueIPs() {
        ArrayList<String> uniqueIP = new ArrayList<String>();
        int i = 0;
        for (LogEntry le : records) {
            if (!uniqueIP.contains(le.getIpAddress())) {
                uniqueIP.add(le.getIpAddress());
                i++;
            }
        }
        return i;
    }

    /**
     * In the LogAnalyzer class, write the void method printAllHigherThanNum that has one integer parameter num. This
     * method should examine all the web log entries in records and print those LogEntrys that have a status code
     * greater than num. Be sure to add code in the Tester class to test out this method with the file short-test_log.
     */
    public void printAllHigherThanNum(int num) {
        for (LogEntry le : records) {
            if (le.getStatusCode() > num) {
                System.out.println(le.toString());
            }
        }
    }

    /**
     * In the LogAnalyzer class, write the method uniqueIPVisitsOnDay that has one String parameter named someday in the
     * format “MMM DD” where MMM is the first three characters of the month name with the first letter capitalized and
     * the others in lowercase, and DD is the day in two digits (examples are “Dec 05” and “Apr 22”). This method
     * accesses the web logs in records and returns an ArrayList of Strings of unique IP addresses that had access on
     * the given day. (Note that the dates in LogEntrys are stored as a Date object, but using toString will allow you
     * to access the characters in the Date. For example, consider that d is a Date. String str = d.toString(); allows
     * you to now use a String representation of the date.) Be sure to test your program with code in the Tester class.
     * Using the file weblog-short_log you should see that the call to uniqueIPVisitsOnDay(“Sep 14”) returns an
     * ArrayList of 2 items and uniqueIPVisitsOnDay(“Sep 30”) returns an ArrayList of 3 items.
     */
    public ArrayList uniqueIPVisitsOnDay(String someday) {
        ArrayList<String> uniqueIPVisits = new ArrayList<String>();
        for (LogEntry le : records) {
            if (le.getAccessTime().toString().contains(someday) & !uniqueIPVisits.contains(le.getIpAddress())) {
                uniqueIPVisits.add(le.getIpAddress());
            }
        }
        return uniqueIPVisits;
    }

    /**
     * In the LogAnalyzer class, write the method countUniqueIPsInRange that has two integer parameters named low and
     * high. This method returns the number of unique IP addresses in records that have a status code in the range from
     * low to high, inclusive. Be sure to test your program on several ranges. For example, using the file
     * short-test_log, the call countUniqueIPsInRange(200,299) returns 4, as there are four unique IP addresses that
     * have a status code from 200 to 299. The call countUniqueIPsInRange(300,399) returns 2. In this case, note that
     * there are three entries in the file that have a status code in the 300 range, but two of them have the same IP
     * address.
     */

    public int countUniqueIPsInRange(int low, int high) {
        ArrayList<String> uniqueIPsInRange = new ArrayList<String>();
        for (LogEntry le : records) {
            if (!uniqueIPsInRange.contains(le.getIpAddress())) {
                if (le.getStatusCode() >= low & le.getStatusCode() <= high) {
                    uniqueIPsInRange.add(le.getIpAddress());
                }
            }
        }
        return uniqueIPsInRange.size();
    }

    /**
     * In the LogAnalyzer class, write the method countVisitsPerIP, which has no parameters. This method returns a
     * HashMap<String, Integer> that maps an IP address to the number of times that IP address appears in records,
     * meaning the number of times this IP address visited the website. Recall that records stores LogEntrys from a file
     * of web logs. For help, refer to the video in this lesson on translating to code. Be sure to test this method on
     * sample files.
     */

    public HashMap<String, Integer> countVisitsPerIP() {
        HashMap<String, Integer> counts = new HashMap<String, Integer>();
        for (LogEntry le : records) {
            String ip = le.getIpAddress();
            if (!counts.containsKey(ip)) {
                counts.put(ip, 1);
            } else {
                counts.put(ip, counts.get(ip) + 1);
            }
        }
        return counts;
    }

    /**
     * In the LogAnalyzer class, write the method mostNumberVisitsByIP, which has one parameter, a HashMap<String,
     * Integer> that maps an IP address to the number of times that IP address appears in the web log file. This method
     * returns the maximum number of visits to this website by a single IP address. For example, the call
     * mostNumberVisitsByIP on a HashMap formed using the file weblog3-short_log returns 3.
     */

    public int mostNumberVisitsByIP(HashMap<String, Integer> map) {
        int i = 0;
        for (String ip : map.keySet()) {
            int visit = map.get(ip);
            if (visit > i) {
                i = visit;
            }
        }
        return i;
    }

    /**
     * In the LogAnalyzer class, write the method iPsMostVisits, which has one parameter, a HashMap<String, Integer>
     * that maps an IP address to the number of times that IP address appears in the web log file. This method returns
     * an ArrayList of Strings of IP addresses that all have the maximum number of visits to this website. For example,
     * the call iPsMostVisits on a HashMap formed using the file weblog3-short_log returns the ArrayList with these two
     * IP addresses,  61.15.121.171 and  84.133.195.161. Both of them visited the site three times, which is the maximum
     * number of times any IP address visited the site.
     */

    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> map) {
        int maxVisit = mostNumberVisitsByIP(map);
        ArrayList<String> ip = new ArrayList<String>();
        for (String ip2 : map.keySet()) {
            if (map.get(ip2).equals(maxVisit)) {
                ip.add(ip2);
            }
        }
        return ip;
    }

    /**
     * In the LogAnalyzer class, write the method iPsForDays, which has no parameters. This method returns a
     * HashMap<String, ArrayList<String>> that uses records and maps days from web logs to an ArrayList of IP addresses
     * that occurred on that day (including repeated IP addresses). A day is in the format “MMM DD” where MMM is the
     * first three characters of the month name with the first letter capital and the others in lowercase, and DD is the
     * day in two digits (examples are “Dec 05” and “Apr 22”). For example, for the file weblog3-short_log, after
     * building this HashMap, if you print it out, you will see that Sep 14 maps to one IP address, Sep 21 maps to four
     * IP addresses, and Sep 30 maps to five IP addresses.
     */

    public HashMap<String, ArrayList<String>> iPsForDays() {
        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        for (LogEntry le : records) {
//            System.out.println(le.toString());
            ArrayList<String> ipList = new ArrayList<String>();
            String date = le.getAccessTime().toString();
            date = date.substring(4, 10);
//            System.out.println(date);
            String ip = le.getIpAddress().toString();
//            System.out.println(ip);
            if (!map.containsKey(date)) {
                ipList.add(ip);
                map.put(date, ipList);
            } else {
                ipList = map.get(date);
                ipList.add(ip);
                map.put(date, ipList);
            }
        }
        return map;
    }

    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }

    /**
     * In the LogAnalyzer class, write the method dayWithMostIPVisits, which has one parameter that is a HashMap<String,
     * ArrayList<String>> that uses records and maps days from web logs to an ArrayList of IP addresses that occurred on
     * that day. This method returns the day that has the most IP address visits. If there is a tie, then return any
     * such day. For example, if you use the file weblog3-short_log, then this method should return the day most visited
     * as Sep 30.
     */

    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> map) {
        int visit = 0;
        String mostVisitedDate = null;
        for (String date : map.keySet()) {
            if (visit < map.get(date).size()) {
                visit = map.get(date).size();
                mostVisitedDate = date;
            }
        }
        return mostVisitedDate;
    }

    /**
     * In the LogAnalyzer class, write the method iPsWithMostVisitsOnDay, which has two parameters—the first one is a
     * HashMap<String, ArrayList<String>> that uses records and maps days from web logs to an ArrayList of IP addresses
     * that occurred on that day, and the second parameter is a String representing a day in the format “MMM DD”
     * described above. This method returns an ArrayList<String> of IP addresses that had the most accesses on the given
     * day. For example, if you use the file weblog3-short_log, and the parameter for the day is “Sep 30”, then there
     * are two IP addresses in the ArrayList returned: 61.15.121.171 and 177.4.40.87. Hint: This method should call
     * another method you have written.
     */

    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> map, String day) {
        ArrayList<String> ipList = map.get(day);
        HashMap<String, Integer> ipCount = new HashMap<String, Integer>();
        for (String ip : ipList) {
            if (!ipCount.containsKey(ip)) {
                ipCount.put(ip, 1);
            } else {
                ipCount.put(ip, 1 + ipCount.get(ip));
            }
        }
        ipList = iPsMostVisits(ipCount);
        return ipList;
    }


    public static void main(String[] args) {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile();
//        System.out.println(lg.countUniqueIPs());
        HashMap<String, ArrayList<String>> map = lg.iPsForDays();
        for (String date : map.keySet()) {
            System.out.println(date + ": " + map.get(date).toString());
            System.out.println(lg.dayWithMostIPVisits(map));

        }
        System.out.println(lg.iPsWithMostVisitsOnDay(map, "Sep 30").toString());
    }
}


