package P2W2TellingARandomStory;

import edu.duke.FileResource;

import java.util.ArrayList;

public class CharactersInPlay {
    private final ArrayList<String> allNames;
    private final ArrayList<Integer> freq;

    public CharactersInPlay() {
        allNames = new ArrayList<String>();
        freq = new ArrayList<Integer>();
    }

    public void update(String person) {
        int index = allNames.indexOf(person);
        if (index == -1) {
            allNames.add(person);
            freq.add(1);
        } else {
            int value = freq.get(index);
            freq.set(index, value + 1);
        }
    }

    public void findAllCharacters() {
        allNames.clear();
        freq.clear();
        FileResource ff = new FileResource();
        for (String line : ff.lines()) {
            int periodIndex = line.indexOf(".");
            if (periodIndex == -1) {
                continue;
            }
            String name = line.substring(0, periodIndex);
            update(name);
        }
    }

    public void charactersWithNumParts(int num1, int num2) {
        for (int k1 = num1; k1 <= num2; k1++) {
            String name = allNames.get(k1);
            System.out.println(name);
        }
    }

    public void tester() {
        findAllCharacters();
        for (int i = 0; i < allNames.size(); i++) {
            if (freq.get(i) > 5) {
                System.out.println(allNames.get(i) + " frequency: " + freq.get(i));
            }
        }
        charactersWithNumParts(10, 15);
    }

    public static void main(String[] args) {
        CharactersInPlay cip = new CharactersInPlay();
        cip.tester();
    }
}
