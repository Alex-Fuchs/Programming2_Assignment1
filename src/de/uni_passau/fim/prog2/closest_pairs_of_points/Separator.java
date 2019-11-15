package closest_pairs_of_points;

import java.util.ArrayList;
import java.util.List;

/**
 * Separator
 *
 * Version: 14.11.19
 *
 * Separiert die Unterfields des Fields, das den Separator erstellt hat.
 * Fügt zudem die Unterfields so zusammen, dass das Field nun die kürzeste
 * Distanz und die Paare dieser Distanz hat.
 */
class Separator {

    /* identDistPairs des Fields, das den Separator erstellt hat */
    private IdentDistPairs identDistPairs;
    /* Trennlinie des Separators, an der sich die 2 Unterfields trennen */
    private int partingLine;
    /* Liste des Fields, nach y-Wert sortiert */
    private List<Point> sortedByY;

    /*
     * Erstellt den Separator mit den nötigen Informationen des Fields, das
     * den Separator erstellt hat
     * @param identDistPairs : IdentDistPairs des Fields
     * @param sortedByY : Liste des Fields, nach y-Wert sortiert
     * @param partingLine : Trennlinie des Separators
     */
    Separator(IdentDistPairs identDistPairs, List<Point> sortedByY,
              int partingLine) {
        this.identDistPairs = identDistPairs;
        this.partingLine = partingLine;
        this.sortedByY = sortedByY;
    }

    /*
     * Setzt die Distanz des identDistPairs durch den Rekonstruktionsschritt
     * des Teile-Herrsche Verfahren vollständig richtig
     */
    void setDistance() {
        List<Point> criticalPoints = getInCriticalDistance();
        calculate(criticalPoints);
    }

    /*
     * Berechnet die kürzeste Distanz, die zwischen zwei Fields auftreten
     * kann. Falls diese kürzer als die bisherigen Distanzen ist, wird
     * die alte Distanz ersetzt. Betrachtet den Spezialfall, das die
     * kürzeste Verbindung von zwei Punkten die Trennlinie schneidet.
     * @param criticalPoints : Liste der Punkte, die für eine kürzeste
     *                         Distanz in Frage kommen und im kritischen
     *                         Bereich sind
     */
    private void calculate(List<Point> criticalPoints) {
        /* Betrachtet werden nur Punkte von unterschiedlichen Seiten, da nur
         * diese Paare noch nicht betrachtet wurden. Aufgrund des Verfahrens
         * müssen nur pointsToWatch nachfolgende Punkte betrachtet werden. */
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

    /*
     * Kreiert eine neue Liste, die von der nach y-Wert sortierten Liste des
     * Fields lediglich die Punkte im kritischen Bereich enthält, da nur diese
     * im folgenden Schritt betrachtet werden müssen
     * @return criticalPoints : Liste der kritischen Punkte
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
