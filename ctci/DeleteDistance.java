import java.util.Arrays;

public class DeleteDistance {
  public static void main(String[] args) {
    String s1 = "dog", s2 = "frog";
    System.out.println(deletionDistance(s1, s2));
  }

  public static int deletionDistance(String str1, String str2) {
    int[][] memo = new int[str1.length() + 1][str2.length() + 1];
    for(int i = 0; i <= str1.length(); i++) {
      for(int j = 0; j <= str2.length(); j++) {
        if(i == 0)
          memo[i][j] = j;
        else if(j == 0)
          memo[i][j] = i;
        else {
          char c1 = str1.charAt(i - 1), c2 = str2.charAt(j - 1);
          int min = Math.min(memo[i - 1][j], memo[i][j - 1]);
          if(c1 == c2)
            memo[i][j] = memo[i - 1][j - 1];
          else
            memo[i][j] = min + 1;
        }
      }
    }

    return memo[str1.length()][str2.length()];
  }
}
