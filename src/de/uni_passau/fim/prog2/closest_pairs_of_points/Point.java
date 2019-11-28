package closest_pairs_of_points;

import java.util.Objects;

/**
 * {@code Point} stellt einen 2D Punkt (x, y) dar.
 *
 * @version 14.11.19
 * @author -----
 */
class Point implements Comparable<Point> {

    /**
     * Entspricht der x-Koordinate.
     */
    private int x;
    /**
     * Entspricht der y-Koordinate.
     */
    private int y;
    /**
     * Seite in dem momentan behandelten {@code Field}. Wird zur Spaltung
     * der nach y- sortierten Listen verwendet, zudem im {@code Separator}.
     */
    private Side side;

    /**
     * Packaged Konstruktor.
     *
     * @param x     x-Wert des {@code Points}
     * @param y     y-Wert des {@code Points}
     */
    Point(int x, int y) {
        this.x = x;
        this.y = y;
        side = Side.UNSET;
    }

    /**
     * Vergleicht die {@code Points} lexikographisch nach den x-Wert
     * und bei Gleichheit auf den y-Wert.
     *
     * @param other     {@code this} wird mit dem {@code Point other}
     *                  verglichen
     * @return          1, falls {@code this} größer als {@code other}.
     *                  -1, falls {@code this} kleiner als {@code other}.
     *                  0, falls {@code this} gleich {@code other}.
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
     * Gibt die kanonische Darstellung des {@code Points} zurück.
     *
     * @return      {@code String} der Darstellung (x, y)
     */
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    /**
     * Prüft Gleichheit der {@code Points} auf x- und y-Wert.
     *
     * @param other     {@code this} wird mit dem {@code Point other}
     *                  verglichen
     * @return          {@code true} bei Gleichheit.
     *                  {@code false} sonst.
     */
    @Override
    public boolean equals(Object other) {
        return (other instanceof Point && compareTo((Point) other) == 0);
    }

    /**
     * Generiert den Hashcode des Objekts.
     *
     * @return      Zurückgegebener Hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y, side);
    }

    /**
     * Vergleicht die {@code Points} lexikographisch nach den y-Wert
     * und bei Gleichheit auf den x-Wert.
     *
     * @param other     {@code this} wird mit dem Punkt other verglichen
     * @return          1, falls {@code this} größer als {@code other}.
     *                  -1, falls {@code this} kleiner als {@code other}.
     *                  0, falls {@code this} gleich {@code other}.
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
     * Berechnet die euklidische Distanz von 2 Punkten.
     *
     * @param other     {@code this} wird mit dem {@code Point other}
     *                  verrechnet
     * @return          Distanz der {@code Points}
     */
    double distance(Point other) {
        double xLength = x - other.getX();
        double yLength = y - other.getY();

        return Math.sqrt(xLength * xLength + yLength * yLength);
    }

    /**
     * Setzt {@code side}.
     *
     * @param side      Übergebene {@code Side}
     * @see             Side
     */
    void setSide(Side side) {
        this.side = side;
    }

    /**
     * Gibt {@code side}.
     *
     * @return      {@code side}
     * @see         Side
     */
    Side getSide() {
        return side;
    }

    /**
     * Gibt den x-Wert zurück.
     *
     * @return      {@code x}
     */
    int getX() {
        return x;
    }

    private int getY() {
        return y;
    }
}
