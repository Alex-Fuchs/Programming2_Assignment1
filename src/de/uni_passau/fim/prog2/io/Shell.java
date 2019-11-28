package io;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * {@code Shell} ist eine Utilityklasse, die den Input in der Shell und
 * somit alle Kommandos des Nutzers verarbeitet und diese darauf an
 * {@code ShellToField} weiterleitet. Dort werden auch, falls vorhanden, die
 * Parameter geprüft. Es können beliebig viele Parameter angehängt werden,
 * nur die ersten, notwendigen Parameter werden weitergeleitet. Zudem kann
 * statt den Kommandos der Form "c" auch ein Wort mit den Anfangsbuchstaben c
 * geschrieben werden.
 *
 * @version 14.11.19
 * @author ------
 */
final class Shell {

    private Shell() { }

    /**
     * Startet das Programm.
     *
     * @param   args            Startübergabe des Programms
     * @throws  IOException     Falls I/O Probleme bei dem Benutzer bestehen,
     *                          wird die {@code IOException} zur JVM
     *                          weitergeleitet und das Programm wird beendet.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader stdin
        = new BufferedReader(new InputStreamReader(System.in));
        execute(stdin);
    }

    /**
     * Liest Befehle inkl Parameter des Benutzers ein, reagiert auf Befehle
     * mit einer Fehlerausgabe oder leitet diese ggf mit Parameter weiter.
     * Die Parameter werden erst nach Weiterleitung geprüft. Statt den
     * Kommandos "c" werden auch Wörter mit dem Anfangsbuchstaben c
     * akzeptiert.
     *
     * @param   stdin           {@code BufferedReader} aus {@code main},
     *                          wird zum Lesen der Befehle verwendet.
     * @throws  IOException     Falls I/O Probleme bei dem Benutzer bestehen,
     *                          wird eine {@code IOException} zu
     *                          {@code main} geleitet.
     * @see                     ShellToField
     */
    private static void execute(BufferedReader stdin) throws IOException {
        boolean quit = false;
        final String prompt = "cp> ";
        while (!quit) {
            System.out.print(prompt);
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
                final int parameterNumberAdd = 2;
                if (tokens.length > parameterNumberAdd) {
                    String[] parametersForAdd = {tokens[1], tokens[2]};
                    ShellToField.add(parametersForAdd);
                } else {
                    ShellToField.printError("Not enough parameters!");
                }
                break;
            case 'r':
                final int parameterNumberRemove = 2;
                if (tokens.length > parameterNumberRemove) {
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

