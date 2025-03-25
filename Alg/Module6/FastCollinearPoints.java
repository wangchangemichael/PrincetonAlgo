package Alg.Module6;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private final ArrayList<LineSegment> segments = new ArrayList<>();

    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();
        for (Point p : points) {
            if (p == null) throw new IllegalArgumentException();
        }
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0) throw new IllegalArgumentException();
            }
        }
        if (points.length < 4) return;
        Point[] pointsCopy = points.clone();
        Point[] newPoints = points.clone();
        ArrayList<Point> collinear = new ArrayList<>();
        for (int i = 0; i < pointsCopy.length; i++) {

            Arrays.sort(newPoints, pointsCopy[i].slopeOrder());
            collinear.clear();
            collinear.add(pointsCopy[i]);
            double prevslope = pointsCopy[i].slopeTo(newPoints[0]);
            collinear.add(newPoints[0]);
            for (int k = 1; k < newPoints.length; k++) {
                double currentSlope = pointsCopy[i].slopeTo(newPoints[k]);
                if (currentSlope == prevslope) {
                    collinear.add(newPoints[k]);
                } else {
                    if (collinear.size() >= 4) {
                        Point[] colarray = collinear.toArray(new Point[collinear.size()]);
                        Arrays.sort(colarray);
                        if (pointsCopy[i].compareTo(colarray[0]) == 0) {
                            segments.add(new LineSegment(colarray[0], colarray[colarray.length - 1]));

                        }

                    }
                    collinear.clear();
                    collinear.add(pointsCopy[i]);
                    collinear.add(newPoints[k]);
                }
                prevslope = currentSlope;


            }
            if (collinear.size() >= 4) {
                Point[] colarray = collinear.toArray(new Point[collinear.size()]);
                Arrays.sort(colarray);
                if (pointsCopy[i].compareTo(colarray[0]) == 0) {
                    segments.add(new LineSegment(colarray[0], colarray[colarray.length - 1]));

                }
            }
        }


    }    // finds all line segments containing 4 or more points

    public int numberOfSegments() {

        return segments.size();
    }        // the number of line segments

    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[segments.size()]);
    }                // the line segments
}
