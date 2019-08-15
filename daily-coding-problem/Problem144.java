/*
 * Given an array of numbers and an index i,
 * return the index of the nearest larger number of the number at index i,
 * where distance is measured in array indices.
 *
 * For example, given [4, 1, 3, 5, 6] and index 0, you should return 3.
 * If two distances to larger numbers are the equal, then return any one of them.
 * If the array at i doesn't have a nearest larger integer, then return null.
 *
 * Follow-up: If you can preprocess the array, can you do this in constant time?
 */
public class Problem144 {
  public static void main(String[] args) {
    int[] arr = new int[] {4,1,3,5,6};
    int[] left = new int[arr.length], right = new int[arr.length];
    preprocess(arr, left, right);
    //System.out.println(closestLargerNumber(arr, 0)); // 3
    System.out.println(closestLargerNumberOptimized(arr, 0)); // 3
  }

  /**
   * Instead of null, let's return -1 for index for the case where there is no larger number.
   * We can do a quick scan thru the array and skip index i, while keeping
   * track of the largest number that's closest to i in terms of absolute distance.
   * O(n) time
   * O(1) space
   */
   public static int closestLargerNumber(int[] arr, int k) {
     int idx = Integer.MAX_VALUE;
     for(int i = 0; i < arr.length; i++) {
       if (arr[i] > arr[k])
        idx = Math.min(idx, Math.abs(i - k));
     }
     return (idx == Integer.MAX_VALUE ? -1 : idx);
   }

   /**
    * We probably can't do better than O(n) in this case since we need
    * to search for the left larger number and the right larger number of index k.
    * Since the array isn't sorted, we can't run binary search on either half.
    *
    * However, if we know that the array isn't being changed and the function is going
    * to be called over and over on the same array, we can do some upfront work.
    * When we preprocess the array, we can come up with 2 new arrays:
    * One to store index of largest number to the left of each index i
    * and one to store index of largest number to the right of each index i.
    */
}
