/*
 * Given a singly-linked list, reverse the list.
 * This can be done iteratively or recursively. Can you get both solutions?
 *
 * Example:
 * Input: 4 -> 3 -> 2 -> 1 -> 0 -> NULL
 * Output: 0 -> 1 -> 2 -> 3 -> 4 -> NULL
 */
public class ReverseLinkedList {
  public static void main(String[] args) {
    Node head = new Node(0), temp = head;
    for(int i = 1; i <= 5; i++) {
      temp.next = new Node(i);
      temp = temp.next;
    }
    System.out.println(head);
    //System.out.println(reverse(head));
    System.out.println(reverseIterative(head));
  }

  /**
   * Keep track of the previous node for each node in order to reorder references.
   * O(n) time, O(n) space.
   */
  public static Node reverse(Node head) {
    return recursiveReverse(head, null);
  }

  public static Node recursiveReverse(Node head, Node prev) {
    if(head == null)
      return prev; // new head
    Node newHead = recursiveReverse(head.next, head);
    head.next = prev;
    return newHead;
  }

  /*
   * Or directly reorder pointers in a while loop. O(n) time, O(1) space.
   *
   * Can also be done with a Stack to imitate the recursive calls.
   * Logic is the same: Make the node at top of Stack point to the next Stack
   * element. The bottom element in the Stack's next is null.
   * The new head is the very first element that was popped. O(n) time, O(n) space.
   */
  public static Node reverseIterative(Node head) {
    Node prev = null;
    while(head != null) {
      Node next = head.next;
      head.next = prev;
      prev = head;
      head = next;
    }
    return prev;
  }
}

class Node {
  Node next;
  int val;

  public Node(int val) {
    this.val = val;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder("[");
    sb.append(val);
    Node temp = this;

    while(temp.next != null) {
      sb.append(",");
      sb.append(temp.next.val);
      temp = temp.next;
    }
    sb.append("]");
    return sb.toString();
  }
}
