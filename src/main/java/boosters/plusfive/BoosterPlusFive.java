package boosters.plusfive;

import boosters.Booster;
import playingcards.InvalidCardException;
import playingcards.PlayingCard;
import playingcards.PlayingCardsFactory;
import playingfield.deck.Pack;

import java.util.ArrayList;
import java.util.List;

public class BoosterPlusFive implements Booster, Pack {
    private PlayingCardsFactory playingCardsFactory;
    private List<PlayingCard> currentDeck;


    @Override
    public List<PlayingCard> initDeck() throws InvalidCardException {
        playingCardsFactory = new PlayingCardsFactory();
        currentDeck = new ArrayList<PlayingCard>();

        for (int i = 0; i < 5; i++) {
            currentDeck.add(playingCardsFactory.createRandomCard());
        }

        return currentDeck;
    }

    @Override
    public PlayingCard getNextCard(List<PlayingCard> currentDeckCards) {
        PlayingCard newCard = currentDeckCards.get(0);
        currentDeckCards.remove(0);
        return newCard;
    }

    @Override
    public boolean isDeckEmpty(List<PlayingCard> currentDeckCards) {
        return currentDeckCards.size() == 0;
    }
}
