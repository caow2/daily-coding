import java.util.LinkedList;
import java.util.Arrays;
/*
 * You are given an N by M matrix of 0s and 1s.
 * Starting from the top left corner, how many ways are there to reach the bottom right corner?
 * You can only move right and down.
 * 0 represents an empty space while 1 represents a wall you cannot walk through.
 * For example, given the following matrix:
 *
 * [[0, 0, 1],
 * [0, 0, 1],
 * [1, 0, 0]]
 * Return two, as there are only two ways to get to the bottom right:
 *
 * Right, down, down, right
 * Down, right, down, right
 * The top left corner and bottom right corner will always be 0.
 */
public class Problem158 {
  public static void main(String[] args) {
    int[][] grid = new int[][] { {0,0,1}, {0,0,1}, {1,0,0} };
    System.out.println(numWays(grid));

    grid = new int[][] { {0,0,1}, {0,0,1}, {0,0,1} };
    System.out.println(numWays(grid));

    grid = new int[][] { {1,0,1}, {0,0,1}, {0,0,0} };
    System.out.println(numWays(grid));

    grid = new int[][] { {0,0,0}, {0,1,1}, {0,0,0} };
    System.out.println(numWays(grid));

    grid = new int[][] { {0,0,1}, {0,0,0}, {1,0,0} };
    System.out.println(numWays(grid));
  }

  public static int numWays(int[][] grid) {
    if(grid.length == 0 || grid[0][0] == 1)
      return 0;

    LinkedList<int[]> q = new LinkedList<int[]>();
    int[][] dp = new int[grid.length][grid[0].length];
    dp[0][0] = 1;

    q.offer(new int[] {0,0});
    while(! q.isEmpty()) {
      int[] c = q.poll();
      int x = c[0], y = c[1];
      if(grid[x][y] == -1)
        continue; // already processed this coordinate before.
      // traverse down
      if(valid(grid, x + 1, y)) {
        dp[x + 1][y] += dp[x][y];
        q.offer(new int[] { x + 1, y});
      }
      // traverse right
      if(valid(grid, x, y + 1)) {
        dp[x][y + 1] += dp[x][y];
        q.offer(new int[] { x, y + 1 });
      }

      grid[x][y] = -1;
    }
    return dp[grid.length - 1][grid[0].length - 1];
  }

  private static boolean valid(int[][] grid, int x, int y) {
    return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length
           && grid[x][y] == 0;
  }
}
