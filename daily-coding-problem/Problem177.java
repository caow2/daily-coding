import java.util.Stack;
/*
 * Given a linked list and a positive integer k, rotate the list to the right by k places.
 * For example, given the linked list 7 -> 7 -> 3 -> 5 and k = 2, it should become 3 -> 5 -> 7 -> 7.
 *
 * Given the linked list 1 -> 2 -> 3 -> 4 -> 5 and k = 3, it should become 3 -> 4 -> 5 -> 1 -> 2.
 */
public class Problem177 {
  public static void main(String[] args) {
    Node head = new Node(1);
    head.next = new Node(2);
    head.next.next = new Node(3);
    head.next.next.next = new Node(4);

    head = rotate(head, 2);
    System.out.println(head); // 3 -> 4 -> 1 -> 2

    head = rotate(head, 2);
    System.out.println(head); // 1 -> 2 -> 3 -> 4

    head = rotate(head, 5);
    System.out.println(head); // 4 -> 1 -> 2-> 3
  }

  public static Node rotate(Node head, int k) {
    int size = 0;
    Stack<Node> st = new Stack<Node>();
    Node temp = head;
    while(temp != null) {
      size++;
      st.push(temp);
      temp = temp.next;
    }

    if(k >= size)
      k %= size;

    while(k > 0) {
      Node newHead = st.pop();
      newHead.next = head;
      head = newHead;
      if(! st.isEmpty())
        st.peek().next = null;
      k--;
    }
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
    return this.val + (next == null ? "" : " -> " + next.toString());
  }
}
