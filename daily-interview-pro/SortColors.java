import java.util.Arrays;
/*
 * Given an array with n objects colored red, white or blue, sort them in-place
 * so that objects of the same color are adjacent, with the colors in the order red, white and blue.
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 * Note: You are not suppose to use the library’s sort function for this problem.
 * Can you do this in a single pass?
 *
 * Example:
 * Input: [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 */
public class SortColors {
  public static void main(String[] args) {
    int[] arr = new int[] {2,0,2,1,1,0};
    sortColors(arr);
    System.out.println(Arrays.toString(arr));

    arr = new int[] {2,0,2,1,0,2,1};
    sortColors(arr);
    System.out.println(Arrays.toString(arr));
  }

  /**
   * Standard dutch flag problem.
   * O(n) time, O(1) space in 1 pass
   */
  public static void sortColors(int[] arr) {
    int i = 0, j = 0, e = arr.length - 1;
    while(i <= e) {
      if(arr[i] > 1)
        swap(arr, i, e--);
      else if(arr[i] < 1)
        swap(arr, i++, j++);
      else
        i++;
    }
  }

  private static void swap(int[] arr, int i, int j) {
    if(i == j)
      return;
    arr[i] ^= arr[j];
    arr[j] ^= arr[i];
    arr[i] ^= arr[j];
  }
}
