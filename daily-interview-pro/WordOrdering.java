import java.util.Map;
import java.util.HashMap;

/*
 * Given a list of words, and an arbitrary alphabetical order,
 * verify that the words are in order of the alphabetical order.
 * Example:
 * Input: words = ["abcd", "efgh"], order="zyxwvutsrqponmlkjihgfedcba"
 * Output: False
 * Explanation: 'e' comes before 'a' so 'efgh' should come before 'abcd'
 *
 * Example 2:
 * Input: words = ["zyx", "zyxw", "zyxwy"],
 * order="zyxwvutsrqponmlkjihgfedcba"
 * Output: True
 * Explanation: The words are in increasing alphabetical order
 */
public class WordOrdering {
  public static void main(String[] args) {
    String[] words = new String[] { "abcd", "efgh" };
    String order = "zyxwvutsrqponmlkjihgfedcba";
    System.out.println(isOrdered(words, order)); // F

    words = new String[] { "zyx", "zyxw", "zyxwy" };
    order = "zyxwvutsrqponmlkjihgfedcba";
    System.out.println(isOrdered(words, order)); // T

    words = new String[] {"", "abc", "abc", "abce"};
    order = "abcdefghijklmnopqrstuvwxyz";
    System.out.println(isOrdered(words, order)); // T

    words = new String[] {"", "abc", "ace", "acd"};
    System.out.println(isOrdered(words, order)); // F
  }

  /**
   * O(n * w) time, O(1) space since alphabet is O(26)
   */
  public static boolean isOrdered(String[] words, String alphabet) {
    Map<Character, Integer> map = mapAlphabet(alphabet);
    for(int i = 1; i < words.length; i++) {
      if(! lessThan(words[i - 1], words[i], map))
        return false;
    }
    return true;
  }

  private static Map<Character, Integer> mapAlphabet(String alphabet) {
    Map<Character, Integer> map = new HashMap<Character, Integer>();
    for(int i = 0; i < alphabet.length(); i++) {
      map.put(alphabet.charAt(i), i + 1);
    }
    return map;
  }

  // Return true if word1 is less than word2 lexographically acccording to the alphabet
  private static boolean lessThan(String word1, String word2, Map<Character, Integer> alphabet) {
    int size = Math.min(word1.length(), word2.length());
    for(int i = 0; i < size; i++) {
      char c1 = word1.charAt(i), c2 = word2.charAt(i);
      if(alphabet.get(c1) > alphabet.get(c2))
        return false;
    }
    return true;
  }
}
