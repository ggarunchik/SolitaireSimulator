package utils;

import java.util.Scanner;

public class ConsoleInputHandler {

    public static int readUserInput() {
        int n;
        Scanner sc = new Scanner(System.in);
        while (!sc.hasNextInt() || (n = sc.nextInt()) <= 0) {
            System.out.print("Error: input must be a positive integer." + "\n");
            sc.nextLine();
        }
        sc.nextLine();
        return n;
    }
}