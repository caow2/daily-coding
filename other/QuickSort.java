import java.util.Arrays;

public class QuickSort {
  public static void main(String[] args) {
      int[] arr = new int[] {0,1,5,3,2,4};
      QS.sort(arr, 0, arr.length - 1);
      System.out.println(Arrays.toString(arr));

      arr = new int[] {0,0,0,1,0,0};
      QS.sort(arr, 0, arr.length - 1);
      System.out.println(Arrays.toString(arr));

      arr = new int[] {-1,-9,-2,3};
      QS.sort(arr, 0, arr.length - 1);
      System.out.println(Arrays.toString(arr));
  }
}

/**
 * QS:
 * partition the array around a pivot value i s.t. everything to left of i is <=, everything to right is >
 * qs left half
 * qs right half
 * {2,1,3,3,5,4};
 */
class QS {
  public static void sort(int[] arr, int l, int r) {
    if(l >= r)
      return;
    int pivotIndex = partition(arr, l, r);
    sort(arr, l, pivotIndex - 1);
    sort(arr, pivotIndex + 1, r);
  }

  // return pivot index
  public static int partition(int[] arr, int l, int r) {
    int pivotVal = arr[l];
    int i = l + 1, j = i;
    while(i <= r) {
      if(arr[i] <= pivotVal) {
        swap(arr, i, j++);
      }
      i++;
    }
    swap(arr, l, j - 1);
    return j - 1;
  }

  public static void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }
}
