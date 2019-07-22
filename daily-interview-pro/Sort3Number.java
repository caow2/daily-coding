import java.util.Arrays;

/* 7/22/19
 * Given a list of numbers with only 3 unique numbers (1, 2, 3), sort the list in O(n) time.
 *
 * Example 1:
 * Input: [3, 3, 2, 1, 3, 2, 1]
 * Output: [1, 1, 2, 2, 3, 3, 3]
 *
 * Challenge: Try sorting the list using constant space.
 */
public class Sort3Number {

  public static void main(String[] args) {
    int[] list = new int[] {3, 3, 2, 1, 3, 2, 1};
    System.out.println(Arrays.toString(solution(list)));
  }

  /**
   * Variant of Dutch Flag Problem https://en.wikipedia.org/wiki/Dutch_national_flag_problem
   * We can just propagate all 1s to the start of the list and all 3s to the end of the
   * list using 3 pointers.
   * All 2s should naturally be pushed towards the center of the array.
   */
  public static int[] solution(int[] list) {
    int l = 0, r = list.length - 1, i = 0;
    while(i <= r) {
      int curr = list[i];
      if(curr < 2)
        swap(list, i++, l++);
      else if(curr > 2)
        swap(list, i, r--); // Don't increment i because new list[i] could be 1.
      else
        i++; // Ignore current element if it's 2.
    }
    return list;
  }

  public static void swap(int[] list, int i, int j) {
    int temp = list[i];
    list[i] = list[j];
    list[j] = temp;
  }
}
