import java.util.LinkedList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Queue;

/*
 * Given a start word, an end word, and a dictionary of valid words,
 * find the shortest transformation sequence from start to end such that only
 * one letter is changed at each step of the sequence, and each transformed
 * word exists in the dictionary.
 * If there is no possible transformation, return null.
 * Each word in the dictionary have the same length as start and end and is lowercase.
 *
 * For example, given start = "dog", end = "cat", and
 * dictionary = {"dot", "dop", "dat", "cat"}, return ["dog", "dot", "dat", "cat"].
 *
 * Given start = "dog", end = "cat", and dictionary = {"dot", "tod", "dat", "dar"},
 * return null as there is no possible transformation from dog to cat.
 */
public class Problem170 {
  public static void main(String[] args) {
    HashSet<String> dictionary = new HashSet<String>();
    dictionary.add("dot");
    dictionary.add("dop");
    dictionary.add("dat");
    dictionary.add("cat");
    String start = "dog", end = "cat";
    System.out.println(transformations(start, end, dictionary));

    dictionary = new HashSet<String>();
    dictionary.add("dot");
    dictionary.add("tod");
    dictionary.add("dat");
    dictionary.add("dar");
    System.out.println(transformations(start, end, dictionary));

    end = "dar";
    dictionary.remove("dat");
    System.out.println(transformations(start, end, dictionary));
  }

  public static List<String> transformations(String start, String end, HashSet<String> dictionary) {
    if(! dictionary.contains(end) || start.length() != end.length())
      return null;
    // Link nodes as we perform BFS so at the end we can recreate the path from start to end
    // This way we don't have to store the path of each node as we traverse the graph
    dictionary.add(start);
    HashMap<String, String> emptyGraph = new HashMap<String, String>();
    for(String word : dictionary) { // O(n)
      emptyGraph.put(word, null);
    }

    // We can do a standard BFS, without recreating the entire graph:
    // From each node, we want to consider all nodes that have a 1 letter difference.
    // This can be done by replacing each character with 'a' to 'z' and checking if that word exists in the dictionary.
    Queue<String> q = new LinkedList<String>();
    q.offer(start);
    // O(n) - we potentially process each Node in the 'graph'
    while(! q.isEmpty()) {
      String s = q.poll();
      if(s.equals(end))
        break; // found shortest path - go on to recreate path

      // O(s) since we look at each character of the longest String
      for(int i = 0; i < end.length(); i++) {
        char[] word = s.toCharArray();
        // replace each character and see if it's in the dictionary
        // then remove the word to avoid considering it again
        for(char c = 'a'; c <= 'z'; c++) {
          word[i] = c;
          String candidate = new String(word);
          if(dictionary.remove(candidate)) {
            q.offer(candidate);
            emptyGraph.put(candidate, s); // connect new node to previous node
          }
        }
      }
    }

    return getPath(emptyGraph, start, end); // O(n)
  }

  // trace back from end to start
  private static List<String> getPath(HashMap<String, String> graph, String start, String end) {
    String current = end;
    List<String> path = new ArrayList<String>();
    while(current != null && ! current.equals(start)) {
      path.add(current);
      current = graph.get(current);
    }
    path.add(start);
    Collections.reverse(path);
    return (current == null ? null : path); // no path
  }
}
