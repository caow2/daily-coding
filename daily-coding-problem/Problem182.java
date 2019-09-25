import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
/*
 * A graph is minimally-connected if it is connected and there is no edge that
 * can be removed while still leaving the graph connected.
 * For example, any binary tree is minimally-connected.
 * Given an undirected graph, check if the graph is minimally-connected.
 * You can choose to represent the graph as either an adjacency matrix or adjacency list.
 */
public class Problem182 {
  public static void main(String[] args) {
    HashMap<Integer, List<Integer>> graph = new HashMap<>();
    for(int i = 0; i <= 4; i++) {
      graph.put(i, new ArrayList<Integer>());
    }

    graph.get(0).add(1);
    graph.get(1).add(2);
    graph.get(3).add(4);
    System.out.println(mConnected(graph)); // T

    graph.get(4).add(0);
    System.out.println(mConnected(graph)); // F

    graph.get(4).remove(0);
    graph.put(5, new ArrayList<Integer>());
    System.out.println(mConnected(graph)); // F

    graph.get(3).add(5);
    System.out.println(mConnected(graph)); // T
  }

  /*
   * We are given an undirected graph and want to see if it's minimally connected.
   * Another way to approach this may be to start with disjoint sets and process
   * all the edges of the minally connected graph.
   * If, at any point, we encounter a cycle, then the given graph cannot be minally connected.
   * Likewise, if at the end of processing all edges, the number of components is greater than 1,
   * the graph cannot be minimally connected.
   *
   * We can use the union-find data structure to track connected components.
   * O(Elog(V)) for E edges and V vertices if we implement UF with rank and path compression.
   * For E edges, there are up to E finds
   */
   public static boolean mConnected(HashMap<Integer, List<Integer>> graph) {
     UnionFind uf = new UnionFind(graph.size());

     // process each edge (i, j)
     for(Integer i : graph.keySet()) {
       for(Integer j : graph.get(i)) {
         if(uf.connected(i, j))
          return false;
         uf.union(i, j);
       }
     }

     return uf.numComponents == 1;
   }
}

class UnionFind {
  int[] parents;
  int[] rank;
  int numComponents;

  public UnionFind(int size) {
    parents = new int[size + 1];
    rank = new int[size + 1];
    numComponents = size - 1; // 0 is a stub element

    for(int i = 0; i < parents.length; i++) {
      parents[i] = i; // each component is its own parent initially
    }
  }

  public int find(int x) {
    if(parents[x] == x)
      return x; // found root
    parents[x] = find(parents[x]); // path compression
    return parents[x];
  }

  public void union(int a, int b) {
    int parentA = find(a), parentB = find(b);

    // We want to merge smaller ranked tree into larger ranked tree
    // Or if they are the same, just pick one and increment the rank
    if(rank[parentA] < rank[parentB]) {
      int temp = parentB;
      parentB = parentA;
      parentA = parentB;
    }
    else if(rank[parentA] == rank[parentB])
      rank[parentA]++;

    // Merge parentB into parentA
    parents[parentB] = parentA;
    numComponents--;
  }

  public boolean connected(int a, int b) {
    return find(a) == find(b);
  }
}
