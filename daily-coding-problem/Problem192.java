/*
 * You are given an array of nonnegative integers. Let's say you start at the beginning of the array and are trying to advance to the end. You can advance at most, the number of steps that you're currently on.
 * Determine whether you can get to the end of the array.
 * For example, given the array [1, 3, 1, 2, 0, 1], we can go from indices 0 -> 1 -> 3 -> 5, so return true.
 *
 * Given the array [1, 2, 1, 0, 0], we can't reach the end, so return false.
 */
public class Problem192 {
  public static void main(String[] args) {
    int[] arr = new int[] { 1,3,1,2,0,1};
    System.out.println(canReachEnd(arr));

    arr = new int[] {1,2,1,0,0};
    System.out.println(canReachEnd(arr));

    arr = new int[] {0,1,2,5};
    System.out.println(canReachEnd(arr));

    arr = new int[] {1,9,0};
    System.out.println(canReachEnd(arr));
  }

  public static boolean canReachEnd(int[] arr) {
    if(arr.length == 0)
      return false;
    int end = arr[0]; // number of steps we can take from index i
    int i = 1;
    while(i <= end && end < arr.length) {
      end = Math.max(end, i + arr[i]); // try to extend the end of the range
      i++;
    }
    return end >= arr.length;
  }
}
