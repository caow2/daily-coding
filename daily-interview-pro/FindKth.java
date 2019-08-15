import java.util.Arrays;

/*
 * Given a list, find the k-th largest element in the list.
 * Input: list = [3, 5, 2, 4, 6, 8], k = 3
 * Output: 5
 */
public class FindKth {
  public static void main(String[] args) {
    int[] arr = new int[] {3, 5, 2, 4, 6, 8};
    //System.out.println(kthLargest(arr, 3));
    System.out.println(arr[quickSelect(arr, 6, 0, arr.length - 1)]);
  }

  /**
   * A basic approach would be to just sort the array and return the n - kth element.
   * O(nlogn) time for average case sort
   * O(logn) space if we use something like quicksort
   */
  public static int kthLargest(int[] arr, int k) {
    Arrays.sort(arr);
    return arr[arr.length - k];
  }

  /**
   * Another idea is to use QuickSelect - similar to quicksort.
   * We partition our array around a value, and if there are k - 1 elements to
   * the right of the partition index, then we've found the kth Largest.
   *
   * Otherwise, if there are < k - 1 elements, the kth Largest is to the left of the pivot
   * if there are > k - 1 elements, kth Largest is to the right of pivot
   * O(logn) average case, O(n) worst case time complexity
   * Same for space complexity
   */
   public static int quickSelect(int[] arr, int k, int start, int end) {
     int index = -1;
     int pivotIdx = partition(arr, start, end);
     if((arr.length - 1 - pivotIdx) == k - 1)
      index = pivotIdx;
     else if(arr.length - 1 - pivotIdx < k - 1)
      index = quickSelect(arr, k, start, pivotIdx - 1);
     else
      index = quickSelect(arr, k, pivotIdx + 1, end);

     return index;
   }

   private static int partition(int[] arr, int start, int end) {
     if(start >= end)
      return start;
     int pivotIdx = start + ((end - start) / 2);
     int pivotVal = arr[pivotIdx];
     swap(arr, start, pivotIdx);
     int i = start + 1, j = start + 1;
     while(j <= end) {
       if(arr[j] <= pivotVal)
        swap(arr, i++, j);
       j++;
     }

     swap(arr, start, i - 1);
     return i - 1;
   }

   private static void swap(int[] arr, int i, int j) {
     if(i == j)
      return;
     arr[i] ^= arr[j];
     arr[j] ^= arr[i];
     arr[i] ^= arr[j];
   }

}
