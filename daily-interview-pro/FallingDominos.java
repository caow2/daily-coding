/*
 * Given a string with the initial condition of dominoes, where:
 * . represents that the domino is standing still
 * L represents that the domino is falling to the left side
 * R represents that the domino is falling to the right side
 *
 * Figure out the final position of the dominoes.
 * If there are dominoes that get pushed on both ends, the force cancels out and that domino remains upright.
 *
 * Example:
 * Input:  ..R...L..R.
 * Output: ..RR.LL..RR
 */
public class FallingDominos {
  public static void main(String[] args) {
    String s = "..R...L..R.";
    System.out.println(dominos(s));

    s = "..L..L..R....L.R.L";
    System.out.println(dominos(s));
  }

  /**
   * We can go thru the String and let integers represent the position of the domino.
   * Negative = R, Positive = L, 0 = .
   * From a first pass (left to right, we only look at R and .):
   *   . . R . . . L . . R .
   *  [0,0,-1,-2,-3,-4,0,0,-1,-2]
   * Whenever we encounter R, we keep an decrementing count for all . dominos to the right of it until
   * we reach a L.
   *
   * From the second pass (right ot left, we only look at L, ., and the sign of the current index)
   *   . .  R  .  .  . L . .  R  .
   *  [0,0,-1,-2,-3,-4,0,0,0,-1,-2]     1st pass
   *  [0,0, 0, 4, 3, 2,1,0,0, 0, 0]     2nd pass
   *  [0,0,-1, 2, 0,-2,1,0,0,-1,-2]     sum
   * We see that when the signs are different, the final position corresponds to the opposite sign of the sum at that index.
   *
   * O(n) time, O(n) space
   */
  public static String dominos(String s) {
    int[] leftRight = new int[s.length()]; // let 0 represent '.', positive represent 'L', negative represent 'R'
    //left to right -> process right dominos
    for(int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if(c == 'R')
        leftRight[i] = -1;
      else if(c == '.' && i > 0 && leftRight[i - 1] < 0) // R domino
        leftRight[i] = leftRight[i - 1] - 1;
    }

    int[] rightLeft = new int[s.length()];
    //right to left -> process left dominos
    for(int i = s.length() - 1; i >= 0; i--) {
      char c = s.charAt(i);
      if(c == 'L')
        rightLeft[i] = 1;
      else if(c == '.' && i < s.length() - 1 && rightLeft[i + 1] > 0) // L domino
        rightLeft[i] = rightLeft[i + 1] + 1;
    }

    StringBuilder sb = new StringBuilder();
    for(int i = 0; i < s.length(); i++) {
      char c = ' ';
      int sum = rightLeft[i] + leftRight[i];
      if(sum == 0)
        c = '.';
      else if(leftRight[i] == 0)
        c = 'L';
      else if(rightLeft[i] == 0)
        c = 'R';
      else // the signs at the indices are different
        c = (sum < 0 ? 'L' : 'R');
      sb.append(c);
    }
    return sb.toString();
  }
}
