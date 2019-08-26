/*
 * Given a string, determine whether any permutation of it is a palindrome.
 * For example, carrace should return true, since it can be rearranged to form
 * racecar, which is a palindrome. daily should return false, since there's no
 * rearrangement that can form a palindrome.
 */
public class Problem157 {
  public static void main(String[] args) {
    String s = "carrace";
    System.out.println(palindromePermutation(s));

    s = "";
    System.out.println(palindromePermutation(s));

    s = "a";
    System.out.println(palindromePermutation(s));

    s = "aaa";
    System.out.println(palindromePermutation(s));

    s = "aabbae";
    System.out.println(palindromePermutation(s));

    s = "racacara";
    System.out.println(palindromePermutation(s));
  }

  /**
   * To check if s is a permutation of a palindrome, we note that for
   * an even length palindrome, there is an even count of each character.
   * For an odd length palindrome, there is only one odd character count.
   *
   * For any possible palindrome then, it must have at most one odd character count.
   * O(n) time, O(n) space.
   */
  public static boolean palindromePermutation(String s) {
    int[] count = new int[26];
    for(char c : s.toCharArray()) {
      count[c - 'a']++;
    }

    boolean odd = false;
    for(int i : count ) {
      if(i % 2 != 0) {
        if(odd)
          return false; // already seen odd count
        odd = true;
      }
    }
    return true;
  }
}
