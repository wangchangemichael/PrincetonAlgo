package Alg.Module2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] result;
    private double mean = 0.0;
    private double stddev = 0.0;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        this.result = new double[trials];
        mean = 0.0;
        stddev = 0.0;
        for (int i = 1; i <= trials; i++) {
            Percolation newtrial = new Percolation(n);
            int sum = 0;
            int j = 0;
            while (!newtrial.percolates()) {
                int randNum = StdRandom.uniformInt(n * n);
                int row = randNum / n + 1;
                int col = randNum % n + 1;
                while (newtrial.isOpen(row, col)) {
                    randNum = StdRandom.uniformInt(n * n);
                    row = randNum / n + 1;
                    col = randNum % n + 1;
                }
                newtrial.open(row, col);
                sum++;
            }
            result[i - 1] = (double) sum / (n * n);

        }


    }

    // sample mean of percolation threshold
    public double mean() {
        mean = StdStats.mean(result);
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        stddev = StdStats.stddev(result);
        return stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return (this.mean() - 1.96 * this.stddev() / Math.sqrt(this.result.length));

    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return (this.mean() + 1.96 * this.stddev() / Math.sqrt(this.result.length));
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n, trials);
        System.out.println("mean                    = " + ps.mean());
        System.out.println("stddev                  = " + ps.stddev());
        System.out.println("95% confidence interval = [" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]");


    }
}
