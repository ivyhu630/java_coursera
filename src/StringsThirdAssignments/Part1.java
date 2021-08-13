package StringsThirdAssignments;

import edu.duke.FileResource;
import edu.duke.StorageResource;

import java.util.Locale;

public class Part1 {

    /**
     * Write the method findStopCodon that has three parameters, a String parameter named dna an integer parameter named
     * startIndex that represents where the first occurrence of ATG occurs in dna, and a String parameter named *
     * stopCodon. This method returns the index of the first occurrence of stopCodon that appears past startIndex and is
     * a multiple of 3 away from startIndex. If there is no such stopCodon, this method returns the length of the dna
     * strand.
     */
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int start = startIndex + 3;
        int stop = dna.indexOf(stopCodon, start);
        while (stop != -1) {
            if ((stop - startIndex) % 3 == 0) {
                return stop;
            }
            stop = dna.indexOf(stopCodon, stop + 3);
        }
        return dna.length();
    }

    public void testFindStopCodon() {
        System.out.println("test1 " + findStopCodon("AATGTGGTAA", 1, "TAA"));
        System.out.println("test2 " + findStopCodon("AATGGGTAA", 1, "TAA"));
        System.out.println("test3 " + findStopCodon("AATGATAGGTAA", 1, "TAA"));
    }

    /**
     * Write the method findGene that has one String parameter dna, representing a string of DNA. In this method you
     * should do the following:
     * <p>
     * Find the index of the first occurrence of the start codon “ATG”. If there is no “ATG”, return the empty string.
     * <p>
     * Find the index of the first occurrence of the stop codon “TAA” after the first occurrence of “ATG” that is a
     * multiple of three away from the “ATG”. Hint: call findStopCodon.
     * <p>
     * Find the index of the first occurrence of the stop codon “TAG” after the first occurrence of “ATG” that is a
     * multiple of three away from the “ATG”. Find the index of the first occurrence of the stop codon “TGA” after the
     * first occurrence of “ATG” that is a multiple of three away from the “ATG”.
     * <p>
     * Return the gene formed from the “ATG” and the closest stop codon that is a multiple of three away. If there is no
     * valid stop codon and therefore no gene, return the empty string.
     */
    public String findGene(String dna, int start) {
        start = dna.indexOf("ATG", start);
        if (start == -1) {
            return "";
        }
        int stopTAA = findStopCodon(dna, start, "TAA");
        int stopTAG = findStopCodon(dna, start, "TAG");
        int stopTGA = findStopCodon(dna, start, "TGA");
        int stop = Math.min(stopTAA, stopTAG);
        stop = Math.min(stopTGA, stop);
        if (stop == dna.length()) {
            return "";
        }
        return dna.substring(start, stop + 3);
    }

    public void testFindGene() {
        System.out.println("test1 " + findGene("AATGTGGTAATAG", 0));
        System.out.println("test2 " + findGene("AATTTGGTAA", 0));
        System.out.println("test3 " + findGene("AATGTGGATAB", 0));
    }

    /**
     * Write the void method printAllGenes that has one String parameter dna, representing a string of DNA. In this
     * method you should repeatedly find genes and print each one until there are no more genes. Hint: remember you
     * learned a while(true) loop and break.
     */
    public void printAllGenes(String dna) {
        int start = 0;
        int count = 0;
        while (true) {
            String gene = findGene(dna, start);
            if (gene.isEmpty()) {
                return;
            }
            System.out.println(gene);
            count++;
            System.out.println(count);
            start = dna.indexOf("ATG", start) + gene.length();
        }
    }

    /**
     * Make a copy of the printAllGenes method called getAllGenes. Instead of printing the genes found, this method
     * should create and return a StorageResource containing the genes found. Remember to import the edu.duke libraries
     * otherwise you will get an error message cannot find the class StorageResource.
     */

    public StorageResource getAllGenes(String dna) {
        StorageResource genepool = new StorageResource();
        int start = 0;
        while (true) {
            String gene = findGene(dna, start);
            if (gene.isEmpty()) {
                break;
            }
            genepool.add(gene);
            start = dna.indexOf("ATG", start) + gene.length();
        }
        return genepool;
    }

    public void testPrintAllGenes() {
        printAllGenes("AACCCT");
    }

    /**
     * Write the method cgRatio that has one String parameter dna, and returns the ratio of C’s and G’s in dna as a
     * fraction of the entire strand of DNA. For example if the String were “ATGCCATAG,” then cgRatio would return 4/9
     * or .4444444.
     * <p>
     * Hint: 9/2 uses integer division because you are dividing an integer by an integer and thus Java thinks you want
     * the result to be an integer. If you want the result to be a decimal number, then make sure you convert one of the
     * integers to a decimal number by changing it to a float. For example, (float) 9/2 is interpreted by Java as 9.0/2
     * and if one of the numbers is a decimal, then Java assumes you want the result to be a decimal number. Thus
     * (float) 9/2 is 4.5.
     */
    public float cgRatio(String dna) {
        int cgCount = 0;
        for (int i = 0; i < dna.length(); ++i) {
            if (dna.charAt(i) == 'C' || dna.charAt(i) == 'G') {
                cgCount++;
            }
        }
        return (float) cgCount / dna.length();
    }

    /**
     * Write a method countCTG that has one String parameter dna, and returns the number of times the codon CTG appears
     * in dna.
     */

    public int countCTG(String dna) {
        int count = 0;
        int findCTG = dna.indexOf("CTG");
        while (findCTG != -1) {
            findCTG = dna.indexOf("CTG", findCTG + 3);
            count++;
        }
        return count;
    }

    /**
     * Write the void method processGenes that has one parameter sr, which is a StorageResource of strings. This method
     * processes all the strings in sr to find out information about them. Specifically, it should:
     * <p>
     * print all the Strings in sr that are longer than 9 characters
     * <p>
     * print the number of Strings in sr that are longer than 9 characters
     * <p>
     * print the Strings in sr whose C-G-ratio is higher than 0.35
     * <p>
     * print the number of strings in sr whose C-G-ratio is higher than 0.35
     * <p>
     * print the length of the longest gene in sr
     */
    public void processGenes(StorageResource sr) {
        int countString = 0;
        int countCGRatio = 0;
        int stringLength = 0;
        for (String item : sr.data()) {
            if (item.length() > 60) {
                System.out.println("longer than 60: " + item);
                countString++;
            }
            if (cgRatio(item) > 0.35) {
                System.out.println("CGratio >.35: " + item);
                countCGRatio++;
            }
            if (stringLength < item.length()) {
                stringLength = item.length();
            }
        }
        System.out.println("countString: " + countString);
        System.out.println("countCGRatio: " + countCGRatio);
        System.out.println("longestgene: " + stringLength);
    }


    public void testCgRatio() {
        System.out.println(cgRatio("ATGCGA"));
    }

    public void testGetAllGenes() {
        StorageResource genepool = getAllGenes("TTTATGAGGTGATTGATGTAAGATATGTAG");
        for (String item : genepool.data()) {
            System.out.println(item);
        }
    }

    public static void main(String[] args) {
        Part1 part1 = new Part1();
        //part1.testPrintAllGenes();

//        //part1.testFindStopCodon();
//        //part1.testFindGene();

//        //part1.testCgRatio();
        System.out.println(
                System.getProperty("user.dir"));
        FileResource fr = new FileResource("dna/" +
                "GRch38dnapart.fa");
        String dna = fr.asString().toUpperCase();
        StorageResource genePool = part1.getAllGenes(dna.toUpperCase());
        part1.processGenes(genePool);
    }
}
