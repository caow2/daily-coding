/*
 * Given a list of numbers, where every number shows up twice except for one number, find that one number.
 *
 * Example:
 * Input: [4, 3, 2, 4, 1, 3, 2]
 * Output: 1
 *
 * Challenge: Can you do this in O(1) space?
 */
 public class NonDuplicateNumber {
   public static void main(String[] args) {
     int[] a1 = new int[] {1,3,2,2,3};
     int[] a2 = new int[] {-1,1,0,1,-1};
     int[] a3 = new int[] {-1,5,2,0,2,5,0};

     System.out.println(findNonDuplicate(a1));
     System.out.println(findNonDuplicate(a2));
     System.out.println(findNonDuplicate(a3));
   }

   /**
    * A brute force approach would be for each number, try to find it in the
    * rest of the array.
    *
    * A better, straightforward solution is to hash numbers that we've seen, and
    * if we see that number again, remove it from the HashSet.
    * This works because there are only 2 instances of each number except for one,
    * and we should be left with one number at the end.
    * O(n) time, O(n) space.
    *
    * For the bitwise operator XOR, recall that n XOR n = 0, and
    * 1 XOR n XOR 1 = n XOR 1 XOR 1 = n XOR 0 = n.
    * Then for the array, we can just XOR each number and duplicates will cancel out.
    * O(n) time, O(1) space.
    */
   public static int findNonDuplicate(int[] arr) {
     int bit = 0;
     for(int i = 0; i < arr.length; i++) {
       bit ^= arr[i];
     }
     return bit;
   }
 }
