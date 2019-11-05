/**
 * Point
 *
 * Version:
 */

public class Point implements Comparable <Point> {

    private int x;      // x-Wert des 2D Punktes
    private int y;      // y-Wert des 2D Punktes

    /**
     * Öffentlicher Konstruktor
     * @param x : x-Wert des 2D Punktes
     * @param y : y-Wert des 2D Punktes
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Rückgabemethode
     * @return x : x-Wert des Punktes
     */
    private int getX() {
        return x;
    }

    /**
     * Rückgabemethode
     * @return y : y-Wert des Punktes
     */
    private int getY() {
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
        } else {
            return 0;
        }
    }

    /**
     * Prüft Gleichheit der Punkte auf x- und y-Wert
     * @param other : Punkt wird mit dem Punkt other verglichen
     * @return boolean : true wird bei Gleichheit zurückgegeben
     *                   false andernfalls
     */
    @Override
    public boolean equals(Object other) {
        return (other instanceof Point && compareTo( (Point) other) == 0);
    }

    /**
     * Berechnet die euklidische Distanz von 2 Punkten
     * @param other : Punkt wird mit dem Punkt other verrechnet
     * @return : Distanz der Punkte
     */
    public double distance(Point other) {
        double xLength = x - other.getX();
        double yLength = y - other.getY();

        return Math.sqrt(xLength * xLength + yLength * yLength);
    }
}