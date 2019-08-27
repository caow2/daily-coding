/**
 * You are given an array of integers.
 * Return the smallest positive integer that is not present in the array.
 * The array may contain duplicate entries.
 *
 * For example, the input [3, 4, -1, 1] should return 2 because it is the smallest positive integer that doesn't exist in the array.
 *
 * Your solution should run in linear time and use constant space.
 */
public class FirstMissingPositive {
  public static void main(String[] args) {
    int[] arr = new int[] {3,4,-1,1};
    System.out.println(firstMissing(arr)); // 2

    arr = new int[] {3,4,2,1};
    System.out.println(firstMissing(arr)); // 5

    arr = new int[] {0,-1,-5,-3};
    System.out.println(firstMissing(arr)); // 1

    arr = new int[] {0,-1,-5,-3,1,2,5,2};
    System.out.println(firstMissing(arr)); // 3
  }

  /**
   * A simple approach is to sort the array and then binary search or traverse thru it
   * to get the smallest missing positive.
   * O(nlogn) time, O(logn) space.
   *
   * Since we can't use extra space, maybe we can use indices of the array
   * as a cache if we've seen index i.
   * arr[i] positive = not seen
   * arr[i] negative = seen
   * We first replace all negative numbers with a positive number from the array,
   * and then traverse thru the array to adjust our indices.
   *
   * Alternatively, we can just replace negative or 0 with Integer.MAX_VALUE
   * to save a few passes over the array.
   * O(n) time, O(1) space
   */
  public static int firstMissing(int[] arr) {
    // convert all negatives and 0 to positive.
    // 0 can't be used because it doesn't tell us if we've seen arr[i] or not
    for(int i = 0; i < arr.length; i++) {
      if(arr[i] <= 0)
        arr[i] = Integer.MAX_VALUE;
    }

    // go thru and switch arr[i] to negative if it's in range [1,length of arr]
    // negative indicates we've seen the value already
    for(int i = 0; i < arr.length; i++) {
      int idx = Math.abs(arr[i]) - 1;
      if(idx < arr.length && arr[idx] > 0)
        arr[idx] *= -1;
    }

    // do a final pass to return the first non negative index, or length + 1
    for(int i = 0; i < arr.length; i++) {
      if(arr[i] > 0)
        return i + 1;
    }

    // in order for [1, length] to all be negative, length + 1 can't be an element in the array
    return arr.length + 1;
  }
}
