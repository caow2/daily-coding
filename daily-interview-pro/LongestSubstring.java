import java.util.HashSet;

/*
 * Given a string, find the length of the longest substring without repeating characters.
 */
public class LongestSubstring {
  public static void main(String[] args) {
    String s1 = ""; // 0
    String s2 = "abcdbcdef"; // 5
    String s3 = "AbcaB"; // 5
    String s4 = "abcdefe"; // 6
    String s5 = "ABDEFGABEF"; // 6
    String s6 = "aaaaaaa"; // 1

    System.out.println(longestSubstring(s1));
    System.out.println(longestSubstring(s2));
    System.out.println(longestSubstring(s3));
    System.out.println(longestSubstring(s4));
    System.out.println(longestSubstring(s5));
    System.out.println(longestSubstring(s6));
  }

  /**
   * A brute force approach would be to try every substring and see if there are
   * duplicate chars.
   * We can avoid duplicated work by managing a window in the String that doesn't
   * have duplicate chars.
   *
   * A bit vector or HashSet can be used to cache the current characters in our window.
   * Bit Vector would be easy to use if we don't care for uppercase characters or
   * s is gauranteed to have lower case.
   * O(n) time, O(n) space.
   */
  public static int longestSubstring(String s) {
    int start = 0, end = 0, max = 0;
    HashSet<Character> set = new HashSet<Character>();
    while(end < s.length()) {
      char c = s.charAt(end);
      while(start <= end && set.contains(c)) {
        set.remove(s.charAt(start++));
      }
      set.add(c);
      max = Math.max(max, end - start + 1);
      end++;
    }
    return max;
  }
}
