package closest_pairs_of_points;

/**
 * {@code Pair} stellt ein Paar (first, second) von {@code Points} dar.
 *
 * @version 14.11.19
 * @author -----
 */
class Pair implements Comparable<Pair> {

    /**
     * Entspricht den ersten {@code Point} des {@code Pair}.
     */
    private Point first;
    /**
     * Entspricht den zweiten {@code Point} des {@code Pair}.
     */
    private Point second;

    /**
     * Packaged Konstruktor, es wird sichergestellt, dass in dem {@code Pair}
     * (first, second) gilt: {@code first} kleiner gleich {@code second}.
     *
     * @param first     Erste {@code Point}
     * @param second    Zweite {@code Point}
     * @see             #compareTo(Pair)
     * @see             Point
     */
    Pair(Point first, Point second) {
        if (first.compareTo(second) <= 0) {
            this.first = first;
            this.second = second;
        } else {
            this.first = second;
            this.second = first;
        }
    }

    /**
     * Vergleicht {@code Pairs} lexikographisch nach den beiden ersten
     * {@code Points} und bei Gleichheit nach den beiden zweiten.
     *
     * @param other     {@code this} wird mit dem {@code Pair other}
     *                  verglichen
     * @return          1, falls {@code this} größer als {@code other}.
     *                  -1, falls {@code this} kleiner als {@code other}.
     *                  0, falls {@code this} gleich {@code other}.
     * @see             Point#compareTo(Point)
     */
    @Override
    public int compareTo(Pair other) {
        if (first.compareTo(other.getFirst()) < 0) {
            return -1;
        } else if (first.compareTo(other.getFirst()) > 0) {
            return 1;
        } else if (second.compareTo(other.getSecond()) < 0) {
            return -1;
        } else if (second.compareTo(other.getSecond()) > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Gibt die kanonische Darstellung des {@code Pairs} zurück.
     *
     * @return      {@code String} als first - second bzw. (x, y) - (x, y)
     */
    @Override
    public String toString() {
        return first.toString() + " - " + second.toString();
    }

    private Point getFirst() {
        return first;
    }

    private Point getSecond() {
        return second;
    }
}
