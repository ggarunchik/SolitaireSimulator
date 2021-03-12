package playingfield.deck;


import playingcards.InvalidCardException;
import playingcards.PlayingCard;
import playingcards.PlayingCardsFactory;
import utils.ConfigReader;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private PlayingCardsFactory playingCardsFactory;
    private List<PlayingCard> currentDeck;

    public Deck() throws InvalidCardException {
        initDeck();
    }

    public List<PlayingCard> initDeck() throws InvalidCardException {
        playingCardsFactory = new PlayingCardsFactory();
        currentDeck = new ArrayList<PlayingCard>();
        String[] currentDeckArray = ConfigReader.getConfigLine(6);
        for (String value : currentDeckArray) {
            if (value.toCharArray().length > 1) {
                char face = value.charAt(0);
                char suit = value.charAt(1);
                currentDeck.add(playingCardsFactory.createCard(String.valueOf(face) +String.valueOf(suit)));
            } else {
                currentDeck.add(playingCardsFactory.createRandomCard());
            }
        }
//        System.out.println("Deck has been inited: ");
//        System.out.println(Arrays.deepToString(new List[]{currentDeck}));
        return currentDeck;
    }

    public PlayingCard getNextCard(List<PlayingCard> currentDeckCards) {
        PlayingCard newCard = currentDeckCards.get(0);
        currentDeckCards.remove(0);
        return newCard;
    }

    public boolean isDeckEmpty(List<PlayingCard> currentDeckCards) {
        return currentDeckCards.size() == 0;
    }

}
