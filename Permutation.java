import edu.princeton.cs.algs4.StdIn;

import java.util.Iterator;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        int count = 0;
        RandomizedQueue<String> strq = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            String next = StdIn.readString();
            count++;
            if (count <k){
                strq.enqueue(next);
            }else{
                strq.dequeue();
                strq.enqueue(next);
            }
        }

        Iterator<String> iter = strq.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }

}
