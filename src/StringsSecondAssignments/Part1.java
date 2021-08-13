package StringsSecondAssignments;

public class Part1 {

    /**
     * Write the method findStopCodon that has three parameters, a String parameter named dna an integer parameter named
     * startIndex that represents where the first occurrence of ATG occurs in dna, and a String parameter named *
     * stopCodon. This method returns the index of the first occurrence of stopCodon that appears past startIndex and is
     * a multiple of 3 away from startIndex. If there is no such stopCodon, this method returns the length of the dna
     * strand.
     */
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int find = dna.indexOf(stopCodon, startIndex + 3);
        if (find == -1 || (find - startIndex) % 3 != 0) {
            return dna.length();
        }
        return find;
    }

    public void testFindStopCodon() {
        System.out.println(findStopCodon("AATGTGGTAA", 1, "TAA"));
        System.out.println(findStopCodon("AATGGGTAA", 1, "TAA"));
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
        int findTAA = findStopCodon(dna, start, "TAA");
        int findTAG = findStopCodon(dna, start, "TAG");
        int findTGA = findStopCodon(dna, start, "TGA");
        int findStopCo = Math.min(findTAA, findTAG);
        findStopCo = Math.min(findStopCo, findTGA);
        if (findStopCo == dna.length()) {
            return "";
        }
        return dna.substring(start, findStopCo + 3);
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
        while (true) {
            String gene = findGene(dna, start);
            if (gene.isEmpty()) {
                return;
            }
            start = dna.indexOf(gene, start) + gene.length();
            System.out.println(gene);
        }
    }

    public void testPrintAllGenes() {
        printAllGenes("TTTATGAGGTGATTGATGTAAGATATGTAG");
    }

    public static void main(String[] args) {
        Part1 part1 = new Part1();
        //part1.testFindGene();
        part1.testPrintAllGenes();
    }
}
