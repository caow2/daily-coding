import java.util.HashMap;
/*
 *
 * Given a Roman numeral, find the corresponding decimal value.
 * Inputs will be between 1 and 3999.
 * Example:
 * Input: IX
 * Output: 9
 *
 * Input: VII
 * Output: 7
 *
 * Input: MCMIV
 * Output: 1904
 * Roman numerals are based on the following symbols:
 * I     1
 * IV    4
 * V     5
 * IX    9
 * X     10
 * XL    40
 * L     50
 * XC    90
 * C     100
 * CD    400
 * D     500
 * CM    900
 * M     1000
 * Numbers are strings of these symbols in descending order. In some cases, subtractive notation is used to avoid repeated characters. The rules are as follows:
 * 1.) I placed before V or X is one less, so 4 = IV (one less than 5), and 9 is IX (one less than 10)
 * 2.) X placed before L or C indicates ten less, so 40 is XL (10 less than 50) and 90 is XC (10 less than 100).
 * 3.) C placed before D or M indicates 100 less, so 400 is CD (100 less than 500), and 900 is CM (100 less than 1000).
 */
public class ConvertRomanToDecimal {
  public static void main(String[] args) {
    String roman = "MCMIV";
    System.out.println(convert(roman));

    roman = "VII";
    System.out.println(convert(roman));

    roman = "CD";
    System.out.println(convert(roman));

    roman = "MCD";
    System.out.println(convert(roman));

    roman = "MCXC";
    System.out.println(convert(roman));
  }

  public static int convert(String s) {
    int i = 0, result = 0;
    HashMap<String, Integer> map = romanMap();
    while(i < s.length()) {
      int curr = map.get("" + s.charAt(i));
      int next = 0;
      if(i < s.length() - 1) { // check if combination of next 2 characters is valid
        String num = s.charAt(i) + "" + s.charAt(i + 1);
        if(map.containsKey(num)) {
          next = map.get(num);
          i++;
        }
      }
      result += Math.max(curr, next);
      i++;
    }
    return result;
  }

  private static HashMap<String, Integer> romanMap() {
    HashMap<String, Integer> map = new HashMap<String, Integer>();
    String[] s = new String[] {"I","IV","V","IX","X","XL","L","XC","C","CD","D","CM","M"};
    int[] v = new int[] {1,4,5,9,10,40,50,90,100,400,500,900,1000};
    for(int i = 0; i < s.length; i++) {
      map.put(s[i], v[i]);
    }
    return map;
  }
}
