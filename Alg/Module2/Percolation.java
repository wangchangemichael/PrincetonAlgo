package Alg.Module2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.In;

public class Percolation {
    private boolean[][] grid;
    private WeightedQuickUnionUF uf2;
    private WeightedQuickUnionUF uf1;
    private int N;
    private int virtualTop;
    private int virtualBottom;
    private int opens;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        N = n;
        grid = new boolean[n][n];
        virtualTop = N * N;
        virtualBottom = N * N + 1;
        this.opens = 0;
        uf1 = new WeightedQuickUnionUF(N * N + 1);
        uf2 = new WeightedQuickUnionUF(n * n + 2); // #N*N is the virtual top site N*N+1 is the virtual bottom site

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = false;
//                uf.union(0, i*n+j);
            }
        }

        for (int i = 0; i < N; i++) {
            uf1.union(i, virtualTop);
            uf2.union(i, virtualTop);
        }

        for (int i = 0; i < N; i++) {
            uf2.union(n * (n - 1) + i, virtualBottom);

        }

    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        row = row - 1;
        col = col - 1;
        if (row < 0 || row > N - 1 || col < 0 || col > N - 1) {
            throw new IllegalArgumentException();
        }
        if (!grid[row][col]) {
            grid[row][col] = true;
            opens++;
        } else {
            return;
        }


        if ((row - 1) >= 0 && isOpen(row, col + 1)) {
            uf2.union((row - 1) * N + col, row * N + col);
            uf1.union((row - 1) * N + col, row * N + col);

        }
        if ((row + 1 < N) && isOpen(row + 2, col + 1)) {
            uf2.union((row + 1) * N + col, row * N + col);
            uf1.union((row + 1) * N + col, row * N + col);
        }
        if ((col - 1) >= 0 && isOpen(row + 1, col)) {
            uf2.union(row * N + col - 1, row * N + col);
            uf1.union(row * N + col - 1, row * N + col);
        }
        if ((col + 1) < N && isOpen(row + 1, col + 2)) {
            uf2.union(row * N + col + 1, row * N + col);
            uf1.union(row * N + col + 1, row * N + col);
        }


    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        row = row - 1;
        col = col - 1;
        if (row < 0 || row > N - 1 || col < 0 || col > N - 1) {
            throw new IllegalArgumentException();
        }

        return grid[row][col];

    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        row = row - 1;
        col = col - 1;
        if (row < 0 || row > N - 1 || col < 0 || col > N - 1) {
            throw new IllegalArgumentException();
        }

        return isOpen(row + 1, col + 1) && uf1.find(virtualTop) == uf1.find(row * N + col);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return opens;
    }

    // does the system percolate?
    public boolean percolates() {

        return N != 1 ? uf2.find(virtualTop) == uf2.find(virtualBottom) : (grid[0][0]);
    }

    //    // test client (optional)
//    public static void main(String[] args)
    public static void main(String[] args) {
        String filename = args[0];
        In in = new In(filename);
        Percolation per = new Percolation(in.readInt());
        while (!in.isEmpty()) {
            int row = in.readInt();
            int col = in.readInt();
            per.open(row, col);
        }
        System.out.println(per.percolates());
    }

}
