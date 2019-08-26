import java.util.LinkedList;
/*
 * You are given the root of a binary tree.
 * Return the deepest node (the furthest node from the root).
 *
 * Example:
 *     a
 *    / \
 *   b   c
 *  /
 * d
 * The deepest node in this tree is d at depth 3.
 */
public class DeepestNode {
  public static void main(String[] args) {
    Node root = new Node(0);
    root.left = new Node(1);
    root.right = new Node(2);
    root.left.left = new Node(3);
    root.right.left = new Node(4);
    root.right.left.left = new Node(5);

    System.out.println(deepest(root)); // 5
    System.out.println(deepestBFSVariant(root)); // 5
    System.out.println(deepestDFS(root));
  }

  /**
   * Use BFS to get to the last level, and return the last node from there.
   * O(V + E) ~ O(V) since E = V - 1
   */
  public static Node deepest(Node root) {
    if(root == null)
      return root;
    LinkedList<Node> q = new LinkedList<Node>();
    Node d = null;
    q.offer(root);

    while(! q.isEmpty()) {
      int size = q.size();
      for(int i = 0; i < size; i++) {
        d = q.poll();
        if(d.left != null)
          q.offer(d.left);
        if(d.right != null)
          q.offer(d.right);
      }
    }

    return d;
  }

  public static Node deepestBFSVariant(Node root) {
    if(root == null)
      return root;

    LinkedList<Node> q = new LinkedList<Node>();
    Node result = null;
    q.offer(root);
    q.offer(null);

    while(! q.isEmpty()) {
      Node d = q.poll();
      if(d == null && ! q.isEmpty())
        q.offer(null);
      else if (d != null) {
        result = d; // only update when not null
        if (d.left != null)
          q.offer(d.left);

        if(d.right != null)
          q.offer(d.right);
      }
    }

    return result;
  }

  public static Node deepestDFS(Node root) {
    return dfsHelper(root, 1).key;
  }

  private static Pair<Node, Integer> dfsHelper(Node root, int depth) {
    if(root == null)
      return new Pair(null, -1); // invalid depth for invalid node
    if(root.left == null && root.right == null)
      return new Pair(root, depth); // base case

    Pair<Node, Integer> left = dfsHelper(root.left, depth + 1);
    Pair<Node, Integer> right = dfsHelper(root.right, depth + 1);
    return (left.value > right.value ? left : right);
  }
}

class Node {
  int val;
  Node left, right;

  public Node(int val) {
    this.val = val;
  }

  public String toString() {
    return val + "";
  }
}

class Pair<K,V> {
  K key;
  V value;

  public Pair(K key, V value) {
    this.key = key;
    this.value = value;
  }
}
