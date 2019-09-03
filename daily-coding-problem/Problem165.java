import java.util.ArrayList;
import java.util.Arrays;
/*
 * Given an array of integers, return a new array where each element in the new
 * array is the number of smaller elements to the right of that element in the original input array.
 * For example, given the array [3, 4, 9, 6, 1], return [1, 1, 2, 1, 0], since:
 * There is 1 smaller element to the right of 3
 * There is 1 smaller element to the right of 4
 * There are 2 smaller elements to the right of 9
 * There is 1 smaller element to the right of 6
 * There are no smaller elements to the right of 1
 */
public class Problem165 {
  public static void main(String[] args) {
    int[] arr = new int[] {3,4,9,6,1};
    System.out.println(Arrays.toString(smaller(arr)));

    arr = new int[] {1,2,3,4,5};
    System.out.println(Arrays.toString(smaller(arr)));

    arr = new int[] {5,4,3,2,1};
    System.out.println(Arrays.toString(smaller(arr)));
  }

  /**
   * Maintain a sorted array as we process from each element from right to left.
   * We know that if we insert a element at index i, there are i - 1 smaller elements to the right
   * because of how we inserted them into the sorted array.
   * example : [1,4,2]
   * insert 2 -> [2]
   * insert 4 -> [2,4]
   * insert 1 -> [1,2,4]
   * O(n^2) time, O(n) space
   */
  public static int[] smaller(int[] arr) {
    ArrayList<Integer> list = new ArrayList<Integer>();
    int[] result = new int[arr.length];
    for(int i = arr.length - 1; i >= 0; i--) { // O(n)
      int index = search(list, arr[i]); // search for insertion index - O(log n)
      result[i] = index;
      list.add(index, arr[i]); // insert is O(n)
    }
    return result;
  }

  private static int search(ArrayList<Integer> list, int num) {
    int l = 0, r = list.size() - 1;
    while(l <= r) {
      int mid = l + ((r - l) / 2);
      if(list.get(mid) < num)
        l = mid + 1;
      else
        r = mid - 1;
    }
    return l;
  }
}
