package oo.hide;

import java.util.Arrays;

public class PointSet {

    private Point[] points;
    private int size;

    public PointSet(int capacity) {
        points = new Point[capacity];
    }

    public PointSet() {

        this(10);
    }

    public void add(Point point) {
        if (contains(point)) {
            return;
        }

        if (size == points.length) {
            points = Arrays.copyOf(points, points.length * 2);
        }

        points[size++] = point;
    }

    public int size() {
        return size;
    }

    public boolean contains(Point point) {
        for (int i = 0; i < size; i++) {
            if (points[i] == null && point == null) {
                return true;
            }

            if (points[i] != null && points[i].equals(point)) {
                return true;
            }
        }

        return false;
    }

    public PointSet subtract(PointSet other) {
        PointSet result = new PointSet();

        for (int i = 0; i < size; i++) {
            if (!other.contains(points[i])) {
                result.add(points[i]);
            }
        }

        return result;
    }

    public PointSet intersect(PointSet other) {
        PointSet result = new PointSet();

        for (int i = 0; i < size; i++) {
            if (other.contains(points[i])) {
                result.add(points[i]);
            }
        }

        return result;
    }

    public void remove(Point point) {
        for (int i = 0; i < size; i++) {
            if ((points[i] == null && point == null) ||
                    (points[i] != null && points[i].equals(point))) {

                points[i] = points[size - 1];
                size--;
                return;
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PointSet other)) {
            return false;
        }

        if (this.size() != other.size()) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (!other.contains(points[i])) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        return 0; // no need to change this
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "";
        }

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < size; i++) {
            if (points[i] == null) {
                result.append("null");
            } else {
                result.append(points[i].toString()
                        .replace("Point[x=", "(")
                        .replace(", y=", ", ")
                        .replace("]", ")"));
            }

            if (i < size - 1) {
                result.append(", ");
            }
        }

        return result.toString();
    }
}