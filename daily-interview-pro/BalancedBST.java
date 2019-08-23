/*
 * Given a sorted list of numbers, change it into a balanced binary search tree.
 * You can assume there will be no duplicate numbers in the list.
 */
public class BalancedBST {
  public static void main(String[] args) {
    int[] arr = new int[] { 1,2,3,4,5 };
    System.out.println(listToBST(arr));

    arr = new int[] { -1, 5, 9, 10, 11, 13 };
    System.out.println(listToBST(arr));
  }

  //O(n) time, O(logn) space
  public static Node listToBST(int[] arr) {
    return helper(arr, 0, arr.length - 1);
  }

  private static Node helper(int[] arr, int start, int end) {
    if(start > end)
      return null;
    if(start == end)
      return new Node(arr[start]);
    int mid = start + ((end - start) / 2);
    Node root = new Node(arr[mid]);
    root.left = helper(arr, start, mid - 1);
    root.right = helper(arr, mid + 1, end);
    return root;
  }
}

class Node {
  int val;
  Node left, right;

  public Node(int val) {
    this.val = val;
  }

  public String toString() {
    return (left == null ? "" : left.toString()) + " " + val + " "
          + (right == null ? "" : right.toString());
  }
}
