package playingfield.hand;

import playingcards.Card;
import playingcards.PlayingCardsFactory;
import utils.ConfigReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hand {

    private final String[] currentHandConfigArray = ConfigReader.getConfigLine(7);
    private PlayingCardsFactory playingCardsFactory;
    private List<Card> currentHandCard;

    public Hand() {
        initStartHand();
    }

    public List<Card> initStartHand() {
        playingCardsFactory = new PlayingCardsFactory();
        currentHandCard = new ArrayList<Card>();

        for (String value : currentHandConfigArray) {
            if (value.toCharArray().length > 1) {
                char face = value.charAt(0);
                char suit = value.charAt(1);
                currentHandCard.add(playingCardsFactory.createCard(String.valueOf(face), String.valueOf(suit)));
            } else {
                currentHandCard.add(playingCardsFactory.createRandomCard());
            }
        }
        System.out.println(Arrays.deepToString(new List[]{currentHandCard}));

        return currentHandCard;
    }

    public Card getCurrentHandCard(){
        return currentHandCard.get(0);
    }
}
