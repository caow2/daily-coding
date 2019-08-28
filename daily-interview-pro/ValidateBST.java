/**
 * You are given the root of a binary search tree.
 * Return true if it is a valid binary search tree, and false otherwise.
 * Recall that a binary search tree has the property that all values in the left
 * subtree are less than or equal to the root, and all values in the right subtree
 * are greater than or equal to the root.
 *     5
 *    / \
 *   3   7
 *  / \ /
 * 1  4 6
 */
public class ValidateBST {
  public static void main(String[] args) {
    Node root = new Node(5);
    root.left = new Node(3);
    root.left.left = new Node(1);
    root.left.right = new Node(4);
    root.right = new Node(7);
    root.right.left = new Node(6);

    System.out.println(isBST(root));

    root.left.left.left = new Node(2);
    System.out.println(isBST(root));

    root = null;
    System.out.println(isBST(root));

    root = new Node(1);
    System.out.println(isBST(root));
  }

  public static boolean isBST(Node root) {
    return isBSTHelper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  private static boolean isBSTHelper(Node root, int leftBound, int rightBound) {
    if(root == null)
      return true;
    if(root.val > rightBound || root.val < leftBound)
      return false;
    return isBSTHelper(root.left, leftBound, root.val) &&
            isBSTHelper(root.right, root.val, rightBound);
  }
}

class Node {
  int val;
  Node left, right;

  public Node(int val) {
    this.val = val;
  }
}
