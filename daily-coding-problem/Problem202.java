/*
 * Write a program that checks whether an integer is a palindrome.
 * For example, 121 is a palindrome, as well as 888. 678 is not a palindrome.
 * Do not convert the integer into a string.
 */
public class Problem202 {
  public static void main(String[] args) {
    int i = 121;
    System.out.println(isPalindrome(i));

    i = 888;
    System.out.println(isPalindrome(i));

    i = 0;
    System.out.println(isPalindrome(i));

    i = 678;
    System.out.println(isPalindrome(i));

    i = 66;
    System.out.println(isPalindrome(i));

    i = -66;
    System.out.println(isPalindrome(i));

  }

  public static boolean isPalindrome(int i) {
    if(i < 0)
      return false;
    int x = 0, temp = i;
    while(temp > 0) {
      x = (x * 10) + (temp % 10);
      temp /= 10;
    }
    return x == i;
  }
}
