/*
 * You are given a singly linked list and an integer k.
 * Return the linked list, removing the k-th last element from the list.
 *
 * Try to do it in a single pass and using constant space.
 */
public class RemoveKth {
  public static void main(String[] args) {
    Node n = new Node(0);
    n.next = new Node(1);
    n.next.next = new Node(2);
    n.next.next.next = new Node(3);

    System.out.println(n); // 0 -> 1 -> 2 -> 3
    n = removeKthToLastNode(n,3);
    System.out.println(n); // 0 -> 2 -> 3
    n = removeKthToLastNode(n,1);
    System.out.println(n); // 0 -> 2
    n = removeKthToLastNode(n, 0);
    System.out.println(n); // 0 -> 2
  }

  /**
   * This can be easily done in 2 passes; one pass to count the size of the list,
   * and a second pass to traverse size - k nodes.
   * Or recursively / using a stack, remove the kth node.
   *
   * If we attempt a single pass, we can try to map each node to an index, but
   * we don't really need all those mappings: we just need a reference to the
   * kth + 1 node.
   * As we traverse through the list, maintain a pointer to the kth + 1 node
   * behind the current element. Then when we reach the end of the list, we can find
   * the element we're supposed to remove.
   * O(n) time, O(1) space.
   */
   public static Node removeKthToLastNode(Node head, int k) {
     if(k == 0)
      return head; // nothing to remove
      
     int size = 0;
     Node kthPrevious = new Node(-1), temp = head;
     kthPrevious.next = head;
     while(temp != null) {
       size++;
       if(size > k) {
         kthPrevious = kthPrevious.next;
       }
       temp = temp.next;
     }

     // For k > size, we can't remove anything
     if(k <= size)
        kthPrevious.next = kthPrevious.next.next;
     if (size == k) // remove the current head of the list
        head = head.next;
     return head;
   }

}

class Node {
  int val;
  Node next;

  public Node(int val) {
    this.val = val;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder( val + " -> " + (next == null ? "null" : next.toString()));
    return sb.toString();
  }
}
