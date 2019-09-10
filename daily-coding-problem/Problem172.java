import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/*
 * Given a string s and a list of words words, where each word is the same length,
 * find all starting indices of substrings in s that is a concatenation of every word in words exactly once.
 *
 * For example, given s = "dogcatcatcodecatdog" and words = ["cat", "dog"],
 * return [0, 13], since "dogcat" starts at index 0 and "catdog" starts at index 13.
 *
 * Given s = "barfoobazbitbyte" and words = ["dog", "cat"],
 * return [] since there are no substrings composed of "dog" and "cat" in s.
 * The order of the indices does not matter.
 */
public class Problem172 {
  public static void main(String[] args) {
    String s = "dogcatcatcodecatdog";
    String[] words = new String[] {"cat", "dog"};
    System.out.println(find(words, s));

    s = "barfoobazbitbyte";
    System.out.println(find(words, s));

    s = "dogandcatanddogcatdog";
    System.out.println(find(words, s)); // [12, 15]

    s = "cccatdogg";
    System.out.println(find(words, s)); // [2]
  }

  // Check every sliding window of length W (sum of all word's lengths)
  // Let L be length of String S, and N be number of words
  // O(N + L*W^2)
  public static List<Integer> find(String[] words, String s) {
    List<Integer> result = new ArrayList<Integer>();
    int window = 0;
    Set<String> set = new HashSet<String>();
    for(String word : words) { //O(N)
      set.add(word);
      window += word.length();
    }

    if(s.length() < window)
      return result;

    for(int i = 0; i <= s.length() - window; i++) { // O(L)
      Set<String> seen = new HashSet<String>();

      int start = i, end = i;
      StringBuilder sb = new StringBuilder();
      while(end < i + window) { //O(W)
        sb.append(s.charAt(end++));
        String current = sb.toString(); //O(W)
        if(set.contains(current) && ! seen.contains(current)) { // new word
          seen.add(current);
          sb = new StringBuilder(); // process the next word
          start = end;
        }
      }

      if(set.size() == seen.size()) // we've encountered all the words
        result.add(i);
    }

    return result;
  }
}
