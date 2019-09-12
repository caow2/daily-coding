import java.util.Queue;
import java.util.LinkedList;
import java.util.Collections;
import java.util.List;

/*
 * You are given an array of tuples (start, end) representing time intervals for lectures.
 * The intervals may be overlapping. Return the number of rooms that are required.
 * For example. [(30, 75), (0, 50), (60, 150)] should return 2.
 */
public class RoomScheduling {
  public static void main(String[] args) {
    List<int[]> times = new LinkedList<int[]>();
    times.add(new int[] {30,75});
    times.add(new int[] {0,50});
    times.add(new int[] {60, 150});
    System.out.println(numRooms(times)); // 2

    times.add(new int[] {55, 75});
    System.out.println(numRooms(times)); // 3

    times.add(new int[] {75, 100});
    times.add(new int[] {75, 80});
    System.out.println(numRooms(times)); // 3 b/c we freed up 2 rooms at 75

    times.add(new int[] {70, 90});
    System.out.println(numRooms(times)); // 4
  }

  /**
   * We can maintain a queue of 'rooms' and store the end times that they are
   * to be freed.
   * In order for this to work, we sort the times so we always process the
   * rooms with earliest start times first.
   *
   * i.e. [(0,50), (30,75), (60,150)]
   * Since the first room is freed at 50, our queue should be [50]
   * For (30, 75), we peek into the first element of the queue and check if
   * the start time is greater.
   * If it is, poll that element.
   * Regardless, we offer the end time of the current element.
   *
   * (30, 75) -> [50, 75]
   * (60, 150) -> [75, 150]
   * The number of rooms is the number of elements in the queue once we're done.
   * O(n) time, O(n) space if no overlapping occurs
   */
  public static int numRooms(List<int[]> times) {
    Collections.sort(times, (x,y) -> x[0] - y[0]);
    Queue<Integer> endTimes = new LinkedList<Integer>();
    for(int[] time : times) {
      // Assume that [30, 50] and [50, 75] do not need different rooms
      if(! endTimes.isEmpty() && endTimes.peek() <= time[0])
        endTimes.poll();
      endTimes.offer(time[1]);
    }
    return endTimes.size();
  }
}
