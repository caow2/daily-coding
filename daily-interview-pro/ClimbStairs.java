/*
 * You are given a positive integer N which represents the number of steps in a staircase.
 * You can either climb 1 or 2 steps at a time.
 * Write a function that returns the number of unique ways to climb the stairs.
 *
 * Can you find a solution in O(n) time?
 */
public class ClimbStairs {
  public static void main(String[] args) {
    int n = 3;
    System.out.println(numWays(n)); // 3

    n = 4;
    System.out.println(numWays(n)); // 5

    n = 5;
    System.out.println(numWays(n)); // 8
  }

  /**
   * Bottom up DP approach.
   * Consider n = 3.
   * If we have an array of length n + 1 to track the number of steps at step i,
   * and from step 0 we can reach step 1 and 2:
   *    [0, 1, 1, 0]     for steps 0, ..., 3
   * Then from step 1 we can reach step 2:
   *    [0, 1, 2, 0]
   * And for step 3 we can reach it from steps 1 and 2:
   *    [0, 1, 2, 3]
   *
   * Note that there are 3 ways to reach step 3 because we can only reach 3 from
   * 1 and 2, and from 1 and 2, there are 1 and 2 ways to reach each step respectively.
   * O(n) time, O(n) space
   *
   * If we consider this from a top to bottom approach, the subproblems become
   * obvious: From step n, we need the number of ways to reach step n - 1, n - 2.
   * From step n - 1, we need the number of ways to reach step n - 2, n - 3.
   * Which makes memoization a good candidate to reduce complexity.
   */
  public static int numWays(int n) {
    int[] arr = new int[n + 1];
    for(int i = 0; i < arr.length; i++) {
      if(i <= 2)
        arr[i] = i;
      else
        arr[i] = arr[i - 1] + arr[i - 2];
    }
    return arr[n];
  }
}
