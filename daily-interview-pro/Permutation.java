import java.util.List;
import java.util.LinkedList;
import java.util.HashSet;

/*
 * You are given an array of integers. Return all the permutations of this array.
 * [1, 2, 3] -> [[1, 2, 3], [2, 1, 3], [2, 3, 1], [1, 3, 2], [3, 1, 2], [3, 2, 1]]
 */
public class Permutation {
  public static void main(String[] args) {
    int[] arr = {1,2,3};
    System.out.println(permute(arr));

    arr = new int[] {1,2};
    System.out.println(permute(arr));
  }

  /**
   * Standard backtracking
   * We go down a path and append a number into our prefix list until we've added
   * every element, then backtrack.
   * O(n! * n) time, O(n) space
   */
  public static List<List<Integer>> permute(int[] arr) {
    List<List<Integer>> permutations = new LinkedList<List<Integer>>();
    List<Integer> prefix = new LinkedList<Integer>();
    HashSet<Integer> seen = new HashSet<Integer>(); // track what we have already in prefix list
    permute(arr, prefix, permutations, seen);
    return permutations;
  }

  private static void permute(int[] arr, List<Integer> prefix, List<List<Integer>> permutations,
                        HashSet<Integer> seen)
  {
    if(seen.size() == arr.length) // processed every element
    {
      permutations.add(new LinkedList(prefix));
      return;
    }
    for(int i = 0; i < arr.length; i++) {
      int num = arr[i];
      if(! seen.add(num))
        continue;
      prefix.add(num);
      permute(arr, prefix, permutations, seen);
      prefix.remove(prefix.size() - 1); // backtrack
      seen.remove(num);
    }
  }
}
