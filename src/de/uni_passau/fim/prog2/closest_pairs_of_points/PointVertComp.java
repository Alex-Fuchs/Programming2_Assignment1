package de.uni_passau.fim.prog2.closest_pairs_of_points;

import java.util.Comparator;

/**
 * PointVertComp
 *
 * Version: 14.11.19
 *
 * Comparator zum Vergleichen der Punkte
 */
class PointVertComp implements Comparator<Point> {

    PointVertComp() {}

    /**
     * Vergleicht zwei Punkte lexikographisch nach y-Wert und bei
     * Gleiheit auf x-Wert
     * @param p1 : erster Punkt
     * @param p2 : zweiter Punkt
     * @return int : 1 wird zurückgegeben, falls p1 > p2
     *              -1 wird zurückgegeben, falls p1 < p2
     *               0 wird zurückgegeben, falls p1 = p2
     */
    @Override
    public int compare(Point p1, Point p2) {
        return p1.compareToY(p2);
    }
}
