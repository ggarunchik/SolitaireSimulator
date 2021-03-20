package playingfield.playingdeck;

import playingcards.InvalidCardException;
import playingcards.PlayingCard;
import playingcards.PlayingCardsFactory;
import utils.ConfigReader;

import java.util.*;

public class PlayingDeck {

    private final String[] currentPlayingDeckConfig = ConfigReader.getConfigLine(0);
    private PlayingCardsFactory playingCardsFactory;
    private HashMap<Integer, PlayingCard> currentPlayingDeck;

    public PlayingDeck() throws InvalidCardException {
        initStartPlayingDeck();
    }

    public HashMap<Integer, PlayingCard> initStartPlayingDeck() throws InvalidCardException {
        playingCardsFactory = new PlayingCardsFactory();
        currentPlayingDeck = new HashMap<>();
        int count = 0;

        for (String value : currentPlayingDeckConfig) {
            if (value.toCharArray().length > 1) {
                char face = value.charAt(0);
                char suit = value.charAt(1);
                currentPlayingDeck.put(count, playingCardsFactory.createCard(String.valueOf(face) + String.valueOf(suit)));
                count++;
            } else {
                currentPlayingDeck.put(count, playingCardsFactory.createRandomCard());
                count++;
            }
        }
        return currentPlayingDeck;
    }

    public HashMap<Integer, PlayingCard> getCurrentPlayingDeckConfig() {
        return currentPlayingDeck;
    }


}
