package Alg.Module1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        if (StdIn.isEmpty()) {
            StdOut.println("The file is empty");
            return;
        }
        int i = 1;
        String champ = "";
        while(!StdIn.isEmpty()) {
            String word = StdIn.readString();
            boolean printornot = StdRandom.bernoulli(1.0/i);
            if (printornot) {
                champ = word;
            }

            i++;

        }

        StdOut.println(champ);



    }

}
