package VigenereProgram;

import java.util.*;

import edu.duke.*;

public class VigenereBreaker {

    /**
     * Write the public method breakVigenere with no parameters. This void method should put everything together, so
     * that you can create a new VigenereBreaker in BlueJ, call this method on it, and crack the cipher used on a
     * message. This method should perform 6 tasks (in this order): Create a new FileResource using its default
     * constructor (which displays a dialog for you to select a file to decrypt). Use the asString method to read the
     * entire contents of the file into a String. Use the tryKeyLength method, which you just wrote, to find the key for
     * the message you read in. For now, you should just pass ‘e’ for mostCommon. You should create a new
     * VigenereCipher, passing in the key that tryKeyLength found for you. You should use the VigenereCipher’s decrypt
     * method to decrypt the encrypted message. Finally, you should print out the decrypted message!
     */
    public void breakVigenere() {
        FileResource ff = new FileResource();
        String encrypted = ff.asString();
        int[] key = tryKeyLength(encrypted, 4, 'e');
        System.out.println(Arrays.toString(key));
        VigenereCipher vc = new VigenereCipher(key);
        String msg = vc.decrypt(encrypted);
        System.out.println(msg);
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
        String message = fr.asString();
        HashSet<String> wordsList = new HashSet<String>();
        for (String word : message.split("\\W")) {
            wordsList.add(word.toLowerCase());
        }
        return wordsList;
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
//        int[] key = v.tryKeyLength(encrypted, 5, 'e');
//        System.out.println(Arrays.toString(key));
        v.breakVigenere();
    }
}
