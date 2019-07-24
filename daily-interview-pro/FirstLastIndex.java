import java.util.Arrays;

/*
 * Given a sorted array, A, with possibly duplicated elements, find the indices
 * of the first and last occurrences of a target element, x.
 * Return -1 if the target is not found.
 *
 * Example:
 * Input: A = [1,3,3,5,7,8,9,9,9,15], target = 9
 * Output: [6,8]
 *
 * Input: A = [100, 150, 150, 153], target = 150
 * Output: [1,2]
 *
 * Input: A = [1,2,3,4,5,6,10], target = 9
 * Output: [-1, -1]
 */
 public class FirstLastIndex {
   public static void main(String[] args) {
     int[] v1 = new int[] {1,2,3,4,4,5};
     int[] v2 = new int[] {1,3,4,4,4};
     int[] v3 = new int[] {-1,-1,-1,0,2,4,7};
     int[] v4 = new int[] {-5, 0, 2, 4, 9, 12};

     int n1 = 4, n2 = 4, n3 = -1, n4 = 0;
     int n5 = 0, n6 = 2;

     System.out.println(Arrays.toString(firstLastIndex(v1,n1)));
     System.out.println(Arrays.toString(firstLastIndex(v2,n2)));
     System.out.println(Arrays.toString(firstLastIndex(v3,n3)));
     System.out.println(Arrays.toString(firstLastIndex(v4,n4)));

     System.out.println(Arrays.toString(firstLastIndex(v1,n5)));
     System.out.println(Arrays.toString(firstLastIndex(v2,n6)));
   }

   /**
    * A straightforward approach is just to iterate thru the array and look
    * for first and last occurrence of x. O(n) time, O(1) space.
    *
    * However, we can leverage the fact that the array is sorted - indicating
    * that maybe binary search could be an applicable technique.
    * If we can figure out how to binary search for the first occurence of x,
    * we can basically modify the conditions to binary search for the last occurence
    * of x. O(logn) time, O(1) space.
    */
   public static int[] firstLastIndex(int[] arr, int x) {
     int first = firstOccurrence(arr, x);
     int last = lastOccurrence(arr, x);
     return new int[] { first, last };
   }

   public static int firstOccurrence(int[] arr, int x) {
     int left = 0, right = arr.length - 1;
     while (left <= right) {
       int mid = left + ((right - left) / 2);
       if (arr[mid] == x) {
         //previous element != x indicates that mid is first occurrence of x
         if(mid == 0 || arr[mid - 1] != x)
          return mid;
         else
          right = mid - 1;
       }
       else if (arr[mid] < x)
        left = mid + 1;
       else
        right = mid - 1;
     }
     return -1;
   }

   /*
    * Very similar to firstOccurrence with minor tweaks to the conditions
    */
   public static int lastOccurrence(int[] arr, int x) {
     int left = 0, right = arr.length - 1;
     while (left <= right) {
       int mid = left + ((right - left) / 2);
       if(arr[mid] == x) {
         if(mid == arr.length - 1 || arr[mid + 1] != x)
          return mid;
         else
          left = mid + 1;
       }
       else if (arr[mid] < x)
        left = mid + 1;
       else
        right = mid - 1;
     }
     return -1;
   }
 }
