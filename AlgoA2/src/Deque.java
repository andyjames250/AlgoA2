/*----------------------------------------------------------------
 *  Author:        Andy James
 *  Written:       7/2/2014
 *  Last updated:  7/2/2014
 *
 *  Compilation:   javac-algs4 Deque.java
 *  Execution:     java-algs4 Deque args
 *  
 *  Implement a double-ended queue (deque).
 *
 *----------------------------------------------------------------*/

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int N; // size of deque                           
    private Node<Item> s; // pointer to sentinel node in deque
    
    private class Node<Item> { // helper linked list class
        private Item item;
        private Node<Item> next;
        private Node<Item> prev;
    }
    
    public Deque() { // construct an empty deque
        s.next = s; // the first element of the list
        s.prev = s; // the last element of the list
        N = 0;
        // assert check();
    }
    
    public boolean isEmpty() { // is the deque empty?
        return s.next == s;
    }
    
    public int size() { // return the number of items on the deque
        return N;
    }
    
    public void addFirst(Item item) { // insert the item at the front
        if (item == null) throw new NullPointerException();
        Node<Item> oldfirst = s.next;
        s.next = new Node<Item>();
        s.next.item = item;
        s.next.next = oldfirst;
        s.next.prev = s;
        oldfirst.prev = s.next;
        // if (isEmpty()) s.prev = s.next;
        N++;
        // assert check();
    }
    
    public void addLast(Item item) { // insert the item at the end
        if (item == null) throw new NullPointerException();
        Node<Item> oldlast = s.prev;
        s.prev = new Node<Item>();
        s.prev.item = item;
        s.prev.next = null;
        // if (isEmpty()) first = last;
        // else oldlast.next = last;
        N++;     
        // assert check();
    }
    
    public Item removeFirst() { // delete and return the item at the front
        if (isEmpty()) throw new NoSuchElementException("Deque underflow");
        Item item = first.item; // save item to return
        first = first.next; // delete first node
        N--;
        // assert check();
        return item; // return the saved item    
    }
    
    public Item removeLast() { // delete and return the item at the end
        if (isEmpty()) throw new NoSuchElementException("Deque underflow");
        Item item = first.item;
        first = first.next;
        N--;
        if (isEmpty()) last = null; // to avoid loitering
        // assert check();
        return item;

    }
    
    public Iterator<Item> iterator() { // return an iterator over items in order from front to end
        return new DequeIterator<Item>(first);
    }
    
    private class DequeIterator<Item> implements Iterator<Item> { // iterator class
        private Node<Item> current;
        
        public DequeIterator(Node<Item> first) {
            current = first;
        }
        
        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }
    
    private boolean check() // check internal invariants
    {
        if (N == 0) {
            if (first != null) return false;
        }
        else if (N == 1) {
            if (first == null)      return false;
            if (first.next != null) return false;
        }
        else {
            if (first.next == null) return false;
        }

        // check internal consistency of instance variable N
        int numberOfNodes = 0;
        for (Node x = first; x != null; x = x.next) {
            numberOfNodes++;
        }
        if (numberOfNodes != N) return false;

        return true;
    }
    
    public static void main(String[] args) {  // unit testing
        
    }
}
