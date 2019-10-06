import java.util.Queue;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.HashSet;

public class TopologicalSort {
  public static void main(String[] args) {
    HashMap<Integer, int[]> graph = new HashMap<>();
    graph.put(0, new int[]{2, 1});
    graph.put(1, new int[]{});
    graph.put(2, new int[]{1});

    System.out.println(isCycle(graph)); // 0 -> 2 -> 1
  }

  /**
   * Premise for top sort -> Find all vertices with 0 incoming edges
   * For each vertex, decreasing the # incoming edge of children by 1
   * If children's incoming edges are 0, we've processed all dependencies
   * ->add them to queue
   * Check at the end if the number of vertices processed is equal to size of graph
   */
  public static boolean isCycle(HashMap<Integer, int[]> graph) {
    HashMap<Integer, Integer> incoming = new HashMap<Integer, Integer>();
    for(Integer key : graph.keySet()) {
      incoming.put(key, 0);
      for(int neighbor : graph.get(key)) {
        incoming.put(neighbor, incoming.getOrDefault(neighbor, 0) + 1);
      }
    }

    Queue<Integer> q = new LinkedList<Integer>();
    for(Integer key : incoming.keySet()) {
      if(incoming.get(key) == 0)
        q.offer(key);
    }

    int processed = 0;
    HashSet<Integer> seen = new HashSet<Integer>();
    while(! q.isEmpty()) {
      int i = q.poll();
      seen.add(i);
      System.out.println(i);
      for(int neighbor : graph.get(i)) {
        incoming.put(neighbor, incoming.get(neighbor) - 1);
        if(incoming.get(neighbor) <= 0 && ! seen.add(neighbor))
          q.offer(neighbor);
      }
    }

    return processed == graph.size();
  }
}
