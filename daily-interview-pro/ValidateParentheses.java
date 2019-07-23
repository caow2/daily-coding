import java.util.Stack;
import java.util.HashMap;

/*
 * Imagine you are building a compiler. Before running any code, the compiler must check that the parentheses in the program are balanced. Every opening bracket must have a corresponding closing bracket. We can approximate this using strings.
 *
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * An input string is valid if:
 * - Open brackets are closed by the same type of brackets.
 * - Open brackets are closed in the correct order.
 * - Note that an empty string is also considered valid.
 *
 * Example:
 * Input: "((()))"
 * Output: True
 *
 * Input: "[()]{}"
 * Output: True
 *
 * Input: "({[)]"
 * Output: False
 */
public class ValidateParentheses {
  public static void main(String[] args) {
    String v1 = "[](){}", v2 = "[{}]()", v3 = "[{()}]", v4 = ""; // valid tests
    String i1 = "[([", i2 = "[(]", i3 = "(}", i4 = "({)}"; //invalid tests

    System.out.println(balancedParentheses(v1));
    System.out.println(balancedParentheses(v2));
    System.out.println(balancedParentheses(v3));
    System.out.println(balancedParentheses(v4));

    System.out.println(balancedParentheses(i1));
    System.out.println(balancedParentheses(i2));
    System.out.println(balancedParentheses(i3));
    System.out.println(balancedParentheses(i4));
  }

  /*
   * Idea is to use a Stack to maintain the opening parentheses seen so far.
   * If we see a closing parentheses and the Stack is empty or the top element
   * isn't the corresponding opening parentheses, it can't be a valid String.
   */
  public static boolean balancedParentheses(String s) {
    HashMap<Character, Character> map = new HashMap<Character, Character>();
    map.put('}', '{');
    map.put(']', '[');
    map.put(')', '(');

    Stack<Character> st = new Stack<Character>();
    for(char c : s.toCharArray()) {
      if (c == '(' || c == '{' || c == '[')
        st.push(c);
      else { // c has to be ), ], or }
        if(st.isEmpty() || st.peek() != map.get(c))
          return false;
        st.pop(); // valid case
      }
    }
    return st.isEmpty(); // if not empty, stack still has unmatched parentheses
  }
}
