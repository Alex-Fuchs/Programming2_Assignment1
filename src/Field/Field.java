/**
 * Field.Field
 *
 * Version:
 */

package Field;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Field {

    private List <Point> points;                    //Punkte des Fields
    private IdentDistPairs identDistPairs;

    public Field() {
        points = new ArrayList<Point>();
    }

    public boolean add(int x, int y) {
        Point toAdd = new Point(x, y);
        if (!points.contains(toAdd)) {
            return points.add(new Point(x, y));
        } else {
            return false;
        }
    }

    public boolean remove(int x, int y) {
        return points.remove(new Point(x, y));
    }

    public String print() {
        StringBuilder allPoints = new StringBuilder();
        allPoints.append("points: ");

        sortByX();
        for (int i = 0; i < points.size(); i++) {
            allPoints.append(points.get(i).toString());
            if(i < points.size() - 1) {
                allPoints.append(", ");
            }
        }
        return allPoints.toString();
    }

    public String distance() {
        if (points.size() > 1) {
            sortByX();
            List<Point> sortedByY = sortByY();
            determineDistance(sortedByY);
            return identDistPairs.toString();
        } else {
            return null;
        }
    }

    private void determineDistance(List <Point> sortedByY) {
        if (points.size() > 3) {
            Field left = new Field();
            Field right = new Field();
            divideFields(left, right);

            left.determineDistance(sortedByY);
            right.determineDistance(sortedByY);
            identDistPairs = left.mergeIdentDistPairs(right);
        } else {
            calculate();
        }
    }

    private void divideFields(Field left, Field right) {
        int rightSize = points.size() / 2;
        int leftSize = points.size() - rightSize;

        left.setPoints(points.subList(0, leftSize));
        right.setPoints(points.subList(leftSize, points.size()));
    }

    private void calculate() {
        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                Point first = points.get(i);
                Point second = points.get(j);
                double distance = first.distance(second);

                if (identDistPairs == null) {
                    identDistPairs = new IdentDistPairs(
                            distance, first, second);
                } else {
                    identDistPairs.newPairDistance(distance, first, second);
                }
            }
        }
    }

    private IdentDistPairs mergeIdentDistPairs(Field other) {
        return identDistPairs.compareTo(other.identDistPairs);
    }

    private void setPoints(List <Point> newPoints) {
        points = newPoints;
    }

    private void sortByX() {
        Collections.sort(points);
    }

    private List <Point> sortByY() {
        List <Point> sortedByY = points.subList(0, points.size() - 1);
        Collections.sort(sortedByY, new PointVertComp());
        return sortedByY;
    }
}
