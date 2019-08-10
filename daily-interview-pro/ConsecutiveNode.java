import java.util.HashMap;
/* Given a linked list of integers, remove all consecutive nodes that sum up to 0.
 *
 * Example:
 * Input: 10 -> 5 -> -3 -> -3 -> 1 -> 4 -> -4
 * Output: 10
 *
 * The consecutive nodes 5 -> -3 -> -3 -> 1 sums up to 0 so that sequence should be removed.
 * 4 -> -4 also sums up to 0 too so that sequence should also be removed.
 */
public class ConsecutiveNode {
  public static void main(String[] args) {
    Node head = new Node(10);
    head.next = new Node(5);
    head.next.next = new Node(-3);
    head.next.next.next = new Node(-3);
    head.next.next.next.next = new Node(1);
    head.next.next.next.next.next = new Node(4);
    head.next.next.next.next.next.next = new Node(-4);
    head = removeConsecutiveSum0(head);
    System.out.println(head); //10

    head = new Node(10);
    head.next = new Node(0);
    head.next.next = new Node(-10);
    head.next.next.next = new Node(0);
    head.next.next.next.next = new Node(1);
    head = removeConsecutiveSum0(head);
    System.out.println(head); // 1

    head = new Node(10);
    head.next = new Node(0);
    head.next.next = new Node(-10);
    head = removeConsecutiveSum0(head);
    System.out.println(head); //null

  }

  /**
   * A general idea is to try every possible sublist and if the sum of any sublist is
   * 0, we remove that.
   * The runtime of this is O(n^2) since if a list has no consecutive nodes that sum to 0,
   * we still process all sublists.
   * The removal of sublists is amortized constant time because we only perform the operation
   * when we get a sublist sum of 0. Even afterwards, we don't process any of those nodes again.
   *
   * We can do better in terms of runtime. If we keep a running sum of the list:
   * [10, 15, 12, 9, 10, 14, 10]
   * we see that whenever we see a sum that's been previously seen, we must have had a
   * consecutive sum of 0 between the first time the sum 10 was seen to the current node.
   *
   * However, we need to perform another operation to forget sums whose nodes have been deleted
   * because if we see the following LL and sums:
   * 10 -> 5 -> -3 -> -3 -> 1 -> 5      Adjusted LL : 10 -> 5
   * [10, 15, 12, 9, 10, 15]            Adjusted sumList : [10, 15]
   * The second 15 doesn't have consecutive nodes that sum to 0 since 5 -> -3 -> -3 -> 5
   * should already be removed.
   * Consider also that the head of the original LL could be part of a consecutive sum,
   * so this needs to be adjusted. We can keep a temporary node that points to the new head.
   */
  public static Node removeConsecutiveSum0(Node head) {
    if(head == null)
      return head;
    HashMap<Integer, Node> map = new HashMap<Integer, Node>(); //map a sum to a node
    int sum = 0;
    Node tHead = new Node(0);
    tHead.next = head; // temporary node always points to the head of the list
    map.put(0, tHead);

    while(head != null) {
      sum += head.val;
      if(map.containsKey(sum)) {
        removeSums(map, sum, head); // remove all nodes between node for the sum and head
        map.get(sum).next = head.next;
      }
      else
        map.put(sum, head);
      head = head.next;
    }
    return tHead.next;
  }

  private static void removeSums(HashMap<Integer, Node> mapSum, int sum, Node head) {
    // we don't want to remove the node at sum - just everything in between it and head
    Node temp = mapSum.get(sum).next;
    while(temp != head) { //head should never be null based on the calling function
      sum += temp.val;
      mapSum.remove(sum);
      temp = temp.next;
    }
  }
}

class Node {
  int val;
  Node next;

  public Node(int val) {
    this.val = val;
  }

  public int hashCode() {
    return val; //simple hashCode for hashing. Same value nodes map to same thing.
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(val + " -> " + (next == null ? "null" : next.toString()));
    return sb.toString();
  }
}
