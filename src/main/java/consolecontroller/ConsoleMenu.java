package consolecontroller;

import playingcards.PlayingCardsFactory;
import utils.ConsoleInputHandler;

public class ConsoleMenu implements MenuActions {


    @Override
    public int enterSimulationTryAmount() {
        System.out.println("Please enter simulations rounds amount:");
        return ConsoleInputHandler.readUserInput();
    }

    public void menu() {
        enterSimulationTryAmount();
    }


}
