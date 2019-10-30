import java.util.Arrays;
/*
 * Version numbers are strings that are used to identify unique states of software products.
 * A version number is in the format a.b.c.d. and so on where a, b, etc. are numeric strings separated by dots.
 * These generally represent a hierarchy from major to minor changes.
 * Given two version numbers version1 and version2, conclude which is the latest version number.
 * Your code should do the following:
 * If version1 > version2 return 1.
 * If version1 < version2 return -1.
 * Otherwise return 0.
 * Note that the numeric strings such as a, b, c, d, etc. may have leading zeroes,
 * and that the version strings do not start or end with dots.
 * Unspecified level revision numbers default to 0.
 *
 * Example:
 * Input:
 * version1 = "1.0.33", version2 = "1.0.27", Output: 1 #version1 > version2
 * Input:
 * version1 = "0.1", version2 = "1.1", Output: -1 #version1 < version2
 * Input:
 * version1 = "1.01", version2 = "1.001", Output: 0 #ignore leading zeroes, 01 and 001 represent the same number.
 * Input:
 * version1 = "1.0", version2 = "1.0.0", Output: 0, #version1 does not have a 3rd level revision number, which
 * defaults to "0"
 */
public class VersionNumber {
  public static void main(String[] args) {
    String v1 = "1.0.33", v2 = "1.0.27";
    System.out.println(compare(v1,v2));

    v1 = "0.1";
    v2 = "1.1";
    System.out.println(compare(v1,v2));

    v1 = "1.01";
    v2 = "1.001";
    System.out.println(compare(v1,v2));

    v1 = "1.0";
    v2 = "1.0.0";
    System.out.println(compare(v1,v2));

    v1 = "10.0";
    v2 = "1.01";
    System.out.println(compare(v1,v2));

    v1 = "";
    v2 = "0.0.00";
    System.out.println(compare(v1,v2));
  }

  /**
   * O(v1 + v2) time where vx is the length of string x
   * O(v1 + v2) space.
   * We process each String at most twice - once to split by the regex literal '.',
   * and then processing each numeric substring.
   */
  public static int compare(String v1, String v2) {
    String[] x1 = v1.split("[.]"), x2 = v2.split("[.]");
    int i1 = 0, i2 = 0;
    while(i1 < x1.length || i2 < x2.length) {
      int n1 = 0, n2 = 0;
      if(i1 < x1.length)
        n1 = processNum(x1[i1++]);
      if(i2 < x2.length)
        n2 = processNum(x2[i2++]);

      if(n1 > n2)
        return 1;
      else if(n1 < n2)
        return -1;
    }
    return 0;
  }

  // Assume that trailing 0s are treated differently: i.e. 1.100 and 1.10 are different versions
  // O(s) time, O(1) space.
  private static int processNum(String s) {
    int num = 0;
    boolean leading = true;
    for(int i = 0; i < s.length(); i++) {
      int c = Character.getNumericValue(s.charAt(i));
      if(leading && c == 0)
        continue;
      else {
        leading = false;
        num = (num * 10) + c;
      }
    }

    return num;
  }
}
