/**
 * IdentDistPairs
 *
 * Version:
 */

package Field;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class IdentDistPairs {

    private double distance;                    //momentane kurzeste Distanz
    private List <Pair> pairs;                  //Paare mit dieser Distanz


    /**
     * Packaged Konstruktor der Klasse IdentDistPairs. Kann nur in
     * Verbindung mit einem Paar erstellt werden.
     * @param newDistance : Distanz des ersten hinzugefügten Paares
     * @param first : zum Paar zugehöriger erster Punkt
     * @param second : zum Paar zugehöriger zweiter Punkt
     */
    IdentDistPairs(double newDistance, Point first, Point second) {
        pairs = new ArrayList<Pair>();
        setDistance(newDistance, first, second);
    }

    /**
     * Setzt die Liste auf die bisher bekannten Paaren dieser Distanz und
     * ersetzt die alte Distanz, falls eine kleinere gefunden wird.
     * @param newDistance : Distanz eines neuen Paares
     * @param first : zum Paar zugehörigerer erster Punkt
     * @param second : zum Paar zugehöriger zweiter Punkt
     */
    void newPairDistance(double newDistance, Point first, Point second) {
            int comparison = compareDistances(newDistance);
            if (comparison == 1) {
                pairs.clear();
                setDistance(newDistance, first, second);
            } else if (comparison == 0) {
                pairs.add(new Pair(first, second));
            }
    }

    /**
     * Vergleicht zwei identDistPairs aus unterschiedlichen Feldern auf deren
     * Distanz und gibt das identDistPairs mit der Kleineren zurück. Bei
     * Gleichheit werden diese verschmolzen
     * @param other : IdentDistPairs wird mit other verglichen
     * @return IdentDistPairs : mit kleinerer Distanz
     */
    IdentDistPairs compareTo(IdentDistPairs other) {
        int comparison = compareDistances(other.getDistance());
        if (comparison < 0) {
            return this;
        } else if (comparison > 0) {
            return other;
        } else {
            return merge(other.getPairs());
        }
    }

    /**
     * Gibt die Darstellung des identdistPairs zurück
     * @return String : String mit der Darstellung
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        double distanceRounded = Math.round(distance * 100) / 100d;

        String[] toAppend = {"distance: ", String.valueOf(distanceRounded), "\n", "pairs: "};
        for (String tmp: toAppend) {
            stringBuilder.append(tmp);
        }

        sortPairs();
        for (int i = 0; i < pairs.size(); i++) {
            stringBuilder.append(pairs.get(i).toString());

            if(i < pairs.size() - 1) {
                stringBuilder.append(", ");
            }
        }
        return stringBuilder.toString();
    }

    /**
     * Hilfsmethode für den Spezialfall des Mergen der identDistPairs.
     * Paar Duplikate nicht möglich, da 2 Punkte nur aus dem selben Feld sein
     * können
     * @param otherPairs : Liste der Paare von der anderen identDistPairs
     * @return this : mit der verschmolzenen Paarliste
     */
    private IdentDistPairs merge(List <Pair> otherPairs) {
        pairs.addAll(otherPairs);
        return this;
    }

    /**
     * Sortiert die Paare lexikographisch nach ersten und bei Gleichheit auf
     * zweiten Punkt. Punkte werden lexikographisch nach x-Wert und bei
     * Gleichheit auf y-Wert sortiert
     */
    private void sortPairs() {
        Collections.sort(pairs);
    }

    /**
     * Vergleicht zwei double auf eine Unschärfe von 0.000001 Radius.
     * @param other : Double distance wird mit dem double other verglichen
     * @return int : 1 wird zurückgegeben, falls distance > other
     *              -1 wird zurückgegeben, falls distance < other
     *               0 wird zurückgegeben, falls distance = other
     */
    private int compareDistances(double other) {
        final double epsilon = 0.000001;
        if (Math.abs(distance - other) <= epsilon) {
            return 0;
        } else if (distance > other) {
            return 1;
        } else {
            return -1;
        }
    }

    /**
     * Setzmethode der Distanz, nur mit einem Paar möglich
     * @param newDistance : neue kürzere Distanz
     * @param first : erster Punkt des Paares
     * @param second : zweiter Punkt des Paares
     */
    private void setDistance(double newDistance, Point first, Point second) {
        distance = newDistance;
        pairs.add(new Pair(first, second));
    }

    /**
     * Rückgabemethode
     * @return distance : momentane kürzeste Distanz von einem Paar
     */
    private double getDistance() {
        return distance;
    }

    /**
     * Rückgabefunktion
     * @return pairs : Liste der Paare
     */
    private List <Pair> getPairs() {
        return pairs;
    }
}
