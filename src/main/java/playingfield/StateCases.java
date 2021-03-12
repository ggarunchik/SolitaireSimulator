package playingfield;

import playingcards.InvalidCardException;
import playingcards.PlayingCard;

import java.io.IOException;
import java.util.HashMap;

public interface StateCases {

    PlayingCard getCurrentHandCard();

    HashMap<Integer, PlayingCard> checkForPlayableCards() throws IOException, InvalidCardException;

    void playCard() throws IOException, InvalidCardException;

    void takeCardFromDeck();

    void playPlusFive();

    void playWildCard();
}
