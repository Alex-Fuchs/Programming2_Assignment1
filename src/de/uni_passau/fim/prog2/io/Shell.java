package io;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * io Utilityklasse
 *
 * Version: 14.11.19
 *
 * Inputklasse, die alle Kommandos des Nutzers in der Shell entgegennimmt
 */
final class Shell {

    private Shell() { }

    /**
     * Startet das Programm mit der main Methode.
     * @param args : Startübergabe des Programms
     * @throws IOException : Falls I/O Probleme bei dem Benutzer bestehen,
     *                     leitet die main Methode die IOException weiter
     *                     und das Programm wird beendet
     */
    public static void main(String[] args) throws IOException {
        BufferedReader stdin
        = new BufferedReader(new InputStreamReader(System.in));
        execute(stdin);
    }

    /*
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

        while (!quit) {
            System.out.print("cp> ");
            String input = stdin.readLine();

            if (input == null || input.trim().equals("")) {
                ShellToField.printError("Please enter a command!");
                continue;
            }

            String[] tokens = input.trim().split("\\s+");
            tokens[0] = tokens[0].toLowerCase();

            switch (tokens[0].charAt(0)) {
            case 'n':
                ShellToField.newField();
                break;
            case 'h':
                ShellToField.help();
                break;
            case 'q':
                quit = true;
                break;
            case 'a':
                if (tokens.length >= 3) {
                    String[] parametersForAdd = {tokens[1], tokens[2]};
                    ShellToField.add(parametersForAdd);
                } else {
                    ShellToField.printError("Not enough parameters!");
                }
                break;
            case 'r':
                if (tokens.length >= 3) {
                    String[] parametersForRemove = {tokens[1], tokens[2]};
                    ShellToField.remove(parametersForRemove);
                } else {
                    ShellToField.printError("Not enough parameters!");
                }
                break;
            case 'p':
                ShellToField.print();
                break;
            case 'd':
                ShellToField.distance();
                break;
            default:
                ShellToField.printError("Type help for overview!");
                break;
            }
        }
    }
}

