/*
 * You are given a string of parenthesis.
 * Return the minimum number of parenthesis that would need to be removed in
 * order to make the string valid.
 * "Valid" means that each open parenthesis has a matching closed parenthesis.
 * Example:
 * "()())()"  1
 * ")("       2
 */
public class MinimumRemovalParentheses {
  public static void main(String[] args) {
    String s = "()())()";
    System.out.println(removeParentheses(s)); // 1

    s = ")(";
    System.out.println(removeParentheses(s)); // 2

    s = "";
    System.out.println(removeParentheses(s)); // 0

    s = "(()()";
    System.out.println(removeParentheses(s)); // 1

    s = "(()())";
    System.out.println(removeParentheses(s)); // 0
  }

  /**
   * Similar to using a Stack, we can keep 2 counters as we process the String:
   * 1. Track the number of open parentheses
   * 2. Track the number of errors when processing closed parentheses
   *    (i.e. encountering closed parentheses when open counter is 0).
   * The number of parentheses to remove should be number of
   * errors + number of open parentheses by the end.
   * O(n) time, O(1) space
   */
  public static int removeParentheses(String s) {
    int open = 0, error = 0;
    for(char c : s.toCharArray()) {
      if(c == '(')
        open++;
      else {
        if(open > 0)
          open--;
        else
          error++;
      }
    }
    return open + error;
  }
}
