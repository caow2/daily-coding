import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;

/*
 * Given a list of words, return the shortest unique prefix of each word.
 * For example, given the list:
 * [ dog, cat, apple, apricot, fish ]
 * Return the list:
 * [ d, c, app, apr, f ]
 */
public class Problem162 {
  public static void main(String[] args) {
    List<String> words = new LinkedList<String>();
    words.add("dog");
    words.add("cat");
    words.add("apple");
    words.add("apricot");
    words.add("fish");

    List<String> prefix = new LinkedList<String>();

    TrieNode trie = new TrieNode(null, 0);
    for(String word : words) {
      trie.add(word);
    }
    for(String word : words) {
      prefix.add(trie.uniquePrefixQuery(word));
    }
    System.out.println(prefix); // [d, c, app, apr, f]

    words.add("fitz");
    trie.add("fitz");
    prefix = new LinkedList<String>(); // need to recompute or update
    for(String word : words) {
      prefix.add(trie.uniquePrefixQuery(word));
    }
    System.out.println(prefix); // [d, c, app, apr, fis, fit]
  }
}

/*
 * Build up a Trie for the list of words that we're given, and store in each TrieNode
 * the number of words the current prefix is associated with.
 * i.e. [apple, apricot] :
 *          * (root)
 *          |
 *       {a : 2}
 *          |
 *       {p : 2}
 *        /   \
 *    {p : 1} {r: 1}
 *         ...
 *
 * Once the trie is built, we can just query for each word and stop once we hit a wordcount
 * of 1.
 * O(s * n) time and space where s is length of longest String and n is number of Strings in the list
 * One downside is that if a new word is added to the list and trie, you'd have to recompute the entire list
 * because a previous longest unique prefix may no longer be valid.
 * i.e. [fish] outputs [f], but if you add the word "fitz", the next correct output should be
 * [fis, fit]
 */
class TrieNode {
  Character c;
  int words;
  Map<Character, TrieNode> map;

  public TrieNode(Character c, int words) {
    this.c = c;
    this.words = words;
    map = new HashMap<Character, TrieNode>();
  }

  public void add(String s) {
    add(s, 0);
  }

  private void add(String s, int index) {
    if(index >= s.length())
      return;
    char ch = s.charAt(index);
    if(map.get(ch) == null)
      map.put(ch, new TrieNode(ch, 0));
    map.get(ch).words++;
    map.get(ch).add(s, index + 1);
  }

  public String uniquePrefixQuery(String s) {
    StringBuilder sb = new StringBuilder();
    query(s, 0, sb);
    return sb.toString();
  }

  private void query(String s, int index, StringBuilder sb) {
    if(index >= s.length() || map.get(s.charAt(index)) == null)
      return;
    char ch = s.charAt(index);
    sb.append(ch);
    if(map.get(ch).words > 1)
      map.get(ch).query(s, index + 1, sb);
  }
}
