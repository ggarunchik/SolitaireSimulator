import consolecontroller.ConsoleMenu;
import playingcards.InvalidCardException;
import playingfield.SimulatorDriver;


public class Main {
    public static void main(String[] args) throws InvalidCardException {
        ConsoleMenu consoleMenu = new ConsoleMenu();
        SimulatorDriver simulatorDriver = new SimulatorDriver();
        try {
            simulatorDriver.simulation(consoleMenu.enterSimulationTryAmount());
        } catch ( InvalidCardException e) {
            e.printStackTrace();
        }
    }
}
