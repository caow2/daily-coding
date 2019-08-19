/*
 * You are given an array.
 * Each element represents the price of a stock on that particular day.
 * Calculate and return the maximum profit you can make from buying and selling that stock only once.
 *
 * For example: [9, 11, 8, 5, 7, 10]
 *
 * Here, the optimal trade is to buy when the price is 5, and sell when it is
 * 10, so the return value should be 5 (profit = 10 - 5 = 5).
 */
public class MaxProfit {
  public static void main(String[] args) {
    int[] arr =  new int[] {9,11,8,5,7,10};
    System.out.println(maxProfit(arr));

    arr = new int[] {5,4,1,2,3,0,2};
    System.out.println(maxProfit(arr));

    arr = new int[] {9,-1,0,5,7,-1};
    System.out.println(maxProfit(arr));
  }

  /**
   * A straightforward approach would be to try every possible pair of prices.
   * O(n^2) time, O(1) space.
   *
   * However, for each number, we can try to find the max profit using that number
   * as a selling price.
   * (i.e.) Track the smallest number up to index i and calculate max profit.
   * O(n) time, O(1) space.
   */
  public static int maxProfit(int[] arr) {
    if(arr.length == 0)
      return 0;
    int minPrice = arr[0], maxProfit = 0;
    for(int i = 1; i < arr.length; i++) {
      if(arr[i] > minPrice)
        maxProfit = Math.max(maxProfit, arr[i] - minPrice);
      else
        minPrice = arr[i];
    }
    return maxProfit;
  }
}
