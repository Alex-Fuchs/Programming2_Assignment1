package de.uni_passau.fim.prog2.closest_pairs_of_points;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * IdentDistPairs
 *
 * Version: 14.11.19
 *
 * Objekt zur Speicherung der kürzesten Distanz eines Fields. Zudem werden
 * auch alle Paare dieser Distanz gespeichert.
 */
class IdentDistPairs {

    private double distance;                    //momentane kurzeste Distanz
    private List<Pair> pairs;                  //Paare mit dieser Distanz

    /*
     * Packaged Konstruktor der Klasse IdentDistPairs. Kann nur in
     * Verbindung mit einem Paar erstellt werden.
     * @param first : zum Paar zugehöriger erster Punkt
     * @param second : zum Paar zugehöriger zweiter Punkt
     */
    IdentDistPairs(Point first, Point second) {
        pairs = new ArrayList<Pair>();
        double newDistance = first.distance(second);
        setDistance(newDistance, first, second);
    }

    /**
     * Gibt die kanonische Darstellung des identdistPairs this zurück
     * @return String : String mit der Darstellung
     */
    @Override
    public String toString() {
        sortPairs();
        StringBuilder stringBuilder = new StringBuilder();
        appendDistanceToString(stringBuilder);
        appendPairsToString(stringBuilder);
        return stringBuilder.toString();
    }

    /*
     * Setzt die Liste auf die bisher bekannten Paaren dieser Distanz und
     * ersetzt die alte Distanz, falls eine kleinere gefunden wird.
     * @param first : zum Paar zugehörigerer erster Punkt
     * @param second : zum Paar zugehöriger zweiter Punkt
     */
    void newPairDistance(Point first, Point second) {
        double newDistance = first.distance(second);
        int comparison = compareDistances(newDistance);
        if (comparison == 1) {
            pairs.clear();
            setDistance(newDistance, first, second);
        } else if (comparison == 0) {
            pairs.add(new Pair(first, second));
        }
    }

    double getDistance() {
        return distance;
    }

    /*
     * Vergleicht zwei identDistPairs aus anliegenden Fields auf deren
     * Distanz und gibt das identDistPairs mit der kleineren Distanz zurück.
     * Bei Gleichheit werden diese verschmolzen
     * @param other : this wird mit other verglichen
     * @return IdentDistPairs : this, falls this < other
     *                          other, falls other > this
     *                          this + other verschmolzen, falls other = this
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

    /*
     * Hilfsmethode für den Spezialfall des Mergen der identDistPairs.
     * Paar Duplikate sind nicht möglich, da die IdentDistPairs aus
     * unterschiedlichen Fields sind und somit alle Paare paarweise
     * verschieden sind
     * @param otherPairs : Liste der Paare von der anderen identDistPairs
     * @return this : mit der verschmolzenen Paarliste
     */
    private IdentDistPairs merge(List <Pair> otherPairs) {
        pairs.addAll(otherPairs);
        return this;
    }

    /*
     * Fügt dem Stringbuilder die kanonische Darstellung der kürzesten
     * Distanz hinzu
     */
    private void appendDistanceToString(StringBuilder stringBuilder) {
        String tmp = String.format(Locale.US, "%.2f", distance);
        String[] toAppend = {"distance: ", tmp, "\n"};
        for (String s: toAppend) {
            stringBuilder.append(s);
        }
    }

    /*
     * Fügt dem Stringbuilder die kanonische Darstellung der Paarliste hinzu
     */
    private void appendPairsToString(StringBuilder stringBuilder) {
        stringBuilder.append("pairs: ");
        for (int i = 0; i < pairs.size(); i++) {
            stringBuilder.append(pairs.get(i).toString());
            if (i < pairs.size() - 1) {
                stringBuilder.append(", ");
            }
        }
    }

    /*
     * Vergleicht zwei double auf eine Unschärfe von 0.000001.
     * @param other : distance wird mit dem double other verglichen
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

    /*
     * Sortiert die Paare lexikographisch nach ersten und bei Gleichheit auf
     * zweiten Punkt. Punkte werden lexikographisch nach x-Wert und bei
     * Gleichheit auf y-Wert sortiert
     */
    private void sortPairs() {
        Collections.sort(pairs);
    }

    /*
     * Setzmethode der Distanz, nur mit einem Paar möglich
     * @param distance : neue kürzere Distanz
     * @param first : erster Punkt des Paares
     * @param second : zweiter Punkt des Paares
     */
    private void setDistance(double distance, Point first, Point second) {
        this.distance = distance;
        pairs.add(new Pair(first, second));
    }

    private List<Pair> getPairs() {
        return pairs;
    }

}
