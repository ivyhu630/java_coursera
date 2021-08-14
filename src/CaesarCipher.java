import edu.duke.FileResource;

public class CaesarCipher {
    /**
     * Write the method encrypt that has two parameters, a String named input and an int named key. This method returns
     * a String that has been encrypted using the Caesar Cipher algorithm explained in the videos. Assume that all the
     * alphabetic characters are uppercase letters. For example, the call  encrypt(“FIRST LEGION ATTACK EAST FLANK!”,
     * 23) should return the string “CFOPQ IBDFLK XQQXZH BXPQ CIXKH!”
     */
    public String encrypt(String input, int key) {
        String aphbt = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAphbt = aphbt.substring(key) + aphbt.substring(0, key);
        String newAphbt = aphbt + aphbt.toLowerCase();
        String newShiftedAphbt = shiftedAphbt + shiftedAphbt.toLowerCase();
        StringBuilder encrypted = new StringBuilder(input);
        for (int i = 0; i < input.length(); i++) {
            char currentChar = encrypted.charAt(i);
            int location = newAphbt.indexOf(currentChar);
            if (location == -1) {
                continue;
            }
            char encryptedChar = newShiftedAphbt.charAt(location);
            encrypted.setCharAt(i, encryptedChar);
        }
        return encrypted.toString();
    }

    /**
     * Write the void method testCaesar that has no parameters. This method should read a file and encrypt the complete
     * file using the Caesar Cipher algorithm, printing the encrypted message. You may want to include the lines:
     */
    public void testCaesar() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt("FIRST LEGION ATTACK EAST FLANK!", 23);
        System.out.println("key is " + 23 + "\n" + encrypted);
    }

    /**
     * Write the method encryptTwoKeys that has three parameters, a String named input, and two integers named key1 and
     * key2. This method returns a String that has been encrypted using the following algorithm. Parameter key1 is used
     * to encrypt every other character with the Caesar Cipher algorithm, starting with the first character, and key2 is
     * used to encrypt every other character, starting with the second character. For example, the call
     * encryptTwoKeys(“First Legion”, 23, 17) should return “Czojq Ivdzle”. Note the ‘F’ is encrypted with key 23, the
     * first ‘i’ with 17, the ‘r’ with 23, and the ‘s’ with 17, etc. Be sure to test this method.
     */

    public String encryptTwoKeys(String input, int key1, int key2) {
        StringBuilder updatePhrase = new StringBuilder(input);
        String encryptKey1 = encrypt(input, key1);
        String encryptKey2 = encrypt(input, key2);
        for (int i = 0; i < input.length(); i++) {
            String ek = (i % 2 == 0) ? encryptKey1 : encryptKey2;
            updatePhrase.setCharAt(i, ek.charAt(i));
        }
        return updatePhrase.toString();
    }


    public static void main(String[] args) {
        CaesarCipher c = new CaesarCipher();
        //test comment
//        System.out.println(c.encrypt("First Legion", 23));
        System.out.println(c.encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21));
//        System.out.println(c.encryptTwoKeys("First Legion", 23, 17));

    }
}

