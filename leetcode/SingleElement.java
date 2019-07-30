/*
 * https://leetcode.com/discuss/interview-question/346626/google-phone-screen-single-element
 *
 * Given an array nums of length n. All elements appear in pairs except one of them.
 * Find this single element that appears alone.
 * Pairs of the same element cannot be adjacent:
 *
 * [2, 2, 1, 2, 2] // ok
 * [2, 2, 2, 2, 1] // not allowed
 *
 * Example 1:
 * Input: [2, 2, 1, 1, 9, 9, 5, 2, 2]
 * Output: 5
 *
 * Example 2:
 * Input: [1, 1, 2]
 * Output: 2
 *
 * Follow up: Can we do better than O(n) ?
 */
public class SingleElement {
  public static void main(String[] args) {
    int[] arr = new int[] { 2, 2, 1, 3, 3 };
    System.out.println(singleElement(arr));
    System.out.println(singleElementBSearch(arr));

    arr = new int[] {1,2,2};
    System.out.println(singleElement(arr));
    System.out.println(singleElementBSearch(arr));

    arr = new int[] {2,2,1};
    System.out.println(singleElement(arr));
    System.out.println(singleElementBSearch(arr));

    arr = new int[] { 2, 2, 1, 1, 9, 9, 5, 2, 2 };
    System.out.println(singleElement(arr));
    System.out.println(singleElementBSearch(arr));
  }

  /**
   * We can use track the elements that we've already seen.
   * For example, imagine we see the following int[] arr = [2, 2, 1, 3, 3].
   * and we have an Integer i that starts at 0:
   *  1. We see 2 --> i is 0, so i += 2
   *  2. We see 2 --> i is 2, so i -= 2
   *  3. We see 1 --> i is 0, so i += 1
   *  4. We see 3 --> i is 1, so 1 is the single number
   * Since all numbers have to appear in pairs, if we see a number that's not 0
   * and not equal to the current number, it has to be the single number.
   *
   * If we have the following array [0,1,1,2,2] it will still work because
   * after iterating thru the entire array, we can just return i = 0.
   * O(n) time, O(1) space.
   *
   * Alternatively, we can use a HashSet or XOR to solve this.
   */
  public static int singleElement(int[] arr) {
    int xor = 0;
    for(int i = 0; i < arr.length; i++) {
      xor ^= arr[i];
    }
    return xor;
  }

  /**
   * Follow up question: Can we do better than O(n) time?
   * If we want to do better than O(n), we can probably attempt some sort of
   * binary search for O(logn) as O(1) time seems impossible.
   *
   * If we observe a middle element, i.e. in arr = [1,1,0,2,2], it is a single element
   * if arr[mid - 1] and arr[mid + 1] != arr[mid]
   *
   * For the given array [1,1,2,2,3], how can we figure out which side to search?
   * The middle element arr[2] = 2 is paired with arr[3].
   * If we remove arr[3] as possible candidate to search, then we notice that
   * the left side has an even number of elements and the adjusted right side
   * has an odd number of elements.
   * From this, the single element has to be on the right side.
   */
   public static int singleElementBSearch(int[] arr) {
     int left = 0, right = arr.length - 1;
     while(left <= right) {
       int mid = left + ((right - left) / 2); // prevent overflow if left & right are large ints
       if(mid > 0 && arr[mid] == arr[mid - 1]) {
         //Find out which side has odd / even # of elements
         if((mid - 1) - left % 2 == 0)
          left = mid + 1;
         else
          right = mid - 2;
       }
       else if (mid < arr.length - 1 && arr[mid] == arr[mid + 1]) {
        if((right - (mid + 1)) % 2 == 0)
          right = mid - 1;
        else
          left = mid + 2;
       }
       else // arr[mid] != arr[mid - 1] and arr[mid + 1]
        return arr[mid];
     }
     return -1;
   }
}
