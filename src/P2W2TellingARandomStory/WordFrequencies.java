package P2W2TellingARandomStory;

import edu.duke.FileResource;

import java.util.ArrayList;
import java.util.Collections;

public class WordFrequencies {
    private final ArrayList<String> myWords;
    private final ArrayList<Integer> myFreqs;

    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }

    public void findUnique() {
        myWords.clear();
        myFreqs.clear();
        FileResource ff = new FileResource();
        for (String word : ff.words()) {
            word = word.toLowerCase();
            int index = myWords.indexOf(word);
            if (index == -1) {
                myWords.add(word);
                myFreqs.add(1);
            } else {
                int value = myFreqs.get(index);
                myFreqs.set(index, value + 1);
            }
        }
    }

    public void tester() {
        findUnique();
        for (int i = 0; i < myWords.size(); i++) {
            System.out.println(myWords.get(i));
            System.out.println(myFreqs.get(i));
        }
        int maxIndex = findIndexOfMax();
        System.out.println("most frequent word: " + myWords.get(maxIndex) + " : " + myFreqs.get(maxIndex));
        System.out.println("#ofUnique words " + myWords.size());
    }

    public int findIndexOfMax() {
        Integer max = Collections.max(myFreqs);
        return myFreqs.indexOf(max);
    }

    public static void main(String[] args) {
        WordFrequencies ww = new WordFrequencies();
        ww.tester();
    }
}
