import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

/**
 * Given the root of a binary search tree, and a target K, return two nodes in the tree whose sum equals K.
 *
 * For example, given the following tree and K of 20
 *
 *    10
 *  /   \
 * 5    15
 *     /  \
 *   11    15
 * Return the nodes 5 and 15.
 */
public class Problem125 {
  public static void main(String[] args) {
    // Build tree from the example above
    TreeNode root = new TreeNode(10);
    root.left = new TreeNode(5);
    root.right = new TreeNode(15);
    root.right.left = new TreeNode(11);
    root.right.right = new TreeNode(15);

    System.out.println(Arrays.toString(findTwo(root, 20)));
    System.out.println(Arrays.toString(findTwo(root, 15)));
    System.out.println(Arrays.toString(findTwo(root, 21)));
    System.out.println(Arrays.toString(findTwo(root, 20)));
    System.out.println(Arrays.toString(findTwo(root, 25)));

    System.out.println(Arrays.toString(findTwo(root, 0)));
    System.out.println(Arrays.toString(findTwo(root, -20)));
  }

  /**
   * Brute force would be to try every 2 nodes. However, we know a BST is ordered
   * in that the left child is always smaller than the root, etc.
   *
   * Imagine if we got a sorted array instead of a BST:
   * [5,10,11,15,15]
   * To find 2 numbers that sum up to k, we can start from each end of the array
   * and propagate down to the middle.
   * Increment the left pointer if the sum is too small.
   * Decrement the right pointer if the sum is too large.
   * We can construct the sorted array from the BST to use this approach.
   * O(n) time, O(n) space.
   */
  public static TreeNode[] findTwo(TreeNode root, int k) {
      List<TreeNode> l = new ArrayList<TreeNode>();
      inorderTraversal(root, l);
      int left = 0, right = l.size() - 1;
      while(left <= right) {
        int sum = l.get(left).val + l.get(right).val;
        if(sum == k)
          return new TreeNode[] { l.get(left), l.get(right) };
        else if (sum < k)
          left++;
        else
          right--;
      }
      return new TreeNode[] { null, null }; //couldn't find a pair that worked
  }

  private static void inorderTraversal(TreeNode root, List<TreeNode> l) {
    if(root == null)
      return;
    inorderTraversal(root.left, l);
    l.add(root);
    inorderTraversal(root.right, l);
  }
}

class TreeNode {
  int val;
  TreeNode left, right;

  public TreeNode(int val) {
    this.val = val;
  }

  public String toString() {
    return val + "";
  }
}
