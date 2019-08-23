import java.util.PriorityQueue;
/*
 * Implement a stack API using only a heap. A stack implements the following methods:
 * - push(item), which adds an element to the stack
 * - pop(), which removes and returns the most recently added element (or throws an error if there is nothing on the stack)
 *
 * Recall that a heap has the following operations:
 * - push(item), which adds a new key to the heap
 * - pop(), which removes and returns the max value of the heap
 */
public class Problem154 {
  public static void main(String[] args) {
    Stack<Integer> st = new HeapStack<Integer>();

    st.push(1);
    st.push(2);
    System.out.println(st.pop());
    st.push(3);
    System.out.println(st.pop());
    System.out.println(st.pop());

    for(int i = 0; i < 5; i++) {
      st.push(i);
    }

    for(int i = 0; i < 5; i++) {
      System.out.println(st.pop());
    }
  }
}

interface Stack<E> {
  void push(E item);

  E pop();
}

class HeapStack<K> implements Stack<K> {
  PriorityQueue<Pair<K, Integer>> pq;
  int index; // will eventually need to reset index

  public HeapStack() {
    pq = new PriorityQueue<Pair<K, Integer>>((x, y) -> y.value - x.value);
    index = 0;
  }
  public void push(K item) {
    pq.offer(new Pair<K, Integer>(item, index++));
  }

  public K pop() {
    return pq.poll().key;
  }
}

class Pair<K,V> {
  K key;
  V value;

  public Pair(K key, V value) {
    this.key = key;
    this.value = value;
  }
}
