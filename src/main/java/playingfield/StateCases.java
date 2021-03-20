package playingfield;

import playingcards.Card;
import playingcards.InvalidCardException;
import playingcards.PlayingCard;

import java.io.IOException;
import java.util.HashMap;

public interface StateCases {

    HashMap<Integer, PlayingCard> checkForPlayableCards() throws IOException, InvalidCardException;

    void playCard() throws IOException, InvalidCardException;

    void takeCardFromDeck() throws InvalidCardException;

    void playPlusFive() throws InvalidCardException;

    void playWildCard() throws InvalidCardException;
}
