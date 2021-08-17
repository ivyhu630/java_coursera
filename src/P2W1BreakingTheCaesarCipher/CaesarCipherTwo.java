package P2W1BreakingTheCaesarCipher;

public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private final int mainKey1;
    private final int mainKey2;

    public CaesarCipherTwo(int key1, int key2) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        alphabet = alphabet + alphabet.toLowerCase();
        shiftedAlphabet1 = shiftedAlphabet1 + shiftedAlphabet1.toLowerCase();
        shiftedAlphabet2 = shiftedAlphabet2 + shiftedAlphabet2.toLowerCase();
        mainKey1 = key1;
        mainKey2 = key2;
    }

    public String encrypt(String input) {
        StringBuilder encrypted1 = new StringBuilder(input);
        for (int i = 0; i < input.length(); i++) {
            char currentChar1 = encrypted1.charAt(i);
            int location = alphabet.indexOf(currentChar1);
            if (location == -1) {
                continue;
            }
            char encryptedChar1 = shiftedAlphabet1.charAt(location);
            encrypted1.setCharAt(i, encryptedChar1);
        }
        StringBuilder encrypted2 = new StringBuilder(input);
        for (int i = 0; i < input.length(); i++) {
            char currentChar2 = encrypted2.charAt(i);
            int location = alphabet.indexOf(currentChar2);
            if (location == -1) {
                continue;
            }
            char encryptedChar2 = shiftedAlphabet2.charAt(location);
            encrypted2.setCharAt(i, encryptedChar2);
        }
        String encryptKey1 = encrypted1.toString();
        String encryptKey2 = encrypted2.toString();
        StringBuilder updatePhrase = new StringBuilder(input);
        for (int i = 0; i < input.length(); i++) {
            String ek = (i % 2 == 0) ? encryptKey1 : encryptKey2;
            updatePhrase.setCharAt(i, ek.charAt(i));
        }
        return updatePhrase.toString();
    }

    public String decrypt(String input) {
        CaesarCipherTwo cct = new CaesarCipherTwo(26 - mainKey1, 26 - mainKey2);
        return cct.encrypt(input);
    }
}




