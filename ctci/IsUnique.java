import java.util.HashSet;
/*
 * Implement an algorithm to determine if a String has all unique characters.
 *
 * What if you can't use additional data structures?
 */
public class IsUnique {
  public static void main(String[] args) {
    String s = "";
    System.out.println(unique(s)); // T

    s = "abc";
    System.out.println(unique(s)); // T

    s = "aba";
    System.out.println(unique(s)); // F

    s = "Aqwea"; // case sensitivity
    System.out.println(unique(s)); // T

    s = "AaeA";
    System.out.println(unique(s)); // F
  }

  /**
   * The easiest way is for each character, check if the rest of the String has this character.
   * O(n^2) time, O(1) space.
   *
   * We could maybe sort it, and then compare each character to the next.
   * O(nlogn) time, O(1) space for something like MergeSort.
   *
   * How can we see if we've seen a character previously?
   * Maybe a data structure such as a hashtable, which offers O(1) lookup on average.
   * O(n) time, O(n) space.
   */
  public static boolean unique(String s) {
    HashSet<Character> seen = new HashSet<Character>();
    for(char c : s.toCharArray()) {
      if(! seen.add(c)) // already in the set
        return false;
    }
    return true;
  }
}
