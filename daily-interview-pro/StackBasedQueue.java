import java.util.Stack;
/*
 * Implement a queue class using two stacks.
 * A queue is a data structure that supports the FIFO protocol (First in = first out).
 * Your class should support the enqueue and dequeue methods like a standard queue.
 */
public class StackBasedQueue {
  public static void main(String[] args) {
    StackQueue<Integer> sq = new StackQueue<Integer>();
    for(int i = 0; i < 5; i++) {
      sq.enqueue(i);
    }

    System.out.println(sq.dequeue());
    sq.enqueue(9);
    while(! sq.isEmpty()) {
      System.out.println(sq.dequeue());
    }
  }
}

interface Queue<E> {
  public void enqueue(E elem);

  public E dequeue();
}

class StackQueue<V> implements Queue<V> {
  Stack<V> s1, s2;

  StackQueue() {
    s1 = new Stack<V>();
    s2 = new Stack<V>();
  }

  public void enqueue(V elem) {
    s1.push(elem);
  }

  public V dequeue() {
    if(s2.isEmpty()) {
      while(!s1.isEmpty()) {
        s2.push(s1.pop());
      }
    }
    return (s2.isEmpty() ? null : s2.pop());
  }

  public boolean isEmpty() {
    return s1.isEmpty() && s2.isEmpty();
  }
}
