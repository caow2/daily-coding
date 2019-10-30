import java.util.LinkedList;
import java.util.Queue;
/*
 * You are given a tree, and your job is to print it level-by-level with linebreaks.
 *      a
 *    /  \
 *   b    c
 *  / \  / \
 * d  e f  g
 * The output should be:
 * a
 * bc
 * defg
 */
public class LevelByLevelPrint {
  public static void main(String[] args) {
    TreeNode t = new TreeNode(1);
    t.left = new TreeNode(2);
    t.right = new TreeNode(3);
    t.left.left = new TreeNode(4);
    t.right.left = new TreeNode(5);
    t.right.right = new TreeNode(6);
    t.right.right.left = new TreeNode(7);

    levelPrint(t);
  }

  /**
   * Print using BFS.
   * O(n) time, O(n) space -> bounded by number of nodes in a level
   */
  public static void levelPrint(TreeNode root) {
    if(root == null)
      return;

    Queue<TreeNode> q = new LinkedList<TreeNode>();
    q.offer(root);
    while(! q.isEmpty()) { // process each level
      int size = q.size();
      for(int i = 0; i < size; i++) {
        TreeNode tn = q.poll();
        System.out.print(tn.val);

        if(tn.left != null)
          q.offer(tn.left);
        if(tn.right != null)
          q.offer(tn.right);
      }
      System.out.println();
    }
  }
}

class TreeNode {
  int val;
  TreeNode left, right;

  public TreeNode(int val) {
    this.val = val;
  }
}
