import java.util.HashMap;

/*
 * Given 2 Strings, write a method to decide if one is a permutation of the other.
 */
public class CheckPermutation {
  public static void main(String[] args) {
    String a = "abc", b = "bca";
    System.out.println(isPermutation(a, b)); // T

    a = "acD";
    b = "acd";
    System.out.println(isPermutation(a, b)); // F

    a = "aaa";
    b = "aca";
    System.out.println(isPermutation(a, b)); // F

    a = "mice";
    b = "icme";
    System.out.println(isPermutation(a, b)); // T

    a = "aa";
    b = "a";
    System.out.println(isPermutation(a, b)); // F

    a = "";
    b = "";
    System.out.println(isPermutation(a, b)); // T
  }

  /**
   * What does it mean to be a permutation of a String?
   * Different order than the original, but contains same characters.
   *
   * We could sort each String, then compare them. O(aloga + blogb) time, O(1) space
   *
   * However, we can just compare the character counts to make sure they match up.
   * Using a HashMap, if the counts are the same, then by definition they are permutations
   * of each other.
   * O(a + b) time, O(a + b) space.
   */
  public static boolean isPermutation(String a, String b) {
    if(a.length() != b.length())
      return false;
    HashMap<Character, Integer> mapA = getCount(a), mapB = getCount(b);
    return mapA.equals(mapB);
  }

  private static HashMap<Character, Integer> getCount(String s) {
    HashMap<Character, Integer> map = new HashMap<Character, Integer>();
    for(char c : s.toCharArray()) {
      map.put(c, map.getOrDefault(c, 0) + 1);
    }
    return map;
  }
}
