import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Arrays;
/*
 * Given a list of points, a central point, and an integer k,
 * find the nearest k points from the central point.
 *
 * For example, given the list of points [(0, 0), (5, 4), (3, 1)],
 * the central point (1, 2), and k = 2, return [(0, 0), (3, 1)].
 */
public class Problem150 {
  public static void main(String[] args) {
    int[][] points = new int[][] { {0,0}, {5,4}, {3,1} };
    int[] center = new int[] {1,2};
    int k = 2;
    System.out.println(Arrays.deepToString(nearestPoints(points, center, k)));

    k = 1;
    System.out.println(Arrays.deepToString(nearestPoints(points, center, k)));
  }

  /**
   * Brute Force -> compute all of the Euclidean distances for each point
   * from the central point and store them in a list.
   * Sort the list and return the k smallest points.
   * O(nlogn) time, O(n) space
   *
   * Better -> We don't need to store all n distances; just the k closest ones.
   * We can loop through the points and maintain a fixed size heap / Priority Queue.
   * O(n log k) time, O(k) space
   */
  public static int[][] nearestPoints(int[][] points, int[] center, int k) {
    PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
      @Override
      public int compare(int[] i1, int[] i2) {
        double d1 = Math.sqrt(Math.pow((double) i1[0] - (double) center[0], 2) +
                    Math.pow((double) i1[1] - (double) center[1], 2));
        double d2 = Math.sqrt(Math.pow((double) i2[0] - (double) center[0], 2) +
                    Math.pow((double) i2[1] - (double) center[1], 2));
        if(d1 < d2)
          return 1;
        else if(d1 > d2)
          return -1;
        return 0;
      }
    });

    for(int[] point : points) {
      if(pq.size() >= k)
        pq.poll();
      pq.offer(point);
    }

    int[][] result = new int[k][points[0].length];
    int i = 0;
    while(!pq.isEmpty()) {
      int[] point = pq.poll();
      result[i][0] = point[0];
      result[i][1] = point[1];
      i++;
    }

    return result;
  }
}
