import java.util.HashMap;
/*
 * Two words can be 'chained' if the last character of the first word is the same
 * as the first character of the second word.
 *
 * Given a list of words, determine if there is a way to 'chain' all the words in a circle.
 * Example:
 * Input: ['eggs', 'karat', 'apple', 'snack', 'tuna']
 * Output: True
 * Explanation:
 * The words in the order of ['apple', 'eggs', 'snack', 'karat', 'tuna'] creates a circle of chained words.
 */
public class CircleWords {
  public static void main(String[] args) {
    String[] words = new String[] { "eggs", "karat", "apple", "snack", "tuna" };
    System.out.println(isChain(words)); // T

    words = new String[] {"aa","aa","aa","aa"};
    System.out.println(isChain(words)); // T

    words = new String[] {"ea","ae","et","te"};
    System.out.println(isChain(words)); // T

    words = new String[] {"ea","ae","et"};
    System.out.println(isChain(words)); // F

    words = new String[] {"aa","ab","bc","ba"};
    System.out.println(isChain(words)); // F
  }

  /**
   * Note that we only care about the first and last letter of each word.
   * i.e. [e-s, k-t, a-e, s-k, t-a]
   * Suppose we have a graph with where each node is one of these characters.
   * If we consider the above list as directed edges, i.e. from e->s,
   * we can connect the graph.
   *
   * However, how do we check if it's a circular chain?
   * Union Find doesn't seem to help here because we know we should encounter a cycle with specific conditions.
   *
   * We can try to track the number of incoming edges, but what about for something like
   * ['aa','aa','aa','aa'] or ['ea','ae','et','te'] ?
   * We note that for each node, the number of incoming edges matches the number of outgoing edges.
   * Essentially, we check to see if the graph is a eulerian circuit.
   */
   public static boolean isChain(String[] words) {
     // let index 0 be # incoming edges, index 1 be # outgoing edges
     HashMap<Character, int[]> inOut = new HashMap<Character, int[]>();
     for(char c = 'a'; c <= 'z'; c++) {
       inOut.put(c, new int[2]);
     }

     for(String word : words) {
       word = word.toLowerCase();
       char node = word.charAt(0), end = word.charAt(word.length() - 1);
       inOut.get(node)[1]++; // outgoing edge
       inOut.get(end)[0]++; //incoming edge
     }

     for(char c : inOut.keySet()) {
       int[] numEdges = inOut.get(c);
       if(numEdges[0] != numEdges[1])
        return false;
     }

     return true;
   }
}
