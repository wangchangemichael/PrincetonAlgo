import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private node head;
    private node last;
    private int size;

    public Deque() {
        head = null;
        last = null;
        size = 0;
    }

    ;


    private class node {
        Item val;
        node next;
        node prev;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public Iterator<Item> iterator() {

        return new DequeIterator();
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (head == null) {
            head = new node();
            head.val = item;
            head.prev = null;
            head.next = null;
            last = head;
            size++;
            return;
        }
        node oldhead = head;
        head = new node();
        head.val = item;
        head.next = oldhead;
        oldhead.prev = head;
        head.prev = null;
        size++;
    }


    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        if (head == null) {
            addFirst(item);
            return;
        }
        node oldlast = last;
        last = new node();
        last.val = item;
        oldlast.next = last;
        last.prev = oldlast;
        size++;
    }

    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        if (size == 1) {
            Item res = head.val;
            head = null;
            last = null;
            return res;
        }
        node oldhead = head;
        head = head.next;
        size--;
        return oldhead.val;
    }

    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        if (size == 1) {
            Item res = head.val;
            head = null;
            last = null;
            return res;
        }
        node oldlast = last;
        last = last.prev;
        last.next = null;
        size--;
        return oldlast.val;


    }

    private class DequeIterator implements Iterator<Item> {
        private node front = head;

        @Override
        public boolean hasNext() {
            return front != null;
        }

        @Override
        public Item next() {
            if (front == null) {
                throw new NoSuchElementException();
            }
            node result = front;
            front = front.next;
            return result.val;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }


    }


    public static void main(String[] args) {
        Deque<Integer> d = new Deque<>();
        StdOut.println(d.isEmpty());
        System.out.println(d.size());
        d.addFirst(1);
        StdOut.println(d.isEmpty());
        d.addLast(2);
        System.out.println(d.size());
        Iterator<Integer> d1 = d.iterator();
        System.out.println(d1.next());
        System.out.println(d1.next());
        StdOut.println(d.isEmpty());
        StdOut.println(d.removeFirst());
        System.out.println(d.size());
        StdOut.println(d.isEmpty());
        StdOut.println(d.removeLast());
        StdOut.println(d.isEmpty());

    }


}








