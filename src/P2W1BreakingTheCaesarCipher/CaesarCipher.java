package P2W1BreakingTheCaesarCipher;

import edu.duke.FileResource;

public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private final int mainKey;

    public CaesarCipher(int key) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        alphabet = alphabet + alphabet.toLowerCase();
        shiftedAlphabet = shiftedAlphabet + shiftedAlphabet.toLowerCase();
        mainKey = key;
    }

    /**
     * Write the method encrypt that has two parameters, a String named input and an int named key. This method returns
     * a String that has been encrypted using the Caesar Cipher algorithm explained in the videos. Assume that all the
     * alphabetic characters are uppercase letters. For example, the call  encrypt(“FIRST LEGION ATTACK EAST FLANK!”,
     * 23) should return the string “CFOPQ IBDFLK XQQXZH BXPQ CIXKH!”
     */
    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder(input);
        for (int i = 0; i < input.length(); i++) {
            char currentChar = encrypted.charAt(i);
            int location = alphabet.indexOf(currentChar);
            if (location == -1) {
                continue;
            }
            char encryptedChar = shiftedAlphabet.charAt(location);
            encrypted.setCharAt(i, encryptedChar);
        }
        return encrypted.toString();
    }

    public String decrypt(String input) {
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        return cc.encrypt(input);
    }
}

