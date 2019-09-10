import java.util.HashMap;
/*
 * Write a function to flatten a nested dictionary. Namespace the keys with a period.
 * For example, given the following dictionary:
 * {
 *    "key": 3,
 *    "foo": {
 *        "a": 5,
 *        "bar": {
 *            "baz": 8
 *        }
 *    }
 * }
 * it should become:
 * {
 *    "key": 3,
 *    "foo.a": 5,
 *    "foo.bar.baz": 8
 * }
 * You can assume keys do not contain dots in them, i.e. no clobbering will occur.
 */
public class Problem173 {
  public static void main(String[] args) {
    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("key", 3);
    HashMap<String, Object> subMap = new HashMap<String, Object>();
    subMap.put("a", 5);
    HashMap<String, Object> subMap2 = new HashMap<String, Object>();
    subMap2.put("baz", 8);
    subMap.put("bar", subMap2);
    map.put("foo", subMap);

    System.out.println(flatten(map));

    HashMap<String, Object> subMap3 = new HashMap<String, Object>();
    subMap3.put("asdqwd", 90);
    subMap.put("aflac", subMap3);
    System.out.println(flatten(map));
  }

  public static HashMap<String, Object> flatten(HashMap<String, Object> dictionary) {
    HashMap<String, Object> result = new HashMap<String, Object>();
    for(String key : dictionary.keySet()) {
      if(dictionary.get(key) instanceof Integer)
        result.put(key, dictionary.get(key));
      else {
        HashMap<String, Object> flattened = flatten((HashMap<String, Object>) dictionary.get(key), key);
        for(String flatKey : flattened.keySet()) {
          result.put(flatKey, flattened.get(flatKey));
        }
      }
    }
    return result;
  }

  // recursive flatten with parent key
  private static HashMap<String, Object> flatten(HashMap<String, Object> dictionary, String parent) {
    HashMap<String, Object> result = new HashMap<String, Object>();
    for(String key : dictionary.keySet()) {
      if(dictionary.get(key) instanceof Integer)
        result.put(parent + "." + key, dictionary.get(key));
      else {
        HashMap<String, Object> flattened = flatten((HashMap<String, Object>) dictionary.get(key), key);
        for(String flatKey : flattened.keySet()) {
          result.put(parent + "." + flatKey, flattened.get(flatKey));
        }
      }
    }
    return result;
  }
}
