import java.util.List;
import java.util.LinkedList;
import java.util.Collections;
/*
 * Given an array of numbers representing the stock prices of a company in
 * chronological order and an integer k, return the maximum profit you can make
 * from k buys and sells. You must buy the stock before you can sell it, and you
 * must sell the stock before you can buy it again.
 *
 * For example, given k = 2 and the array [5, 2, 4, 0, 1], you should return 3.
 */
 public class Problem130 {
   public static void main(String[] args) {
     int[] a1 = new int[] { 5, 2, 4, 0, 1};
     int k1 = 1, k2 = 2, k3 = 3;
     System.out.println(maxProfit(a1, k1)); // 2
     System.out.println(maxProfit(a1, k2)); // 3
     System.out.println(maxProfit(a1, k3)); // 3

     int[] a2 = new int[] {0,1,0,1,0,1,0,1};
     System.out.println(maxProfit(a2, k1)); // 1
     System.out.println(maxProfit(a2, k2)); // 2
     System.out.println(maxProfit(a2, k3)); // 3

     int[] a3 = new int[] {5, 10, 0, 9, 10, 11, 1};
     System.out.println(maxProfit(a3, k1)); // 11
     System.out.println(maxProfit(a3, k2)); // 16
     System.out.println(maxProfit(a3, k3)); // 16
   }

   /**
    * Obtain a list of all the possible profits from the sequence and sort it.
    * Then we can just take the kth largest profits and add them.
    * O(nlogn) time for sorting, O(n) space.
    */
   public static int maxProfit(int[] arr, int k) {
     List<Integer> profits = getProfits(arr);
     Collections.sort(profits);
     int profit = 0;
     for(int i = profits.size() - 1; i >= 0 && k > 0; i--) {
       profit += profits.get(i);
       k--;
     }
     return profit;
   }

   /**
    * We can imagine arr as a sequence of numbers, and profits occur at
    * local maxima - local minima.
    * We only calculate a new profit when a monotonic increasing part of the sequence
    * starts decreasing: i.e. the current price is less than the previous price / curret max
    *
    * Otherwise if the current price is greater than the current maximum,
    * we just update the max since it's still monotonic increasing.
    * O(n) time, O(n) space.
    */
   private static List<Integer> getProfits(int[] arr) {
     List<Integer> profits = new LinkedList<Integer>();
     int min = Integer.MAX_VALUE, max = Integer.MAX_VALUE;

     for(int i = 0 ; i < arr.length; i++) {
       int price = arr[i];
       if(price < max) {
         if(max - min > 0)
          profits.add(max - min);
         min = price;
         max = min;
       }
       else
        max = price;
     }

     if(max - min > 0)
      profits.add(max - min); // In case the end of array is monotonic increasing

     return profits;
   }
 }
