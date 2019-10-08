/*
 * You are given an array of arrays of integers, where each array corresponds to a
 * row in a triangle of numbers. For example, [[1], [2, 3], [1, 5, 1]] represents the triangle:
 *   1
 *  2 3
 * 1 5 1
 * We define a path in the triangle to start at the top and go down one row at
 * a time to an adjacent value, eventually ending with an entry on the bottom
 * row. For example, 1 -> 3 -> 5. The weight of the path is the sum of the entries.
 * Write a program that returns the weight of the maximum weight path.
 */
public class Problem201 {
  public static void main(String[] args) {
    int[][] arr = {{1},{2,3},{1,5,1}};
    System.out.println(maxWeight(arr));

    arr = new int[][] {{1},{2,3},{1000,5,1}};
    System.out.println(maxWeight(arr));

    arr = new int[][] {{1}, {-1,50},{100,10,10}};
    System.out.println(maxWeight(arr));
  }


  /*
   * Let us view the 'triangle' as a right triangle instead:
   * 1
   * 2 3
   * 1 5 1
   * It seems that from triangle[row][i], we can either choose
   * triangle[row + 1][i] or triangle[row + 1][i + 1] as our next candidate.
   *
   * We note that a greedy approach is not always optimal as we could have
   * the following input: [1],[2,3],[1000,5,1].
   * The greedy approach would choose 1->3->5 and not 1->2->1000.
   *
   * A brute force approach would be to try every path and choose the maximum.
   * Specifically, from triangle[0][0], we have the following:
   * max_path(triangle[0][0]) = triangle[0][0] +
   *                Math.max(max_path(triangle[1][0]), max_path(triangle[1][1])
   *
   * If we expand this structure out, we see multiple overlapping problems:
   *  - max_path(triangle[1][0]) requires max_path(triangle[2][0]), max_path(triangle[2][1])
   *  - max_path(triangle[1][1]) requires max_path(triangle[2][1]), max_path(triangle[2][2])
   * It seems like we can use a dynamic programming approach to prevent recomputation.
   * O(n) where n is the total number of elements across all arrays.
   */
   public static int maxWeight(int[][] tri) {
     if(tri.length == 0 || tri[0].length == 0)
      return 0;
     int[][] dp = new int[tri.length][tri.length];
     return maxWeight(tri, dp, 0, 0);
   }

   private static int maxWeight(int[][] tri, int[][] dp, int i, int j) {
     if(i == tri.length - 1)
      return tri[i][j];
     if(dp[i][j] > 0)
      return dp[i][j]; //already computed
     dp[i][j] = tri[i][j] + Math.max(maxWeight(tri, dp, i + 1, j), maxWeight(tri, dp, i + 1, j + 1));
     return dp[i][j];
   }
}
