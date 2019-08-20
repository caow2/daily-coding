import java.util.Arrays;
import java.util.LinkedList;
/*
 * Given a 2-D matrix representing an image,
 * a location of a pixel in the screen and a color C,
 * replace the color of the given pixel and all adjacent same colored pixels with C.
 *
 * For example, given the following matrix, and location pixel of (2, 2), and 'G' for green:
 * B B W
 * W W W
 * W W W
 * B B B
 * Becomes
 * B B G
 * G G G
 * G G G
 * B B B
 */
public class Problem151 {
  public static void main(String[] args) {
    char[][] image = new char[][] {{'B','B','W'}, {'W','W','W'}, {'W','W','W'}, {'B','B','B'}};
    boolean[][] visited = new boolean[image.length][image[0].length];
    int x = 2, y = 2;
    changeColor(image, visited, x, y, 'G', image[x][y]);

    System.out.println(Arrays.deepToString(image));

    x = 0;
    y = 0;
    changeColorBFS(image, x, y, 'Y');
    System.out.println(Arrays.deepToString(image));

    x = 2;
    y = 0;
    changeColorBFS(image, x, y, 'O');
    System.out.println(Arrays.deepToString(image));
  }

  // DFS approach - O(n) time, O(n) space
  public static void changeColor(char[][] image, boolean[][] visited, int x, int y, char color, char original) {
    if(! valid(x, y, image) || visited[x][y] ||
      image[x][y] != original)
      return;
    visited[x][y] = true;
    image[x][y] = color;
    changeColor(image, visited, x + 1, y, color, original);
    changeColor(image, visited, x - 1, y, color, original);
    changeColor(image, visited, x, y + 1, color, original);
    changeColor(image, visited, x, y - 1, color, original);
  }

  private static boolean valid(int x, int y, char[][] image) {
    return x >= 0 && x < image.length && y >= 0 && y < image[0].length;
  }

  // BFS approach - O(n) time, O(n) space
  public static void changeColorBFS(char[][] image, int x, int y, char color) {
    LinkedList<int[]> q = new LinkedList<int[]>();
    q.offer(new int[] { x, y });
    char original = image[x][y];
    boolean[][] visited = new boolean[image.length][image[0].length];
    while(! q.isEmpty()) {
      int[] pixel = q.poll();
      x = pixel[0];
      y = pixel[1];
      if(! valid(x, y, image) || visited[x][y] || image[x][y] != original)
        continue;
      visited[x][y] = true;
      image[x][y] = color;
      q.offer(new int[] { x + 1, y });
      q.offer(new int[] { x - 1, y });
      q.offer(new int[] { x, y + 1 });
      q.offer(new int[] { x, y - 1 });
    }
  }
}
