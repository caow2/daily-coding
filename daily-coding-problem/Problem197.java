import java.util.Arrays;

/*
 * Given an array and a number k that's smaller than the length of the array,
 * rotate the array to the right k elements in-place.
 */
public class Problem197 {
  public static void main(String[] args) {
    int[] arr = {1,2,3,4,5};
    System.out.println(Arrays.toString(rotate(arr, 1)));
    System.out.println(Arrays.toString(rotate(arr, 4)));
    System.out.println(Arrays.toString(rotate(arr, 10)));
  }

  /**
   * A brute force approach would be to just rotate and shift elements k times.
   * If we were working with a LinkedList, this would be okay, but we have an array,
   * which means that n shifts would require n * k operations.
   * Additionally, if we were allowed extra space, we could have an auxillary array to
   * store the final destination of each element.
   * O(n) time, O(n) space.
   *
   * Note that however, for an array like [1,2,3,4,5], k = 3, the result should be
   * [3,4,5,1,2].
   * Suppose we reversed the array first. -> [5,4,3,2,1]
   * Then we notice that [5,4,3] can be reverse to become [3,4,5] and [2,1] can become [1,2].
   * Perhaps then, we can reverse the whole array and reverse the first k elements.
   * Then reverse k + 1 to the end.
   * O(n) time, O(1) space.
   */
  public static int[] rotate(int[] arr, int k) {
    k %= arr.length;
    reverse(arr, 0, arr.length - 1);
    reverse(arr, 0, k - 1);
    reverse(arr, k, arr.length - 1);
    return arr;
  }

  public static void reverse(int[] arr, int i, int j) {
    if(i >= j)
      return;
    while(i < j) {
      arr[i] += arr[j];
      arr[j] = arr[i] - arr[j];
      arr[i] -= arr[j];
      i++;
      j--;
    }
  }


}
