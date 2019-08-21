import java.util.List;
import java.util.Arrays;
import java.util.LinkedList;

/*
 * You are given an array of intervals - that is, an array of tuples (start, end).
 * The array may not be sorted, and could contain overlapping intervals.
 * Return another array where the overlapping intervals are merged.
 *
 * For example:
 * [(1, 3), (5, 8), (4, 10), (20, 25)]
 *
 * This input should return [(1, 3), (4, 10), (20, 25)] since (5, 8) and (4, 10)
 * can be merged into (4, 10).
 */
public class MergeInterval {
  public static void main(String[] args) {
    System.out.println("Case 1");
    int[][] intervals = new int[][] {{1,3},{5,8},{4,10},{20,25}};
    for(int[] i : merge(intervals)) {
      System.out.println(Arrays.toString(i));
    }

    System.out.println("Case 2");
    intervals = new int[][] {{1,3},{5,8},{4,10},{8,25}};
    for(int[] i : merge(intervals)) {
      System.out.println(Arrays.toString(i));
    }

    System.out.println("Case 3");
    intervals = new int[][] {{1,3},{3,8},{9,10},{10,25}};
    for(int[] i : merge(intervals)) {
      System.out.println(Arrays.toString(i));
    }
  }

  public static List<int[]> merge(int[][] intervals) {
    List<int[]> result = new LinkedList<int[]>();
    if(intervals.length == 0)
      return result;
    Arrays.sort(intervals, (x,y) -> x[0] - y[0]);

    int[] interval = intervals[0];
    for(int i = 1; i < intervals.length; i++) {
      int[] curr = intervals[i];
      if(overlap(interval, curr)) {
        interval[0] = Math.min(interval[0], curr[0]);
        interval[1] = Math.max(interval[1], curr[1]);
      }
      else {
        result.add(interval);
        interval = curr;
      }
    }
    result.add(interval);
    return result;
  }

  private static boolean overlap(int[] i1, int[] i2) {
    return i1[0] <= i2[1] && i1[1] >= i2[0];
  }
}
