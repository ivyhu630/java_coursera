package VigenereProgram;

import java.util.*;

import edu.duke.*;

public class VigenereBreaker {

    /**
     * Finally, you need to modify your breakVigenere method to make use of your new code. The steps below describe what
     * your breakVigenere method should do. They are similar to the steps from the previous portion of this project but
     * have been updated: new items are in italics, [and old items that you should no longer do are in square brackets.]
     * Create a new FileResource using its default constructor (which displays a dialog for you to select a file to
     * decrypt). Use that FileResource’s asString method to read the entire contents of the file into a String. You
     * should make a new FileResource to read from the English dictionary file that we have provided. [Use the
     * tryKeyLength method that you just wrote to find the key for the message you read in. For now, you should just
     * pass ‘e’ for mostCommon.] You should use your readDictionary method to read the contents of that file into a
     * HashSet of Strings. [You should create a new VigenereCipher, passing in the key that tryKeyLength found for you.]
     * You should use the breakForLanguage method that you just wrote to decrypt the encrypted message. [You should use
     * a VigenereCipher object to decrypt the encrypted message.] Finally, you should print out the decrypted message!
     * Test this method on the text file athens_keyflute.txt. The first line should be “SCENE II. Athens. QUINCE'S
     * house”, and the key is “flute”, or {5, 11, 20, 19, 4}. This file contains 376 valid words out of 386 (total word
     * count ignores whitespace).
     */
    public void breakVigenere() {
        FileResource ff = new FileResource();
        String encrypted = ff.asString();
        FileResource dd = new FileResource();
        HashSet<String> dictionary = readDictionary(dd);
        String msg = breakForLanguage(encrypted, dictionary);
        System.out.println(msg);
    }

    /**
     * In the VigenereBreaker class, write the public method mostCommonCharIn, which has one parameter—a HashSet of
     * Strings dictionary. This method should find out which character, of the letters in the English alphabet, appears
     * most often in the words in dictionary. It should return this most commonly occurring character. Remember that you
     * can iterate over a HashSet of Strings with a for-each style for loop.
     */
    public char mostCommonCharIn(HashSet<String> dictionary) {
        HashMap<Character, Integer> charCount = new HashMap<Character, Integer>();
        String wordsCombined = dictionary.toString();
        char[] words = wordsCombined.toCharArray();
        String alph = "abcdefghijklmnopqrstuvwxyz";
        char[] alphbet = alph.toCharArray();
        for (char ch : words) {
            if (charCount.containsKey(ch)) {
                charCount.put(ch, charCount.get(ch) + 1);
            } else {
                charCount.put(ch, 1);
            }
        }
        char mostCommon = 0;
        int maxCount = 0;
        for (char ch : alphbet) {
            Integer currentCount = charCount.get(ch);
            if (currentCount != null) {
                if (maxCount < currentCount) {
                    maxCount = currentCount;
                    mostCommon = ch;
                }
            }
        }
        System.out.println(mostCommon);
        return mostCommon;
    }

