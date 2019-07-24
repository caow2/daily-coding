import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Iterator;

/*
 * Given an undirected graph with at most D degrees (max # of edges connected to a node),
 * find a graph coloring using at most D + 1 colors.
 */
public class GraphColoring {
  public static void main(String[] args) {
    Map<Integer, GraphNode> graph = new HashMap<Integer, GraphNode>();

    for(int i = 0; i < 4; i++) {
      graph.put(i, new GraphNode(i));
    }

    // Build edges
    graph.get(0).neighbors.add(graph.get(1)); // 0-1
    graph.get(1).neighbors.add(graph.get(0));

    graph.get(0).neighbors.add(graph.get(2)); // 0-2
    graph.get(2).neighbors.add(graph.get(0));

    graph.get(1).neighbors.add(graph.get(2)); // 1-2
    graph.get(2).neighbors.add(graph.get(1));

    graph.get(1).neighbors.add(graph.get(3)); // 1-3
    graph.get(3).neighbors.add(graph.get(1));

    graph.get(2).neighbors.add(graph.get(3)); // 2-3
    graph.get(3).neighbors.add(graph.get(2));

    System.out.println(colorGraph(graph));
  }

  /**
   * Represent the graph as a map between a node's value and its GraphNode object.
   * If we didn't need an extra value for color,
   * can just represent as a Map<Integer, List<Integer>>.
   * Assume graph is labeled 0, 1, ... and all colors are initially 0.
   *
   * A brute force approach would try every possible color combination.
   * However, we can try to each node one at a time, and enforce that it has to
   * have a different color from its neighbors.
   *
   * Suppose we start at node 0 and run BFS on the graph. Color is denoted as a number
   * and is defaulted to 0.
   * For each of node 0's children, we make sure it has a different "color" from node 0,
   * and then offer the child to the queue.
   * For each node in the queue, we then ensure its children has a different "color" from
   * the parent.
   * i.e. increment color of a child if it's the same as its parent.
   *
   * Since for every node we visit, we enforce that its children has to have a different color
   * and that it never reuses any previous colors of its parents by incrementing.
   * (i.e. 0 -> 1 -> 2 -> 3 -> ...)
   * We use at most D + 1 colors because if a node has D edges and they all enforce 1 color,
   * then for this node we will pick the D + 1 th color.
   * O(N + E) time, O(N) space.
   */
  public static Map<Integer, GraphNode> colorGraph(Map<Integer, GraphNode> graph) {
    LinkedList<GraphNode> q = new LinkedList<GraphNode>();
    q.offer(graph.get(0));
    while(! q.isEmpty()) {
      GraphNode g = q.poll();
      if(g.visited) // A node could be added twice to the queue i.e. 1-2-3-1 relation
        continue;
      g.visited = true;
      for(GraphNode neighbor : g.neighbors) {
        if(neighbor.visited)
          continue;
        if(neighbor.color == g.color)
          neighbor.color++;
        q.offer(neighbor);
      }
    }
    return graph;
  }


}

/*
 * For simplicity, represent color as an int.
 */
class GraphNode {
  static final int DEFAULT_COLOR = 0;

  int val, color;
  boolean visited;
  List<GraphNode> neighbors;

  public GraphNode(int val) {
    this.val = val;
    this.color = DEFAULT_COLOR;
    visited = false;
    neighbors = new LinkedList<GraphNode>();
  }

  public String toString() {
    StringBuilder sb = new StringBuilder(" " + val + "<" + color + "> : [");
    Iterator<GraphNode> it = neighbors.iterator();
    while(it.hasNext()) {
      sb.append(" " + it.next().val);
      if(it.hasNext())
        sb.append(",");
    }
    sb.append(" ]");
    return sb.toString();
  }
}
