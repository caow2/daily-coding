import java.util.Map;
import java.util.HashMap;

/*
 * https://leetcode.com/discuss/interview-question/341818/google-onsite-skip-iterator
 * Given an Iterator interface, design and implement a
 * SkipIterator that supports the skip(int num) operation
 *
 * Example:
 * SkipIterator itr = new SkipIterator([2, 3, 5, 6, 5, 7, 5, -1, 5, 10]);
 * itr.hasNext(); // true
 * itr.next(); // returns 2
 * itr.skip(5);
 * itr.next(); // returns 3
 * itr.next(); // returns 6 because 5 should be skipped
 * itr.next(); // returns 5
 * itr.skip(5);
 * itr.skip(5);
 * itr.next(); // returns 7
 * itr.next(); // returns -1
 * itr.next(); // returns 10
 * itr.hasNext(); // false
 */
interface Iterator<E> {
   /**
   * Returns true if the iterator has more elements.
   */
  boolean hasNext();

   /**
   * Returns the next element in the iteration.
   */
  E next();
}

/**
 * Use a Map to cache the elements that need to be skipped.
 * The map should be a mapping from the Integer to the number of times it needs to be skipped.
 * We assume that the following is accurate:
 * [5,5,5,5]
 * skip(5); x4
 * hasNext(); // false
 *
 * Question -> What if we have an Iterator for the following list [1], and perform the following calls:
 * hasNext(); //true
 * skip(1);
 * next(); // Should this be null? or 1? Since the previous hasNext() was true
 *
 * Assume it should be null and client should call hasNext() after calling skip()
 */
class SkipIterator implements Iterator<Integer> {
  Iterator<Integer> it;
  Map<Integer,Integer> skipMap;
  Integer nxt;

	// if there's no iterators in your language the input can be just an array
	public SkipIterator(Iterator<Integer> it) {
    this.it = it;
    skipMap = new HashMap<Integer, Integer>();
	}

  private void loadNext() {
    // 'Load' nxt to be the next valid number, or null if there is no valid number
    if(nxt == null) {
      boolean loop = true;
      while(it.hasNext() && loop) {
        int n = it.next();
        if(skipMap.get(n) != null) { // need to skip this number
          if(skipMap.get(n) > 1)
            skipMap.put(n, skipMap.get(n) - 1);
          else
            skipMap.remove(n);
        }
        else {
          nxt = n;
          loop = false;
        }
      }
    }
  }

	public boolean hasNext() {
    loadNext(); // Always call loadNext
    return nxt != null;
	}

	public Integer next() {
    Integer i = null;
    if(hasNext()) {
      i = nxt;
      nxt = null;
    }
    return i;
	}

	/**
	* The input parameter is an int, indicating that the next element equals 'num' needs to be skipped.
	* This method can be called multiple times in a row. skip(5), skip(5) means that the next two 5s should be skipped.
	*/
	public void skip(int num) {
    if(skipMap.get(num) == null)
      skipMap.put(num, 1);
    else
      skipMap.put(num, skipMap.get(num) + 1);
	}
}
