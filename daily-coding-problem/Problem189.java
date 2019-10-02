import java.util.HashSet;
/*
 * Given an array of elements, return the length of the longest subarray
 * where all its elements are distinct.
 *
 * For example, given the array [5, 1, 3, 5, 2, 3, 4, 1],
 * return 5 as the longest subarray of distinct elements is [5, 2, 3, 4, 1].
 */
public class Problem189 {
  public static void main(String[] args) {
    int[] arr = new int[] {5,1,3,5,2,3,4,1};
    System.out.println(longest(arr));

    arr = new int[] {1,1,1,1,1,1};
    System.out.println(longest(arr));

    arr = new int[] {1,1,1,0,1,3};
    System.out.println(longest(arr));
  }

  /**
   * Maintain a window of distinct elements, and a HashSet of numbers for fast lookup.
   * If we see a number that was already in the HS, move the start of the window until
   * number is no longer in / encounter number.
   * O(n) time, O(n) space
   */
  public static int longest(int[] arr) {
    HashSet<Integer> set = new HashSet<Integer>();
    int start = 0, end = 0, longest = 0;
    while(start <= end && end < arr.length) {
      int num = arr[end];
      while(set.contains(num)) {
        set.remove(arr[start++]);
      }
      set.add(num);
      longest = Math.max(longest, end - start + 1);
      end++;
    }
    return longest;
  }
}
