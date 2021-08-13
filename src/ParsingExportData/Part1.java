package ParsingExportData;


import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class Part1 {
    /**
     * Write a method named countryInfo that has two parameters, parser is a CSVParser and country is a String. This
     * method returns a string of information about the country or returns “NOT FOUND” if there is no information about
     * the country. The format of the string returned is the country, followed by “: “, followed by a list of the
     * countries’ exports, followed by “: “, followed by the countries export value. For example, using the file
     * exports_small.csv and the country Germany, the program returns the string: -Done
     */
    public String countryInfo(CSVParser parser, String country) {
        for (CSVRecord record : parser) {
            if (country.equals(record.get("Country"))) {
                return country + ": " + record.get("Exports") + ": " + record.get("Value (dollars)");
            }
        }
        return "NOT FOUND";
    }

    /**
     * Write a void method named listExportersTwoProducts that has three parameters, parser is a CSVParser, exportItem1
     * is a String and exportItem2 is a String. This method prints the names of all the countries that have both
     * exportItem1 and exportItem2 as export items. For example, using the file exports_small.csv, this method called
     * with the items “gold” and “diamonds” would print the countries -SuccessOperation Yay
     */
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        for (CSVRecord record : parser) {
            if (record.get("Exports").contains(exportItem1) && record.get("Exports").contains(exportItem2)) {
                System.out.println(record.get("Country"));
            }
        }
    }

    /**
     * Write a method named numberOfExporters, which has two parameters, parser is a CSVParser, and exportItem is a
     * String. This method returns the number of countries that export exportItem. For example, using the file
     * exports_small.csv, this method called with the item “gold” would return 3.
     */
    public int numberOfExporters(CSVParser parser, String exportItem) {
        int countExporters = 0;
        for (CSVRecord record : parser) {
            if (record.get("Exports").contains(exportItem)) {
                countExporters++;
            }
        }
        return countExporters;
    }

    /**
     * Write a void method named bigExporters that has two parameters, parser is a CSVParser, and amount is a String in
     * the format of a dollar sign, followed by an integer number with a comma separator every three digits from the
     * right. An example of such a string might be “$400,000,000”. This method prints the names of countries and their
     * Value amount for all countries whose Value (dollars) string is longer than the amount string. You do not need to
     * parse either string value as an integer, just compare the lengths of the strings.
     */
    public void bigExporters(CSVParser parser, String amount) {
        for (CSVRecord record : parser) {
            if (record.get("Value (dollars)").length() > amount.length()) {
                System.out.println(record.get("Country") + ": " + record.get("Value (dollars)"));
            }
        }
    }

    public static void main(String[] args) {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        Part1 p = new Part1();
//        System.out.println(p.countryInfo(parser, "Nauru"));
//        parser = fr.getCSVParser();
//        p.listExportersTwoProducts(parser, "cotton", "flowers");
//        System.out.println(p.numberOfExporters(parser, "cocoa"));
        p.bigExporters(parser, "$999,999,999,999");

    }
}
