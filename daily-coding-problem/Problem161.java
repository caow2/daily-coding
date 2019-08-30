/*
 * Given a 32-bit integer, return the number with its bits reversed.
 *
 * For example, given the binary number
 * 1111 0000 1111 0000 1111 0000 1111 0000,
 * return 0000 1111 0000 1111 0000 1111 0000 1111.
 */
public class Problem161 {
  public static void main(String[] args) {
    int i = 11;
    System.out.println(reverseBits(i));
  }

  /**
   * Assuming ... 0000 1011 (11) -> 1101 0000 ...
   */
  public static int reverseBits(int num) {
    int i = 0;
    int result = 0;
    while(i < 32) {
      int b = ((1 << i) & num);
      if(b > 0)
        result |= (1 << 31 - i);
      i++;
    }
    return result;
  }
}
