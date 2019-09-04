import java.util.HashMap;

/*
 * Given a list of numbers, find if there exists a pythagorean triplet in that list.
 * A pythagorean triplet is 3 variables a, b, c where a^2 + b^2 = c^2
 *
 * Example:
 * Input: [3, 5, 12, 5, 13]
 * Output: True
 * Here, 5^2 + 12^2 = 13^2.
 */
public class PythagoreanTriplets {
  public static void main(String[] args) {
    int[] arr = new int[] {3,5,12,5,13};
    System.out.println(isTriplet(arr));

    arr = new int[] {1,2,3,5,8};
    System.out.println(isTriplet(arr));

    arr = new int[] {-3,-5,12,5,-13};
    System.out.println(isTriplet(arr));
  }

  /**
   * A brute force approach tries to compare every 3 numbers to see if they satisfy
   * the relationship a^2 + b^2 = c^2.
   * However, we don't really need to compare 3 numbers; if we have a^2 and b^2,
   * we can hash a^2 + b^2 to see if it appears later in the array.
   *
   * It's important to make sure that c is not the same index as a or b, so maybe
   * we can store the index of b with (a^2 + b^2).
   * We don't need to store a's index because the outer loop is a.
   * i.e. 0^2 + 2^2 = 2^2
   *
   * Assuming that the summing or squaring the input numbers will not result in integer overflow.
   * O(n^2) time, O(n^2) space
   */
  public static boolean isTriplet(int[] arr) {
    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    for(int i = 0; i < arr.length; i++) {
      int aSquare = arr[i] * arr[i];
      if(map.containsKey(aSquare) && map.get(aSquare) != i) //not same index as b
        return true;
      for(int j = i + 1; j < arr.length; j++) {
        int bSquare = arr[j] * arr[j];
        map.put(aSquare + bSquare, j);
      }
    }
    return false;
  }
}
