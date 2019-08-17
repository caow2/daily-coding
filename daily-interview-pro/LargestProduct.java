import java.util.PriorityQueue;

/*
 * You are given an array of integers.
 * Return the largest product that can be made by multiplying any 3 integers in the array.
 *
 * Example:
 * [-4, -4, 2, 8] should return 128 as the largest product can be made by
 * multiplying -4 * -4 * 8 = 128.
 */
public class LargestProduct {
  public static void main(String[] args) {
    int[] arr = new int[] {-4,-4,2,8};
    System.out.println(largestProduct(arr));

    arr = new int[] {-4, 1, 3, 9, 4};
    System.out.println(largestProduct(arr));
  }

  /**
   * A basic idea would be to try every combination possible in the array and
   * keep track of the maximum.
   * O(n^3) time, O(1) space.
   *
   * Since we are multiplying numbers, we can assume that numbers will be within a
   * valid range ( x * y * z <= Integer.MAX_VALUE)
   * Observe that the largest product must be formed from the 3 largest positive numbers
   * in the list, or the 2 largest negative numbers and the largest positive.
   * We can sort the array to find this, but it can be done in a single pass.
   * O(n) time, O(1) space.
   */
   public static int largestProduct(int[] arr) {
     // use a min and max heap to maintain largest 3 positive and negatives
     PriorityQueue<Integer> pos = new PriorityQueue<Integer>(3, (x, y) -> y - x);
     PriorityQueue<Integer> neg = new PriorityQueue<Integer>(2, (x, y) -> x - y);

     for(int i = 0; i < arr.length; i++) {
       pos.offer(arr[i]);
       neg.offer(arr[i]);
     }

     int largest = pos.poll();

     return Math.max(largest * pos.poll() * pos.poll(),
            largest * neg.poll() * neg.poll());
   }
}
