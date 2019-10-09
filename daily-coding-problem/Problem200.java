import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

/*
 * Let X be a set of n intervals on the real line.
 * We say that a set of points P "stabs" X if every interval in X
 * contains at least one point in P. Compute the smallest set of points that stabs X.
 * For example, given the intervals [(1, 4), (4, 5), (7, 9), (9, 12)], you should return [4, 9].
 */
public class Problem200 {
  public static void main(String[] args) {
    int[][] arr = new int[][] {{1,4},{4,5},{7,9},{9,12}};
    System.out.println(minimumPoints(arr));

    arr = new int[][] {{4,5},{1,4},{2,3},{7,9}};
    System.out.println(minimumPoints(arr));
  }

  /**
   * This question can be viewed similarly as the mergeIntervals problem.
   * We can first sort the input to ensure we process the ones with earliest start
   * intervals.
   * Next, we can try to merge intervals. Whenever we encounter an interval i we can't merge
   * into the current one, we know we need another point that 'stabs' i.
   * This should give us the minimum set of points because you are
   * considering successive intervals and viewing their intersections.
   * O(nlogn) to sort input, O(n) space
   */
  public static Set<Integer> minimumPoints(int[][] intervals) {
    Arrays.sort(intervals, (x, y) -> x[0] - y[0]);
    Set<Integer> points = new HashSet<Integer>();
    if(intervals.length == 0)
      return points;

    int[] itv = intervals[0];
    for(int i = 1; i < intervals.length; i++) {
        int[] curr = intervals[i];
        if(overlap(itv, curr)) {
          //reduce the intersection
          itv[0] = Math.max(itv[0], curr[0]);
          itv[1] = Math.min(itv[1], curr[1]);
        }
        else {
          points.add(itv[0]);
          itv = curr;
        }
    }
    points.add(itv[0]);

    return points;
  }

  private static boolean overlap(int[] a, int[] b) {
    return a[0] <= b[1] && a[1] >= b[0];
  }
}
