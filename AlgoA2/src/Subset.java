/*----------------------------------------------------------------
 *  Author:        Andy James
 *  Written:       7/2/2014
 *  Last updated:  7/6/2014
 *
 *  Compilation:   javac-algs4 Subset.java
 *  Execution:     java-algs4 Subset N
 *  
 *  Implement subset client.
 *
 *----------------------------------------------------------------*/

public class Subset {
   public static void main(String[] args) {
       int k = Integer.parseInt(args[0]);
       RandomizedQueue<String> q = new RandomizedQueue<String>();
       while (!StdIn.isEmpty()) q.enqueue(StdIn.readString());
       for (int i = 0; i < k; i++)
           System.out.println(q.dequeue());
   }
}