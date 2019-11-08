/**
 * Shell.ShellToField Hilfsklasse
 *
 * Version:
 */

package Shell;

import Field.Field;

final class ShellToField {

    private static Field field = new Field();            //Field des Programms

    /**
     * Privater Konstruktor der Klasse Shell,
     * da keine Objekte der Klasse initialisiert werden sollen
     */
    private ShellToField() { }

    /**
     * Setzt das Field.Field vollständig zurück
     */
    static void newField() {
        field = new Field();
    }

    /**
     * Fügt den Punkt (x, y) dem Field.Field hinzu. Falls dieser schon existiert
     * oder Parameter fehlerhaft sind, wird ein Fehler in der Konsole
     * ausgegeben
     * @param tokens : String array der Parameter als Strings
     */
    static void add(String[] tokens) {
        Integer[] parameters = checkParameters(tokens);
        if (parameters != null) {
            int x = parameters[0];
            int y = parameters[1];
            boolean added = field.add(x, y);

            if (!added) {
                printError("Field.Point already added!");
            }
        }
    }

    /**
     * Entfernt den Punkt (x, y) aus dem Field.Field. Falls dieser nicht existiert
     * oder Parameter fehlerhaft sind, wird ein Fehler in der Konsole
     * ausgegeben
     * @param tokens : String array der Parameter als Strings
     */
    static void remove(String[] tokens) {
        Integer[] parameters = checkParameters(tokens);
        if (parameters != null) {
            int x = parameters[0];
            int y = parameters[1];
            boolean removed = field.remove(x, y);

            if (!removed) {
                printError("Field.Point has not been added before!");
            }
        }
    }

    /**
     * Gibt alle Punkte des Fields in der Konsole aus.
     */
    static void print() {
        String allPoints = field.print();
        System.out.println(allPoints);
    }

    /**
     * Gibt die kürzeste Distanz des Fields inkl. aller Paare in der Konsole
     * aus. Falls diese nicht existiert, wird ein Fehler in der Konsole
     * ausgegeben
     */
    static void distance() {
        String result = field.distance();
        if (result == null) {
            printError("Less than 2 points in the field!");
        } else {
            System.out.println(result);
        }
    }

    /**
     * Gibt alle möglichen Kommandos in der Konsole aus
     */
    static void help() {
        String[] commands = {"Following commands supported:",
                "h: prints all commands",
                "q: system quit",
                "n: creates a new field",
                "p: prints all points in the field",
                "r <integer x> <integer y>: removes the point (x, y)",
                "a <integer x> <integer y>: adds the point (x,y)",
                "d: prints the shortest distance of 2 points"};

        for (String tmp: commands) {
            System.out.println(tmp);
        }
    }

    /**
     * Gibt eine spezielle Fehlernachricht in der Konsole aus
     * @param message : Spezialisierung des Fehlers
     */
    static void printError(String message) {
        String errorMessage = "Error!";
        System.out.println(errorMessage + " " + message);
    }

    /**
     * Prüft, ob alle nötigen Parameter ganze Zahlen sind
     * @param tokens : String array der Länge 2 mit den Strings der
     *               Parameter x, y
     * @return parameters : null, falls ein Parameter keine ganze Zahl ist
     *                      andernfalls Integer array der Länge 2 mit den
     *                      ganzen Zahlen x, y
     */
    private static Integer[] checkParameters(String[] tokens) {
        Integer[] parameters = new Integer[tokens.length];
        for (int i = 0; i < parameters.length; i++) {
            try {
                parameters[i] = Integer.parseInt(tokens[i]);
            } catch (NumberFormatException e) {
                printError("Parameter " + i + " is no integer!");
                return null;
            }
        }
        return parameters;
    }
}
