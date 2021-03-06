package P2W2TellingARandomStory;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> map;

    public WordsInFiles() {
        map = new HashMap<>();
    }

    private void addWordsFromFile(File f) {
        FileResource fr = new FileResource(f);
        for (String word : fr.words()) {
            if (!map.containsKey(word)) {
                ArrayList<String> fileName = new ArrayList<>();
                fileName.add(f.getName());
                map.put(word, fileName);
            } else if (!map.get(word).contains(f.getName())) {
                map.get(word).add(f.getName());
            }
        }
    }

    public void buildWordFileMap() {
        map.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }

    public int maxNumber() {
        int maxNumber = -1;
        for (String word : map.keySet()) {
            if (maxNumber < map.get(word).size()) {
                maxNumber = map.get(word).size();
            }
        }
        return maxNumber;
    }

    public ArrayList<String> wordsInNumFiles(int number) {
        ArrayList<String> wordsInNumFiles = new ArrayList<>();
        for (String word : map.keySet()) {
            if (number != map.get(word).size()) {
                continue;
            }
            wordsInNumFiles.add(word);
        }
        return wordsInNumFiles;
    }

    public void printFilesIn(String word) {
        System.out.println(map.get(word));
    }

    public void tester() {
        buildWordFileMap();
        int maxNumber = maxNumber();
        System.out.print("maxNumber " + maxNumber + " which are: ");
        System.out.println(wordsInNumFiles(maxNumber).toString());
        System.out.println(wordsInNumFiles(7).size());
        System.out.println(map.get("laid").toString());
        System.out.println(map.get("tree").toString());
//        int i = 0;
//        ArrayList<String> filenames = wordsInNumFiles(maxNumber);
//        while (filenames.size() > i) {
//            printFilesIn(filenames.get(i));
//            i++;
//        }
    }

    public static void main(String[] args) {
        WordsInFiles w = new WordsInFiles();
        w.tester();
    }

}
