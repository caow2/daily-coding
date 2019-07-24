import java.util.HashSet;

/*
 * You are given a list of numbers, and a target number k. Return whether or not
 * there are two numbers in the list that add up to k.
 *
 * Example:
 * Given [4, 7, 1 , -3, 2] and k = 5,
 * return true since 4 + 1 = 5.
 */
public class TwoSum {
  public static void main(String[] args) {
    // True cases
    int[] v1 = new int[] { 4, 7, 1, -3, 2 };
    int[] v2 = new int[] { 5, 4, 3, -5, 6, 5};
    int[] v3 = new int[] { 0, -2, 1, 3, 2 };

    int[] i1 = new int[] { 0, 2, 3, -1, -1 };
    int[] i2 = new int[] {};
    int[] i3 = new int[] {-2, -2, 2, 4, 1};

    int n1 = 5, n2 = 10, n3 = 0;
    int n4 = 0, n5 = 1, n6 = 4;

    System.out.println(twoSum(v1, n1));
    System.out.println(twoSum(v2, n2));
    System.out.println(twoSum(v3, n3));
    System.out.println(twoSum(i1, n4));
    System.out.println(twoSum(i2, n5));
    System.out.println(twoSum(i3, n6));
  }

  /**
   * Brute Force solution would be to try every pair of numbers to see if they
   * add up to k. This would result in O(n^2) time and O(1) space.
   *
   * Another alternative would be to sort the list and use 2 pointers.
   * Start at each end of the list and check if elements at pointers sum up to k.
   * If larger, we decrement the right pointer. If smaller, increment left pointer.
   * If the pointers meet, then 2 elements that sum up to k doesn't exist.
   * O(nlogn) time and O(1) space assuming sorting doesn't take any extra space.
   *
   * However, we can avoid duplicate work or the sorting bottlenecks by hashing.
   * If we see a number n, we can cache k - n into a HashSet.
   * Then if we see k - n and it is in our set, we know that there are at least
   * 2 numbers that sum up to k: n and k - n.
   * O(n) time and O(n) space.
   */
  public static boolean twoSum(int[] arr, int k) {
    HashSet<Integer> set = new HashSet<Integer>();
    for(int i : arr) {
      if(set.contains(i))
        return true;
      set.add(k - i);
    }
    return false;
  }
}
