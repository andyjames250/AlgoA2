/*----------------------------------------------------------------
 *  Author:        Andy James
 *  Written:       7/2/2014
 *  Last updated:  7/6/2014
 *
 *  Compilation:   javac-algs4 RandomizedQueue.java
 *  Execution:     java-algs4 RandomizedQueue
 *  
 *  Implement a randomized queue.
 *
 *----------------------------------------------------------------*/
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] q; // queue elements
    private int N = 0; // number of elements on queue
    private int first = 0; // index of first element of queue
    private int last  = 0; // index of next available slot
    
    public RandomizedQueue() { // construct an empty randomized queue
        // cast needed since no generic array creation in Java
        q = (Item[]) new Object[2];
    }

    public boolean isEmpty() { // is the queue empty?
        return N == 0;
    }
    
    public int size() { // return the number of items on the queue
        return N;
    }
    
    public void enqueue(Item item) { // add the item
        // double size of array if necessary and recopy to front of array
        if (N == q.length) resize(2 * q.length); // double size of array if necessary
        q[last++] = item; // add item
        if (last == q.length) last = 0; // wrap-around
        N++;
    }
    
    public Item dequeue() { // delete and return a random item
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        exchange(q, first, first + StdRandom.uniform(N - 1));
        Item item = q[first];
        q[first] = null;                            // to avoid loitering
        N--;
        first++;
        if (first == q.length) first = 0;           // wrap-around
        // shrink size of array if necessary
        if (N > 0 && N == q.length / 4) resize(q.length / 2); 
        return item;        
    }
    
    public Item sample() { // return (but do not delete) a random item
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return q[StdRandom.uniform(N - 1) + first];        
    }
    
    public Iterator<Item> iterator() { // return an independent iterator over items in random order
        return new RandomizedQueueIterator();
    }
    
    private class RandomizedQueueIterator implements Iterator<Item> { // an iterator
//        private int count = 0;
        private int i = 0;
        private int[] idx;

        public RandomizedQueueIterator() {
            idx = new int[N];
            for (int j = 0; j < N; j++)
                idx[j] = j;
            StdRandom.shuffle(idx);
        }
        
        public boolean hasNext() { return i < N; }
        public void remove() { throw new UnsupportedOperationException(); }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = q[(idx[i] + first) % q.length];
            i++;
            return item;
        }
    }
    
    private void resize(int max) { // resize the underlying array
        assert max >= N;
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
            temp[i] = q[(first + i) % q.length];
        }
        q = temp;
        first = 0;
        last  = N;
    }

    private void exchange(Item[] a, int i, int j) { // swap entries i & j in the array a
        if (i == j)
            return;
        Item swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
    
    public static void main(String[] args) { // unit testing
        
        
    }
}