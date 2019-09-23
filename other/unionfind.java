import java.util.Arrays;

public class unionfind {
  public static void main(String[] args) {
    UF uf = new UF(5);
    UFCompressed ufc = new UFCompressed(5);
    System.out.println(uf.find(1)); // 1
    System.out.println(ufc.find(1)); // 1

    uf.union(1,2);
    ufc.union(1,2);
    System.out.println(uf.find(1)); // 1
    System.out.println(ufc.find(1)); // 1

    uf.union(3,1);
    ufc.union(3,1);
    System.out.println(uf.find(1)); // 3
    System.out.println(ufc.find(1)); // 1

    uf.union(3, 2);
    ufc.union(3,2);
    System.out.println(uf.find(1)); // 3
    System.out.println(uf.find(2)); // 3
    System.out.println(uf.find(3)); // 3
    System.out.println(uf.find(4)); // 4

    System.out.println(ufc.find(1)); // 1
    System.out.println(ufc.find(2)); // 1
    System.out.println(ufc.find(3)); // 1
    System.out.println(ufc.find(4)); // 4
  }
}

// Naive Union Find implementation
class UF {
  int[] parents;

  public UF(int size) {
    parents = new int[size + 1]; //leave 0 as garbage index
    for(int i = 0; i < parents.length; i++) {
      parents[i] = i;
    }
  }

  public void union(int a, int b) {
    int parentA = find(a), parentB = find(b);
    parents[parentB] = parentA;
  }

  public int find(int x) {
    return (parents[x] == x ? x : find(parents[x]));
  }
}

// Union Find with Path Compression and Rank
class UFCompressed extends UF {
  int[] rank;
  int numComponents;

  public UFCompressed(int size) {
    super(size);
    rank = new int[size + 1]; // each component starts w/ rank 0
    numComponents = size;
  }

  @Override
  public int find(int x) {
    if(parents[x] == x)
      return x;
    parents[x] = find(parents[x]); // compress path - point directly to root
    return parents[x];
  }

  @Override
  public void union(int a, int b) {
    int parentA = find(a), parentB = find(b);

    if(parentA == parentB)
      return; // already connected

    // The larger ranked component becomes the new parent.
    // We want to merge parentB into parentA
    if(rank[parentA] < rank[parentB]) {
      int temp = parentA;
      parentA = parentB;
      parentB = temp;
    }
    else if(rank[parentA] == rank[parentB]) // same ranks, just merge into parentA and increase rank
      rank[parentA]++;

    parents[parentB] = parentA;
    numComponents--;
  }
}
