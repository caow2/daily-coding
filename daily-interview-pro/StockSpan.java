import java.util.Stack;
import java.util.Arrays;
/*
 * The stock span problem is a financial problem where we have a series of n
 * daily price quotes for a stock and we need to calculate span of stock’s price for all n days.
 * The span Si of the stock’s price on a given day i is defined as the maximum number of
 * consecutive days just before the given day, for which the price of the stock
 * on the current day is less than or equal to its price on the given day.
 * For example, if an array of 7 days prices is given as {100, 80, 60, 70, 60, 75, 85},
 * then the span values for corresponding 7 days are {1, 1, 1, 2, 1, 4, 6}.
 */
public class StockSpan {
  public static void main(String[] args) {
    int[] prices = {100, 80, 60, 70, 60, 75, 85};
    System.out.println(Arrays.toString(span(prices)));
    System.out.println(Arrays.toString(spanOptimized(prices)));

    prices = new int[] {1,2,3,4,5,4};
    System.out.println(Arrays.toString(span(prices)));
    System.out.println(Arrays.toString(spanOptimized(prices)));

    prices = new int[] {3,2,1,4,3,5};
    System.out.println(Arrays.toString(span(prices)));
    System.out.println(Arrays.toString(spanOptimized(prices)));
  }

  // Brute Force O(n^2) time, O(1) space
  public static int[] span(int[] arr) {
    int[] result = new int[arr.length];
    for(int i = 0; i < arr.length; i++) {
      int span = 1;
      int j = i - 1;
      while(j >= 0 && arr[j] <= arr[i]) {
        span++;
        j--;
      }
      result[i] = span;
    }
    return result;
  }

  /**
   * Since we look back until we find an element that's greater than arr[i],
   * we could use a Stack to track seen elements.
   * We only pop from a Stack while the top element is <= arr[i] and add to span[i]
   * The logic is that for a future element arr[j], if arr[j] < arr[i], it has a
   * span of 1 -> i.e. we don't need any info from arr[i].
   * if arr[j] >= arr[i], it means that arr[i] is part of the span of arr[j]
   * and we can combine their spans.
   * O(n) time, O(n) space.
   */
  public static int[] spanOptimized(int[] arr) {
    int[] result = new int[arr.length];
    Stack<int[]> st = new Stack<int[]>(); // store [index, span of index i]
    for(int i = 0; i < arr.length; i++) {
      int span = 1;
      while(! st.isEmpty() && arr[st.peek()[0]] <= arr[i]) {
        span += st.pop()[1];
      }
      st.push(new int[] {i, span});
      result[i] = span;
    }
    return result;
  }
}
