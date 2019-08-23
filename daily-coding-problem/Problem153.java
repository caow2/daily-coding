/*
 * Find an efficient algorithm to find the smallest distance
 * (measured in number of words) between any two given words in a string.
 *
 * For example, given words "hello", and "world" and a text content of
 * "dog cat hello cat dog dog hello cat world", return 1 because there's only
 * one word "cat" in between the two words.
 */
public class Problem153 {
  public static void main(String[] args) {
    String text = "dog cat hello cat dog dog hello cat world";
    String w1 = "hello";
    String w2 = "world";
    System.out.println(shortestDistance(text, w1, w2));
  }

  // O(T * (w1 + w2)^2)
  public static int shortestDistance(String text, String w1, String w2) {
    String[] txt = text.split(" "); // O(T)
    String current = null;
    int dist = 0, minDist = Integer.MAX_VALUE;
    for(String s : txt) { // O(T)
      if(s.equals(w1) || s.equals(w2)) { //O(w1 + w2)
        if(current == null)
          current = s;
        else if (! s.equals(current)) { // O(w1 + w2)
          minDist = Math.min(minDist, dist);
          current = s;
        }
        dist = 0;
      }
      else
        dist++;
    }
    return minDist == Integer.MAX_VALUE ? -1 : minDist;
  }
}
