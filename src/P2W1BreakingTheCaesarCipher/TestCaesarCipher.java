package P2W1BreakingTheCaesarCipher;

import edu.duke.FileResource;

public class TestCaesarCipher {
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

    public String breakCaesarCipher(String input) {
        CaesarCipher cc = new CaesarCipher(15);
        int[] freqs = countLetters(input);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        return cc.encrypt(input);
    }


    public void simpleTests() {
//        FileResource f = new FileResource();
//        String input = f.asString();
        String test = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        CaesarCipher cc = new CaesarCipher(15);
        System.out.println(cc.encrypt(test));
//        System.out.println(breakCaesarCipher(test));
    }

    public static void main(String[] args) {
        TestCaesarCipher t = new TestCaesarCipher();
        t.simpleTests();
    }
}
