import java.util.Stack;
import java.util.Map;
import java.util.HashMap;

/**
 * The Tower of Hanoi is a puzzle game with three rods and n disks, each a different size.
 * All the disks start off on the first rod in a stack.
 * They are ordered by size, with the largest disk on the bottom and the smallest one at the top.
 *
 * The goal of this puzzle is to move all the disks from the first rod to the last rod while following these rules:
 * - You can only move one disk at a time.
 * - A move consists of taking the uppermost disk from one of the stacks and placing it on top of another stack.
 * - You cannot place a larger disk on top of a smaller disk.
 * Write a function that prints out all the steps necessary to complete the Tower of Hanoi. You should assume that the rods are numbered, with the first rod being 1, the second (auxiliary) rod being 2, and the last (goal) rod being 3.
 *
 * For example, with n = 3, we can do this in 7 moves:
 *
 * Move 1 to 3
 * Move 1 to 2
 * Move 3 to 2
 * Move 1 to 3
 * Move 2 to 1
 * Move 2 to 3
 * Move 1 to 3
 */
 public class Problem128 {
   static String srcName = "S1", auxName = "S2", destName = "S3";
   static Stack<Integer> s1, s2, s3;

   public static void main(String[] args) {
     s1 = new Stack<Integer>();
     s2 = new Stack<Integer>();
     s3 = new Stack<Integer>();

     int n = 3;
     for(int i = n; i >= 1; i--) {
       s1.push(i);
     }

     hanoiSolver(s1, s2, s3, n);
   }

   /**
    * We can see for that n = 1, we simply solve it by moving to s3
    * For n = 2, we solve it by moving 1 to s2 and 2 to s3, then move 1 to s3.
    *
    * Another way to think about it is solving n = 1 by using s2 as the destination,
    * then moving 2 to s3, then solving n = 1 by using s2 as a source and s3 as the destination.
    *
    * Likewise, for n = 3, we can think of it as the following:
    * Recursively solve n = 2 using s1 as source and s2 as dest.
    * Move 3 to s3.
    * Recursively solve n = 2 using s2 as source and s3 as dest.
    *
    * O((2^n) - 1) Time -> n = 1: 1 step, n = 2: 3, n = 3: 3 + 3 + 1 = 7, ...
    * O(n) space
    */
   public static void hanoiSolver(Stack<Integer> src, Stack<Integer> aux, Stack<Integer> dest,
   int n) {
     if(n <= 0)
      return;
     String sName = (src == s1 ? srcName : (src == s2 ? auxName : destName));
     String dName = (dest == s1 ? srcName : ( dest == s2 ? auxName : destName));
     if(n == 1) { // base case -> move to dest
       dest.push(src.pop());
       System.out.println("Move " + sName + " to " + dName);
     }
     else {
       hanoiSolver(src, dest, aux, n - 1);
       dest.push(src.pop());
       System.out.println("Move " + sName + " to " + dName);
       hanoiSolver(aux, src, dest, n - 1);
     }
   }
 }
