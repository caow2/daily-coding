import java.util.Arrays;

/**
 * Implementation of QuickSort
 */
public class QuickSort {
  public static void main(String[] args) {
    int[] arr = new int[] {5,1,2,0,4};
    sort(arr, 0, arr.length - 1);
    System.out.println(Arrays.toString(arr));

    arr = new int[] {-1,-1,5,2,9,10,4};
    sort(arr, 0, arr.length - 1);
    System.out.println(Arrays.toString(arr));

    arr = new int[] {1};
    sort(arr, 0, arr.length - 1);
    System.out.println(Arrays.toString(arr));
  }

  public static void sort(int[] arr, int start, int end) {
    if(start >= end) {
      return; // base case
    }
    int p = partition(arr, start, end); // p is in right place
    sort(arr, start, p - 1); // quicksort left side of partition
    sort(arr, p + 1, end); // quicksort right side of partition
  }

  /**
   * Return the partitioning index
   */
  public static int partition(int[] arr, int start, int end) {
    int pivotIndex = start + ((end - start) / 2);
    int pivotVal = arr[pivotIndex];
    swap(arr, start, pivotIndex); //pivot stays at beginning of array
    int i = start + 1, j = start + 1; // i tracks next element to be swapped
    while(j <= end) {
      if(arr[j] <= pivotVal) // swap with i
        swap(arr, i++, j);
      //otherwise arr[j] > pivot, so don't do anything
      j++;
    }
    swap(arr, i - 1, start); // all elems from start + 1 to i - 1 is <= pivot
    return i - 1;
  }

  public static void swap(int[] arr, int i, int j) {
    if(i == j)
      return;
    arr[i] ^= arr[j]; // i ^ j
    arr[j] ^= arr[i]; // j ^ i ^ j
    arr[i] ^= arr[j]; // i ^ j ^ i
  }
}
