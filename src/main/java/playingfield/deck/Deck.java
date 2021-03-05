package playingfield.deck;


import playingcards.Card;
import playingcards.PlayingCard;
import playingcards.PlayingCardsFactory;
import utils.ConfigReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Deck {
    private PlayingCardsFactory playingCardsFactory;
    private List<Card> currentDeck;

    public Deck() {
        initDeck();
    }

    public List<Card> initDeck() {
        playingCardsFactory = new PlayingCardsFactory();
        currentDeck = new ArrayList<Card>();
        String[] currentDeckArray = ConfigReader.getConfigLine(6);
        for (String value : currentDeckArray) {
            if (value.toCharArray().length > 1) {
                char face = value.charAt(0);
                char suit = value.charAt(1);
                currentDeck.add(playingCardsFactory.createCard(String.valueOf(face), String.valueOf(suit)));
            } else {
                currentDeck.add(playingCardsFactory.createRandomCard());
            }
        }
        System.out.println(Arrays.deepToString(new List[]{currentDeck}));
        return currentDeck;
    }

    public Card getNextCard() {
        Card newCard = currentDeck.get(0);
        currentDeck.remove(0);

        return newCard;
    }

    public boolean isDeckEmpty() {
        return currentDeck.size() == 0;
    }

}