    /**
     * n the VigenereBreaker class, write the public method breakForAllLangs, which has two parameters—a String
     * encrypted, and a HashMap, called languages, mapping a String representing the name of a language to a HashSet of
     * Strings containing the words in that language. Try breaking the encryption for each language, and see which gives
     * the best results! Remember that you can iterate over the languages.keySet() to get the name of each language, and
     * then you can use .get() to look up the corresponding dictionary for that language. You will want to use the
     * breakForLanguage and countWords methods that you already wrote to do most of the work (it is slightly inefficient
     * to re-count the words here, but it is simpler, and the inefficiency is not significant). You will want to print
     * out the decrypted message as well as the language that you identified for the message.
     */

    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
        
    }

    /**
     * In the VigenereBreaker class, write the public method readDictionary, which has one parameter—a FileResource fr.
     * This method should first make a new HashSet of Strings, then read each line in fr (which should contain exactly
     * one word per line), convert that line to lowercase, and put that line into the HashSet that you created. The
     * method should then return the HashSet representing the words in a dictionary. All the dictionary files, including
     * the English dictionary file, are included in the starter program you download. They are inside the folder called
     * ‘dictionaries’.
     */
    public HashSet<String> readDictionary(FileResource fr) {
//        String message = fr.asString();
        HashSet<String> wordsList = new HashSet<String>();
        for (String line : fr.lines()) {
            wordsList.add(line.toLowerCase());
        }
        return wordsList;
    }

    /**
     * In the VigenereBreaker class, write the public method countWords, which has two parameters—a String message, and
     * a HashSet of Strings dictionary. This method should split the message into words (use .split(“\\W+”), which
     * returns a String array), iterate over those words, and see how many of them are “real words”—that is, how many
     * appear in the dictionary. Recall that the words in dictionary are lowercase. This method should return the
     * integer count of how many valid words it found.
     */

    public int countWords(String message, HashSet<String> dictionary) {
        int i = 0;
        for (String word : message.split("\\W+")) {
            if (dictionary.contains(word.toLowerCase())) {
                i++;
            }
        }
        return i;
    }

    /**
     * In the VigenereBreaker class, write the public method breakForLanguage, which has two parameters—a String
     * encrypted, and a HashSet of Strings dictionary. This method should try all key lengths from 1 to 100 (use your
     * tryKeyLength method to try one particular key length) to obtain the best decryption for each key length in that
     * range. For each key length, your method should decrypt the message (using VigenereCipher’s decrypt method as
     * before), and count how many of the “words” in it are real words in English, based on the dictionary passed in
     * (use the countWords method you just wrote). This method should figure out which decryption gives the largest
     * count of real words, and return that String decryption. Note that there is nothing special about 100; we will
     * just give you messages with key lengths in the range 1–100. If you did not have this information, you could
     * iterate all the way to encrypted.length(). Your program would just take a bit longer to run.
     */

    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        int count = 0;
        String message = "";
        int[] test = null;
        int keyLength = 0;
        for (int i = 1; i < 100; i++) {
            int[] key = tryKeyLength(encrypted, i, 'e');
            VigenereCipher vc = new VigenereCipher(key);
            String decrypted = vc.decrypt(encrypted);
            int currentCount = countWords(decrypted, dictionary);
            if (currentCount > count) {
                message = decrypted;
                count = currentCount;
//                System.out.println(message);
                test = key;
                keyLength = i;
            }
        }
        System.out.println("valid word: " + count);
        System.out.println(Arrays.toString(test));
        System.out.println("key length: " + keyLength);
        return message;
    }

    /**
     * Write the public method sliceString, which has three parameters—a String message, representing the encrypted
     * message, an integer whichSlice, indicating the index the slice should start from, and an integer totalSlices,
     * indicating the length of the key. This method returns a String consisting of every totalSlices-th character from
     * message, starting at the whichSlice-th character.
     */
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder slicedMsg = new StringBuilder();
        for (int i = whichSlice; i < message.length(); i += totalSlices) {
            slicedMsg.append(message.charAt(i));
        }
        return slicedMsg.toString();
    }

    /**
     * Write the public method tryKeyLength, which takes three parameters—a String encrypted that represents the
     * encrypted message, an integer klength that represents the key length, and a character mostCommon that indicates
     * the most common character in the language of the message. This method should make use of the CaesarCracker class,
     * as well as the sliceString method, to find the shift for each index in the key. You should fill in the key (which
     * is an array of integers) and return it. Test this method on the text file athens_keyflute.txt, which is a scene
     * from A Midsummer Night’s Dream encrypted with the key “flute”, and make sure you get the key {5, 11, 20, 19, 4}.
     */
    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for (int i = 0; i < klength; i++) {
            String encryptedSlice = sliceString(encrypted, i, klength);
            key[i] = cc.getKey(encryptedSlice);
        }
        return key;
    }

    public static void main(String[] args) {
        VigenereBreaker v = new VigenereBreaker();
//        System.out.println(v.sliceString("abcdefghijklm", 0, 3));
//        System.out.println(v.sliceString("abcdefghijklm", 1, 4));
//        System.out.println(v.sliceString("abcdefghijklm", 4, 5));
//        System.out.println(v.sliceString("abcdefghijklm", 0, 5));
//        FileResource ff = new FileResource();
//        String encrypted = ff.asString();
//        System.out.print("key is: ");
//        int[] key = v.tryKeyLength(encrypted, 38, 'e');
        FileResource dd = new FileResource();
        HashSet<String> dictionary = v.readDictionary(dd);
//        VigenereCipher vc = new VigenereCipher(key);
//        String msg = vc.decrypt(encrypted);
//        System.out.println(Arrays.toString(key));
//        v.breakVigenere();
//        String test = "I have a dream.";
//        FileResource dd = new FileResource();
//        HashSet<String> dictionary = v.readDictionary(dd);
//        System.out.println(v.countWords(msg, dictionary));
        v.mostCommonCharIn(dictionary);
    }
}
