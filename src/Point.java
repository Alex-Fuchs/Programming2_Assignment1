/**
 * Point
 *
 * Version:
 */

public class Point implements Comparable <Point> {

    private double x;       // x-Wert des 2D Punktes
    private double y;       // y-Wert des 2D Punktes

    /**
     * Öffentlicher Konstruktor der Klasse Point
     * @param x : x-Wert des 2D Punktes
     * @param y : y-Wert des 2D Punktes
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Rückgabefunktion
     * @return x : x-Wert des Punktes
     */
    private double getX() {
        return x;
    }

    /**
     * Rückgabefunktion
     * @return y : y-Wert des Punktes
     */
    private double getY() {
        return y;
    }

    /**
     * Vergleicht die Punkte lexikographisch nach den x-Wert
     * und bei Gleichheit auf den y-Wert
     * @param other : Punkt wird mit dem Punkt other verglichen
     * @return int : 1 wird zurückgegeben, falls this > other
     *              -1 wird zurückgegeben, falls this < other
     *               0 wird zurückgegeben, falls this = other
     */
    @Override
    public int compareTo(Point other) {
        if (x < other.getX()) {
            return -1;
        } else if (x > other.getX()) {
            return 1;
        } else if (y < other.getY()) {
            return -1;
        } else if (y > other.getY()) {
            return 1;
        }
        return 0;
    }

    /**
     * Prüft Gleichheit auf x- und y-Wert
     * @param other : Punkt wird mit dem Punkt other verglichen
     * @return boolean : true wird zurückgegeben, falls this = other
     *                   false wird zurückgegeben, falls this != other
     */
    @Override
    public boolean equals(Object other) {
        if ((other instanceof Point) && (compareTo( (Point) other) == 0)) {
            return true;
        }
        return false;
    }
}
