package playingfield.hand;

import playingcards.Card;
import playingcards.InvalidCardException;
import playingcards.PlayingCard;
import playingcards.PlayingCardsFactory;
import utils.ConfigReader;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private final String[] currentHandConfigArray = ConfigReader.getConfigLine(7);
    private PlayingCardsFactory playingCardsFactory;
    private List<PlayingCard> currentHandCard;

    public Hand() throws InvalidCardException {
        initStartHand();
    }

    public List<PlayingCard> initStartHand() throws InvalidCardException {
        playingCardsFactory = new PlayingCardsFactory();
        currentHandCard = new ArrayList<PlayingCard>();

        for (String value : currentHandConfigArray) {
            if (value.toCharArray().length > 1) {
                char face = value.charAt(0);
                char suit = value.charAt(1);
                currentHandCard.add(playingCardsFactory.createCard(String.valueOf(face)+ String.valueOf(suit)));
            } else {
                currentHandCard.add(playingCardsFactory.createRandomCard());
            }
        }
//        System.out.println("Card in the hand has been inited: ");
//        System.out.println(Arrays.deepToString(new List[]{currentHandCard}));

        return currentHandCard;
    }

    public PlayingCard getCurrentHandCard(){
        return currentHandCard.get(0);
    }

    public PlayingCard setCurrentHandCard(PlayingCard card){
        currentHandCard.clear();
        currentHandCard.add(card);
        return card;
    }
}
