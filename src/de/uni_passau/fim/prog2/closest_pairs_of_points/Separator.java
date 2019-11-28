package closest_pairs_of_points;

import java.util.ArrayList;
import java.util.List;

/**
 * {@code Separator} separiert das {@code Field}, das diesen erstellt hat,
 * zudem wird das aufgespaltene {@code Field} so zusammengefügt,
 * dass das {@code Field} nun die kürzeste Distanz und die
 * {@code Pairs} dieser Distanz gespeichert hat.
 *
 * @version 14.11.19
 * @author -----
 */
class Separator {

    /**
     * identDistPairs des {@code Fields}, das den {@code Separator} erstellt
     * hat.
     */
    private IdentDistPairs identDistPairs;
    /**
     * Trennlinie des {@code Separator}, an der sich die 2 {@code Fields}
     * trennen.
     */
    private int partingLine;
    /**
     * Liste der nach y-Wert sortierten {@code Points} des {@code Fields}.
     */
    private List<Point> sortedByY;

    /**
     * Erstellt den {@code Separator} mit den nötigen Informationen des
     * {@code Field}, das den {@code Separator} erstellt hat.
     *
     * @param identDistPairs    {@code IdentDIstPairs} des {@code Fields}
     * @param sortedByY         Liste des {@code Fields}, nach y-Wert sortiert
     * @param partingLine       Trennlinie des {@code Separator}
     * @see                     IdentDistPairs
     */
    Separator(IdentDistPairs identDistPairs, List<Point> sortedByY,
              int partingLine) {
        this.identDistPairs = identDistPairs;
        this.partingLine = partingLine;
        this.sortedByY = sortedByY;
    }

    /**
     * Setzt die Distanz durch den Rekonstruktionsschritt des
     * Teile-Herrsche-Verfahren vollständig richtig.
     */
    void setDistance() {
        List<Point> criticalPoints = getInCriticalDistance();
        calculate(criticalPoints);
    }

    /**
     * Berechnet die kürzeste Distanz, die zwischen zwei {@code Fields}
     * auftreten kann. Betrachtet den Spezialfall, das die
     * kürzeste Verbindung von zwei {@code Points} die Trennlinie schneidet.
     *
     * @param criticalPoints    Liste der {@code Points}, die für eine
     *                          kürzeste Distanz in Frage kommen und im
     *                          kritischen Bereich sind.
     * @see                     IdentDistPairs#newPairDistance(Point, Point)
     */
    private void calculate(List<Point> criticalPoints) {
        /*
         * Betrachtet werden nur Punkte von unterschiedlichen Seiten, da nur
         * diese Paare noch nicht betrachtet wurden. Aufgrund des Verfahrens
         * müssen nur pointsToWatch nachfolgende Punkte betrachtet werden.
         */
        final int pointsToWatch = 4;
        for (int i = 0; i < criticalPoints.size(); i++) {
            for (int u = i + 1; u <= i + pointsToWatch
                                && u < criticalPoints.size(); u++) {
                Point first = criticalPoints.get(i);
                Point second = criticalPoints.get(u);
                if (first.getSide() != second.getSide()) {
                    identDistPairs.newPairDistance(first, second);
                }
            }
        }
    }

    /**
     * Kreiert eine neue Liste, die von der nach y-Wert sortierten Liste des
     * {@code Fields} lediglich die {@code Points} im kritischen Bereich
     * enthält, da nur diese im folgenden Schritt betrachtet werden müssen.
     *
     * @return      Liste der kritischen {@code Points}
     */
    private List<Point> getInCriticalDistance() {
        List<Point> criticalPoints = new ArrayList<Point>();
        double criticalDistance = identDistPairs.getDistance();
        for (Point p: sortedByY) {
            if (Math.abs(partingLine - p.getX()) <= criticalDistance) {
                criticalPoints.add(p);
            }
        }
        return criticalPoints;
    }
}
