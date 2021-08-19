package P2W2TellingARandomStory;

import edu.duke.FileResource;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class codonCount {
    private HashMap<String, Integer> map;

    public codonCount() {
        map = new HashMap<String, Integer>();
    }

    public void buildCodonMap(int start, String dna) {
        map.clear();
        int i = 0;
        while (i + start + 3 < dna.length()) {
            String codon = dna.substring(start + i, start + 3 + i);
            if (map.containsKey(codon)) {
                map.put(codon, map.get(codon) + 1);
            } else {
                map.put(codon, 1);
            }
            i += 3;
        }
    }

    public String getMostCommonCodon() {
        int maxOccurence = 0;
        String mostCommonCodon = null;
        for (String s : map.keySet()) {
            if (map.get(s) > maxOccurence) {
                maxOccurence = map.get(s);
                mostCommonCodon = s;
            }
        }
        return mostCommonCodon;
    }

    public void printCodonCounts(int start, int end) {
        int uniqueCodon = 0;
        for (String s : map.keySet()) {
            uniqueCodon++;
            if (start <= map.get(s) && map.get(s) <= end) {
                System.out.println(s + " " + map.get(s));
            }
        }
        System.out.println("# of unique codon: " + uniqueCodon);
    }

    public void tester(int start, int begin, int end) {
        FileResource f = new FileResource();
        String dna = f.asString().toUpperCase().trim();
        buildCodonMap(start, dna);
        String mostCommonCodon = getMostCommonCodon();
        System.out.println("Most Common Codon: " + mostCommonCodon + " count: " + map.get(mostCommonCodon));
        printCodonCounts(begin, end);
    }

    public static void main(String[] args) {
        codonCount c = new codonCount();
        c.tester(2, 1, 5);
    }
}
