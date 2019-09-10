import java.util.Collections;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

/*
 * You are given a list of data entries that represent entries and exits of
 * groups of people into a building.
 *
 * An entry looks like this:
 * {"timestamp": 1526579928, count: 3, "type": "enter"}
 * This means 3 people entered the building. An exit looks like this:
 * {"timestamp": 1526580382, count: 2, "type": "exit"}
 * This means that 2 people exited the building. timestamp is in Unix time.
 *
 * Find the busiest period in the building, that is, the time with the most
 * people in the building. Return it as a pair of (start, end) timestamps.
 * You can assume the building always starts off and ends up empty, i.e. with 0 people inside.
 */
public class Problem171 {
  public static void main(String[] args) {
    ArrayList<TimeStamp> times = new ArrayList<TimeStamp>();
    times.add(new TimeStamp(0, 3, "enter"));
    times.add(new TimeStamp(1, 5, "enter"));
    times.add(new TimeStamp(5, 1, "enter"));
    times.add(new TimeStamp(3, 2, "exit"));
    times.add(new TimeStamp(10, 7, "exit"));

    System.out.println(Arrays.toString(busiest(times))); // [1,3]

    times.add(new TimeStamp(7, 100, "enter"));
    times.add(new TimeStamp(13, 100, "exit"));
    System.out.println(Arrays.toString(busiest(times))); // [7, 10]
  }

  /**
   * O(nlogn) time to sort input
   */
  public static int[] busiest(List<TimeStamp> times) {
    // An arraylist would be a better input here
    // Also assuming that 2 timestamp events can't happen simultaneously i.e. people entering & exit at same timestamp
    Collections.sort(times, (x, y) -> x.time - y.time);
    // Don't need to process last element since we know the building ends at 0 -> can't be a busiest period
    int[] busy = new int[2];
    int globalCount = 0, count = 0;
    for(int i = 0; i < times.size() - 1; i++) {
      TimeStamp ts = times.get(i);
      if(ts.type.equals("enter")) {
        count += ts.count;
        if(count > globalCount) {
          globalCount = count;
          busy[0] = ts.time;
          busy[1] = times.get(i + 1).time; // busiest until start of next timestamp
        }
      }
      else
        count -= ts.count;
    }
    return busy;
  }
}

class TimeStamp {
  int time, count;
  String type;

  public TimeStamp(int time, int count, String type) {
    this.time = time;
    this.count = count;
    this.type = type;
  }
}
