import java.util.Stack;

/* There are n people lined up, and each have a height represented as an integer.
 * A murder has happened right in front of them, and only people who are taller than
 * everyone in front of them are able to see what has happened.
 * How many witnesses are there?
 *
 * Example:
 * Input: [3, 6, 3, 4, 1]
 * Output: 3
 * Explanation: Only [6, 4, 1] were able to see in front of them.
 * #
 * #
 * # #
 * ####
 * ####
 * #####
 * 36341                                 x (murder scene)
 */
public class Witness {
  public static void main(String[] args) {
    int[] line = new int[] {3,6,3,4,1};
    System.out.println(numWitness(line)); // 3

    line = new int[] {1,2,3,4,5};
    System.out.println(numWitness(line)); // 1

    line = new int[] {5,4,3,2,1};
    System.out.println(numWitness(line)); // 5

    line = new int[] {7,2,9,3,5};
    System.out.println(numWitness(line)); // 2
  }

  /**
   * A BF algorithm may be for each witness at line[i], try to see if there's
   * a taller witness to the right.
   * If there isn't, increment a counter to track # witnesses.
   *
   * However, we can see that we want to maintain a strict monotonic decreasing sequence
   * (elements can't be equal : a < b < c < ... < d )
   * We can use a Stack to track this decreasing sequence as we process from left to right.
   * If the current element line[i] is > top of the stack, pop from the stack.
   * Once top of stack is > current element, we can push.
   * O(n) time, O(n) space.
   */
  public static int numWitness(int[] line) {
    Stack<Integer> st = new Stack<Integer>();
    for(int i = 0; i < line.length; i++) {
      while(!st.isEmpty() && st.peek() <= line[i]) {
        st.pop();
      }
      st.push(line[i]);
    }
    return st.size();
  }
}
