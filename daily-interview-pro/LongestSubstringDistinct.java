import java.util.HashMap;
/*
 * You are given a string s, and an integer k.
 * Return the length of the longest substring in s that contains at most k distinct characters.
 *
 * For instance, given the string:
 * aabcdefff and k = 3, then the longest substring with 3 distinct characters
 * would be defff. The answer should be 5.
 */
public class LongestSubstringDistinct {
  public static void main(String[] args) {
    String s = "aabcdefff";
    int k = 3;
    System.out.println(longestSubstring(s,k)); // 5

    k = 5;
    System.out.println(longestSubstring(s,k)); // 7

    k = 6;
    System.out.println(longestSubstring(s,k)); // 9

    k = 1;
    System.out.println(longestSubstring(s,k)); // 3

    k = 0;
    System.out.println(longestSubstring(s,k)); // 0

    s = "abcabcdabc";
    k = 3;
    System.out.println(longestSubstring(s,k)); // 6

    k = 4;
    System.out.println(longestSubstring(s,k)); // 10
  }

  public static int longestSubstring(String s, int k) {
    if(k <= 0)
      return 0;

    int longest = 0;
    HashMap<Character, Integer> map = new HashMap<Character, Integer>();
    int start = 0, end = 0;

    while(start <= end && end < s.length()) {
      char ch = s.charAt(end);
      map.put(ch, map.getOrDefault(ch, 0) + 1);

      while(map.size() > k) {
        ch = s.charAt(start);
        map.put(ch, map.get(ch) - 1);
        if(map.get(ch) == 0)
          map.remove(ch);
        start++;
      }

      end++;
      longest = Math.max(longest, end - start);
    }
    return longest;
  }
}
