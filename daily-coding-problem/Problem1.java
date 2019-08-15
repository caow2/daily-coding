import java.util.Set;
import java.util.HashSet;

/*
 * Given a list of numbers and a number k,
 * return whether any two numbers from the list add up to k.
 *
 * For example, given [10, 15, 3, 7] and k of 17, return true since 10 + 7 is 17.
 *
 * Bonus: Can you do this in one pass?
 */
public class Problem1 {

  public static void main(String[] args) {
    int[] arr = new int[] {10,15,3,7};
    int k = 17;
    System.out.println(twoSum(arr, k));

    k = -1;
    System.out.println(twoSum(arr, k));

    k = 3;
    System.out.println(twoSum(arr, k));

    k = 10;
    System.out.println(twoSum(arr, k));
  }

  /**
   * For each number, we can try to scan the rest of the array to if there's a
   * number x such that x = k - arr[i].
   *
   * However, to reduce duplicated work, we can cache k - arr[i] in a HashSet
   * and if we see k - arr[i], then we know that arr[i] and k - arr[i] both exist.
   */
  public static boolean twoSum(int[] arr, int k) {
    Set<Integer> cache = new HashSet<Integer>();
    for(int i = 0; i < arr.length; i++) {
      if(cache.contains(arr[i]))
        return true;
      cache.add(k - arr[i]);
    }
    return false;
  }
}
