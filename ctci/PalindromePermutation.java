import java.util.HashMap;
/*
 * Given a String, write a function to check if it is a permutation of a palindrome.
 * A palindrome is a word or phrase that is the same forwards and backwards.
 * A permutation is a rearrangement of letters.
 * The palindrome does not need to be limited to just dictionary words.
 */
public class PalindromePermutation {
  public static void main(String[] args) {
    String s = "";
    System.out.println(isPalindromePermutation(s)); // T

    s = "tacocat";
    System.out.println(isPalindromePermutation(s)); // T

    s = "tcoacta";
    System.out.println(isPalindromePermutation(s)); // T

    s = "tacoat";
    System.out.println(isPalindromePermutation(s)); // F
  }

  /**
   * Note that a palindrome like 'tacocat' has an even number of each character,
   * and at most 1 odd character.
   * This makes sense because in order for a String to be a palindrome, we have
   * a center, and then the same characters on both sides. i.e. ---a---, ------
   *
   * A permutation of a palindrome must also satisfy these constraints.
   * O(n) time, O(n) space.
   */
  public static boolean isPalindromePermutation(String s) {
    HashMap<Character, Integer> map = new HashMap<Character, Integer>();

    for(char c : s.toCharArray()) {
      map.put(c, map.getOrDefault(c, 0) + 1);
    }

    int oddCount = 0;
    for(char c : map.keySet()) {
      if(map.get(c) % 2 > 0)
        oddCount++;
    }
    return oddCount <= 1;
  }
}
