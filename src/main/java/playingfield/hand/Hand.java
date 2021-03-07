package playingfield.hand;

import playingcards.Card;
import playingcards.PlayingCard;
import playingcards.PlayingCardsFactory;
import utils.ConfigReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hand {

    private final String[] currentHandConfigArray = ConfigReader.getConfigLine(7);
    private PlayingCardsFactory playingCardsFactory;
    private List<PlayingCard> currentHandCard;

    public Hand() {
        initStartHand();
    }

    public List<PlayingCard> initStartHand() {
        playingCardsFactory = new PlayingCardsFactory();
        currentHandCard = new ArrayList<PlayingCard>();

        for (String value : currentHandConfigArray) {
            if (value.toCharArray().length > 1) {
                char face = value.charAt(0);
                char suit = value.charAt(1);
                currentHandCard.add(playingCardsFactory.createCard(String.valueOf(face), String.valueOf(suit)));
            } else {
                currentHandCard.add(playingCardsFactory.createRandomCard());
            }
        }
        System.out.println("Card in the han has been inited: ");
        System.out.println(Arrays.deepToString(new List[]{currentHandCard}));

        return currentHandCard;
    }

    public PlayingCard getCurrentHandCard(){
        return currentHandCard.get(0);
    }

    public Card setCurrentHandCard(PlayingCard card){
        currentHandCard.clear();
        currentHandCard.add(card);
        return card;
    }
}
