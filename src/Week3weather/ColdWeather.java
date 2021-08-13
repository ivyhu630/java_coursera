package Week3weather;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

public class ColdWeather {

    /**
     * 1. Write a method named coldestHourInFile that has one parameter, a CSVParser named parser. This method returns
     * the CSVRecord with the coldest temperature in the file and thus all the information about the coldest
     * temperature, such as the hour of the coldest temperature. You should also write a void method named
     * testColdestHourInFile() to test this method and print out information about that coldest temperature, such as the
     * time of its occurrence.
     *
     * @return
     */

    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord coldestTempRow = null;
        double coldestTemp = 0.00;
        for (CSVRecord record : parser) {
            if (record.get("TemperatureF").equals("-9999")) {
                continue;
            }
            double temp = Double.parseDouble(record.get("TemperatureF"));
            if (coldestTempRow == null || coldestTemp > temp) {
                coldestTemp = temp;
                coldestTempRow = record;
            }
        }
        return coldestTempRow;
    }

    /**
     * Write the method fileWithColdestTemperature that has no parameters. This method should return a string that is
     * the name of the file from selected files that has the coldest temperature. You should also write a void method
     * named testFileWithColdestTemperature() to test this method. Note that after determining the filename, you could
     * call the method coldestHourInFile to determine the coldest temperature on that day. When
     * fileWithColdestTemperature runs and selects the files for January 1–3 in 2014, the method should print out
     */

    public String fileWithColdestTemperature() {
        DirectoryResource dr = new DirectoryResource();
        File coldestFile = null;
        double coldestTemp = 0.00;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord coldestTempRow = coldestHourInFile(parser);
            double temp = Double.parseDouble(coldestTempRow.get("TemperatureF"));
            if (coldestFile == null || coldestTemp > temp) {
                coldestTemp = temp;
                coldestFile = f;
            }
        }
        return coldestFile.getPath();
    }

    /**
     * Write a method named lowestHumidityInFile that has one parameter, a CSVParser named parser. This method returns
     * the CSVRecord that has the lowest humidity. If there is a tie, then return the first such record that was found.
     * <p>
     * Note that sometimes there is not a number in the Humidity column but instead there is the string “N/A”. This only
     * happens very rarely. You should check to make sure the value you get is not “N/A” before converting it to a
     * number.
     * <p>
     * Also note that the header for the time is either TimeEST or TimeEDT, depending on the time of year. You will
     * instead use the DateUTC field at the right end of the data file to  get both the date and time of a temperature
     * reading.
     * <p>
     * You should also write a void method named testLowestHumidityInFile() to test this method that starts with these
     * lines:
     */

    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestHumRow = null;
        double lowestHum = 0.00;
        for (CSVRecord record : parser) {
            if (record.get("Humidity").equals("N/A")) {
                continue;
            }
            double temp = Double.parseDouble(record.get("Humidity"));
            if (lowestHumRow == null || lowestHum > temp) {
                lowestHum = temp;
                lowestHumRow = record;
            }
        }
        return lowestHumRow;
    }

    /**
     * Write the method lowestHumidityInManyFiles that has no parameters. This method returns a CSVRecord that has the
     * lowest humidity over all the files. If there is a tie, then return the first such record that was found. You
     * should also write a void method named testLowestHumidityInManyFiles() to test this method and to print the lowest
     * humidity AND the time the lowest humidity occurred. Be sure to test this method on two files so you can check if
     * it is working correctly. If you run this program and select the files for January 19, 2014 and January 20, 2014,
     * you should get
     */

    public CSVRecord lowestHumidityInManyFiles() {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowHumLine = null;
        double lowestHum = 0.00;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord lowestHumRow = lowestHumidityInFile(parser);
            double temp = Double.parseDouble(lowestHumRow.get("Humidity"));
            if (lowHumLine == null || lowestHum > temp) {
                lowestHum = temp;
                lowHumLine = lowestHumRow;
            }
        }
        return lowHumLine;
    }

    /**
     * Write the method averageTemperatureInFile that has one parameter, a CSVParser named parser. This method returns a
     * double that represents the average temperature in the file. You should also write a void method named
     * testAverageTemperatureInFile() to test this method. When this method runs and selects the file for January 20,
     * 2014, the method should print out
     * <p>
     */

    public double averageTemperatureInFile(CSVParser parser) {
        int i = 0;
        double totalTemp = 0;
        for (CSVRecord record : parser) {
            if (record.get("TemperatureF").equals("-9999")) {
                continue;
            }
            i++;
            totalTemp += Double.parseDouble(record.get("TemperatureF"));
        }
        return totalTemp / i;
    }

    /**
     * Write the method averageTemperatureWithHighHumidityInFile that has two parameters, a CSVParser named parser and
     * an integer named value. This method returns a double that represents the average temperature of only those
     * temperatures when the humidity was greater than or equal to value. You should also write a void method named
     * testAverageTemperatureWithHighHumidityInFile() to test this method. When this method runs checking for humidity
     * greater than or equal to 80 and selects the file for January 20, 2014, the method should print out
     */

    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        int i = 0;
        double totalTemp = 0;
        for (CSVRecord record : parser) {
            if (record.get("TemperatureF").equals("-9999") || record.get("Humidity").equals("N/A")) {
                continue;
            }
            if (Double.parseDouble(record.get("Humidity")) >= value) {
                i++;
                totalTemp += Double.parseDouble(record.get("TemperatureF"));
            }
        }
        return totalTemp / i;
    }


    public void testLowestHumidityInManyFiles() {
        CSVRecord record = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + record.get("Humidity") + " at " + record.get("DateUTC"));
    }

    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }

    public void testFileWithColdestTemperature() {
        String f = fileWithColdestTemperature();
        System.out.print("Coldest day was in file ");
        System.out.println(f);
        FileResource fr = new FileResource(f);
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldRecord = coldestHourInFile(parser);
        System.out.println("Coldest temperature on that day was " + coldRecord.get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day were:");
        parser = fr.getCSVParser();
        for (CSVRecord record : parser) {
            System.out.println(record.get("DateUTC") + ": " + record.get("TemperatureF"));
        }
    }

    public void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println(coldestHourInFile(parser));
    }

    public static void main(String[] args) {
        ColdWeather w = new ColdWeather();
//        w.testColdestHourInFile();
        w.testFileWithColdestTemperature();
//        w.testLowestHumidityInFile();
//        w.testLowestHumidityInManyFiles();
//        FileResource fr = new FileResource();
//        CSVParser parser = fr.getCSVParser();
//        Double temp = w.averageTemperatureWithHighHumidityInFile(parser, 80);
//        if (Double.isNaN(temp)) {
//            System.out.println("No temperatures with that humidity");
//        } else {
//            System.out.println(temp);
//        }
//        System.out.println(w.averageTemperatureInFile(parser));

    }
}
