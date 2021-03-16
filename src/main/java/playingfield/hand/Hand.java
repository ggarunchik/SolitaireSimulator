package playingfield.hand;

import boosters.wildcard.BoosterWildCard;
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
    private List<Card> currentHandCard;

    public Hand() throws InvalidCardException {
        initStartHand();
    }

    public List<Card> initStartHand() throws InvalidCardException {
        playingCardsFactory = new PlayingCardsFactory();
        currentHandCard = new ArrayList<Card>();

        for (String value : currentHandConfigArray) {
            if (value.toCharArray().length > 1) {
                char face = value.charAt(0);
                char suit = value.charAt(1);
                currentHandCard.add(playingCardsFactory.createCard(String.valueOf(face)+ String.valueOf(suit)));
            } else {
                currentHandCard.add(playingCardsFactory.createRandomCard());
            }
        }

        return currentHandCard;
    }

    public Card getCurrentHandCard(){
        return currentHandCard.get(0);
    }

    public PlayingCard setCurrentHandCard(PlayingCard card){
        currentHandCard.clear();
        currentHandCard.add(card);
        return card;
    }

    public BoosterWildCard setCurrentBoosterCard(BoosterWildCard booster){
        currentHandCard.clear();
        currentHandCard.add(booster);
        return booster;
    }
}
