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

  }

  /**
   * We can check whether 2 rectangles overlap in constant time.
   * However, for a list of rectangles, we may have to end up comparing
   * each rectangle with every other rectangle.
   * This would result in a quadratic runtime and constant space.
   * However, if we process each rectangle and hash it somehow,
   * maybe we can see if the current rectangle overlaps with a previous one with a better complexity.
   */
}
