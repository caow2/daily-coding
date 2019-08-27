import java.util.HashSet;

/*
 * Given a string, return the first recurring character in it, or null if there is no recurring character.
 *
 * For example, given the string "acbbac", return "b". Given the string "abcdef", return null.
 */
public class Problem159 {
  public static void main(String[] args) {
    String s = "aba";
    System.out.println(firstRecurring(s));

    s = "ab";
    System.out.println(firstRecurring(s));

    s = "";
    System.out.println(firstRecurring(s));

    s = "abeba";
    System.out.println(firstRecurring(s));
  }

  public static Character firstRecurring(String s) {
    HashSet<Character> set = new HashSet<Character>();
    for(char c : s.toCharArray()) {
      if(! set.add(c))
        return c;
    }
    return null;
  }
}
