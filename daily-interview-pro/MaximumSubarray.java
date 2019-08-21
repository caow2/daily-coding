/*
 * You are given an array of integers.
 * Find the maximum sum of all possible contiguous subarrays of the array.
 * Example:
 * [34, -50, 42, 14, -5, 86]
 * Given this input array, the output should be 137.
 * The contiguous subarray with the largest sum is [42, 14, -5, 86].
 * Your solution should run in linear time.
 */
public class MaximumSubarray {
  public static void main(String[] args) {
    int[] arr = new int[] {1, -1, 2, 1, -5, 3};
    System.out.println(maxSumSubarray(arr));

    arr = new int[] {34, -50, 42, 14, -5, 86};
    System.out.println(maxSumSubarray(arr));
  }

  /**
   * We can try every sub array as a brute force approach. O(n^2)
   * Consider the array [1, -1, 2, 1, -5, 3].
   * We see that for each index, we can either include it in the current subarray sum or not
   * based on two cases:
   * 1. If it makes current subarray sum positive, then we include it and try
   *    to update a global max sum.
   * 2. If it makes the current subarray sum negative, then we reset the current subarray sum
   *    to 0 because we have to start by considering a new subarray.
   * (Kadane's algorithm) -> O(n) time, O(1) space.
   */
  public static int maxSumSubarray(int[] arr) {
    int max = 0, currentSum = 0;
    for(int i = 0; i < arr.length; i++) {
      if(currentSum + arr[i] >= 0)
        currentSum += arr[i];
      else
        currentSum = 0;
      max = Math.max(currentSum, max);
    }
    return max;
  }
}
