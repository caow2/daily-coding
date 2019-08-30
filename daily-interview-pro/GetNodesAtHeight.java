import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/*
 * Given a binary tree, return all values given a certain height h.
 *     1
 *    / \
 *   2   3
 *  / \   \
 * 4   5   7
 */
public class GetNodesAtHeight {
  public static void main(String[] args) {
    Node root = new Node(1);
    root.left = new Node(2);
    root.left.left = new Node(4);
    root.left.right = new Node(5);
    root.right = new Node(3);
    root.right.right = new Node(7);
    int height = 3;
    System.out.println(getNodesBFS(root, height)); // [4,5,7]
    System.out.println(getNodesDFS(root, height));
    System.out.println(getNodesDFSIterative(root, height));

    height = 4;
    System.out.println(getNodesBFS(root, height)); // []
    System.out.println(getNodesDFS(root, height));
    System.out.println(getNodesDFSIterative(root, height));

    height = 2;
    System.out.println(getNodesBFS(root, height)); // [2,3]
    System.out.println(getNodesDFS(root, height));
    System.out.println(getNodesDFSIterative(root, height));

    root.right.right.left = new Node(14);
    height = 4;
    System.out.println(getNodesBFS(root, height)); // [14]
    System.out.println(getNodesDFS(root, height));
    System.out.println(getNodesDFSIterative(root, height));
  }

  public static List<Node> getNodesBFS(Node root, int height) {
    LinkedList<Node> q = new LinkedList<Node>();
    if(root == null || height == 0)
      return q;

    q.offer(root);
    int h = 1;
    while(!q.isEmpty() && h < height) {
      int size = q.size();
      for(int i = 0; i < size; i++) {
        Node n = q.poll();
        if(n.left != null)
          q.offer(n.left);
        if(n.right != null)
          q.offer(n.right);
      }
      h++;
    }

    return q;
  }

  public static List<Node> getNodesDFS(Node root, int height) {
    List<Node> l = new LinkedList<Node>();
    dfs(root, l, 1, height);
    return l;
  }

  private static void dfs(Node root, List<Node> l, int currentHeight, int targetHeight) {
    if(root == null || currentHeight > targetHeight)
      return;
    if(currentHeight == targetHeight)
      l.add(root);
    dfs(root.left, l, currentHeight + 1, targetHeight);
    dfs(root.right, l, currentHeight + 1, targetHeight);
  }

  public static List<Node> getNodesDFSIterative(Node root, int height) {
    List<Node> l = new LinkedList<Node>();
    if(root == null || height == 0)
      return l;

    Stack<Pair<Node, Integer>> st = new Stack<Pair<Node, Integer>>();
    st.push(new Pair<Node, Integer>(root, 1));
    while(!st.isEmpty()) {
      Pair<Node, Integer> e = st.pop();
      Node n = e.getKey();
      int h = e.getValue();
      if(h == height)
        l.add(n);
      else if (h < height) {
        if(n.right != null)
          st.push(new Pair<Node, Integer>(n.right, h + 1));
        if(n.left != null)
          st.push(new Pair<Node, Integer>(n.left, h + 1));
      }
    }

    return l;
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

  public K getKey() {
    return key;
  }

  public V getValue() {
    return value;
  }
}
