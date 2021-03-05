package utils;

import java.util.Scanner;

public class ConsoleInputHandler {
    private static Scanner scanner = new Scanner(System.in);

    public static int readUserInput() {
        int input;
        while (!scanner.hasNextInt()) {
            String userInput = scanner.next();
            System.out.printf("\"%s\" is not INT.\n", userInput);
        }
        input = scanner.nextInt();
        return input;


    }
}