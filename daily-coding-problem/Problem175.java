import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/*
 * You are given a starting state start, a list of transition probabilities for a Markov chain,
 * and a number of steps num_steps. Run the Markov chain starting from start for num_steps
 * and compute the number of times we visited each state.
 * For example, given the starting state a, number of steps 5000, and the following transition probabilities:
 * [
 *  ('a', 'a', 0.9),
 *  ('a', 'b', 0.075),
 *  ('a', 'c', 0.025),
 *  ('b', 'a', 0.15),
 *  ('b', 'b', 0.8),
 *  ('b', 'c', 0.05),
 *  ('c', 'a', 0.25),
 *  ('c', 'b', 0.25),
 *  ('c', 'c', 0.5)
 * ]
 * One instance of running this Markov chain might produce { 'a': 3012, 'b': 1656, 'c': 332 }.
 */
public class Problem175 {
  public static void main(String[] args) {
    List<Object[]> states = new ArrayList<Object[]>();
    states.add(new Object[] {"a", "a", .9});
    states.add(new Object[] {"a", "b", .075});
    states.add(new Object[] {"a", "c", .025});
    states.add(new Object[] {"b", "a", .15});
    states.add(new Object[] {"b", "b", .8});
    states.add(new Object[] {"b", "c", .05});
    states.add(new Object[] {"c", "a", .25});
    states.add(new Object[] {"c", "b", .25});
    states.add(new Object[] {"c", "c", .5});

    int steps = 50000;
    String start = "a";
    System.out.println(simulation(states, steps, start));

  }

  public static HashMap<String, Integer> simulation(List<Object[]> states, int steps, String s) {
    HashMap<String, Node> graph = new HashMap<String, Node>();
    for(Object[] state : states) {
      String start = (String) state[0], end = (String) state[1];
      Double p = (Double) state[2];
      if(graph.get(start) == null)
        graph.put(start, new Node(start));
      graph.get(start).addEdge(end, p);
    }

    HashMap<String, Integer> count = new HashMap<String, Integer>();
    for(int i = 0; i < steps; i++) {
      s = graph.get(s).transition();
      count.put(s, count.getOrDefault(s, 0) + 1);
    }

    return count;
  }
}

class Node {
  String val;
  List<String> next;
  List<double[]> prob; // exclusive of end -> [0,.25] means that .25 counts as a diff prob for another state
  double p = 0.00;

  public Node(String val) {
    this.val = val;
    next = new ArrayList<String>();
    prob = new ArrayList<double[]>();
  }

  public void addEdge(String node, Double pr) {
    if(p >= 1.00) {
      System.out.println("error");
      return;
    }
    next.add(node);
    prob.add(new double[] {p, p + pr});
    p += pr;
  }

  /**
   * Get next state. Assuming next states are well defined i.e. probabilites sum to 1
   * Can do linear search for O(n) through list of nodes
   * Alternatively, we can perform a binary search using the ranges of probabilities
   * since they are generated in a sorted order.
   */
  public String transition() {
    Double probability = Math.random();
    int l = 0, r = prob.size() - 1;
    while(l <= r) {
      int mid = l + (r - l) / 2;
      double p1 = prob.get(mid)[0], p2 = prob.get(mid)[1];
      if(p1 <= probability && probability < p2)
        return next.get(mid);
      else if(probability >= p2)
        l = mid + 1;
      else
        r = mid - 1;
    }
    return null;
  }
}
