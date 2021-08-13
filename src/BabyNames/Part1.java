package BabyNames;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

public class Part1 {

    public void totalBirth(FileResource fr) {
        int totalBirth = 0;
        int totalGirls = 0;
        int count = 0;
        int countGirls = 0;
        for (CSVRecord record : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(record.get(2));
            totalBirth += numBorn;
            count++;
            if (record.get(1).equals("F")) {
                totalGirls += Integer.parseInt(record.get(2));
                countGirls++;
            }
        }
        System.out.println("total births = " + totalBirth + " " + count);
        System.out.println("total girls = " + totalGirls + " " + countGirls);
        System.out.println("total boys = " + (totalBirth - totalGirls) + " " + (count - countGirls));
    }


    /**
     * Write the method named getRank that has three parameters: an integer named year, a string named name, and a
     * string named gender (F for female and M for male). This method returns the rank of the name in the file for the
     * given gender,  where rank 1 is the name with the largest number of births. If the name is not in the file, then
     * -1 is returned.  For example, in the file "yob2012short.csv", given the name Mason, the year 2012 and the gender
     * ‘M’, the number returned is 2, as Mason is the boys name with the second highest number of births. Given the name
     * Mason, the year 2012 and the gender ‘F’, the number returned is -1 as Mason does not appear with an F in that
     * file.
     */
    public int getRank(int year, String name, String gender) {
        FileResource f = new FileResource("us_babynames/us_babynames_by_year/yob" + String.valueOf(year) + ".csv");
        int rank = 0;
        for (CSVRecord record : f.getCSVParser(false)) {
            if (!record.get(1).equals(gender)) {
                continue;
            }
            rank++;
            if (record.get(0).equals(name)) {
                return rank;
            }
        }
        return -1;
    }

    /**
     * Write the method named getName that has three parameters: an integer named year, an integer named rank, and a
     * string named gender (F for female and M for male). This method returns the name of the person in the file at this
     * rank, for the given gender, where rank 1 is the name with the largest number of births. If the rank does not
     * exist in the file, then “NO NAME”  is returned
     */

    public String getName(int year, int rank, String gender) {
        FileResource f = new FileResource("us_babynames/us_babynames_by_year/yob" + String.valueOf(year) + ".csv");
        int totalName = 0;
        for (CSVRecord record : f.getCSVParser(false)) {
            if (!record.get(1).equals(gender)) {
                continue;
            }
            totalName++;
            if (rank == totalName) {
                return record.get(0);
            }
        }
        return "NO NAME";
    }

    /**
     * What would your name be if you were born in a different year? Write the void method named whatIsNameInYear that
     * has four parameters: a string name, an integer named year representing the year that name was born,  an integer
     * named newYear and a string named gender (F for female and M for male). This method determines what name would
     * have been named if they were born in a different year, based on the same popularity. That is, you should
     * determine the rank of name in the year they were born, and then print the name born in newYear that is at the
     * same rank and same gender. For example, using the files "yob2012short.csv" and "yob2014short.csv", notice that in
     * 2012 Isabella is the third most popular girl's name. If Isabella was born in 2014 instead, she would have been
     * named Sophia, the third most popular girl's name that year. The output might look like this:
     */

    public String whatIsNameInYear(String name, int year, int newYear, String gender) {
        return getName(newYear, getRank(year, name, gender), gender);
    }

    /**
     * Write the method yearOfHighestRank that has two parameters: a string name, and a string named gender (F for
     * female and M for male). This method selects a range of files to process and returns an integer, the year with the
     * highest rank for the name and gender. If the name and gender are not in any of the selected files, it should
     * return -1. For example, calling yearOfHighestRank with name Mason and gender ‘M’ and selecting the three test
     * files above results in returning the year 2012. That is because Mason was ranked the  2nd most popular name in
     * 2012, ranked 4th in 2013 and ranked 3rd in 2014. His highest ranking was in 2012.
     */

    public int yearOfHighestRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int year = -1;
        int highestRank = -1;
        for (File f : dr.selectedFiles()) {
            String fileName = f.getName();
            int startLoc = 3;
            String yearString = fileName.substring(startLoc, startLoc + 4);
            int currentYear = Integer.parseInt(yearString);
            int currentRank = getRank(currentYear, name, gender);
            if (currentRank == -1) {
                continue;
            }
            if (highestRank == -1 || currentRank < highestRank) {
                highestRank = currentRank;
                year = currentYear;
            }
        }
        return year;
    }

    /**
     * Write the method getAverageRank that has two parameters: a string name, and a string named gender (F for female
     * and M for male). This method selects a range of files to process and returns a double representing the average
     * rank of the name and gender over the selected files. It should return -1.0 if the name is not ranked in any of
     * the selected files. For example calling getAverageRank with name Mason and gender ‘M’ and selecting the three
     * test files above results in returning 3.0, as he is rank 2 in the year 2012, rank 4 in 2013 and rank 3 in 2014.
     * As another example, calling   getAverageRank with name Jacob and gender ‘M’ and selecting the three test files
     * above results in returning 2.66.
     */

    public double getAverageRank(String name, String gender) {
        double avgRank = -1.0;
        DirectoryResource dr = new DirectoryResource();
        int totalRank = 0;
        int count = 0;
        for (File f : dr.selectedFiles()) {
            String fileName = f.getName();
            int startLoc = 3;
            String yearString = fileName.substring(startLoc, startLoc + 4);
            int currentYear = Integer.parseInt(yearString);
            int currentRank = getRank(currentYear, name, gender);
            if (currentRank == -1) {
                break;
            } else {
                totalRank += currentRank;
                count++;
            }
        }
        if (count == 0) {
            return -1;
        }
        return (double) totalRank / count;
    }

    /**
     * Write the method getTotalBirthsRankedHigher that has three parameters: an integer named year, a string named
     * name, and a string named gender (F for female and M for male). This method returns an integer, the total number
     * of births of those names with the same gender and same year who are ranked higher than name. For example, if
     * getTotalBirthsRankedHigher accesses the "yob2012short.csv" file with name set to “Ethan”, gender set to “M”, and
     * year set to 2012, then this method should return 15, since Jacob has 8 births and Mason has 7 births, and those
     * are the only two ranked higher than Ethan.
     */

    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        int totalBirth = 0;
        int currentRank = getRank(year, name, gender);
        if (currentRank == -1) {
            return 0;
        }
        int rank = 1;
        FileResource f = new FileResource("us_babynames/us_babynames_by_year/yob" + String.valueOf(year) + ".csv");
        for (CSVRecord record : f.getCSVParser(false)) {
            if (!record.get(1).equals(gender)) {
                continue;
            }
            int numBorn = Integer.parseInt(record.get(2));
            totalBirth += numBorn;
            rank++;
            if (rank >= currentRank) {
                return totalBirth;
            }
        }
        return totalBirth;
    }

    public static void main(String[] args) {
        Part1 p = new Part1();
//        System.out.println(
//                p.getRank(1971, "Frank", "M"));
//        FileResource fr = new FileResource();
//        p.totalBirth(fr);
//        System.out.println(p.getName(1982, 450, "M"));
//        System.out.println(p.whatIsNameInYear("Owen", 1974, 2014, "M"));
//        System.out.println(p.yearOfHighestRank("Genevieve", "F"));
//        System.out.println(p.getAverageRank("Robert", "M"));
        System.out.println(p.getTotalBirthsRankedHigher(1990, "Drew", "M"));
    }

}
