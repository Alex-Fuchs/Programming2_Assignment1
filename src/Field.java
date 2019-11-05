/**
 * Field
 *
 * Version:
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Field {

    private List <Point> sortedByX;
    private List <Point> sortedByY;
    private IdentDistPairs identDistPairs;

    public Field() {
        sortedByX = new ArrayList<Point>();
        sortedByY = new ArrayList<Point>();
        identDistPairs = new IdentDistPairs();
    }

    public boolean add(int x, int y) {
        if (findPoint(x, y) == null) {
            Point point = new Point(x, y);
            sortedByX.add(point);
            sortedByY.add(point);
            return true;
        } else {
            return false;
        }
    }

    private void addSortedByX(Point p) {
        sortedByX.add(p);
    }

    public boolean remove(int x, int y) {
        Point point = findPoint(x, y);
        if (point != null) {
            sortedByX.remove(point);
            sortedByY.remove(point);
            return true;
        } else {
            return false;
        }
    }

    public String print() {
        return "";
    }

    public double distance() {
        sortByX();
        sortByY();
        setDistance();
        return identDistPairs.getDistance();
    }

    private Point findPoint(int x, int y)
    {
        Point point = new Point(x, y);
        for (Point p: sortedByX) {
            if (p.equals(point)) {
                return p;
            }
        }
        return null;
    }

    private void setDistance() {
        if (sortedByX.size() > 3) {
            Field left = new Field();
            Field right = new Field();
            divide(left, right);

        } else {
            determineDistance();
        }
    }

    private void divide(Field left, Field right) {
        int rightSize = sortedByX.size() / 2;
        int leftSize = sortedByX.size() - rightSize;

        for (int i = 0; i < leftSize; i++) {
            left.addSortedByX(sortedByX.get(i));
        }

        for (int i = 0; i < rightSize; i++) {
            right.addSortedByX(sortedByX.get(i));
        }
    }

    private void determineDistance() {
        for (int i = 0; i < sortedByX.size(); i++) {
            for(int j = i + 1; j < sortedByX.size(); j++) {
                Point first = sortedByX.get(i);
                Point second = sortedByX.get(j);
                double distance = first.distance(second);

                identDistPairs.checkDistance(distance, first, second);
            }
        }
    }

    public void sortByX() {
        Collections.sort(sortedByX);
    }

    public void sortByY() {

    }
}
