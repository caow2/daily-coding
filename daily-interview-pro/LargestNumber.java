import java.util.Arrays;
import java.util.Comparator;
/*
 * Given a list of integers, combine them so it would create the largest number.
 *
 * Example:
 * Input:  [17, 7, 2, 45, 72]
 * Output:  77245217
 */
public class LargestNumber {
  public static void main(String[] args) {
    Integer[] arr = {17,7,2,45,72};
    System.out.println(largestNum(arr));

    arr = new Integer[] {17,7,2,45,7,92};
    System.out.println(largestNum(arr));
  }

  /**
   * Let's consider the case [7,72].
   * The largest number we can make is 772. It seems like there is a specific ordering
   * amongst the numbers and we want to choose the 'largest' numbers first:
   * 7 vs 72 -> 7 is considered larger
   * 7 vs 9 -> 9 is considered larger
   * 7 vs 92 -> 92 is still considered larger
   * This pattern somewhat resembles the lexographic ordering of Strings:
   * a vs ab where a is the smaller value
   *
   * However, we can sort the array based on our custom ordering.
   * Assumes that the output is still an integer
   * O(d*nlogn) because there are nlogn comparisons for (assuming) MergeSort,
   * and each comparison will take O(d) where d is the number of digits in the numbers
   */
  public static int largestNum(Integer[] arr) {
    Arrays.sort(arr, new Comparator<Integer>() {
      public int compare(Integer x, Integer y) {
        String xterm = x + "", yterm = y + "";
        int i = 0;
        while(i < xterm.length() && i < yterm.length()) {
          x = Character.getNumericValue(xterm.charAt(i));
          y = Character.getNumericValue(yterm.charAt(i));
          if(x - y != 0)
            return x - y;
          i++;
        }

        if(xterm.length() != yterm.length()) // comparisons were equal up to here
          return (xterm.length() < yterm.length() ? 1 : -1);
        return 0;
      }
    });

    // System.out.println(Arrays.toString(arr));
    StringBuilder sb = new StringBuilder();
    for(int i = arr.length - 1; i >= 0; i--) {
      sb.append("" + arr[i]);
    }

    return Integer.parseInt(sb.toString());
  }
}
