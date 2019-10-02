import java.util.Random;
import java.util.Arrays;
/*
 * You have a function rand7() that generates a random integer from 1 to 7.
 * Use it to write a function rand5() that generates a random integer from 1 to 5.
 */
public class SimulateDice {
  public static void main(String[] args) {
    int[] count = new int[5];
    for(int i = 0; i < 1000; i++) {
      count[rand5() - 1]++;
    }

    System.out.println(Arrays.toString(count));
  }

  /**
   * May never terminate
   */
  public static int rand5() {
    int num = Integer.MAX_VALUE;
    while(num > 5) {
      num = rand7();
    }
    return num;
  }

  public static int rand7() {
    Random r = new Random();
    return r.nextInt(7) + 1; // exclusive of 8
  }
}
