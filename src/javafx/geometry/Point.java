

package javafx.geometry;

import javafx.beans.NamedArg;


// PENDING_DOC_REVIEW of this whole class

public class Point {

    public static final Point ZERO = new Point(0.0, 0.0);


    private double x;
    public final double getX() {
        return x;
    }
    private double y;
    public final double getY() {
        return y;
    }
    private int hash = 0;

    public Point(@NamedArg("x") double x, @NamedArg("y") double y) {
        this.x  = x;
        this.y = y;
    }

    public double distance(double x1, double y1) {
        double a = getX() - x1;
        double b = getY() - y1;
        return Math.sqrt(a * a + b * b);
    }
    public double distance(Point point) {
        return distance(point.getX(), point.getY());
    }
    public Point add(double x, double y) {
        return new Point(
                getX() + x,
                getY() + y);
    }
    public Point add(Point point) {
        return add(point.getX(), point.getY());
    }

    public Point subtract(double x, double y) {
        return new Point(
                getX() - x,
                getY() - y);
    }
    public Point multiply(double factor) {
        return new Point(getX() * factor, getY() * factor);
    }
    public Point subtract(Point point) {
        return subtract(point.getX(), point.getY());
    }
    public Point normalize() {
        final double mag = magnitude();

        if (mag == 0.0) {
            return new Point(0.0, 0.0);
        }

        return new Point(
                getX() / mag,
                getY() / mag);
    }
    public Point midpoint(double x, double y) {
        return new Point(
                x + (getX() - x) / 2.0,
                y + (getY() - y) / 2.0);
    }
    public Point midpoint(Point point) {
        return midpoint(point.getX(), point.getY());
    }
    public double angle(double x, double y) {
        final double ax = getX();
        final double ay = getY();

        final double delta = (ax * x + ay * y) / Math.sqrt(
                (ax * ax + ay * ay) * (x * x + y * y));

        if (delta > 1.0) {
            return 0.0;
        }
        if (delta < -1.0) {
            return 180.0;
        }

        return Math.toDegrees(Math.acos(delta));
    }
    public double angle(Point point) {
        return angle(point.getX(), point.getY());
    }
    public double angle(Point p1, Point p2) {
        final double x = getX();
        final double y = getY();

        final double ax = p1.getX() - x;
        final double ay = p1.getY() - y;
        final double bx = p2.getX() - x;
        final double by = p2.getY() - y;

        final double delta = (ax * bx + ay * by) / Math.sqrt(
                (ax * ax + ay * ay) * (bx * bx + by * by));

        if (delta > 1.0) {
            return 0.0;
        }
        if (delta < -1.0) {
            return 180.0;
        }

        return Math.toDegrees(Math.acos(delta));
    }
    public double magnitude() {
        final double x = getX();
        final double y = getY();

        return Math.sqrt(x * x + y * y);
    }
    public double dotProduct(double x, double y) {
        return getX() * x + getY() * y;
    }
    public double dotProduct(Point vector) {
        return dotProduct(vector.getX(), vector.getY());
    }
    public Point3D crossProduct(double x, double y) {
        final double ax = getX();
        final double ay = getY();

        return new Point3D(
                0, 0, ax * y - ay * x);
    }
    public Point3D crossProduct(Point vector) {
        return crossProduct(vector.getX(), vector.getY());
    }
    @Override public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj instanceof Point) {
            Point other = (Point) obj;
            return getX() == other.getX() && getY() == other.getY();
        } else return false;
    }


    @Override public int hashCode() {
        if (hash == 0) {
            long bits = 7L;
            bits = 31L * bits + Double.doubleToLongBits(getX());
            bits = 31L * bits + Double.doubleToLongBits(getY());
            hash = (int) (bits ^ (bits >> 32));
        }
        return hash;
    }
    @Override public String toString() {
        return "Point [x = " + getX() + ", y = " + getY() + "]";
    }
}
