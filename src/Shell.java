/**
 * Shell
 *
 * Version:
 */

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public final class Shell {

    /**
     * Privater Konstruktor der Klasse Shell,
     * da keine Objekte der Klasse initialisiert werden sollen
     */
    private Shell() { }

    /**
     * Startet das Programm mit der main Methode.
     * @param args : Startübergabe des Programms
     * @throws IOException : Falls I/O Probleme bei dem Benutzer bestehen,
     *                     leitet die main Methode die IOException weiter
     *                     und das Programm wird beendet
     */
    public static void main(String[] args) throws IOException {
        BufferedReader stdin = new BufferedReader(
                                new InputStreamReader(System.in));
        execute(stdin);
    }

    /**
     * Liest Befehle inkl. Parameter des Benutzers ein, reagiert auf Befehle
     * mit einer Fehlerausgabe oder leitet diese ggf. mit Parameter weiter.
     * Die Parameter werden erst nach Weiterleitung geprüft
     * @param stdin : BufferedReader aus der main Methode,
     *              wird zum Einlesen der Befehle verwendet
     * @throws IOException : Falls I/O Probleme bei dem Benutzer bestehen,
     *                     wird eine IOException zur main Methode geworfen
     */
    private static void execute(BufferedReader stdin) throws IOException {
        boolean quit = false;
        Field field = new Field();

        while (!quit) {
            System.out.print("cp> ");
            String input = stdin.readLine();

            if (input == null || input.equals("")) {
                System.out.println("Error! Please enter a command!");
                continue;
            }

            String[] tokens = input.trim().split("\\s+");
            tokens[0] = tokens[0].toLowerCase();

            switch (tokens[0]) {
            case "n":
                break;
            case "h":
                break;
            case "q":
                quit = true;
                break;
            case "a":
                break;
            case "r":
                break;
            case "p":
                break;
            case "d":
                break;
            default:
                System.out.println("Error! Type help for Overview!");
                break;
            }
        }
    }
}

