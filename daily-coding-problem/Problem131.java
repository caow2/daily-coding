/*
 * Given the head to a singly linked list, where each node also has a “random”
 * pointer that points to anywhere in the linked list, deep clone the list.
 *
 * A trivial would involve using HashTables to store each Node and its copy.
 * Do this is O(1) space.
 */
public class Problem131 {
  public static void main(String[] args) {
    Node head = new Node(0);
    head.next = new Node(1);
    head.next.next = new Node(2);
    head.next.next.next = new Node(3);

    head.random = head.next.next;
    head.next.random = head;
    head.next.next.next.random = head.next;

    System.out.println(head); // Node val (random pointer val) -> ...

    Node copy = deepCopy(head);
    System.out.println(copy);
    System.out.println(copy == head); // false

  }

  /*
   * Since we can't use any alternative data structures, the only thing we
   * can really modify is the reference pointers of the nodes.
   *
   * We can perform a 3 pass approach to create the deep copy:
   * 1. Go through and copy each node. Make each node's next point to copy, and copy's next
   *    point to the original node.next.
   *    i.e. node -> copy -> node.next
   * 2. For each original node, assign the copy's random pointer to
   *    original.random.next, which should be pointing to the copy of original.random.
   * 3. Go through and fix the next pointers so they are pointing to the correct nodes for
   *    each list.
   *
   * On hindsight, we can perform all of the above using a HashTable in fewer iterations, but
   * we essentially do the same thing.
   * Note that we can't assign the copy's random pointer in pass 1 because a node
   * may be pointing forward in the list (and the copy of that node doesn't exist yet).
   * We also can't combine pass 2 and pass 3 because a node may be pointing backwards in the list,
   * so we can't adjust the next pointers because we need access to each node's copy.
   *
   * O(n) time, O(1) space.
   */
  public static Node deepCopy(Node head) {
    Node copy = null, temp = head;
    while(temp != null) {
      copy = new Node(temp.val);
      copy.next = temp.next;
      temp.next = copy;
      temp = copy.next;
    }

    copy = head.next;
    temp = head;
    while(temp != null) {
      // copy's random -> if temp's random is not null, get copy of that node.
      temp.next.random = (temp.random == null ? null : temp.random.next);
      temp = temp.next.next; // skip copy node
    }

    temp = head;
    //temp.next == is an edge case for end of copy list
    while(temp != null && temp.next != null) {
      Node c = temp.next; // reference to copy
      temp.next = temp.next.next; // point back to original next if temp's not a copy
      temp = c;
    }
    return copy;
  }
}

class Node {
  Node next, random;
  int val;

  public Node(int val) {
    this.val = val;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder("" + this.val);
    sb.append(" (" + (random == null ? null : random.val) + ")");
    if(next != null)
      sb.append(" -> " + next.toString());
    return sb.toString();
  }
}
