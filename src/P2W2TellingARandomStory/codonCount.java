package P2W2TellingARandomStory;

import edu.duke.FileResource;

import java.util.HashMap;
import java.util.Map;

public class codonCount {
    private HashMap<String, Integer> map;

    public codonCount() {
        map = new HashMap<String, Integer>();
    }

    public HashMap buildCodonMap(int start, String dna) {
        map.clear();
        int i = 0;
        for (i + start + 3 < dna.length()) {
            String condon = dna.substring(start + i, start + 3)
        }


        return map;
    }
}
