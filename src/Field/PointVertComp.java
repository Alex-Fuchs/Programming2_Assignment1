/**
 * PointVertComp
 *
 * Version:
 */

package Field;

import java.util.Comparator;

public class PointVertComp implements Comparator <Point> {

    /**
     * Vergleicht zwei Punkte lexikographisch nach y-Wert und bei
     * Gleiheit auf x-Wert
     * @param o1 : erster Punkt
     * @param o2 : zweiter Punkt
     * @return int : 1 wird zurückgegeben, falls o1 > o2
     *              -1 wird zurückgegeben, falls o1 < o2
     *               0 wird zurückgegeben, falls o1 = o2
     */
    @Override
    public int compare(Point o1, Point o2) {
        return o1.compareToY(o2);
    }
}
