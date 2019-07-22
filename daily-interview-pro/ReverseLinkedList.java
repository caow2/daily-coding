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
