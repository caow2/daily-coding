/*
 * You are given given a list of rectangles represented by min and max x- and y-coordinates.
 * Compute whether or not a pair of rectangles overlap each other.
 * If one rectangle completely covers another, it is considered overlapping.
 * For example, given the following rectangles:
 * {
 *    "top_left": (1, 4),
 *    "dimensions": (3, 3) # width, height
 * },
 * {
 *    "top_left": (-1, 3),
 *    "dimensions": (2, 1)
 * },
 * {
 *    "top_left": (0, 5),
 *    "dimensions": (4, 3)
 * }
 * return true as the first and third rectangle overlap each other.
 */
public class Problem187 {
  public static void main(String[] args) {
    Rectangle r1 = new Rectangle(new int[] {1,4}, new int[] {3,3});
    Rectangle r2 = new Rectangle(new int[] {-1,3}, new int[] {2,1});
    Rectangle r3 = new Rectangle(new int[] {0,5}, new int[] {4,3});
    Rectangle[] r = new Rectangle[] {r1, r2, r3};

    System.out.println(overlapBruteForce(r));
  }

  /**
   * We can check whether 2 rectangles overlap in constant time.
   * However, for a list of rectangles, we may have to end up comparing
   * each rectangle with every other rectangle.
   * This would result in a quadratic runtime and constant space.
   * However, if we process each rectangle and hash it somehow,
   * maybe we can see if the current rectangle overlaps with a previous one with a better complexity.
   */
   public static boolean overlapBruteForce(Rectangle[] arr) {
     for(int i = 0; i < arr.length; i++) {
       for(int j = i + 1; j < arr.length; j++) {
         if(arr[i].overlap(arr[j]))
          return true;
       }
     }
     return false;
   }
}

class Rectangle {
  int x1, y1, x2, y2;

  public Rectangle(int[] topleft, int[] dimensions) {
    x1 = topleft[0];
    y1 = topleft[1];
    x2 = x1 + dimensions[0];
    y2 = y1 + dimensions[1];
  }

  public boolean overlap(Rectangle r) {
    return overlap1D(x1, x2, r.x1, r.x2) && overlap1D(y1, y2, r.y1, r.y2);
  }

  public boolean overlap1D(int a, int b, int c, int d) {
    return a < d && b > c;
  }
}
