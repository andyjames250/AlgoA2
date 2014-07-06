/*----------------------------------------------------------------
 *  Author:        Andy James
 *  Written:       7/2/2014
 *  Last updated:  7/6/2014
 *
 *  Compilation:   javac-algs4 Deque.java
 *  Execution:     java-algs4 Deque
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
        s = new Node<Item>();
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
//        if (isEmpty()) s.next = s; // to avoid loitering
        // assert check();
        return item; // return the saved item    
    }
    
    public Item removeLast() { // delete and return the item at the end
        if (isEmpty()) throw new NoSuchElementException("Deque underflow");
        Item item = s.prev.item;
        s.prev.prev.next = s;
        s.prev = s.prev.prev;
        N--;
//        if (isEmpty()) s.prev = s; // to avoid loitering
        // assert check();
        return item;

    }
    
    public Iterator<Item> iterator() { // return an iterator over items in order from front to end
        return new DequeIterator();
    }
    
    private class DequeIterator implements Iterator<Item> { // iterator class
        private Node<Item> current = s.next;
        
        public boolean hasNext()  { return current != s; } // removed: current != null &&
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
        Deque<Integer> d = new Deque<Integer>();
        int seqLength = 5;
        for (int i = 0; i < seqLength; i++) {
<<<<<<< HEAD
//            d.addFirst(-1*(i+1));
            d.addFirst(i+1);            
        }
        System.out.println("Status: " + d.isEmpty() + " " + d.size());
        for (int i = 0; i < seqLength-2; i++) {
//            System.out.println(d.removeLast());
            System.out.println(d.removeFirst());
=======
            d.addFirst(-1*(i+1));
//            d.addLast(i+1);            
        }
        System.out.println("Status: " + d.isEmpty() + " " + d.size());
        for (int i = 0; i < seqLength; i++) {
            System.out.println(d.removeLast());
//            System.out.println(d.removeFirst());
>>>>>>> FETCH_HEAD
        }
        System.out.println("Status: " + d.isEmpty() + " " + d.size());
        d.addFirst(6);
        System.out.println("Status: " + d.isEmpty() + " " + d.size());
        System.out.println(d.removeLast());
//        System.out.println(d.removeFirst());
        System.out.println("Status: " + d.isEmpty() + " " + d.size());
        d.addLast(7);
        System.out.println("Status: " + d.isEmpty() + " " + d.size());
//        System.out.println(d.removeLast());
        System.out.println(d.removeFirst());
        System.out.println("Status: " + d.isEmpty() + " " + d.size());
        for (int i = 0; i < seqLength; i++) {
            d.addFirst(i+1+15);
        }
        for (int i = 0; i < seqLength; i++) {
            System.out.println(d.removeLast());
        }
        System.out.println("Status: " + d.isEmpty() + " " + d.size());
//      System.out.println(d.removeLast());
//      System.out.println(d.removeFirst());
//        d.addFirst(null);
      for (int i = 0; i < seqLength; i++) {
          d.addFirst(i+1);
//          d.addLast(i+1);            
      }
<<<<<<< HEAD
      System.out.println("Status: " + d.isEmpty() + " " + d.size());
      Iterator<Integer> iter1 = d.iterator();
      Iterator<Integer> iter2 = d.iterator(); 
//      iter1.remove();
      System.out.println("while");
      while (iter1.hasNext()) {
          System.out.println(iter1.next());
      }
//    iter1.next();
      System.out.println("Status: " + d.isEmpty() + " " + d.size());
      while (iter2.hasNext()) {
          System.out.println(iter2.next());
      }
      System.out.println("Status: " + d.isEmpty() + " " + d.size());
      System.out.println("foreach");
      for (Iterator<Integer> i = d.iterator(); i.hasNext(); ) { 
          System.out.println(i.next());
      }
      System.out.println("Status: " + d.isEmpty() + " " + d.size());
      System.out.println("Double foreach");      
      for (Iterator<Integer> i = d.iterator(); i.hasNext(); ) { 
          System.out.println("i " + i.next());
          for (Iterator<Integer> j = d.iterator(); j.hasNext(); ) { 
              System.out.println("j " + j.next());             
          }
      }
      System.out.println("Status: " + d.isEmpty() + " " + d.size());

=======
      Iterator<Integer> iterator = d.iterator();
//      iterator.remove();
      while (iterator.hasNext()) {
          System.out.println(iterator.next());
      }
//      iterator.next();
>>>>>>> FETCH_HEAD
    }
}
