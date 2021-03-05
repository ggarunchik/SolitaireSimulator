import consolecontroller.ConsoleMenu;
import playingfield.SimulatorDriver;
import playingfield.deck.Deck;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        new ConsoleMenu();
        new Deck();
        SimulatorDriver simulatorDriver = new SimulatorDriver();
        try {
            simulatorDriver.checkForPlayableCards();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
