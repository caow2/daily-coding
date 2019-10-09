import java.util.Arrays;
import java.util.HashSet;
import java.util.HashMap;
/*
 * You are given an array of integers.
 *  Return the length of the longest consecutive elements sequence in the array.
 * For example, the input array [100, 4, 200, 1, 3, 2] has the longest
 * consecutive sequence 1, 2, 3, 4, and thus, you should return its length, 4.
 *
 * Follow up : Can you do this in linear time?
 */
public class LongestConsecutive {
  public static void main(String[] args) {
    int[] arr = new int[] {100,4,200,1,3,2};
    System.out.println(longestOptimal(arr));
    System.out.println(longestOptimized(arr));
    System.out.println(longest(arr));

    arr = new int[] {100,103,4,102,1,3,2,101,104};
    System.out.println(longest(arr));
    System.out.println(longestOptimized(arr));
    System.out.println(longestOptimal(arr));
  }

  /**
   * Simple solution is to sort the array and then do a linear scan.
   * O(nlogn) time, O(1) space.
   */
  public static int longest(int[] arr) {
    Arrays.sort(arr);
    int longest = 0, counter = 1;
    for(int i = 1; i < arr.length; i++) {
      if(arr[i] - 1 == arr[i - 1])
        longest = Math.max(++counter, longest);
      else
        counter = 1;
    }
    return longest;
  }

  /**
   * To do this in linear time, we need to get rid of our sorting bottle neck.
   * As we process [4, 1, 3, 2], we want to place each number into a group.
   * i.e. [4], [[4],[1]], [[4,3],[1]],[[4,3],[1,2]] -> [[4,3,1,2]]
   * This could boil down to a connected components problem using Union Find.
   *
   * For each new number arr[i], we can try to union(arr[i], arr[i - 1])
   * and union(arr[i], arr[i + 1]) if we've seen those numbers.
   * To track the numbers we've seen, store them in a HashTable.
   * Lastly, we can track the size of each connected component
   * O(n + max(arr[i])) time, O(n + max(arr[i])) space.
   *
   * We can try to optimize further on the UnionFind data structure because
   * the range of arr[i] is a bottleneck.
   * i.e. Let the UF just union indices
   */
   public static int longestOptimized(int[] arr) {
     HashSet<Integer> seen = new HashSet<Integer>();
     int max = Integer.MIN_VALUE;
     for(int i = 0; i < arr.length; i++) {
       max = Math.max(max, arr[i]);
     }

     UnionFind uf = new UnionFind(max);
     for(int i = 0; i < arr.length; i++) {
       int num = arr[i];
       seen.add(num);
       if(seen.contains(num - 1))
        uf.union(num, num - 1);
       if(seen.contains(num + 1))
        uf.union(num, num + 1);
     }

     int longest = 0;
     for(int i : uf.size) {
       longest = Math.max(longest, i);
     }
     return longest;
   }

   /**
    * Optimized from O(n + max(arr[i])) to O(n).
    * If we just map each arr[i] to its index, we can reduce the need to have
    * a whole range of numbers.
    * union(index for arr[i], index for arr[i + 1]) ...
    * However, we need to track the indices for each arr[i] -> HashMap
    * since we are no longer working with the values of each arr[i] in the UnionFind
    */
   public static int longestOptimal(int[] arr) {
     HashMap<Integer, Integer> map = new HashMap<>(); // value, index
     UnionFind uf = new UnionFind(arr.length);
     for(int i = 0; i < arr.length; i++) {
       map.put(arr[i], i);
       if(map.get(arr[i] - 1) != null)
        uf.union(i, map.get(arr[i] - 1));
       if(map.get(arr[i] + 1) != null)
        uf.union(i, map.get(arr[i] + 1));
     }

     int longest = 0;
     for(int i : uf.size) {
       longest = Math.max(longest, i);
     }
     return longest;
   }
}

class UnionFind {
  int[] parents, rank, size;

  public UnionFind(int size) {
    parents = new int[size + 1]; //ignore index 0
    rank = new int[size + 1];
    this.size = new int[size + 1];

    Arrays.fill(this.size, 1);
    for(int i = 0; i < parents.length; i++) {
      parents[i] = i;
    }
  }

  public void union(int a, int b) {
    int parA = find(a), parB = find(b);

    if(rank[parA] < rank[parB]) {
      int temp = parA;
      parA = parB;
      parB = temp;
    }
    else if(rank[parA] == rank[parB])
      rank[parA]++;

    parents[parB] = parA;
    size[parA] += size[parB];
  }

  public int find(int x) {
    if(parents[x] == x)
      return x;
    parents[x] = find(parents[x]);
    return parents[x];
  }
}
