import edu.duke.FileResource;

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
        resource = new FileResource();
        
    }

    public static void main(String[] args) {

    }
}
