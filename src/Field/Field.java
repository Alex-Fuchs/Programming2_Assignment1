/**
 * Field
 *
 * Version:
 */

package Field;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Field {

    private List <Point> points;                    //Punkte des Fields
    private IdentDistPairs identDistPairs;          //Kürz. Distanz mit Paaren

    /**
     * Öffentlicher Konstruktor der Klasse Field
     */
    public Field() {
        points = new ArrayList<Point>();
    }

    /**
     * Fügt einen Punkt (x, y) in das Field hinzu. Falls dieser in dem Field
     * schon existiert, wird kein weiterer Punkt hinzugefügt.
     * @param x : x-Wert des neuen Punktes
     * @param y : y-Wert des neuen Punktes
     * @return boolean : true, falls der Punkt erfolgreich hinzugefügt wurde
     *                 false, falls der Punkt schon vorhanden war
     */
    public boolean add(int x, int y) {
        Point toAdd = new Point(x, y);
        if (!points.contains(toAdd)) {
            return points.add(new Point(x, y));
        } else {
            return false;
        }
    }

    /**
     * Entfernt einen Punkt (x, y) aus dem Field. Falls dieser nicht vorhanden
     * war, passiert nichts
     * @param x : x-Wert des zu entfernenden Punktes
     * @param y : y-Wert des zu entfernenden Punktes
     * @return boolean : true, falls der Punkt erfolgreich entfernt wurde
     *                 false, falls der Punkt nicht vorhanden war
     */
    public boolean remove(int x, int y) {
        return points.remove(new Point(x, y));
    }

    /**
     * Gibt die Darstellung des Fields zurück
     * @return String : String der Darstellung
     */
    public String print() {
        sortByX();
        StringBuilder allPoints = new StringBuilder();
        allPoints.append("points: ");
        for (int i = 0; i < points.size(); i++) {
            allPoints.append(points.get(i).toString());
            if (i < points.size() - 1) {
                allPoints.append(", ");
            }
        }
        return allPoints.toString();
    }

    /**
     * Berechnet die kürzeste Distanz des Fields und gibt diese mit den
     * zugehörigen Paaren in einer Darstellung zurück
     * @return String : null, falls keine Distanz existiert
     *                andernfalls die Darstellung mit dem String
     */
    public String distance() {
        if (points.size() > 1) {
            sortByX();
            List<Point> sortedByY = sortByY();
            determineDistance(sortedByY);

            IdentDistPairs distance = identDistPairs;
            identDistPairs = null;
            return distance.toString();
        } else {
            return null;
        }
    }

    /**
     * rekursive Hilfsfunktion von distance. Berechnet bei weniger als 4
     * Punkten die Distanz per Brute-Force, ansonsten mit einem Herrsche-Teile
     * Verfahren durch Aufspalten der Fields in 2 gleich große Teile.
     * @param sortedByY : nach y-sortierte points des Oberfields
     */
    private void determineDistance(List <Point> sortedByY) {
        if (points.size() > 3) {
            Field left = new Field();
            Field right = new Field();
            List <Point> leftSortedByY = new ArrayList<Point>();
            List <Point> rightSortedByY = new ArrayList<Point>();

            int median = divideFields(left, right);
            createSortedByYLists(leftSortedByY, rightSortedByY, sortedByY);

            left.determineDistance(leftSortedByY);
            right.determineDistance(rightSortedByY);
            identDistPairs = left.mergeIdentDistPairs(right);
            conquer(median, leftSortedByY, rightSortedByY);
        } else {
            calculate();
        }
    }

    /**
     * Spaltet das Field für den rekursiven Schritt in zwei gleich große
     * Fields auf. Setzt zudem die Seite der Punkte für diesen
     * Teile-Schritt richtig
     * @param left : linkes Field
     * @param right : rechtes Field
     * @return leftSize - 1 : Median des Fields
     */
    private int divideFields(Field left, Field right) {
        int rightSize = points.size() / 2;
        int leftSize = points.size() - rightSize;
        List <Point> leftPoints = points.subList(0, leftSize);
        List <Point> rightPoints = points.subList(leftSize, points.size());
        setSide(leftPoints, rightPoints);

        left.setPoints(leftPoints);
        right.setPoints(rightPoints);
        return leftSize - 1;
    }

    /**
     * Berechnet bei weniger als 4 Punkten die kürzeste Distanz per
     * Brute-Force
     */
    private void calculate() {
        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                Point first = points.get(i);
                Point second = points.get(j);
                if (identDistPairs == null) {
                    identDistPairs = new IdentDistPairs(first, second);
                } else {
                    identDistPairs.newPairDistance(first, second);
                }
            }
        }
    }

    /**
     * Fügt die Fields endgültig mit dem Teile-Herrsche Verfahren zusammen
     * @param median : Median des Fields
     * @param leftSortedByY : points des linken Fields nach y-Wert, rückwärts
     *                     sortiert
     * @param rightSortedByY : points des rechten Fields nach y-Wert, rückwärts
     *                      sortiert
     *
     */
    private void conquer(int median, List <Point> leftSortedByY,
                         List <Point> rightSortedByY) {
        int partingLine = points.get(median).getX();
        Separator separator = new Separator(identDistPairs, leftSortedByY,
                                            rightSortedByY, partingLine);
        separator.setDistance();
    }

    /**
     * Fügt Listen für das linke und rechte Field die Punkte nach y-Wert
     * rückwärts sortiert hinzu
     * @param left : Liste für das linke Field
     * @param right : Liste für das rechte Field
     * @param sortedByY : Liste des gesamten Fields, rückwärts nach y-Wert
     *                  sortiert
     */
    private void createSortedByYLists(List <Point> left, List <Point> right,
                                      List <Point> sortedByY) {
        for (Point p: sortedByY) {
            if (p.getSide() == Side.LEFT) {
                left.add(p);
            } else if (p.getSide() == Side.RIGHT) {
                right.add(p);
            } else if (p.getSide() == Side.UNSET) {
                throw new IllegalStateException();
            }
        }
    }

    /*
     * Setzmethode. Setzt das Attribut Side in jedem Punkt nach den Listen
     * als Parameter
     * @param left : points des linken Fields
     * @param right : points des rechten Fields
     */
    private void setSide(List <Point> left, List <Point> right) {
        for (Point p: left) {
            p.setSide(Side.LEFT);
        }
        for (Point p: right) {
            p.setSide(Side.RIGHT);
        }
    }

    /*
     * Fügt die identDistPairs der 2 aufgespaltenen Fields zu einem zusammen.
     * Tritt im Zusammenfügen zum Oberfield auf, um die kürzeste Distanz der
     * zwei Unterfields zu vereinigen
     * @param other : Field, mit dem verschmolzen werden soll
     * @return IdentDistPairs : Rückgabe des zusammengefügten identDistPairs
     */
    private IdentDistPairs mergeIdentDistPairs(Field other) {
        return identDistPairs.compareTo(other.identDistPairs);
    }

    /*
     * Setzmethode
     * @param newPoints : Liste der Punkte
     */
    private void setPoints(List <Point> newPoints) {
        points = newPoints;
    }

    /*
     * Sortiert points lexikographisch nach x- und bei Gleichheit auf y-Wert
     */
    private void sortByX() {
        Collections.sort(points);
    }

    /*
     * Sortiert eine zu points äqivalente Liste mit gleichen Punkten
     * lexikographisch nach y- und bei Gleichheit nach x-Wert
     * @return sortedByX : sortierte Liste
     */
    private List <Point> sortByY() {
        List <Point> sortedByY = new ArrayList<Point>(points);
        Collections.sort(sortedByY, (new PointVertComp()));
        return sortedByY;
    }
}
