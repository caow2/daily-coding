import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;

/* You are given a hash table where the key is a course code, and the value is
 * a list of all the course codes that are prerequisites for the key.
 * Return a valid ordering in which we can complete the courses.
 * If no such ordering exists, return NULL.
 *
 * Example:
 * {
 *  'CSC300': ['CSC100', 'CSC200'],
 *  'CSC200': ['CSC100'],
 *  'CSC100': []
 * }
 *
 * This input should return the order that we need to take these courses:
 * ['CSC100', 'CSC200', 'CSCS300']
 */
public class CoursePrerequisite {
  public static void main(String[] args) {
    Map<String, List<String>> courses = new HashMap<String, List<String>>();
    List<String> prereqs = new LinkedList<String>();
    prereqs.add("CSC100");
    prereqs.add("CSC200");
    courses.put("CSC300", prereqs);

    prereqs = new LinkedList<String>();
    prereqs.add("CSC100");
    courses.put("CSC200", prereqs);

    prereqs = new LinkedList<String>();
    courses.put("CSC100", prereqs);

    System.out.println(courseOrder(courses));
  }

  /**
   * Standard topological sort question since we have a directed graph that's potentially acyclic.
   * If we let the directed edge denote the relationship 'prerequisite for' :
   *        CSC300 <--- CSC100
   *          ^         /
   *          |       V
   *        CSC200
   * Our queue should only contain nodes with no prereqs.
   * Once we process a node, remove it's outgoing edge
   * and if a neighbor has no more prereqs, add it to the queue.
   *
   * To detect cycles, we count the number of nodes we've processed,
   * and if at the end, the number of nodes processed != number of nodes in graph,
   * there has to be a cycle.
   * This takes care of the case for disconnected graphs as well.
   * O(v + e)
   */
  public static List<String> courseOrder(Map<String, List<String>> courses) {
    HashMap<String, Node> graph = generateGraph(courses);
    List<String> order = new LinkedList<String>();
    LinkedList<Node> q = new LinkedList<Node>();

    for(String course : graph.keySet()) {
      Node n = graph.get(course);
      if(n.numPrereqs == 0)
        q.offer(n);
    }

    while(! q.isEmpty()) {
      Node n = q.poll();
      order.add(n.course);
      for(Node neighbor : n.prereqs) {
        neighbor.numPrereqs--;
        if(neighbor.numPrereqs == 0)
          q.offer(neighbor);
      }
    }
    return (order.size() == graph.size() ? order : null);
  }

  private static HashMap<String, Node> generateGraph(Map<String, List<String>> courses) {
    HashMap<String, Node> graph = new HashMap<String, Node>();
    // initialize nodes
    for(String course : courses.keySet()) {
      graph.put(course, new Node(course));
    }
    //create edges
    for(String course : courses.keySet()) {
      Node n = graph.get(course);
      for(String prereq : courses.get(course)) {
        Node other = graph.get(prereq);
        other.prereqs.add(n);
        n.numPrereqs++;
      }
    }
    return graph;
  }
}

class Node {
  String course;
  Set<Node> prereqs;
  int numPrereqs; // Don't have to manually remove prereq edges -> just decrement this variable

  public Node(String course) {
    this.course = course;
    prereqs = new HashSet<Node>();
    numPrereqs = 0;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder(course + " : [ ");
    for(Node n : prereqs) {
      sb.append(n.course + " ");
    }
    sb.append("] ");
    return sb.toString();
  }
}
