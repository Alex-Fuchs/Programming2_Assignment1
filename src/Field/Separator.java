/**
 * Separator
 *
 * Version:
 */

package Field;

import java.util.ArrayList;
import java.util.List;

class Separator {

    IdentDistPairs identDistPairs;
    int partingLine;
    List <Point> leftSortedByY;
    List <Point> rightSortedByY;

    Separator(IdentDistPairs identDistPairs, List <Point> leftSortedByY,
                     List <Point> rightSortedByY, int partingLine) {
        this.identDistPairs = identDistPairs;
        this.partingLine = partingLine;
        this.leftSortedByY = leftSortedByY;
        this.rightSortedByY = rightSortedByY;
    }

    void setDistance() {
        List <Point> criticalPoints = createCriticalPoints();
        calculate(criticalPoints);
    }

    private void calculate(List <Point> criticalPoints) {
        for (int i = 0; i < criticalPoints.size(); i++) {
            for (int u = i + 1; u < i + 5 && u < criticalPoints.size(); u++) {
                Point first = criticalPoints.get(i);
                Point second = criticalPoints.get(u);
                if (first.getSide() != second.getSide()) {
                    identDistPairs.newPairDistance(first, second);
                }
            }
        }
    }

    private List <Point> createCriticalPoints() {
        List <Point> criticalPoints = new ArrayList<Point>();
        leftSortedByY = getInCriticalDistance(leftSortedByY);
        rightSortedByY = getInCriticalDistance(rightSortedByY);

        int pointSize = leftSortedByY.size() + rightSortedByY.size();
        int leftPointer = 0;
        int rightPointer = 0;

        for (int i = 0; i < pointSize; i++) {
            if (leftPointer < leftSortedByY.size()
                    && rightPointer < rightSortedByY.size()) {
                Point p1 = leftSortedByY.get(leftPointer);
                Point p2 = rightSortedByY.get(rightPointer);
                if (p1.compareToY(p2) >= 0) {
                    criticalPoints.add(p1);
                    leftPointer++;
                } else {
                    criticalPoints.add(p2);
                    rightPointer++;
                }
            } else if (leftPointer >= leftSortedByY.size()) {
                Point p = rightSortedByY.get(rightPointer);
                criticalPoints.add(p);
                rightPointer++;
            } else {
                Point p = leftSortedByY.get(leftPointer);
                criticalPoints.add(p);
                leftPointer++;
            }
        }
        return criticalPoints;
    }

    private List <Point> getInCriticalDistance(List <Point> list) {
        List <Point> newList = new ArrayList<Point>();
        double leftCritical = partingLine - identDistPairs.getDistance();
        double rightCritical = partingLine + identDistPairs.getDistance();
        for (Point p: list) {
            if (p.getX() >= leftCritical && p.getX() <= rightCritical) {
                newList.add(p);
            }
        }
        return newList;
    }
}
