import java.util.HashMap;
import java.util.LinkedList;

/**
 * You are given the preorder and inorder traversals of a binary tree in the form of arrays.
 * Write a function that reconstructs the tree represented by these traversals.
 * Example:
 * Preorder: [a, b, d, e, c, f, g]
 * Inorder: [d, b, e, a, f, c, g]
 * The tree that should be constructed from these traversals is:
 *     a
 *    / \
 *   b   c
 *  / \ / \
 * d  e f  g
 */
public class ReconstructBinaryTree {
  public static void main(String[] args) {
    char[] preorder = new char[] {'a', 'b', 'd', 'e', 'c', 'f', 'g'};
    char[] inorder = new char[] {'d', 'b', 'e', 'a', 'f', 'c', 'g'};
    System.out.println(reconstruct(preorder, inorder)); // should print preorder
  }

  /**
   * Assuming no duplicates
   */
  public static Node reconstruct(char[] preorder, char[] inorder) {
    LinkedList<Character> q = new LinkedList<Character>();
    for(char c : preorder) {
      q.offer(c);
    }
    HashMap<Character, Integer> map = index(inorder);
    return reconstruct(q, map, 0, preorder.length - 1);
  }

  private static Node reconstruct(LinkedList<Character> q, HashMap<Character, Integer> map, int start, int end) {
    Node n = null;
    if(q.isEmpty())
      return n;
    Character c = q.peek();
    int index = map.get(c);
    if(index >= start && index <= end) {
      n = new Node(q.poll());
      n.left = reconstruct(q, map, start, index - 1);
      n.right = reconstruct(q, map, index + 1 , end);
    }
    return n;
  }

  private static HashMap<Character, Integer> index(char[] inorder) {
    HashMap<Character, Integer> map = new HashMap<Character, Integer>();
    for(int i = 0; i < inorder.length; i++) {
      map.put(inorder[i], i);
    }
    return map;
  }
}

class Node {
  char val;
  Node left, right;

  public Node(char val) {
    this.val = val;
  }

  // preorder
  public String toString() {
    return val + (left == null ? "" : " " + left.toString()) +
          (right == null ? "" : " " + right.toString());
  }
}
