/*
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * Find the minimum element in O(log N) time. You may assume the array does not contain duplicates.
 * For example, given [5, 7, 10, 3, 4], return 3.
 */
public class Problem203 {
  public static void main(String[] args) {
    int[] arr = {1,2,3,4,5};
    System.out.println(optimizedFindMin(arr));

    arr = new int[] {5,1,2,3,4};
    System.out.println(optimizedFindMin(arr));

    arr = new int[] {2,3,4,5,1};
    System.out.println(optimizedFindMin(arr));

    arr = new int[] {};
    System.out.println(optimizedFindMin(arr));

    arr = new int[] {2,-1,1};
    System.out.println(optimizedFindMin(arr));

    arr = new int[] {1};
    System.out.println(optimizedFindMin(arr));
  }

  /**
   * Brute force is to simply walk through the array and look for the min
   * O(n) time, O(1) space
   */
  public static int findMin(int[] arr) {
    int min = Integer.MAX_VALUE;
    for(int i = 0; i < arr.length; i++) {
      min = Math.min(min, arr[i]);
    }
    return min;
  }

  /**
   * [1,2,3,4,5]
   * [5,1,2,3,4]
   * [4,5,1,2,3]
   * [3,4,5,1,2]
   * Since we know that array is sorted to an extent, we can probably
   * perform some sort of modified binary search.
   * Consider the input [5,1,2,3,4] with variables Left, Right, and Mid
   * We know that when Mid < Mid - 1 we have the smallest element.
   * Otherwise, we compare Left, Right, and Mid:
   *  - Left < Right -> smallest is to the left [sorted sequence]
   *  - Left > Right -> Mid > Left -> smallest is to right (Left to Mid is a shifted ascending sequence)
   *                    Mid < Left -> smallest is to the left
   * O(log n)
   */
   public static int optimizedFindMin(int[] arr) {
     int left = 0, right = arr.length - 1;
     while(left <= right) {
       int mid = left + (right - left) / 2, prev = (mid == 0 ? arr.length - 1 : mid - 1);
       if(arr[mid] <= arr[prev]) // = is for case of single element
        return arr[mid];
       else if(arr[left] < arr[right])
        right = mid - 1;
       else {
         if(arr[mid] >= arr[left]) // equals is for when mid == left
          left = mid + 1;
         else
          right = mid - 1;
       }
     }
     return Integer.MIN_VALUE;
   }
}
