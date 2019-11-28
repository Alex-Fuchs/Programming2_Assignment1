package closest_pairs_of_points;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * {@code Field} stellt ein Koordinatensystem dar, in dem {@code Points}
 * hinzugefügt und entfernt werden können, zudem können alle {@code Points}
 * ausgegeben werden und die kürzeste Distanz inkl aller {@code Pairs} kann
 * berechnet werden. Die Berechnung verwendet ein Teile-Herrsche Verfahren
 * mit einer Laufzeit O(n * log (n)).
 *
 * @version 14.11.19
 * @author -----
 */
public class Field {

    /**
     * Entspricht der Liste von {@code Points}, die hinzugefügt wurden.
     */
    private List<Point> points;
    /**
     * Enthält die kürzeste Distanz mit allen {@code Pairs}.
     */
    private IdentDistPairs identDistPairs;

    /**
     * Öffentlicher Konstruktor. Initialisiert die Liste der {@code Points}.
     */
    public Field() {
        points = new ArrayList<Point>();
    }

    /**
     * Fügt einen {@code Point} (x, y) in das {@code Field} hinzu.
     * Falls dieser schon existiert, wird nichts hinzugefügt.
     *
     * @param x     {@code int} des x-Wertes des neuen {@code Point}
     * @param y     {@code int} des y-Wertes des neuen {@code Point}
     * @return      {@code true}, falls erfolgreich hinzugefügt wurde.
     *              {@code false}, andernfalls.
     * @see         Point
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
     * Entfernt einen {@code Point} (x, y) aus dem {@code Field}.
     * Falls dieser nicht vorhanden war, passiert nichts.
     *
     * @param x     {@code int} des x-Wertes des zu entfernenden {@code Point}
     * @param y     {@code int} des y-Wertes des zu entfernenden {@code Point}
     * @return      {@code true}, falls erfolgreich entfernt wurde.
     *              {@code false}, andernfalls.
     * @see         Point
     */
    public boolean remove(int x, int y) {
        return points.remove(new Point(x, y));
    }

