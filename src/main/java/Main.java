import consolecontroller.ConsoleMenu;
import playingcards.InvalidCardException;
import playingfield.SimulatorDriver;


public class Main {
    public static void main(String[] args) throws InvalidCardException {
        new ConsoleMenu();
        SimulatorDriver simulatorDriver = new SimulatorDriver();
        try {
            simulatorDriver.simulation();
        } catch ( InvalidCardException e) {
            e.printStackTrace();
        }
    }
}
