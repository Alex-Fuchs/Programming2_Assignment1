package closest_pairs_of_points;

/**
 * Pair
 *
 * Version: 14.11.19
 *
 * Stellt ein Paar von 2D Punkten eines Fields dar
 */
class Pair implements Comparable<Pair> {

    /*
     * Paar (first, second) von 2D Punkten,
     * Es gilt immer first <= second
     */
    private Point first;
    private Point second;

    /*
     * Packaged Konstruktor der Klasse Pair
     * Es wird sichergestellt, dass in dem Paar (first, second) der erste
     * Punkt <= dem zweiten Punkt gilt
     * @param first : Erste 2D Punkt des Paares
     * @param second : Zweite 2D Punkt des Paares
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
     * Vergleicht die Paare lexikographisch nach den beiden ersten Punkten der
     * Paare und bei Gleichheit nach den beiden zweiten Punkten
     * @param other : Paar wird mit dem Paar other verglichen
     * @return int : 1 wird zurückgegeben, falls this > other
     *              -1 wird zurückgegeben, falls this < other
     *               0 wird zurückgegeben, falls this = other
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
     * Gibt die kanonische Darstellung des Paares zurück
     * @return String : first - second bzw. (x, y) - (x, y)
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
