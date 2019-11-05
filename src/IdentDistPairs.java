/**
 * IdentDistPairs
 *
 * Version:
 */

import java.util.ArrayList;
import java.util.List;

public class IdentDistPairs {

    private Double distance;                    //momentane kurzeste Distanz
    private List <Pair> shortestDistance;       //Paare mit dieser Distanz

    /**
     * Öffentlicher Konstruktor
     */
    public IdentDistPairs() {
        shortestDistance = new ArrayList<Pair>();
    }

    /**
     * Setzt die Liste auf die bisher bekannten Paaren dieser Distanz und
     * ersetzt die alte Distanz, falls eine kleinere gefunden wird
     * @param distance : momentan kürzeste Distanz des Fields
     * @param first : zum Paar zugehörigerer erster Punkt
     * @param second : zum Paar zugehöriger zweiter Punkt
     */
    public void checkDistance(double distance, Point first, Point second) {
        int comparison = compareDoubles(distance);

        if (this.distance == null || comparison == -1) {
            this.distance = distance;
            shortestDistance.clear();
            shortestDistance.add(new Pair(first, second));
        } else if (comparison == 0) {
            shortestDistance.add(new Pair(first, second));
        }
    }

    /**
     * Rückgabemethode
     * @return distance : momentane kürzeste Distanz von einem Paar
     */
    public Double getDistance() {
        return distance;
    }

    /**
     * Vergleicht zwei double auf eine Unschärfe von 0.000001 Radius.
     * Falls der Double distance noch nicht gesetzt wurde, wird dieser hier
     * gesetzt
     * @param other : Double distance wird mit dem double other verglichen
     * @return int : 1 wird zurückgegeben, falls distance > other
     *              -1 wird zurückgegeben, falls distance < other
     *               0 wird zurückgegeben, falls distance = other
     */
    private int compareDoubles(double other) {
        double epsilon = 0.000001;

        if (distance <= (other + epsilon) && distance >= (other - epsilon)) {
            return 0;
        } else if (distance > other) {
            return 1;
        } else {
            return -1;
        }
    }
}
