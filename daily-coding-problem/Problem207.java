import java.util.HashSet;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Arrays;

/*
 * Given an undirected graph G, check whether it is bipartite.
 * Recall that a graph is bipartite if its vertices can be divided into two
 * independent sets, U and V, such that no edge connects vertices of the same set.
 */
public class Problem207 {
  public static void main(String[] args) {
    Node a = new Node(), b = new Node(), c = new Node(), d = new Node(), e = new Node();
    a.connect(b);
    b.connect(c);
    c.connect(a);
    d.connect(e);
    // d.connect(a); this would make graph non bipartite
    HashSet<Node> graph = new HashSet<Node>();
    for(Node n : Arrays.asList(a,b,c,d,e)) {
      graph.add(n);
    }

    System.out.println(isBipartite(graph));
  }

  /**
   * Go thru graph and assign each unprocessed node a label.
   * If the graph is indeed bipartite we should only have assigned 2 labels.
   * Labels default to 0, indicating unprocessed node.
   * O(n) time where n is the number of nodes
   * O(n) space in worst case for our BFS if graph is complete
   */
  public static boolean isBipartite(HashSet<Node> graph) {
    int counter = 1;
    for(Node n : graph) {
      if(n.label == 0) {
        n.label = counter++;
        if(! validate(n))
          return false;
      }
    }
    return counter == 3; // bipartite indicates 2 labels given
  }

  // For each node, check the rest of the connected graph
  // and label all unlabeled nodes + check if each labeled node is same as n
  private static boolean validate(Node n) {
    int label = n.label;
    Queue<Node> q = new LinkedList<Node>();
    q.offer(n);
    while(! q.isEmpty()) {
      n = q.poll();
      if(n.label == 0)
        n.label = label;
      else if (n.label != label)
        return false;

      for(Node neighbor : n.neighbors) {
        if(neighbor.label != label) // not yet processed or part of different graph
          q.offer(neighbor);
      }
    }
    return true;
  }
}

class Node {
  int label;
  List<Node> neighbors;

  public Node() {
    label = 0;
    neighbors = new LinkedList<Node>();
  }

  public void connect(Node n) {
    this.neighbors.add(n);
    n.neighbors.add(this);
  }
}
