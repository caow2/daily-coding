import java.util.Arrays;
/*
 * The h-index is a metric that attempts to measure the productivity and
 * citation impact of the publication of a scholar.
 * The definition of the h-index is if a scholar has at least h of their papers cited h times.
 *
 * Given a list of publications of the number of citations a scholar has, find their h-index.
 * Example:
 * Input: [3, 5, 0, 1, 3]
 * Output: 3
 * Explanation:
 * There are 3 publications with 3 or more citations, hence the h-index is 3.
 */
public class Hindex {
  public static void main(String[] args) {
    int[] arr = new int[] {3,5,0,1,3, 9, 5, 6};
    System.out.println(hIndex(arr)); // 3
    System.out.println(hIndexOptimal(arr)); // 3

    arr = new int[] {0,1,2,3,4};
    System.out.println(hIndex(arr)); // 2
    System.out.println(hIndexOptimal(arr)); // 2

    arr = new int[] {0,0,0,0};
    System.out.println(hIndex(arr)); // 0
    System.out.println(hIndexOptimal(arr)); // 0

    arr = new int[] {1,5,2,9,0};
    System.out.println(hIndex(arr)); // 2
    System.out.println(hIndexOptimal(arr)); // 2

    arr = new int[] {0,1,2,3,5,5,5,6};
    System.out.println(hIndex(arr)); // 4
    System.out.println(hIndexOptimal(arr)); // 4
  }

  /**
   * A straightforward approach may be for each element, count how many other elements
   * are >= to it, and update the H-index if possible.
   * O(n^2) time, O(1) space.
   *
   * We can try sorting. i.e. [2,0,3,1] -> [0,1,2,3]
   * Note that by sorting, we know exactly how many elements are larger than the current index.
   * O(nlogn) time, O(1) space
   */
  public static int hIndex(int[] arr) {
    Arrays.sort(arr);
    int hindex = 0;
    for(int i = 0; i < arr.length; i++) {
      if(arr.length - i >= arr[i]){
        hindex = Math.max(hindex, arr[i]);
      }
    }
    return hindex;
  }

  /**
   * Let's try to improve the runtime. Hashing isn't exactly a viable option,
   * since for each element, we may have to update multiple other elements.
   *
   * Since we are dealing with Integers, we could try a counting sort approach.
   * Let's make one pass through the input, and store the count of occurrences per element.
   * At the end, go through the array from the end (the largest number),
   * and update each index with the number of elements >= than index i.
   * O(n) time, O(max(arr[0], arr[1], ...)) assuming numbers are positive
   */
   public static int hIndexOptimal(int[] arr) {
     int max = 0;
     for(int i = 0; i < arr.length; i++) { max = Math.max(max, arr[i] ); }

     int[] count = new int[max + 1];
     for(int i = 0; i < arr.length; i++) { count[arr[i]]++; }

     int greater = 0;
     for(int i = count.length - 1; i >= 0; i--) {
       count[i] += greater;
       greater = count[i];
       if(count[i] >= i)
        return i;
     }
     return 0;
   }
}
