/*
 * Given two strings A and B of lowercase letters,
 * return true if and only if we can swap two letters in A so that the result equals B.
 */
public class BuddyString {
  public static void main(String[] args) {
    String a = "abc", b = "abc";
    System.out.println(isBuddyString(a,b));

    a = "acb";
    System.out.println(isBuddyString(a,b));

    b = "bca";
    System.out.println(isBuddyString(a,b));

    b = "bac";
    System.out.println(isBuddyString(a,b));
  }


  //Find 2 indices that differ between a and b
  public static boolean isBuddyString(String a, String b) {
    if(a.length() != b.length())
      return false;
    int[] idx = new int[2];
    int index = 0;
    for(int i = 0; i < a.length(); i++) {
      if(a.charAt(i) != b.charAt(i)) {
        if(index >= idx.length)
          return false;
        idx[index++] = i;
      }
    }
    if(index < idx.length)
      return false;
    return a.charAt(idx[0]) == b.charAt(idx[1]) &&
           a.charAt(idx[1]) == b.charAt(idx[0]);
  }
}
