import java.util.HashMap;
import java.util.PriorityQueue;

/*
 * https://leetcode.com/discuss/interview-question/365872/
 */
public class EqualDigitSum {
  public static void main(String[] args) {
    int[] arr = new int[] {51, 71, 17, 42};
    System.out.println(maxSum(arr)); // 93

    arr = new int[] {42,33,60};
    System.out.println(maxSum(arr)); // 102

    arr = new int[] {51,32,43};
    System.out.println(maxSum(arr)); // -1

    arr = new int[] {0, 00, 100};
    System.out.println(maxSum(arr)); // 0

    arr = new int[] {10, 100, 1000, 20, 200};
    System.out.println(maxSum(arr)); // 1100

    arr = new int[] {};
    System.out.println(maxSum(arr)); // -1
  }

  /**
   * Go thru arr and place each number into a 'bucket' based on the sum of digits
   * Each bucket will contain the 2 maximum values for that bucket.
   * Go thru each bucket and if size == 2, compute sum and update max
   * Check if we've computed a sum at the end
   *
   * O(nlogm) time where m is largest number in arr, O(n) space
   *
   * An optimized solution would only take a single pass and store mapping from
   * digitSum to maximum number for that digitSum.
   */
  public static int maxSum(int[] arr) {
    HashMap<Integer, PriorityQueue<Integer>> buckets = new HashMap<>();
    for(int i = 0; i < arr.length; i++) { // O(n)
      int sum = getSum(arr[i]); // O(log m) where m is largest number in arr
      if(buckets.get(sum) == null)
        buckets.put(sum, new PriorityQueue<Integer>()); // min PQ
      PriorityQueue<Integer> pq = buckets.get(sum);
      pq.offer(arr[i]);
      if(pq.size() > 2)
        pq.poll(); // only keep 2 elem in pq
    }

    int maxSum = Integer.MIN_VALUE;
    for(Integer sum : buckets.keySet()) { // O(n) worst case if each number forms a bucket
      PriorityQueue<Integer> pq = buckets.get(sum);
      if(pq.size() <= 1)
        continue;
      maxSum = Math.max(maxSum, pq.poll() + pq.poll());
    }

    return maxSum == Integer.MIN_VALUE ? -1 : maxSum;
  }

  // O(d) -> d is number of digits in i
  // or O(log i) since we divide by factor of 10
  public static int getSum(int i) {
    int digitSum = 0;
    while(i > 0) {
      digitSum += (i % 10);
      i /= 10;
    }
    return digitSum;
  }
}
