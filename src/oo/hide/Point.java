package oo.hide;

public class Point {

    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point[x=%s, y=%s]".formatted(x, y);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Point other)) {
            return false;
        }

        return x == other.x && y== other.y;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(x, y);
    }

}
