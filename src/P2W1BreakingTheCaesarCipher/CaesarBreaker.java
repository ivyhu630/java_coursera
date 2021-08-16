package P2W1BreakingTheCaesarCipher;

public class CaesarBreaker {
    /**
     * Complete the decryption method shown in the lesson by creating a P2W1BreakingTheCaesarCipher.CaesarBreaker class
     * with the methods countLetters, maxIndex, and decrypt. Recall that the decrypt method creates a
     * P2W1BreakingTheCaesarCipher.CaesarCipher object in order to use the encrypt method you wrote for the last lesson.
     * Make sure that your P2W1BreakingTheCaesarCipher.CaesarCipher class is in the same folder as
     * P2W1BreakingTheCaesarCipher.CaesarBreaker! You may want to use the following code as part of your decrypt
     * method.
     */
    public String decrypt(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        return cc.encrypt(encrypted, 26 - dkey);
    }

    public int[] countLetters(String encrypted) {
        String aphbt = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int[] counts = new int[26];
        for (int k = 0; k < encrypted.length(); k++) {
            char ch = Character.toUpperCase(encrypted.charAt(k));
            int dex = aphbt.indexOf(ch);
            if (dex != -1) {
                counts[dex]++;
            }
        }
        return counts;
    }

    public int maxIndex(int[] values) {
        WordLengths w = new WordLengths();
        return w.indexOfMax(values);
    }

    /**
     * Write the method halfOfString in the P2W1BreakingTheCaesarCipher.CaesarBreaker class that has two parameters, a
     * String parameter named message and an int parameter named start. This method should return a new String that is
     * every other character from message starting with the start position. For example, the call halfOfString(“Qbkm
     * Zgis”, 0) returns the String “Qk gs” and the call halfOfString(“Qbkm Zgis”, 1) returns the String “bmZi”. Be sure
     * to test this method with a small example.
     */

    public String halfOfString(String message, int start) {
        StringBuilder sb0 = new StringBuilder();
        StringBuilder sb1 = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char currentChar = message.charAt(i);
            if (i % 2 == 0) {
                sb0.append(currentChar);
            } else {
                sb1.append(currentChar);
            }
        }
        if (start == 0) {
            return sb0.toString();
        } else {
            return sb1.toString();
        }

    }

    /**
     * Write the method getKey in the P2W1BreakingTheCaesarCipher.CaesarBreaker class that has one parameter, a String
     * s. This method should call countLetters to get an array of the letter frequencies in String s and then use
     * maxIndex to calculate the index of the largest letter frequency, which is the location of the encrypted letter
     * ‘e’, which leads to the key, which is returned.
     */

    public int getKey(String s) {
        int[] freqs = countLetters(s);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        return dkey;
    }

    /**
     * Write the method decryptTwoKeys in the P2W1BreakingTheCaesarCipher.CaesarBreaker class that has one parameter, a
     * String parameter named encrypted that represents a String that was encrypted with the two key algorithm discussed
     * in the previous lesson. This method attempts to determine the two keys used to encrypt the message, prints the
     * two keys, and then returns the decrypted String with those two keys. More specifically, this method should:
     * Calculate a String of every other character starting with the first character of the encrypted String by calling
     * halfOfString.
     * <p>
     * - Calculate a String of every other character starting with the second character of the encrypted String.
     * <p>
     * - Then calculate the key used to encrypt each half String.
     * <p>
     * - You should print the two keys found.
     * <p>
     * - Calculate and return the decrypted String using the encryptTwoKeys method from your
     * P2W1BreakingTheCaesarCipher.CaesarCipher class, again making sure it is in the same folder as your
     * P2W1BreakingTheCaesarCipher.CaesarBreaker class.
     */

    public String decryptTwoKeys(String encrypted) {
        String half0 = halfOfString(encrypted, 0);
        String half1 = halfOfString(encrypted, 1);
        int key0 = getKey(half0);
        System.out.println(key0);
        System.out.println(half0);
        int key1 = getKey(half1);
        System.out.println(key1);
        System.out.println(half1);
        CaesarCipher cc = new CaesarCipher();
        return cc.encryptTwoKeys(encrypted, 26 - key0, 26 - key1);
    }

    public void testDecrypt() {
//        FileResource f = new FileResource();
//        String original = f.asString();
//        System.out.println("original: " + original);
//        P2W1BreakingTheCaesarCipher.CaesarCipher cc = new P2W1BreakingTheCaesarCipher.CaesarCipher();
//        System.out.println("encrypted: " + encrypt);
//        String decrypt = decrypt(encrypt);
//        System.out.println("decrypted: " + decryptTwoKeys(original));
        String test = "Akag tjw Xibhr awoa aoee xakex znxag xwko";
        String test1 = "Gwpv c vbuq pvokki yfve iqqu qc bgbgbgbgbgbgbgbgbu";
//        String test = "Top ncmy qkff vi vguv vbg ycpx";

//        String encrypt = cc.encryptTwoKeys(test, 26 - 2, 26 - 20);
        System.out.println(decryptTwoKeys(test));


    }

    public static void main(String[] args) {
        CaesarBreaker b = new CaesarBreaker();
        b.testDecrypt();
//        System.out.println(b.halfOfString("Qbkm Zgis", 1));
//        System.out.println(b.decryptTwoKeys("Top ncmy qkff vi vguv vbg ycpx"));
    }
}
