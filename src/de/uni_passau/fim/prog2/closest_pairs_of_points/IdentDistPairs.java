package closest_pairs_of_points;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * {@code IdentDistPairs} speichert die kürzeste Distanz eines {@code Fields},
 * inkl aller {@code Pairs} dieser Distanz. Zudem werden in dieser Klasse
 * die Distanzen verglichen und berechnet.
 *
 * @version 14.11.19
 * @author -----
 */
class IdentDistPairs {

    /**
     * Entspricht der momentan kürzesten Distanz, die gefunden wurde.
     */
    private double distance;
    /**
     * Entspricht allen {@code Pairs} mit dieser Distanz.
     */
    private List<Pair> pairs;

    /**
     * Packaged Konstruktor, kann nur in Verbindung mit einem {@code Pair}
     * erstellt werden.
     *
     * @param first     zugehöriger erster {@code Point}
     * @param second    zugehöriger zweiter {@code Point}
     * @see             Point
     */
    IdentDistPairs(Point first, Point second) {
        pairs = new ArrayList<Pair>();
        double newDistance = first.distance(second);
        setDistance(newDistance, first, second);
    }

    /**
     * Gibt die kanonische Darstellung von {@code identDistPairs} zurück.
     *
     * @return      {@code String} mit der Darstellung
     * @see         #sortPairs()
     * @see         #appendDistanceToString(StringBuilder)
     * @see         #appendPairsToString(StringBuilder)
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        sortPairs();
        appendDistanceToString(stringBuilder);
        appendPairsToString(stringBuilder);
        return stringBuilder.toString();
    }

    /**
     * Setzt {@code pairs} auf die bisher bekannten {@code Pairs} dieser
     * Distanz und ersetzt die alte, falls eine kleinere gefunden wird.
     *
     * @param first     zugehörigerer erster {@code Point}
     * @param second    zugehöriger zweiter {@code Point}
     * @see             #compareDistances(double)
     * @see             Pair
     * @see             Point
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

    /**
     * Gibt die momentan kürzeste Distanz zurück.
     *
     * @return      momentan kürzeste Distanz
     */
    double getDistance() {
        return distance;
    }

    /**
     * Vergleicht zwei {@code IdentDistPairs} aus anliegenden {@code Fields}
     * auf deren Distanz und gibt das mit der kleineren Distanz zurück.
     * Bei Gleichheit werden diese verschmolzen.
     *
     * @param other     {@code this} wird mit {@code other} verglichen
     * @return          {@code this}, falls {@code this} kleiner als
     *                  {@code other}.
     *                  {@code other}, falls {@code other} größer als
     *                  {@code this}.
     *                  {@code this} + {@code other} verschmolzen andernfalls.
     * @see             #compareDistances(double)
     * @see             #merge(List)
     */
    IdentDistPairs compare(IdentDistPairs other) {
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
     * Hilfsmethode für den Spezialfall des Mergen der {@code IdentDistPairs}.
     * Duplikate sind nicht möglich, da diese aus unterschiedlichen
     * {@code Fields} stammen.
     *
     * @param otherPairs    {@code pairs} von den anderen
     *                      {@code IdentDistPairs}
     * @return              verschmolzenes {@code IdentDistPairs}
     */
    private IdentDistPairs merge(List<Pair> otherPairs) {
        pairs.addAll(otherPairs);
        return this;
    }

    /**
     * Fügt dem {@code Stringbuilder} die kanonische Darstellung der kürzesten
     * Distanz hinzu.
     *
     * @param stringBuilder     übergebener {@code Stringbuilder}
     */
    private void appendDistanceToString(StringBuilder stringBuilder) {
        String tmp = String.format(Locale.US, "%.2f", distance);
        String[] toAppend = {"distance: ", tmp, "\n"};
        for (String s: toAppend) {
            stringBuilder.append(s);
        }
    }

    /**
     * Fügt dem {@code Stringbuilder} die kanonische Darstellung
     * von {@code pairs} hinzu.
     *
     * @param stringBuilder     übergebener {@code Stringbuilder}
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

    /**
     * Vergleicht zwei {@code double} auf eine Unschärfe von 0.000001.
     *
     * @param other     {@code distance} wird mit dem {@code double other}
     *                  verglichen
     * @return          1, falls {@code distance} größer als {@code other}.
     *                  -1, falls {@code distance} kleiner als {@code other}.
     *                  0, falls {@code distance} gleich {@code other}.
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
     * Sortiert {@code pairs} lexikographisch nach ersten und bei Gleichheit
     * auf zweiten {@code Point}.
     *
     * @see     Point#compareTo(Point)
     */
    private void sortPairs() {
        Collections.sort(pairs);
    }

    /**
     * Setzmethode der Distanz, nur mit einem {@code Pair} möglich.
     *
     * @param distance      neue kürzere Distanz
     * @param first         erste {@code Point}
     * @param second        zweiter {@code Point}
     */
    private void setDistance(double distance, Point first, Point second) {
        this.distance = distance;
        pairs.add(new Pair(first, second));
    }

    private List<Pair> getPairs() {
        return pairs;
    }

}
