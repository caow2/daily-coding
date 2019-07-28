import java.util.LinkedList;
/*
 * You are given the root of a binary tree.
 * Invert the binary tree in place. That is, all left children should become right children,
 * and all right children should become left children.
 *
 * Example:
 *        a
 *       / \
 *      b   c
 *     / \  /
 *   d   e f
 *
 * The inverted version of this tree is as follows:
 *
 *     a
 *    / \
 *   c   b
 *   \  / \
 *   f e  d
 */
public class InvertBinaryTree {
  public static void main(String[] args) {
      Node root = new Node(0);
      root.left = new Node(1);
      root.right = new Node(2);
      root.left.left = new Node(3);
      root.left.right = new Node(4);
      root.right.left = new Node(5);

      System.out.println(root); // 0, 1, 3, 4, 2, 5
      //invert(root);
      invertIterative(root);
      System.out.println(root); // 0, 2, 5, 1, 4, 3
  }

  /**
   * The recursive DFS logic is fairly straightforward:
   * Base case: the root is null, so don't do anything
   * Otherwise: invert the left child
   *            invert the right child
   * Then swap the left and right child, which were already inverted
   *
   * O(n) time since its a binary tree.
   * O(h) where h is the height of the tree.
   * Note that h is O(n) if the tree is unbalanced.
   */
  public static void invert(Node root) {
    if(root == null)
      return;
    invert(root.left);
    invert(root.right);
    Node l = root.left;
    root.left = root.right;
    root.right = l;
  }

  /**
   * BFS solution with almost identical logic.
   * For each node we come across, we swap its children and
   * offer them to the queue if they aren't null.
   * By the end, every node should have had its children swapped.
   * O(n) time, O(n) space where n is the number of nodes in the binary tree.
   */
  public static void invertIterative(Node root) {
    LinkedList<Node> q = new LinkedList<Node>();
    q.offer(root);
    while(!q.isEmpty()) {
       Node n = q.poll();
       Node l = n.left;
       n.left = n.right;
       n.right = l;
       if(n.left != null)
        q.offer(n.left);
       if(n.right != null)
        q.offer(n.right);
    }
  }
}

/*
 * Binary Tree
 */
class Node {
  int val;
  Node left, right;

  public Node(int val) {
    this.val = val;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder("" + val);
    if(left != null)
      sb.append(" " + left.toString());
    if(right != null)
      sb.append(" " + right.toString());
    return sb.toString();
  }
}
