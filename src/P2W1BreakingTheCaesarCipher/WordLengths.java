package P2W1BreakingTheCaesarCipher;

import edu.duke.FileResource;

import java.util.Arrays;


public class WordLengths {

    /**
     * Write a void method countWordLengths that has two parameters, a FileResource named resource and an integer array
     * named counts. This method should read in the words from resource and count the number of words of each length for
     * all the words in resource, storing these counts in the array counts. - For example, after this method executes,
     * counts[k] should contain the number of words of length k.
     * <p>
     * - If a word has a non-letter as the first or last character, it should not be counted as part of the word length.
     * For example, the word And, would be considered of length 3 (the comma is not counted), the word “blue-jeans”
     * would be considered of length 10 (the double quotes are not counted, but the hyphen is). Note that we will
     * miscount some words, such as “Hello,” which will be counted as 6 since we don’t count the double quotes but will
     * count the comma, but that is OK as there should not be many words in that category.
     * <p>
     * - For any words equal to or larger than the last index of the counts array, count them as the largest size
     * represented in the counts array.
     * <p>
     * - You may want to consider using the Character.isLetter method that returns true if a character is a letter, and
     * false otherwise. For example, Character.isLetter(‘a’) returns true, and Character.isLetter(‘-’) returns false.
     */

    public void countWordLengths(FileResource resource, int[] counts) {
        for (String word : resource.words()) {
            int length = word.length();
            int currentLength = length;
            char begChar = word.charAt(0);
            char endChar = word.charAt(length - 1);
            if (Character.isDigit(begChar)) {
                continue;
            }
            if (!Character.isLetter(begChar)) {
                currentLength--;
            }
            if (!Character.isLetter(endChar)) {
                currentLength--;
            }
            if (currentLength >= counts.length) {
                counts[counts.length - 1]++;
                continue;
            }
            counts[currentLength]++;
        }
    }

    /**
     * Write a void method testCountWordLengths that creates a FileResource so you can select a file, and creates a
     * counts integer array of size 31. This method should call countWordLengths with a file and then print the number
     * of words of each length. Test it on the small file smallHamlet.txt shown below. Write a method indexOfMax that
     * has one parameter named values that is an integer array. This method returns the index position of the largest
     * element in values. Then add code to the method testCountWordLengths to call indexOfMax to determine the most
     * common word length in the file. For example, calling indexOfMax after calling countWordLengths on the file
     * smallHamlet.txt should return 3.
     */

    public void testCountWordLengths() {
        FileResource fr = new FileResource();
        int[] counts = new int[100];
        countWordLengths(fr, counts);
        System.out.println(Arrays.toString(counts));
        System.out.println(indexOfMax(counts));
    }

    public int indexOfMax(int[] values) {
        int maxValue = 0;
        int indexOfMax = -1;
        for (int i = 0; i < values.length; i++) {
            if (values[i] > maxValue) {
                maxValue = values[i];
                indexOfMax = i;
            }
        }
        return indexOfMax;
    }

    public static void main(String[] args) {
        WordLengths w = new WordLengths();
        w.testCountWordLengths();
    }
}
