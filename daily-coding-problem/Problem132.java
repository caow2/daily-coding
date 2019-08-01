import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;

/*
 * Design and implement a HitCounter class that keeps track of requests (or hits).
 * It should support the following operations:
 *
 * record(timestamp): records a hit that happened at timestamp
 * total(): returns the total number of hits recorded
 * range(lower, upper): returns the number of hits that occurred between timestamps lower and upper (inclusive)
 *
 * Follow-up: What if our system has limited memory?
 *
 * In a memory efficient implementation, we could store the data onto disk and
 * only keep a subset of it in memory (The minimal range needed).
 * Alternatively, if we don't need much granularity in querying, we could
 * partition timestamps into groups.
 */
public class Problem132 {
  public static void main(String[] args) {
    LocalDateTime t1 = LocalDateTime.of(2019, 7, 31, 0, 0);
    LocalDateTime t2 = LocalDateTime.of(2019, 8, 1, 0, 0);
    LocalDateTime t3 = LocalDateTime.of(2019, 8, 2, 0, 0);

    //ListHitCounter l = new ListHitCounter();
    BSTHitCounter l = new BSTHitCounter();
    l.record(t1);
    System.out.println(l.total()); // 1
    System.out.println(l.range(t2, t3)); // 0
    System.out.println(l.range(t1, t3)); // 1
    l.record(t2);
    System.out.println(l.total()); // 2
    System.out.println(l.range(t2, t3)); // 1
    System.out.println(l.range(t1, t3)); // 2
    System.out.println(l.range(t1, t2)); // 2
    l.record(t3);
    System.out.println(l.range(t2, t3)); // 2
    System.out.println(l.range(t1, t3)); // 3
    System.out.println(l.range(t1, t2)); // 2
    System.out.println(l.range(t1, LocalDateTime.of(2019, 7, 31, 1, 1))); // 1
  }
}

interface HitCounter {
  public void record(LocalDateTime t);
  public int total();
  public int range(LocalDateTime lower, LocalDateTime upper);
}

/*
 * We can maintain a sorted list to store the request / hits.
 * record(timestamp) would use binary search to find the index of the new timestamp and then add the element
 * total() just returns the size of the list
 * range(lower, upper) we binary search for largest timestamp that's <= lower and upper,
 * then perform a upper - lower + 1 (since inclusive)
 *
 * If there are duplicate timestamps, we can add an additional binary search to
 * find the last index of a given timestamp.
 * Assume unique timestamps for now.
 */
class ListHitCounter implements HitCounter {
  List<LocalDateTime> list = new ArrayList<LocalDateTime>();

  public ListHitCounter() {}

  // O(n) for adding operation since we are using ArrayList
  // BinarySearch is not optimal for generic LinkedLists
  public void record(LocalDateTime t) {
    int index = findIndexOfElementLE(t) + 1;
    list.add(index, t);
  }

  // O(1)
  public int total() {
    return list.size();
  }

  // O(log n)
  public int range(LocalDateTime lower, LocalDateTime upper) {
    int lowerIndex = findIndexOfElementLE(lower), upperIndex = findIndexOfElementLE(upper);
    if(list.get(lowerIndex).compareTo(lower) != 0)
      lowerIndex++; //For lowerIndex, we want the smallest index that's greater than or equal to lower
    return upperIndex - lowerIndex + 1;
  }

  /**
   * Returns the index of the largest element that's less than t, or equal to t.
   */
  private int findIndexOfElementLE(LocalDateTime t) {
    int index = -1;
    int left = 0, right = list.size() - 1;
    while(left <= right) {
      int mid = left + ((right - left) / 2);
      if(list.get(mid).compareTo(t) <= 0)  { // mid <= t
        index = Math.max(index, mid);
        left = mid + 1;
      }
      else
        right = mid - 1; // mid > t
    }
    return index;
  }
}

/*
 * However, we can probably reduce the O(n) bottleneck from using an ArrayList.
 * If we have a BST instead, record() will become a O(logn) operation, but range()
 * will become O(n).
 *
 * One way to optimize this is to store the number of nodes less than each node
 * in a node.
 * This is equivalent to storing the index of that node if the BST was converted to a list.
 * Then we can basically do the same thing as a ListHitCounter. O(log n) for range()
 *
 * For total(), we can store a variable to track the size of the BST.
 */
class BSTHitCounter implements HitCounter {
  int size;
  Node root;

  public BSTHitCounter() {
  }

  public void record(LocalDateTime t) {
    if(root == null)
      root = new Node(t, 0);
    else
      record(t, root);
    size++;
  }

  private void record(LocalDateTime t, Node n) {
    if(n.t.compareTo(t) > 0) {
      if(n.left == null)
        n.left = new Node(t, n.index);
      else
        record(t, n.left);
      n.index++; // always increment this index no matter what
    }
    else {
      if(n.right == null)
        n.right = new Node(t, n.index + 1);
      else
        record(t, n.right);
    }

  }

  public int total() {
    return size;
  }

  public int range(LocalDateTime lower, LocalDateTime upper) {
    Node low = findSmallestNodeGE(lower, root, new Node(null, -1));
    Node hi = findLargestNodeLE(upper, root, new Node(null, -1));
    if(low.index == -1 || hi.index == -1 || hi.index < low.index)
      return 0;
    return hi.index - low.index + 1;
  }

  // Find the smallest node greater than or equal to lower
  // Result tracks the current smallest
  // result is a stub Node for now
  private Node findSmallestNodeGE(LocalDateTime lower, Node n, Node result) {
    if(n == null)
      return result;
    if(n.t.compareTo(lower) < 0)
      return findSmallestNodeGE(lower, n.right, result);
    else {
      result = n; // We know result is <= n because of our search path
      return findSmallestNodeGE(lower, n.left, result);
    }
  }

  public String toString() {
    if(root == null)
      return "";
    StringBuilder sb = new StringBuilder();
    toString(root , sb);
    return sb.toString();
  }

  private void toString(Node n, StringBuilder sb) {
    if(n == null)
      return;
    toString(n.left, sb);
    sb.append("[" + n.toString() + "] ");
    toString(n.right, sb);
  }


  private Node findLargestNodeLE(LocalDateTime upper, Node n, Node result) {
    if(n == null)
      return result;
    if(n.t.compareTo(upper) > 0)
      return findLargestNodeLE(upper, n.left, result);
    else {
      result = n;
      return findLargestNodeLE(upper, n.right, result);
    }
  }
}

class Node {
  Node left, right;
  LocalDateTime t;
  int index;

  public Node(LocalDateTime t, int index) {
    this.t = t;
    this.index = index;
  }

  public int compareTo(Object o) {
    Node n = (Node) o;
    return t.compareTo(n.t);
  }

  public String toString() {
    return t.toString() + ", " + index;
  }
}
