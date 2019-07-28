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
  public void invert(Node root) {
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
  public void invertIterative(Node root) {
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
}
