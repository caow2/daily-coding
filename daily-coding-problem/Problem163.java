import java.util.Stack;

/*
 * Given an arithmetic expression in Reverse Polish Notation, write a program to evaluate it.
 * The expression is given as a list of numbers and operands.
 * For example: [5, 3, '+'] should return 5 + 3 = 8.
 *
 * For example, [15, 7, 1, 1, '+', '-', '/', 3, '*', 2, 1, 1, '+', '+', '-']
 * should return 5, since it is equivalent to ((15 / (7 - (1 + 1))) * 3) - (2 + (1 + 1)) = 5.
 * You can assume the given expression is always valid.
 */
public class Problem163 {
  public static void main(String[] args) {
    String[] expression = new String[] {"15","7","1","1","+","-","/","3","*","2","1","1","+","+","-"};
    System.out.println(evaluate(expression));

    expression = new String[] {"5","3","+"};
    System.out.println(evaluate(expression));

    expression = new String[] {"0", "0", "/"}; // Divide by 0 error
    System.out.println(evaluate(expression));
  }

  /**
   * Whenever we encounter an operation, we evaluate the last 2 numbers using that operation,
   * and stash the result for future calculations.
   * Since we need access to previous elements, we can probably store it in some sort of data structure,
   * and since operations are always evaluated on the last 2 numbers, a Stack could be used.
   *
   * [5,3,'+'] -> 5 + 3 -> push 8 back into the Stack for future operations
   * At the end, just remove the remaining element from the Stack.
   * We can also view each expression as a tree and evaluate it that way:
   *              '+'
   *             /  \
   *            5   3
   * O(n) time and O(n) space
   */
  public static int evaluate(String[] exp) {
    Stack<Integer> st = new Stack<Integer>();
    for(String s : exp) {
      try {
        int i = Integer.parseInt(s);
        st.push(i);
      }
      catch(Exception e) {
        int result = st.pop(); // second operand
        switch(s) {
          case "+":
            result += st.pop();
            break;
          case "-":
            result = st.pop() - result;
            break;
          case "*":
            result *= st.pop();
            break;
          case "/":
            result = st.pop() / result;
            break;
        }
        st.push(result);
      }
    }
    if(st.size() > 1)
      System.err.println("Invalid Expression");
    return st.pop();
  }
}
