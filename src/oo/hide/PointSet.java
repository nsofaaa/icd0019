package oo.hide;

public class PointSet {

    public PointSet(int capacity) {
    }

    public PointSet() {

        this(10);
    }

    public void add(Point point) {
    }

    public int size() {
        return 0;
    }

    public boolean contains(Point point) {
        return false;
    }

    public PointSet subtract(PointSet other) {
        return null;
    }

    public PointSet intersect(PointSet other) {
        return null;
    }

    public void remove(Point point) {
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PointSet other)) {
            return false;
        }

        return this.size() == other.size();
    }

    @Override
    public int hashCode() {
        return 0; // no need to change this
    }
}
