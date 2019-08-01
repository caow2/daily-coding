import java.util.Arrays;
/*
 * You're working on a secret team solving coded transmissions.
 * Your team is scrambling to decipher a recent message, worried it's a plot to
 * break into a major European National Cake Vault.
 * The message has been mostly deciphered, but all the words are backward!
 * Your colleagues have handed off the last step to you.
 *
 * Write a function reverse_words() that takes a message as a list of characters
 * and reverses the order of the words in place.
 *
 * message = [ 'c', 'a', 'k', 'e', ' ',
 *          'p', 'o', 'u', 'n', 'd', ' ',
 *          's', 't', 'e', 'a', 'l' ]
 *
 * reverse_words(message) -> "steal pound cake"
 */
public class ReverseWords {
  public static void main(String[] args) {
    char[] message = new char[] {' ','c','a','k','e',' ','i','s', ' ','b','a','d', ' '};
    reverseWords(message);
    System.out.println(Arrays.toString(message));
  }

  /**
   * A naive approach would be to push every word to the end of the array
   * and reduce the end index.
   * For example, we push cake to the end of the array and reduce the end index to
   * message.length - 4.
   * However, each time we do this, we have to shift elements in the array to make
   * room at the end of the array.
   * O(n^2) time, O(1) space
   *
   * However, we do this because we don't know how much room cake will take up
   * at the end of the array, and if we observed the character-level reversed
   * version of the String, we notice the following:
   * cake pound steal
   * laets dnuop ekac
   * All the words are in the right places, just reversed.
   * We can spend one pass to reverse the entire String, then reverse each word in a second pass
   * O(n) time, O(1) space
   */
  public static void reverseWords(char[] message) {
    //First reverse the entire message
    reverse(message, 0, message.length - 1);

    //Reverse each word
    int index = message.length; // invalid index for error cases
    for(int i = 0; i < message.length; i++) {
      if(message[i] == ' ' || i == message.length - 1) {
        reverse(message, index, i - 1);
        index = message.length;
      }
      else {
        if(index == message.length)
          index = i;
      }
    }
  }

  private static void reverse(char[] message, int start, int end) {
    while(start <= end) {
      char c = message[start];
      message[start++] = message[end];
      message[end--] = c;
    }
  }
}
