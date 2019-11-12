/**
 * Field.Point
 *
 * Version:
 */

package Field;

class Point implements Comparable <Point> {

    private int x;                      // x-Wert des 2D Punktes
    private int y;                      // y-Wert des 2D Punktes
    private Side side;                  // Seite des Oberfields

    /**
     * Packaged Konstruktor der Klasse Point
     * @param newX : x-Wert des 2D Punktes
     * @param newY : y-Wert des 2D Punktes
     */
    Point(int newX, int newY) {
        x = newX;
        y = newY;
        side = Side.UNSET;
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
     * Vergleicht die Punkte lexikographisch nach den y-Wert
     * und bei Gleichheit auf den x-Wert
     * @param other : Punkt wird mit dem Punkt other verglichen
     * @return int : 1 wird zurückgegeben, falls this > other
     *              -1 wird zurückgegeben, falls this < other
     *               0 wird zurückgegeben, falls this = other
     */
    int compareToY(Point other) {
        if (y < other.getY()) {
            return -1;
        } else if (y > other.getY()) {
            return 1;
        } else if (x < other.getX()) {
            return -1;
        } else if (x > other.getX()) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Rückgabemethode
     * @return x : x-Wert des Punktes
     */
    int getX() {
        return x;
    }

    /**
     * Setzmethode
     * @param newSide : Seite des Oberfields
     */
    void setSide(Side newSide) {
        side = newSide;
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
     * @return double : Distanz der Punkte
     */
    double distance(Point other) {
        double xLength = x - other.getX();
        double yLength = y - other.getY();

        return Math.sqrt(xLength * xLength + yLength * yLength);
    }

    /**
     * Gibt die Darstellung des Punktes zurück
     * @return String : (x, y)
     */
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    /**
     * Rückgabefunktion
     * @return side : Seite des Punktes im Oberfield
     */
    Side getSide() {
        return side;
    }

    /**
     * Rückgabemethode
     * @return y : y-Wert des Punktes
     */
    private int getY() {
        return y;
    }
}
