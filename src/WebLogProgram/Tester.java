package WebLogProgram;
/**
 * Write a description of class Tester here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import WebLogProgram.LogEntry;
import edu.duke.FileResource;

import java.util.*;

public class Tester {
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }

    /**
     * In the Tester class (or you can write a new class for testing) write the void method testUniqueIP that has no
     * parameters. This method should create a LogAnalyzer, read from the file short-test_log, and then test the method
     * countUniqueIPs.
     */

    public void testUniqueIp() {
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile();
        System.out.println("# of unique IPs are: " + lg.countUniqueIPs());
    }

    public void testLogAnalyzer() {
        // In the Tester class you will need to complete the testLogAnalyzer method, which creates a LogAnalyzer
        // object, calls readFile on the data file short-test_log, and then calls printAll to print all the web logs.
        LogAnalyzer log = new LogAnalyzer();
        FileResource ff = new FileResource();
        log.printAll();
    }

    public void testCountAnalyzer() {
        LogAnalyzer log = new LogAnalyzer();
        log.readFile();
        for (String ip : log.countVisitsPerIP().keySet()) {
            System.out.print("IP: " + ip);
            System.out.println(" visits: " + log.countVisitsPerIP().get(ip));
        }

    }

    public static void main(String[] args) {
        Tester t = new Tester();
//        t.testUniqueIp();
        LogAnalyzer lg = new LogAnalyzer();
        lg.readFile();
//        System.out.println(lg.countUniqueIPsInRange(300, 399));
//        System.out.println(lg.uniqueIPVisitsOnDay("Mar 17").size());
//        lg.printAllHigherThanNum(400);
//        t.testCountAnalyzer();
        HashMap<String, Integer> count = lg.countVisitsPerIP();
        System.out.println("most visit IP counts: " + lg.mostNumberVisitsByIP(count));
        System.out.println("most visit IPs: " + lg.iPsMostVisits(count).toString());
//        HashMap<String, ArrayList<String>> map = lg.iPsForDays();
//        for (String date : map.keySet()) {
//            System.out.println(date + ": " + map.get(date).toString());
//        }

    }

}
