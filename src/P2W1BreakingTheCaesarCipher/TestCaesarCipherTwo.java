package P2W1BreakingTheCaesarCipher;

import edu.duke.FileResource;

public class TestCaesarCipherTwo {
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

    private String halfOfString(String message, int start) {
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

    public void simpleTests() {
        FileResource f = new FileResource();
        String input = f.asString();
//        String input = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";

//        CaesarCipherTwo cc2 = new CaesarCipherTwo(14, 24);
//        System.out.println(cc2.encrypt(input));
        System.out.println(breakCaesarCipher(input));
    }

    public String breakCaesarCipher(String input) {
        String input1 = halfOfString(input, 0);
        String input2 = halfOfString(input, 1);
        int[] freqs1 = countLetters(input1);
        int maxDex1 = maxIndex(freqs1);
        int dkey1 = maxDex1 - 4;
        if (maxDex1 < 4) {
            dkey1 = 26 - (4 - maxDex1);
        }
        int[] freqs2 = countLetters(input2);
        int maxDex2 = maxIndex(freqs2);
        int dkey2 = maxDex2 - 4;
        if (maxDex2 < 4) {
            dkey2 = 26 - (4 - maxDex2);
        }
        CaesarCipherTwo cc2 = new CaesarCipherTwo(dkey1, dkey2);
        System.out.println((dkey1) + " " + (dkey2));
        return cc2.decrypt(input);
    }

    public static void main(String[] args) {
        TestCaesarCipherTwo tc2 = new TestCaesarCipherTwo();
        tc2.simpleTests();
    }

}
