package utils;

import java.util.Scanner;

public class ConsoleInputHandler {

    public static int readUserInput() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String line = sc.nextLine();
            try {
                int n = Integer.parseInt(line);
                if (n > 0) {
                    return n;
                }
            } catch (NumberFormatException e) {
                //empty body I want it to go to the next line
            }
            System.out.print("Error: input must be a positive integer.\n");
        }
    }
}