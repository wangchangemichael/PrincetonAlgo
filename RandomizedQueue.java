import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    Item[] items;
    int size;
    int head;
    // construct an empty randomized queue
    public RandomizedQueue(){
        items = (Item[]) new Object[2];
        head = 0;
        size = 0;
    }


    // is the randomized queue empty?
    public boolean isEmpty(){
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size(){
        return size;
    }

    // add the item
    public void enqueue(Item item){
        if (item == null) throw new IllegalArgumentException();
        if(size == items.length){
            Item[] itemsnew = (Item[]) new Object[items.length*2];
            int pInNew = 0;
            for (int i = 0; i < items.length; i++){
                if (items[i] != null) itemsnew[pInNew++] = items[i];
            }
            items = itemsnew;
            head = size;
        }
        items[head++] = item;
        size++;
    }

    // remove and return a random item
    public Item dequeue(){
        if (size == 0) throw new NoSuchElementException();
        int len = items.length;
        int choice = StdRandom.uniformInt(len);
        while(items[choice] == null){
            choice = StdRandom.uniformInt(len);
        };
        Item res = items[choice];
        items[choice] = null;
        size--;
        if (size > 0 && size == items.length/4){
            Item[] itemsnew = (Item[]) new Object[items.length/2];
            int pInNew = 0;
            for (int i = 0; i < items.length; i++){
                if (items[i] != null) itemsnew[pInNew++] = items[i];
            }
            items = itemsnew;
        }
        return res;
    }

    // return a random item (but do not remove it)
    public Item sample(){
        if (size == 0) throw new NoSuchElementException();
        int len = items.length;
        int choice = StdRandom.uniformInt(len);
        while(items[choice] == null){
            choice = StdRandom.uniformInt(len);
        };
        return items[choice];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator(){
        return new RandomizedQueueIterator();

    }




    public class RandomizedQueueIterator implements Iterator<Item> {

        private int current;
        private Item[] storage;
        public RandomizedQueueIterator(){
            storage = (Item[]) new Object[size];
            int pInNew = 0;
            for (int i = 0; i < items.length; i++){
                if (items[i] != null) storage[pInNew++] = items[i];
            }
            StdRandom.shuffle(storage);
            current = size-1;
        }
        @Override
        public boolean hasNext() {
            return current >= 0;

        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return storage[current--];
        }


        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }


    // unit testing (required)
    public static void main(String[] args){
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        System.out.println(rq.isEmpty());
        rq.enqueue(1);
        rq.enqueue(2);
        System.out.println(rq.size());
        System.out.println(rq.isEmpty());
        System.out.println(rq.sample());
        rq.enqueue(3);
        Iterator<Integer> it = rq.iterator();
        System.out.println(it.hasNext());
        System.out.println(it.next());
        System.out.println(it.next());
        System.out.println(it.next());
        System.out.println(rq.dequeue());
        rq.size();
        Iterator<Integer> it2 = rq.iterator();
        System.out.println(it2.hasNext());
        System.out.println(it2.next());
        System.out.println(it2.next());

    }
}
