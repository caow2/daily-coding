/*
 * Given a binary tree where all nodes are either 0 or 1,
 * prune the tree so that subtrees containing all 0s are removed.
 *
 * For example, given the following tree:
 *
 *   0
 *  / \
 * 1   0
 *    / \
 *   1   0
 *  / \
 * 0   0
 * should be pruned to:
 *
 *   0
 *  / \
 * 1   0
 *    /
 *   1
 * We do not remove the tree at the root or its left child because it still has a 1 as a descendant.
 */
public class Problem146 {
  public static void main(String[] args) {
    Node root = new Node(0);
    root.left = new Node(1);
    root.right = new Node(0);
    root.right.left = new Node(1);
    root.right.left.left = new Node(0);
    root.right.left.right = new Node(0);
    root.right.right = new Node(0);

    // Strings are from in order traversal
    System.out.println(root); // 1 0 0 1 0 0 0
    root = prune(root);
    System.out.println(root); // 1 0 1 0
  }

  /**
   * Standard DFS problem with special conditions
   * O(n) time, O(h) space where h is height of tree
   */
  public static Node prune(Node root) {
    if(root == null)
      return null;
    root.left = prune(root.left);
    root.right = prune(root.right);
    if(root.val == 0 && root.left == null && root.right == null)
      return null;
    return root;
  }
}

class Node {
  int val;
  Node left, right;

  public Node(int val) {
    this.val = val;
  }

  // in order toString
  public String toString() {
    return (left == null ? "" : left.toString() + ", ") + val +
          (right == null? "" : ", " + right.toString());
  }
}
