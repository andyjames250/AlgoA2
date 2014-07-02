/*----------------------------------------------------------------
 *  Author:        Andy James
 *  Written:       7/2/2014
 *  Last updated:  7/2/2014
 *
 *  Compilation:   javac-algs4 Deque.java
 *  Execution:     java-algs4 Deque N
 *  
 *  Implement a randomized queue.
 *
 *----------------------------------------------------------------*/

public class RandomizedQueue<Item> implements Iterable<Item> {
   public RandomizedQueue()                 // construct an empty randomized queue
   public boolean isEmpty()                 // is the queue empty?
   public int size()                        // return the number of items on the queue
   public void enqueue(Item item)           // add the item
   public Item dequeue()                    // delete and return a random item
   public Item sample()                     // return (but do not delete) a random item
   public Iterator<Item> iterator()         // return an independent iterator over items in random order
   public static void main(String[] args)   // unit testing
}