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
     * @param newFirst : Erste 2D Punkt des Paares
     * @param newSecond : Zweite 2D Punkt des Paares
     */
    public Pair(Point newFirst, Point newSecond) {
        if (newFirst.compareTo(newSecond) <= 0) {
            first = newFirst;
            second = newSecond;
        } else {
            first = newSecond;
            second = newFirst;
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
     * Gibt die Darstellung des Paares zurück
     * @return String : Visualisierung first - second
     *                bzw. (x, y) - (x, y)
     */
    @Override
    public String toString() {
        return first.toString() + " - " + second.toString();
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
}
