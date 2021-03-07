package playingfield;

import playingcards.PlayingCard;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface StateCases {

    List<PlayingCard> getCurrentHandCard();

    HashMap<Integer, PlayingCard> checkForPlayableCards() throws IOException;

    HashMap<Integer, PlayingCard> playCard(HashMap playableCards);

    void takeCardFromDeck();

    void playPlusFive();

    void playWildCard();
}
