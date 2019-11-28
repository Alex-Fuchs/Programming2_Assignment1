package closest_pairs_of_points;

import java.util.Comparator;

/**
 * {@code PointVertComp} ist ein {@code Comparator} zum Vergleichen
 * von {@code Points}, lexikographisch nach y-Wert und bei Gleichheit
 * nach x-Wert.
 *
 * @version 14.11.19
 * @author -----
 */
class PointVertComp implements Comparator<Point> {

    /**
     * Packaged Konstruktor. Objekte werden lediglich zum Vergleichen erzeugt.
     */
    PointVertComp() { }

    /**
     * Vergleicht zwei {@code Points} lexikographisch nach y-Wert und bei
     * Gleiheit auf x-Wert.
     *
     * @param p1        erster {@code Point}
     * @param p2        zweiter {@code Point}
     * @return          1, falls {@code p1} größer als {@code p2}.
     *                  -1, falls {@code p1} kleiner als {@code p2}.
     *                  0, falls {@code p1} gleich {@code p2}.
     */
    @Override
    public int compare(Point p1, Point p2) {
        return p1.compareToY(p2);
    }
}
