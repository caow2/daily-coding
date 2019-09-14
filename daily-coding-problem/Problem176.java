import java.util.HashMap;

/*
 * Determine whether there exists a one-to-one character mapping from one string s1 to another s2.
 * For example, given s1 = abc and s2 = bcd, return true since we can map a to b, b to c, and c to d.
 *
 * Given s1 = foo and s2 = bar, return false since the o cannot map to two characters.
 */
public class Problem176 {
  public static void main(String[] args) {
    String s1 = "abcd", s2 = "bedc";
    System.out.println(oneToOne(s1, s2)); // T

    s1 = "abcd";
    s2 = "abcb";
    System.out.println(oneToOne(s1, s2)); // F

    s1 = "abbd";
    s2 = "bccc";
    System.out.println(oneToOne(s1, s2)); // F

    s2 = "bced";
    System.out.println(oneToOne(s1, s2)); // F

    s2 = "bdda";
    System.out.println(oneToOne(s1, s2)); // T

    s2 = "abab";
    System.out.println(oneToOne(s1, s2)); // F
  }

  // Assume s1 and s2 are same length
  public static boolean oneToOne(String s1, String s2) {
    HashMap<Character, Character> ab = new HashMap<Character, Character>();
    HashMap<Character, Character> ba = new HashMap<Character, Character>();
    for(int i = 0; i < s1.length(); i++) {
      char c1 = s1.charAt(i), c2 = s2.charAt(i);
      if(ab.get(c1) == null && ba.get(c2) == null) {
        ab.put(c1, c2);
        ba.put(c2, c1);
      }
      else if(ab.get(c1) == null || ba.get(c2) == null ||
        c1 != (ba.get(c2)) || c2 != (ab.get(c1))) // mapping is different
        return false;
    }
    return true;
  }
}
