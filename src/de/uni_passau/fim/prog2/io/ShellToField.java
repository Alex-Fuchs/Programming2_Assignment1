package io;

import closest_pairs_of_points.Field;

/**
 * {@code ShellToField} ist eine Utilityklasse, die den Output des Programms
 * steuert und ebenso die Klassen {@code Field} und {@code Shell} verbindet,
 * damit beide wiederverwendet werden können. Parameter werden auf Richtigkeit
 * geprüft. Befehle werden an das {@code Field} Objekt weitergeleitet.
 *
 * @version 14.11.19
 * @author -----
 */
final class ShellToField {

    /**
     * {@code Field} Objekt des Programms
     */
    private static Field field = new Field();

    private ShellToField() { }

    /**
     * Setzt das {@code Field} Objekt vollständig zurück.
     *
     * @see     Field
     */
    static void newField() {
        field = new Field();
    }

    /**
     * Fügt den {@code Point} (x, y) dem {@code Field} hinzu.
     * Falls dieser schon existiert oder Parameter fehlerhaft sind,
     * wird ein Fehler in der Konsole ausgegeben.
     *
     * @param tokens        {@code String[] array} der Länge 2 mit den
     *                      Parametern x, y als {@code String}
     * @see                 #checkParameters(String[])
     * @see                 Field#add(int, int)
     * @see                 closest_pairs_of_points.Point
     */
    static void add(String[] tokens) {
        Integer[] parameters = checkParameters(tokens);
        if (parameters != null) {
            int x = parameters[0];
            int y = parameters[1];
            boolean added = field.add(x, y);
            if (!added) {
                printError("Point already added!");
            }
        }
    }

    /**
     * Entfernt den {@code Point} (x, y) aus dem {@code Field}.
     * Falls dieser nicht existiert oder Parameter fehlerhaft sind,
     * wird ein Fehler in der Konsole ausgegeben.
     *
     * @param tokens        {@code String[] array} der Länge 2 mit den
     *                      Parametern x, y als {@code String}
     * @see                 #checkParameters(String[])
     * @see                 Field#remove(int, int)
     * @see                 closest_pairs_of_points.Point
     */
    static void remove(String[] tokens) {
        Integer[] parameters = checkParameters(tokens);
        if (parameters != null) {
            int x = parameters[0];
            int y = parameters[1];
            boolean removed = field.remove(x, y);
            if (!removed) {
                printError("Point has not been added before!");
            }
        }
    }

    /**
     * Gibt alle {@code Points} des {@code Fields} in der Konsole aus.
     *
     * @see     Field#toString()
     * @see     closest_pairs_of_points.Point
     */
    static void print() {
        System.out.println(field);
    }

    /**
     * Gibt die kürzeste Distanz des {@code Fields} inkl aller {@code Pairs}
     * in der Konsole aus. Falls zu wenig {@code Points} vorhanden sind,
     * wird ein Fehler in der Konsole ausgegeben.
     *
     * @see     Field#distance()
     * @see     closest_pairs_of_points.Pair
     * @see     closest_pairs_of_points.Point
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
     * Gibt alle möglichen Kommandos in der Konsole aus.
     */
    static void help() {
        String[] commands = {"-----",
                "closest Pairs commands:",
                "h: prints all commands",
                "q: system quit",
                "n: creates a new field",
                "p: prints all points in the field",
                "r <integer x> <integer y>: removes the point (x, y)",
                "a <integer x> <integer y>: adds the point (x,y)",
                "d: prints the shortest distance of 2 points",
                "-----"};

        for (String tmp: commands) {
            System.out.println(tmp);
        }
    }

    /**
     * Gibt eine spezielle Fehlernachricht in der Konsole aus.
     *
     * @param message       {@code String} der Fehlernachricht
     */
    static void printError(String message) {
        final String errorMessage = "Error!";
        System.out.println(errorMessage + " " + message);
    }

    /**
     * Prüft, ob alle nötigen Parameter zu {@code int} konvertiert werden
     * können.
     *
     * @param tokens        {@code String[] array} der Länge 2 mit den
     *                      Parametern x, y als {@code String}
     * @return              null, falls ein Parameter keine ganze Zahl ist.
     *                      {@code int[] array} der Länge 2 mit Parameter x, y
     *                      als {@code int} andernfalls.
     */
    private static Integer[] checkParameters(String[] tokens) {
        Integer[] parameters = new Integer[tokens.length];
        for (int i = 0; i < parameters.length; i++) {
            try {
                parameters[i] = Integer.parseInt(tokens[i]);
            } catch (NumberFormatException e) {
                printError("Parameter " + i + " is no integer or too long!");
                return null;
            }
        }
        return parameters;
    }
}
