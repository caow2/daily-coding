/*
 * Given n numbers, find the greatest common denominator between them.
 * For example, given the numbers [42, 56, 14], return 14.
 */
public class Problem184 {
  public static void main(String[] args) {
    int[] arr = new int[] {42, 56, 14};
    System.out.println(gcd(arr)); // 14

    arr = new int[] {1,2,3,4};
    System.out.println(gcd(arr)); // 1

    arr = new int[] {2,3,4};
    System.out.println(gcd(arr)); // 1

    arr = new int[] {25, 35, 40};
    System.out.println(gcd(arr)); // 5
  }

  /**
   * Let's first consider the problem of how to find the gcd between 2 numbers.
   * Suppose we are given 2 numbers a and b.
   * We can iterate from 1 to min(a, b) and keep track of the gcd.
   * This would require O(a + b) time and O(1) space.
   *
   * Alternatively, we can consider Euclid's gcd algorithm for input x, y:
   * x = y * n + r
   * y = r * n + r'
   * ...
   * r'' = r''' * n + 0       r''' is gcd
   * This would take log(max(a,b)) time since we cut down the input by at most a factor
   * of min(a, b) each time.
   */
   public static int gcd(int a, int b) {
     if(a < b) {
       int temp = a;
       a = b;
       b = temp;
     }
     int remainder = a % b;
     if(remainder == 0)
       return b;
    return gcd(b, remainder);
   }

   /**
    * By Euclid's algorithm, gcd(x,y) = gcd(y, r) = gcd(r, r') and so forth.
    * Let g = gcd(x,y).
    * For another number z, it follows that gcd(g, z) = gcd(x, y, z)
    * O(n * log(max(arr[i]))) time, O(1) space
    */
   public static int gcd(int[] arr) {
     int gcd = 0;
     for(int i = 1; i < arr.length; i++) {
       if(gcd == 0)
        gcd = gcd(arr[i - 1], arr[i]);
       else
        gcd = gcd(gcd, arr[i]);
     }
     return gcd;
   }
}
