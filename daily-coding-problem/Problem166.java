/*
 * Implement a 2D iterator class. It will be initialized with an array of arrays,
 * and should implement the following methods:
 * - next(): returns the next element in the array of arrays.
 *           If there are no more elements, raise an exception.
 * - has_next(): returns whether or not the iterator still has elements left.
 * For example, given the input [[1, 2], [3], [], [4, 5, 6]], calling next()
 * repeatedly should output 1, 2, 3, 4, 5, 6.
 * Do not use flatten or otherwise clone the arrays. Some of the arrays can be empty.
 */
public class Problem166 {
  public static void main(String[] args) {
    int[][] arr = new int[][] {{1,2}, {3}, {}, {4,5,6}};
    Iterator2D it = new Iterator2D(arr);
    try {
      for(int i = 0; i < 6; i++) {
        System.out.println(it.hasNext() + " " + it.next());
      }

      System.out.println(it.hasNext());
      System.out.println(it.next());
    }
    catch(Exception e) {
      System.out.println(e);
    }
  }
}

class Iterator2D {
  int[][] arr;
  int[] current;
  int index, nextArrayIndex;

  public Iterator2D(int[][] arr) {
    this.arr = arr;
    current = new int[] {}; // []
    index = 0;
    nextArrayIndex = 0;
  }

  public boolean hasNext() {
    loadNext();
    return index < current.length;
  }

  public int next() throws Exception {
    loadNext();
    if(index >= current.length)
      throw new Exception("No next element.");
    return current[index++];
  }

  private void loadNext() {
    while(index >= current.length && nextArrayIndex < arr.length) {
      current = arr[nextArrayIndex++];
      index = 0;
    }
  }
}
