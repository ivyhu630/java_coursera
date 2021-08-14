import javax.imageio.stream.ImageInputStream;

public class WordPlay {
    /**
     * Write a method isVowel that has one Char parameter named ch. This method returns true if ch is a vowel (one of
     * 'a', 'e', 'i', 'o', or 'u' or the uppercase versions) and false otherwise. You should write a tester method to
     * see if this method works correctly. For example, isVowel(‘F’) should return false, and isVowel(‘a’) should return
     * true.
     */
    public boolean isVowel(char ch) {
        String vowel = "aeiouAEIOU";
        for (int i = 0; i < vowel.length(); i++) {
            if (ch == vowel.charAt(i)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Write a method emphasize with two parameters, a String named phrase and a character named ch. This method should
     * return a String that is the string phrase but with the Char ch (upper- or lowercase) replaced by ‘*’ if it is in
     * an odd number location in the string (e.g. the first character has an odd number location but an even index, it
     * is at index 0), or
     * <p>
     * ‘+’ if it is in an even number location in the string (e.g. the second character has an even number location but
     * an odd index, it is at index 1).
     * <p>
     * For example, the call emphasize(“dna ctgaaactga”, ‘a’) would return the string “dn* ctg+*+ctg+”, and the call
     * emphasize(“Mary Bella Abracadabra”, ‘a’) would return the string “M+ry Bell+ +br*c*d*br+”. Be sure to test this
     * method.
     */

    public String emphasize(String phrase, char ch) {
        StringBuilder updatePhrase = new StringBuilder(phrase);
        for (int i = 0; i < phrase.length(); i++) {
            if (Character.toLowerCase(ch) != Character.toLowerCase(phrase.charAt(i))) {
                continue;
            }
            updatePhrase.setCharAt(i, (i % 2 == 0) ? '*' : '+');
        }
        return updatePhrase.toString();
    }

    public static void main(String[] args) {
        WordPlay w = new WordPlay();
//            System.out.println(w.isVowel('F'));
//            System.out.println(w.isVowel('a'));
        System.out.println(w.emphasize("dna ctgaaactga", 'a'));
        System.out.println(w.emphasize("Mary Bella Abracadabra", 'a'));
    }
}
