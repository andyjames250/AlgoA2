/*----------------------------------------------------------------
 *  Author:        Andy James
 *  Written:       7/2/2014
 *  Last updated:  7/6/2014
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
        private Node<Item> next;
        private Node<Item> prev;
        private Item item;
    }
    
    public Deque() { // construct an empty deque
        s.next = s; // the first element of the list
        s.prev = s; // the last element of the list
        s.item = null; 
        N = 0;
        // assert check();
    }
    
    public boolean isEmpty() { // is the deque empty?
        return N == 0;
    }
    
    public int size() { // return the number of items on the deque
        return N;
    }
    
    public void addFirst(Item item) { // insert the item at the front
        if (item == null) throw new NullPointerException();
        Node<Item> newFirst = new Node<Item>();
//        Node<Item> oldFirst = s.next;
        newFirst.item = item;
        newFirst.next = s.next;
        newFirst.prev = s;
        s.next.prev = newFirst;
        s.next = newFirst;
        N++;
        // assert check();
    }
    
    public void addLast(Item item) { // insert the item at the end
        if (item == null) throw new NullPointerException();
        Node<Item> newLast = new Node<Item>();
//        Node<Item> oldLast = s.prev;
        newLast.item = item;
        newLast.next = s;
        newLast.prev = s.prev;
        s.prev.next = newLast;
        s.prev = newLast;
        N++;     
        // assert check();
    }
    
    public Item removeFirst() { // delete and return the item at the front
        if (isEmpty()) throw new NoSuchElementException("Deque underflow");
        Item item = s.next.item; // save item to return
        s.next = s.next.next; // delete first node
        N--;
        // assert check();
        return item; // return the saved item    
    }
    
    public Item removeLast() { // delete and return the item at the end
        if (isEmpty()) throw new NoSuchElementException("Deque underflow");
        Item item = s.prev.item;
        s.prev = s.prev.prev;
        N--;
        if (isEmpty()) s.prev = null; // to avoid loitering
        // assert check();
        return item;

    }
    
    public Iterator<Item> iterator() { // return an iterator over items in order from front to end
        return new DequeIterator<Item>(s.next);
    }
    
    private class DequeIterator<Item> implements Iterator<Item> { // iterator class
        private Node<Item> current;
        
        public DequeIterator(Node<Item> first) {
            current = first;
        }
        
        public boolean hasNext()  { return current != null && current != s; }
        public void remove()      { throw new UnsupportedOperationException(); }

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
            if (s.next != null) return false;
        }
        else if (N == 1) {
            if (s.prev == null) return false;
            if (s.next != null) return false;
        }
        else {
            if (s.next.next == null) return false;
        }

        // check internal consistency of instance variable N
        int numberOfNodes = 0;
        for (Node x = s.next; x != null; x = x.next) {
            numberOfNodes++;
        }
        if (numberOfNodes != N) return false;

        return true;
    }
    
    public static void main(String[] args) {  // unit testing
        
    }
}
