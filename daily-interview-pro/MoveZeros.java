import java.util.Arrays;

/*
 * Given an array nums, write a function to move all 0's to the end of it while
 * maintaining the relative order of the non-zero elements.
 *
 * Example:
 * Input: [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 * You must do this in-place without making a copy of the array.
 * Minimize the total number of operations.
 */
public class MoveZeros {
  public static void main(String[] args) {
    int[] arr = new int[] {0,1,0,3,12};
    move(arr);
    System.out.println(Arrays.toString(arr));

    arr = new int[] { 1,0,0,0,2,0 };
    move(arr);
    System.out.println(Arrays.toString(arr));

    arr = new int[] { 1,2,3,4,5,0 };
    move(arr);
    System.out.println(Arrays.toString(arr));

    arr = new int[] { 1,1,0,2,5 };
    move(arr);
    System.out.println(Arrays.toString(arr));
  }

  /**
   * Maintain 2 pointers l and r. l is always at a garbage element (0)
   * and r moves thru the array.
   * Whenever we see arr[r] != 0, swap arr[r] and arr[l] and move pointers.
   * O(n) time, O(1) space
   */
  public static void move(int[] arr) {
    int l = -1, r = 0; // arr[0] might not always be 0 so start l at -1
    while(r < arr.length) {
      if(arr[r] != 0 && l >= 0) // l at valid index
        swap(arr, r, l++);
      else if (arr[r] == 0 && l < 0) // initialize l
        l = r;
      // if arr[r] == 0 and l > 0, nothing to do
      // likewise, if arr[r] != 0 && l < 0, there's no 0 before arr[r].
      r++;
    }
  }

  private static void swap(int[] arr, int l, int r) {
    arr[l] += arr[r]; // l + r
    arr[r] = arr[l] - arr[r]; // l + r - r = l
    arr[l] -= arr[r]; // l + r - l = l;
  }
}
