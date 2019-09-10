import java.util.HashMap;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.List;
/*
 * Given a list of words, group the words that are anagrams of each other.
 * (An anagram are words made up of the same letters).
 * Example:
 * Input: ['abc', 'bcd', 'cba', 'cbd', 'efg']
 * Output: [['abc', 'cba'], ['bcd', 'cbd'], ['efg']]
 */
public class GroupAnagrams {
  public static void main(String[] args) {
    String[] arr = new String[] {"abc", "bcd", "cba", "cbd", "efg"};
    System.out.println(group(arr));
  }

  /**
   * Let's first consider the simpler problem of how to figure out if 2 Strings
   * are anagrams or not:
   *  1. Sort both Strings -> 'acb' and 'bcd' are both 'abc' when sorted.
   *     We can then use the sorted String as a key to a list of its anagrams
   *  2. Convert each String to a HashMap of character counts -> 'abc' becomes { a : 1, b : 1, c : 1}
   *     and use the map as a key instead.
   *     Theoretically much more space intensive.
   */
  public static List<List<String>> group(String[] arr) {
    HashMap<String, List<String>> anagrams = new HashMap<String, List<String>>();
    for(String s : arr) { // O(n)
      String sorted = sort(s); //O(s log s)
      if(anagrams.get(sorted) == null) // O(1) assuming hashcode is precomputed and static
        anagrams.put(sorted, new LinkedList<String>());
      anagrams.get(sorted).add(s);
    }

    return new LinkedList<List<String>>(anagrams.values());
  }

  private static String sort(String s) {
    char[] ch = s.toCharArray();
    Arrays.sort(ch);
    return new String(ch);
  }
}
