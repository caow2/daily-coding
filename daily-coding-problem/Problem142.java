import java.util.Stack;
/* You're given a string consisting solely of (, ), and *. * can represent either a
 * (, ), or an empty string. Determine whether the parentheses are balanced.
 *
 * For example, (()* and (*) are balanced. )*( is not balanced.
 */
public class Problem142 {
  public static void main(String[] args) {
    String s = new String("()");
    System.out.println(isBalanced(s)); // T

    s = new String(")(");
    System.out.println(isBalanced(s)); // F

    s = new String("(*)");
    System.out.println(isBalanced(s)); // T

    s = new String(")*(");
    System.out.println(isBalanced(s)); // F

    s = new String("*");
    System.out.println(isBalanced(s)); // T

    s = new String("(*");
    System.out.println(isBalanced(s)); // T

    s = new String("*)");
    System.out.println(isBalanced(s)); // T

    s = new String("**");
    System.out.println(isBalanced(s)); // T

    s = new String("*)(*)");
    System.out.println(isBalanced(s)); // T
  }

  /**
   * If we consider a simpler version of the problem, such as checking if
   * a String without wildcards is balanced, we can use a Stack to track
   * the last character that needs to be balanced.
   *
   * However, now that we are considering wildcards, each wildcard has 3 possible
   * states: '(', '', and ')'.
   *                                                '(' -- ')'      --> '(()'
   * For the String '(*)',                          /
   * We can view this as a recursion tree: '(' -- '*' -- '' -- ')'  --> '()'
   *                                                \
   *                                                ')' -- ')'      --> '())'
   *
   * If any one of those possibilities is balanced, then we have a balanced String.
   * Otherwise, we keep backtracking until we've exhausted all possibilities.
   *
   * Complexity analysis: O((n*3^w) + n) Time:
   *                      - w is number of wildcards, n is length of String
   *                      - For a String full of wildcards, (3^w) >> n
   *                      - For a String with no wildcards, n >> (3^w)
   *                      O(n) Space:
   *                      - We only keep track of one path down the recursion tree at any point
   */
  public static boolean isBalanced(String s) {
    return isBalancedHelper(s, 0, new Stack<Character>());
  }

  private static boolean isBalancedHelper(String s, int index, Stack<Character> st) {
    if(st == null)
      return false; // for when we don't need to check wildcard
    if(index >= s.length())
      return st.isEmpty();

    Stack<Character> a = null, b = null; // in case of wildcard chars
    char c = s.charAt(index);
    if(c == '(')
      st.push(c);
    else if (c == ')') {
      if(st.isEmpty())
        return false;
      st.pop();
    }
    else {
      // '' is the same as using original stack
      // Make copies of st because there's not gaurantee on st having same state after
      // n * 3^2 because in a String of wildcards, we keep making clones of st
      a = (Stack<Character>) st.clone();
      a.push('(');
      b = (Stack<Character>) st.clone();
      if(! b.isEmpty())
        b.pop();
    }
    return isBalancedHelper(s, index + 1, st) ||
           isBalancedHelper(s, index + 1, a)  ||
           isBalancedHelper(s, index + 1, b);
  }
}
