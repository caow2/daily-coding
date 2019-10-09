import java.util.HashMap;

/*
 * Given the root of a binary tree, find the most frequent subtree sum.
 * The subtree sum of a node is the sum of all values under a node, including the node itself.
 *
 * For example, given the following tree:
 *
 *   5
 *  / \
 * 2  -5
 * Return 2 as it occurs twice: once as the left leaf, and once as the sum of 2 + 5 - 5.
 */
public class Problem196 {
  public static void main(String[] args) {
    TreeNode root = new TreeNode(5);
    root.left = new TreeNode(2);
    root.right = new TreeNode(-5);
    System.out.println(mostFrequentSum(root)); // 2

    root = new TreeNode(5);
    root.left = new TreeNode(2);
    root.right = new TreeNode(-5);
    root.left.left = new TreeNode(-7);
    System.out.println(mostFrequentSum(root)); // -5

  }

  public static int mostFrequentSum(TreeNode root) {
    HashMap<Integer, Integer> count = new HashMap<>();
    dfs(root, count);

    int max = 0, ctr = -1;
    for(int key : count.keySet()) {
      if(count.get(key) > ctr) {
        max = key;
        ctr = count.get(key);
      }
    }

    return max;
  }

  public static void dfs(TreeNode root, HashMap<Integer, Integer> count) {
    if(root == null)
      return;
    dfs(root.left, count);
    dfs(root.right, count);
    root.val += (root.left == null ? 0 : root.left.val) +
                (root.right == null ? 0 : root.right.val);
    count.put(root.val, count.getOrDefault(root.val, 0) + 1);
  }
}

class TreeNode {
  TreeNode left, right;
  int val;

  public TreeNode(int val) {
    this.val = val;
  }
}
