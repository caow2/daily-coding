import java.util.List;
import java.util.LinkedList;
import java.util.Arrays;
/*
 * Given an array, nums, of n integers, find all unique triplets (three numbers, a, b, & c)
 * in nums such that a + b + c = 0. Note that there may not be any triplets that
 * sum to zero in nums, and that the triplets must not be duplicates.
 *
 * Example:
 * Input: nums = [0, -1, 2, -3, 1]
 * Output: [0, -1, 1], [2, -3, 1]
 */
public class ThreeSum {
  public static void main(String[] args) {
    int[] arr = new int[] {0,-1,2,-3,1};
    System.out.println(threeSum(arr));

    arr = new int[] {0,-1,2,-3,2,1,1,-4};
    System.out.println(threeSum(arr));
  }

  /**
   * To solve 3 sum, we can use the solution to a simpler version of the problem: 2 sum.
   * For 2 sum, we want to find 2 numbers such that a + b = 0.
   * We can slightly tweak the solution for 3 sum since we want a + b + c = 0.
   * i.e. For each number a from arr[0], ..., arr[length - 2], solve 2 sum for b + c = -a.
   *
   * Since we want to avoid duplicates, we can either use a HashSet of Lists to maintain
   * the unique solutions.
   * Alternatively, we can sort the input and take care of duplicates manually, since
   * we don't increase the runtime complexity by sorting.
   *
   * O(n^2) time.
   */
  public static List<List<Integer>> threeSum(int[] arr) {
    Arrays.sort(arr);
    List<List<Integer>> result = new LinkedList<List<Integer>>();
    for(int i = 0; i < arr.length - 2; i++) {
      if(i > 0 && arr[i] == arr[i - 1])
        continue;
      int l = i + 1, r = arr.length - 1;
      while(l < r) {
        int sum = arr[i] + arr[l] + arr[r];
        if(sum == 0)
          result.add(Arrays.asList(arr[i], arr[l++], arr[r--]));
        else if (sum < 0)
          l++;
        else
          r--;

        // Avoid duplicates
        while(l > i + 1 && l < r && arr[l] == arr[l - 1]) { l++; }
        while(r < arr.length - 1 && r > l && arr[r] == arr[r + 1]) { r--; }
      }
    }
    return result;
  }
}
