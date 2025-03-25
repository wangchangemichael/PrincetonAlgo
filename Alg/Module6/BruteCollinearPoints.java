package Alg.Module6;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private final ArrayList<LineSegment> segments;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();
        for (Point p : points) {
            if (p == null) throw new IllegalArgumentException();
        }
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0) throw new IllegalArgumentException();
            }
        }
        Point[] pointsCopy = Arrays.copyOf(points, points.length);
        Arrays.sort(pointsCopy);
        segments = new ArrayList<>();
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                for (int k = j + 1; k < points.length; k++) {
                    for (int l = k + 1; l < points.length; l++) {
                        double slopeij = pointsCopy[i].slopeTo(pointsCopy[j]);
                        double slopeik = pointsCopy[i].slopeTo(pointsCopy[k]);
                        double slopeil = pointsCopy[i].slopeTo(pointsCopy[l]);
                        if (slopeij == slopeik && slopeik == slopeil) {
                            segments.add(new LineSegment(pointsCopy[i], pointsCopy[l]));
                        }

                    }
                }

            }
        }
    }    // finds all line segments containing 4 points


    public int numberOfSegments() {
        return segments.size();


    }        // the number of line segments

    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[segments.size()]);
    }


}
