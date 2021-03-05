package playingfield;

import playingcards.Card;
import playingfield.deck.Deck;
import playingfield.hand.Hand;
import playingfield.playingdeck.PlayingDeck;
import utils.ConfigReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimulatorDriver implements StateCases {
    private Deck deck = new Deck();
    private Hand hand = new Hand();
    private PlayingDeck playingDeck = new PlayingDeck();

    public void simulation() {
        while (playingDeck.getCurrentPlayingDeckConfig().size() > 0) {

        }
    }

    @Override
    public List<Card> getCurrentHandCard() {
        List<Card> currentHandCard = new ArrayList<>();
        currentHandCard.add(hand.getCurrentHandCard());

        return currentHandCard;
    }

    @Override
    public List<Card> checkForPlayableCards() throws IOException {
        int[][] currentMatrix = ConfigReader.getMatrixConfig();
        List<ConfigReader.Edge> currentEdges = new ArrayList<>(ConfigReader.returnEdge(currentMatrix));
        List<Card> currentPlayingCardsDeck = new ArrayList<>(playingDeck.getCurrentPlayingDeckConfig());
        List<Card> playableCards = new ArrayList<>();

        for (int i = 0; i < currentMatrix.length; i++) {
            for (int j = 0; j < currentMatrix.length; j++) {
                if (currentMatrix[i][j] == 1) {
                    playableCards.add(currentPlayingCardsDeck.get(i));
                }
            }
        }
        System.out.println(Arrays.deepToString(new List[]{playableCards}));

        return playableCards;
    }

    @Override
    public void playCard() {

    }

    @Override
    public void takeCardFromDeck() {

    }

    @Override
    public void playPlusFive() {

    }

    @Override
    public void playWildCard() {

    }
}
