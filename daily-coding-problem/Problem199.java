/*
 * Given a string of parentheses, find the balanced string that can be produced
 * from it using the minimum number of insertions and deletions.
 * If there are multiple solutions, return any of them.
 *
 * For example, given "(()", you could return "(())". Given "))()(", you could return "()()()()".
 */
public class Problem199 {
  public static void main(String[] args) {
    String s = "(()";
    System.out.println(balance(s));

    s = "))()(";
    System.out.println(balance(s));

    s = "";
    System.out.println(balance(s));

    s = ")";
    System.out.println(balance(s));

    s = "(";
    System.out.println(balance(s));

    s = "()";
    System.out.println(balance(s));
  }

  /**
   * Let's first solve a simpler problem -> how to check if a String is balanced?
   * An easy way is to go through the String and keep a counter of open brackets.
   * When we encounter a close bracket -> try to pair it with an open bracket.
   * If there is none, that means we need to add an open bracket.
   * At the end, for each x open bracket, we need to add x closing brackets.
   *
   * The minimum number of insertions and deletions may not matter because
   * an insertion is one step and a deletion is one step, and we should
   * only perform one when we have a mismatch.
   */
  public static String balance(String s) {
    int open = 0;
    StringBuilder sb = new StringBuilder();
    for(int i = 0 ; i < s.length(); i++) {
      char c = s.charAt(i);
      if(c == '(')
        open++;
      else {
        if(open == 0)
          sb.append('(');
        else
          open--;
      }
      sb.append(c);
    }

    while(open-- > 0) {
      sb.append(")");
    }
    
    return sb.toString();
  }
}
