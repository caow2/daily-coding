/*
 * Given the head of a singly linked list, swap every two nodes and return its head.
 *
 * For example, given 1 -> 2 -> 3 -> 4, return 2 -> 1 -> 4 -> 3.
 */
public class Problem145 {
  public static void main(String[] args) {
    Node n = new Node(1);
    n.next = new Node(2);
    n.next.next = new Node(3);
    n.next.next.next = new Node(4);

    n = swapAdjacent(n);
    System.out.println(n);

    n = new Node(0);
    n = swapAdjacent(n);
    System.out.println(n);

    n = new Node(1);
    n.next = new Node(2);
    n.next.next = new Node(3);
    n = swapAdjacent(n);
    System.out.println(n);
  }

  /**
   * Straightforward swapping of pointers.
   * Keep reference to previous node to properly adjust the references.
   * O(n) time, O(1) space
   */
  public static Node swapAdjacent(Node head) {
    if(head == null || head.next == null)
      return head;
    Node temp = head, prev = null, newHead = head.next;
    while(temp != null && temp.next != null) {
      // head.next null means no swapping possible
      Node t = temp.next;
      temp.next = temp.next.next;
      t.next = temp;

      if(prev != null)
        prev.next = t;
      prev = temp;
      temp = temp.next;
    }
    return newHead;
  }
}

class Node {
  int val;
  Node next;

  public Node(int val) {
    this.val = val;
  }

  public String toString() {
    return val + " -> " + (next == null ? "null" : next.toString());
  }
}
