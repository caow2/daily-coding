import java.util.Stack;
/*
 * A unival tree is a tree where all the nodes have the same value.
 * Given a binary tree, return the number of unival subtrees in the tree.
 * For example, the following tree should return 5:
 *   0
 *  / \
 * 1   0
 *    / \
 *   1   0
 *  / \
 * 1   1
 *
 * The 5 trees are:
 * - The three single '1' leaf nodes. (+3)
 * - The single '0' leaf node. (+1)
 * - The [1, 1, 1] tree at the bottom. (+1)
 */
public class UnivalTree {
  static int uTrees = 0;

  public static void main(String[] args) {
    Node root = new Node(0);
    root.left = new Node(1);
    root.right = new Node(0);
    root.right.left = new Node(1);
    root.right.left.left = new Node(1);
    root.right.left.right = new Node(1);
    root.right.right = new Node(0);

    System.out.println(univalTrees(root));
    System.out.println(Math.abs(univalTreesNoStaticVar(root)));
  }

  /**
   * Base case: a leaf node is gauranteed to be a unival tree.
   * For any subtree, it's a unival tree if the left subtree is a unival tree
   * and the right subtree is a unival tree and that values are the same.
   * O(n) time, O(h) space where h is height of tree for recursion.
   */
  public static int univalTrees(Node root) {
    dfs(root);
    return uTrees;
  }

  private static boolean dfs(Node root) {
    if(root == null)
      return true;
    boolean uTree = (dfs(root.left) && dfs(root.right) &&
      (root.left == null ? root.val : root.left.val) == root.val &&
      (root.right == null ? root.val : root.right.val) == root.val);
    if(uTree)
      uTrees++;
    return uTree;
  }

  /**
  * Works under assumption of no negative numbers since we basically use
  * negatives as an indicator of whether a subtree is unival
  */
  public static int univalTreesNoStaticVar(Node root) {
    if(root == null)
      return 0;
    int left = univalTreesNoStaticVar(root.left);
    int right = univalTreesNoStaticVar(root.right);
    boolean uTree = left > -1 && right > -1 &&
                    (root.left == null ? root.val : root.left.val) == root.val &&
                    (root.right == null ? root.val : root.right.val) == root.val;
    int numTrees = Math.abs(left) + Math.abs(right);
    if(uTree)
      return numTrees + 1;
    return -1 * numTrees;
  }
}

class Node {
  int val;
  Node left, right;

  public Node(int val) {
    this.val = val;
  }
}
