package playingfield;

import playingcards.Card;

import java.io.IOException;
import java.util.List;

public interface StateCases {

    List<Card> getCurrentHandCard();
    List<Card> checkForPlayableCards() throws IOException;
    void playCard();
    void takeCardFromDeck();
    void playPlusFive();
    void playWildCard();
}