    /**
     * Gibt die kanonische Darstellung der nach x-Wert sortierten
     * {@code points} des {@code Fields} zurück.
     *
     * @return      {@code String} der kanonischen Darstellung
     * @see         #sortByX()
     * @see         Point
     */
    @Override
    public String toString() {
        StringBuilder allPoints = new StringBuilder();
        sortByX();
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
     * Berechnet die kürzeste Distanz des {@code Fields} und gibt diese mit
     * den zugehörigen {@code Pairs} in einer Darstellung zurück. Setzt zudem
     * {@code identDistPairs} für die nächste Distanzsuche vollständig zurück.
     *
     * @return      {@code null}, falls keine Distanz existiert.
     *              {@code String} mit der Darstellung andernfalls.
     * @see         IdentDistPairs
     * @see         Pair
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
     * Berechnet bei weniger als 4 {@code Points} die Distanz per Brute-Force,
     * ansonsten mit einem Teile-Herrsche-Verfahren durch Aufspalten von
     * {@code Fields} und Rekombinieren. Rekursive Hilfsfunktion
     * von distance().
     *
     * @param sortedByY     nach y-sortierte Liste der {@code Points} des
     *                      {@code Fields}
     * @see                 #calculate()
     * @see                 #mergeIdentDistPairs(Field)
     * @see                 #divideFields(Field, Field)
     * @see                 #conquer(int, List)
     * @see                 #createSortedByYLists(List, List, List)
     */
    private void determineDistance(List<Point> sortedByY) {
        final int minDivideSize = 4;
        if (points.size() >= minDivideSize) {
            /*
             * Bildet mit divideFields zwei Unterfields left und right, dazu
             * nach y-Wert sortierte Listen, führt die Rekursion aus und fügt
             * diese darauf durch mergeIdentDistPairs und conquer wieder
             * zusammen
             */
            Field left = new Field();
            Field right = new Field();
            List<Point> leftSortedByY = new ArrayList<Point>();
            List<Point> rightSortedByY = new ArrayList<Point>();
            int median = divideFields(left, right);
            createSortedByYLists(leftSortedByY, rightSortedByY, sortedByY);

            left.determineDistance(leftSortedByY);
            right.determineDistance(rightSortedByY);
            identDistPairs = left.mergeIdentDistPairs(right);
            conquer(median, sortedByY);
        } else {
            calculate();
        }
    }

    /**
     * Spaltet das {@code Field} für den rekursiven Schritt auf. Setzt zudem
     * die {@code Side} der {@code Points} für das Kreieren der
     * nach y-Wert sortierten Listen.
     *
     * @param left      linkes {@code Field}, das vorher initialisiert wurde
     * @param right     rechtes {@code Field}, das vorher initialisiert wurde
     * @return          Median der Spaltung bzw. letzter {@code Point} des
     *                  nach x-Wert sortierten, linken {@code Fields}
     * @see             #setSide(int)
     */
    private int divideFields(Field left, Field right) {
        int rightSize = points.size() / 2;
        int leftSize = points.size() - rightSize;
        List<Point> leftPoints = points.subList(0, leftSize);
        List<Point> rightPoints = points.subList(leftSize, points.size());
        setSide(leftSize - 1);
        left.setPoints(leftPoints);
        right.setPoints(rightPoints);
        return leftSize - 1;
    }

    /**
     * Berechnet bei weniger als 4 Punkten die kürzeste Distanz per
     * Brute-Force. Erstellt ggf ein {@code IdentDistPairs}, da dieses nur
     * existiert, wenn bereits eine Distanz bekannt ist.
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
     * Fügt die {@code Fields} endgültig mit dem Teile-Herrsche Verfahren
     * zusammen. Setzt zudem die {@code Side} der {@code Points} erneut,
     * da diese in der Rekursion nicht diesem {@code Field} entsprechend
     * gesetzt wurden.
     *
     * @param median        Median der Spaltung
     * @param sortedByY     nach y-sortierte Liste der {@code Points} des
     *                      {@code Fields}
     * @see                 #setSide(int)
     */
    private void conquer(int median, List<Point> sortedByY) {
        int partingLine = points.get(median).getX();
        Separator separator
        = new Separator(identDistPairs, sortedByY, partingLine);
        setSide(median);
        separator.setDistance();
    }

    /**
     * Kreiert nach y-Wert sortierte Listen für das linke und rechte
     * {@code Field}. Dabei müssen die {@code Sides} der {@code Points}
     * davor gesetzt werden.
     *
     * @param left          Liste der {@code Points} für das linke
     *                      {@code Field}
     * @param right         Liste der {@code Points} für das rechte
     *                      {@code Field}
     * @param sortedByY     nach y-sortierte Liste der {@code Points} des
     *                      {@code Fields}
     * @see                 #setSide(int)
     */
    private void createSortedByYLists(List<Point> left, List<Point> right,
                                      List<Point> sortedByY) {
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

    /**
     * Setzt die {@code Sides} der {@code Points} anhand des Medians,
     * da vorher nach x-Wert sortiert wurde.
     *
     * @param median    Median der Spaltung
     * @see             Point#setSide(Side)
     */
    private void setSide(int median) {
        for (int i = 0; i < points.size(); i++) {
            if (i <= median) {
                points.get(i).setSide(Side.LEFT);
            } else {
                points.get(i).setSide(Side.RIGHT);
            }
        }
    }

    /**
     * Fügt die {@code IdentDistPairs} der 2 {@code Fields} zu einem zusammen.
     * Beachtet lediglich kürzeste Distanzen in den getrennten {@code Fields}.
     *
     * @param other     {@code Field}, mit dem verschmolzen werden soll
     * @return          zusammengefügtes {@code IdentDistPairs}
     * @see             IdentDistPairs#compare(IdentDistPairs)
     */
    private IdentDistPairs mergeIdentDistPairs(Field other) {
        return identDistPairs.compare(other.identDistPairs);
    }

    /**
     * Sortiert {@code points} lexikographisch nach x- und bei Gleichheit
     * auf y-Wert.
     */
    private void sortByX() {
        Collections.sort(points);
    }

    /**
     * Sortiert eine zu {@code points} äqivalente Liste lexikographisch nach
     * y- und bei Gleichheit nach x-Wert.
     *
     * @return          nach y-Wert sortierte Liste von {@code points}
     * @see             PointVertComp
     */
    private List<Point> sortByY() {
        List<Point> sortedByY = new ArrayList<Point>(points);
        Collections.sort(sortedByY, (new PointVertComp()));
        return sortedByY;
    }

    private void setPoints(List<Point> points) {
        this.points = points;
    }
}
