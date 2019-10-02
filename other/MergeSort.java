import java.util.Arrays;

public class MergeSort {
  public static void main(String[] args) {
    int[] arr = new int[] {0,5,1,3,2,4};
    MS.sort(arr, 0, arr.length - 1);
    System.out.println(Arrays.toString(arr));

    arr = new int[] {-1,-5,-9,-10};
    MS.sort(arr, 0, arr.length - 1);
    System.out.println(Arrays.toString(arr));

    arr = new int[] {0,1,2,3,4};
    MS.sort(arr, 0, arr.length - 1);
    System.out.println(Arrays.toString(arr));
  }
}


/*
 * Outline of MergeSort:
 * - partition the array in half
 * - mergesort the left half
 * - mergesort right half
 * - merge the two arrays together
 */
class MS {
  public static void sort(int[] arr, int l , int r) {
    if(l >= r)
      return;
    int mid = l + (r - l) / 2;
    sort(arr, l, mid);
    sort(arr, mid + 1, r);
    merge(arr, l, mid, r);
  }

  public static void merge(int[] arr, int start, int mid, int end) {
    int s1 = mid - start + 1, s2 = end - mid;
    int[] left = new int[s1], right = new int[s2];
    //copy data
    for(int i = 0; i < s1; i++) {
      left[i] = arr[i + start];
    }

    for(int i = 0; i < s2; i++) {
      right[i] = arr[i + mid + 1];
    }

    // merge them together
    int l = 0, r = 0, idx = start;
    while(l < s1 && r < s2) {
      if(left[l] < right[r])
        arr[idx] = left[l++];
      else
        arr[idx] = right[r++];
      idx++;
    }

    while(l < s1) {
      arr[idx++] = left[l++];
    }

    while(r < s2) {
      arr[idx++] = right[r++];
    }
  }

}
