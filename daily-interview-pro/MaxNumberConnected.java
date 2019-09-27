import java.util.Queue;
import java.util.LinkedList;
/*
 * Given a grid of x colors, return the maximum number of connected components.
 * For example, for the following grid, return 5:
 * [R G B]
 * [R R R]
 * [G G R]
 * since there are 5 red connected components.
 *
 * There may be alot of colors, and the grid may be fairly large - a recursive solution may
 * not be optimal.
 */
public class MaxNumberConnected {
  public static void main(String[] args) {
    int[][] grid = new int[][] {{1,2,3}, {1,1,1}, {2,2,1}};
    System.out.println(maxConnected(grid)); // 5

    grid = new int[][] {{1,1,2},{2,2,1},{3,3,1}};
    System.out.println(maxConnected(grid)); // 2

    grid = new int[][] {{1,1,2},{2,2,1},{3,3,3}};
    System.out.println(maxConnected(grid)); // 3
  }

  public static int maxConnected(int[][] grid) {
    int max = 0;
    if(grid.length == 0)
      return max;
    boolean[][] seen = new boolean[grid.length][grid[0].length];
    for(int i = 0; i < grid.length; i++) {
      for(int j = 0; j < grid[0].length; j++) {
        if(seen[i][j])
          continue;
        max = Math.max(max, bfs(i, j, grid, seen));
      }
    }
    return max;
  }

  private static int bfs(int i, int j, int[][] grid, boolean[][] seen) {
    Queue<int[]> q = new LinkedList<int[]>();
    int connected = 0;
    q.offer(new int[] {i,j});

    while(!q.isEmpty()) {
      int[] coord = q.poll();
      int x = coord[0], y = coord[1];
      if(x < 0 || x >= grid.length || y < 0 || y >= grid[0].length
        || seen[x][y] || grid[x][y] != grid[i][j])
        continue;
      connected++;
      seen[x][y] = true;
      q.offer(new int[] {x - 1, y});
      q.offer(new int[] {x + 1, y});
      q.offer(new int[] {x, y - 1});
      q.offer(new int[] {x, y + 1});
    }

    return connected;
  }
}
