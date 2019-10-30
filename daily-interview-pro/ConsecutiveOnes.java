/*
 * Return the longest run of 1s for a given integer n's binary representation.
 * Example:
 * Input: 242
 * Output: 4
 * 242 in binary is 0b11110010, so the longest run of 1 is 4.
 */
public class ConsecutiveOnes {
  public static void main(String[] args) {
    int x = 242;
    System.out.println(longestOnes(x));

    x = Integer.MAX_VALUE;
    System.out.println(longestOnes(x));

    x = 5;
    System.out.println(longestOnes(x));
  }


  /**
   * Do a linear scan over the input and keep a counter for ones.
   * If current bit is a 1, increment counter. Otherwise reset counter.
   * Track the maximum as we scan.
   * O(1) time and space
   */
  public static int longestOnes(int x) {
    int curr = 0, counter = 0, longest = 0;
    for(int i = 0; i < 32; i++) {
      curr = (1 << i) & x;
      if(curr > 0)
        counter++;
      else
        counter = 0;
      longest = Math.max(longest, counter);
    }
    return longest;
  }
}
