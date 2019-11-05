/**
 * Pair
 *
 * Version:
 */

public class Pair implements Comparable<Pair> {

    // (first, second) Paar von 2D Punkten, first <= second
    private Point first;
    private Point second;

    /**
     * Öffentlicher Konstruktor
     * Es wird sichergestellt, dass in dem Paar (erster Punkt, zweiter Punkt)
     * der erste Punkt <= dem zweiten Punkt gilt
     * @param first : Erste 2D Punkt des Paares
     * @param second : Zweite 2D Punkt des Paares
     */
    public Pair(Point first, Point second) {
        if (first.compareTo(second) <= 0) {
            this.first = first;
            this.second = second;
        } else {
            this.first = second;
            this.second = first;
        }
    }

    /**
     * Rückgabemethode
     * @return : Der erste Punkt des 2-Tupels
     */
    private Point getFirst() {
        return first;
    }

    /**
     * Rückgabemethode
     * @return : Der zweite Punkt des 2-Tupels
     */
    private Point getSecond() {
        return second;
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
}
