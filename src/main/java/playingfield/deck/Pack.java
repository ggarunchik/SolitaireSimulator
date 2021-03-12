package playingfield.deck;

import playingcards.InvalidCardException;
import playingcards.PlayingCard;

import java.util.List;

public interface Pack {

    List<PlayingCard> initDeck() throws InvalidCardException;
    PlayingCard getNextCard(List<PlayingCard> currentDeckCards);
    boolean isDeckEmpty(List<PlayingCard> currentDeckCards);

}
