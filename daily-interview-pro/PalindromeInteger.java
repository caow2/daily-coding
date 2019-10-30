/*
 * Given an integer, check if that integer is a palindrome.
 * For this problem do not convert the integer to a string to check if it is a palindrome.
 *
 * print is_palindrome(1234321) # True
 * print is_palindrome(1234322) # False
 */
public class PalindromeInteger {
  public static void main(String[] args) {
    System.out.println(isPalindrome(1234321)); // t
    System.out.println(isPalindrome(123321)); // t
    System.out.println(isPalindrome(1)); // t
    System.out.println(isPalindrome(11)); // t
    System.out.println(isPalindrome(0)); // t

    System.out.println(isPalindrome(-1)); // f
    System.out.println(isPalindrome(1234322)); // f
    System.out.println(isPalindrome(123123)); // f
  }

  /*
   * Reverse the integer. I assume that a negative value can't be a palindrome.
   * O(d) time where d is the number of digits in x.
   */
  public static boolean isPalindrome(int x) {
    if(x < 0)
      return false;

    int y = 0, temp = x;
    while(temp > 0) {
      int r = temp % 10;
      temp /= 10;

      y = (y * 10) + r;
    }
    return y == x;
  }
}
