/*
 * You are given an array of length n + 1 whose elements belong to the set {1, 2, ..., n}.
 * By the pigeonhole principle, there must be a duplicate. Find it in linear time and space.
 */
public class Problem164 {
  public static void main(String[] args) {
    int[] nums = new int[] {1,2,3,3,4};
    System.out.println(dupe(nums));
    System.out.println(dupeOptimized(nums));

    nums = new int[] {1,2,5,3,4,5};
    System.out.println(dupe(nums));
    System.out.println(dupeOptimized(nums));

  }

  /**
   * We can use an array / set to track elements we've seen already.
   * If n was bounded by 32 or 64, we could use a vector as well.
   */
  public static int dupe(int[] nums) {
    boolean[] seen = new boolean[nums.length - 1]; // 0 indexed, but our sequence starts at 1
    for(int i = 0; i < nums.length; i++) {
      if(seen[nums[i] - 1])
        return nums[i];
      seen[nums[i] - 1] = true;
    }
    return -1;
  }

  /**
   * Alternatively, if we were going for a constant space solution,
   * we can use the sign of index of the array to check if we've seen that index number before.
   * Since our elements are in the range {1, ..., n}
   * If the element at index 0 is negative, that means we've seen 1 before.
   *
   * If it was gauranteed that we would have all of the numbers from {1, ..., n}
   * We could keep a running sum and just find the duplicate through subtraction
   */
   public static int dupeOptimized(int[] nums) {
     for(int i = 0; i < nums.length; i++) {
       int n = Math.abs(nums[i]) - 1;
       if(nums[n] < 0)
        return n + 1;
       nums[n] *= -1;
     }
     return -1;
   }
}
