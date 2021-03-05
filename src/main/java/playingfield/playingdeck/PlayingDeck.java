package playingfield.playingdeck;

import playingcards.Card;
import playingcards.PlayingCardsFactory;
import utils.ConfigReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayingDeck {

    private final String[] currentPlayingDeck = ConfigReader.getConfigLine(0);
    private PlayingCardsFactory playingCardsFactory;
    private List<Card> currentPLayingDeck;

    public PlayingDeck() {
        initStartPlayingDeck();
    }

    public List<Card> initStartPlayingDeck() {
        playingCardsFactory = new PlayingCardsFactory();
        currentPLayingDeck = new ArrayList<Card>();

        for (String value : currentPlayingDeck) {
            if (value.toCharArray().length > 1) {
                char face = value.charAt(0);
                char suit = value.charAt(1);
                currentPLayingDeck.add(playingCardsFactory.createCard(String.valueOf(face), String.valueOf(suit)));
            } else {
                currentPLayingDeck.add(playingCardsFactory.createRandomCard());
            }
        }
        System.out.println(Arrays.deepToString(new List[]{currentPLayingDeck}));

        return currentPLayingDeck;
    }

    public List<Card> getCurrentPlayingDeckConfig(){
        return currentPLayingDeck;
    }
}
