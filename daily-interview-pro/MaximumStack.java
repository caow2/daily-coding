import java.util.Stack;
/*
 * Implement a class for a stack that supports all the regular functions (push, pop)
 * and an additional function of max() which returns the maximum element in the stack
 * (return None if the stack is empty). Each method should run in constant time.
 */
public class MaximumStack {
  public static void main(String[] args) {
    MaxStack m = new MaxStack();
    m.push(0);
    m.push(1);
    m.push(-1);
    m.push(-5);
    m.push(10);

    while(! m.isEmpty()) {
      System.out.println("Max: " + m.max() + ", Popped: " + m.pop());
    }
  }
}

/**
 * We know that for a Stack, elements come in a LIFO order,
 * and for each element, we wish to keep track of the maximum element at that point
 * in the Stack.
 *
 * Keeping track of the current maximum isn't enough, because once we remove the
 * max element from the Stack, we'd have to look for the new max in the rest of the
 * Stack; which isn't constant time.
 * Suppose our stack is the following:
 *  |4|         max = 4
 *  |3|         max = 3
 *  |2|         max = 2
 *  |1|         max = 1
 * We can see that for each element, we associate it with a maximum in this case.
 * We can store an auxillary Stack for the maximum per element, and this
 * auxillary data structure is populated along with the main Stack.
 */
class MaxStack {
  Stack<Integer> st, max;

  public MaxStack() {
    st = new Stack<Integer>();
    max = new Stack<Integer>();
  }

  public void push(int i) {
    // The new maximum is either i or the top element of the max stack
    max.push(max.isEmpty() ? i : Math.max(i, max.peek()));
    st.push(i);
  }

  public int pop() {
    max.pop();
    return st.pop();
  }

  public int max() {
    return max.peek();
  }

  public boolean isEmpty() {
    return st.isEmpty();
  }
}
