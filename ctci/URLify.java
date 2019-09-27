/*
 * Write a method to replace all spaces in a string with '%20'.
 * You may assume that the String has enough sufficient space at the end
 * to hold the additional characters, and you are given the "true"
 * length of the String.
 */
public class URLify {
  public static void main(String[] args) {
    String s = "Mr John Smith    ";
    System.out.println(urlify(s, 13)); // "Mr%20John%20Smith"

    s = "   ";
    System.out.println(urlify(s, 1)); // "%20"

    s = "a b  ";
    System.out.println(urlify(s, 3)); // "a%20b"

    s = "";
    System.out.println(urlify(s, 0)); // ""

    s = " ab  ";
    System.out.println(urlify(s, 3)); // "%20ab"
  }

  /**
   * Make a pass through the array, and each time we encounter a space,
   * shift all characters after it by 3, etc.
   * O(n^2) time, O(1) space.
   * The char array isn't considered since it is necessary for String manipulation.
   *
   * Instead of traversing from front to back, let's traverse the String from back to front.
   * We can keep a pointer starting at the end of the array to place the next element.
   * If we encounter a space, then we can fill 3 spots.
   * Our index will always be >= pointer since there is enough space in the array.
   * O(n) time, O(1) space.
   */
  public static String urlify(String s, int len) {
    char[] word = s.toCharArray();
    int end = word.length - 1, index = len - 1;
    while(index >= 0) {
      char c = word[index];
      if(c == ' ') {
        word[end--] = '0';
        word[end--] = '2';
        word[end--] = '%';
      }
      else
        word[end--] = c;
      index--;
    }

    return new String(word);
  }
}
