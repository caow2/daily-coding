import java.util.Queue;
import java.util.LinkedList;

/*
 * You are given the root of a binary tree.
 * Find the level for the binary tree with the minimum sum, and return that value.
 * For instance, in the example below, the sums of the trees are 10,
 * 2 + 8 = 10, and 4 + 1 + 2 = 7. So, the answer here should be 7.
 *     10
 *    /  \
 *   2    8
 *  / \    \
 * 4   1    2
 */
public class LevelWithMinSum {
  public static void main(String[] args) {
    TreeNode root = new TreeNode(10);
    root.left = new TreeNode(2);
    root.left.left = new TreeNode(4);
    root.left.right = new TreeNode(1);
    root.right = new TreeNode(8);
    root.right.right = new TreeNode(2);

    System.out.println(minSum(root));
    System.out.println(minSum(null));

    root.right.right.left = new TreeNode(-100);
    System.out.println(minSum(root));
  }

  /**
   * BFS and keep track of sum per level.
   */
  public static int minSum(TreeNode root) {
    int minSum = Integer.MAX_VALUE;
    if(root == null)
      return 0;
    Queue<TreeNode> q = new LinkedList<TreeNode>();
    q.offer(root);
    while(! q.isEmpty()) {
      // System.out.println(q);
      int size = q.size(), sum = 0;
      for(int i = 0; i < size; i++) {
        TreeNode tn = q.poll();
        sum += tn.val;
        if(tn.left != null)
          q.offer(tn.left);
        if(tn.right != null)
          q.offer(tn.right);
      }
      minSum = Math.min(minSum, sum);
    }
    return minSum;
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
