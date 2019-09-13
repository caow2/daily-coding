import java.util.List;
import java.util.LinkedList;
/*
 * Given a sorted list of numbers, return a list of strings that represent all of the consecutive numbers.
 *
 * Example:
 * Input: [0, 1, 2, 5, 7, 8, 9, 9, 10, 11, 15]
 * Output: ['0->2', '5->5', '7->11', '15->15']
 * Assume that all numbers will be greater than or equal to 0, and each element can repeat.
 */
public class MergeRange {
  public static void main(String[] args) {
    int[] arr = new int[] {0,1,2,5,7,8,9,9,10,11,15};
    System.out.println(merge(arr));

    arr = new int[] {-2,-2,-1,0,3,4,5,7};
    System.out.println(merge(arr));
  }

  public static List<String> merge(int[] arr) {
    List<String> result = new LinkedList<String>();
    Integer start = arr[0], end = start;
    for(int i = 1; i < arr.length; i++) {
      if(arr[i] <= end + 1) // takes care of dupes as well
        end += arr[i] - end; // with negatives -> -1 - (-2) = 1
      else {
        result.add(start + "->" + end);
        start = arr[i];
        end = start;
      }
    }
    result.add(start + "->" + end);
    return result;
  }
}
