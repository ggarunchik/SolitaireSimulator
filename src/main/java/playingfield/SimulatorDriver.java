package playingfield;

import org.apache.commons.lang3.ArrayUtils;
import playingcards.Card;
import playingcards.PlayingCard;
import playingfield.deck.Deck;
import playingfield.hand.Hand;
import playingfield.playingdeck.PlayingDeck;
import utils.adjacencymatrixhelper.AdjacencyMatrixHelper;
import utils.ConfigReader;
import utils.adjacencymatrixhelper.Edge;

import java.io.IOException;
import java.util.*;

public class SimulatorDriver implements StateCases {
    private Deck deck = new Deck();
    private Hand hand = new Hand();
    private PlayingDeck playingDeck = new PlayingDeck();
    private HashMap<Integer, PlayingCard> currentPlayingCardsDeck = new HashMap<>();
    private int[][] currentMatrix;


    public void initCards() {
        if (currentPlayingCardsDeck.isEmpty()) {
            currentPlayingCardsDeck = new HashMap(playingDeck.getCurrentPlayingDeckConfig());
        }
        if (ArrayUtils.isEmpty(currentMatrix)) {
            currentMatrix = ConfigReader.getMatrixConfig();
        }
    }

    public void simulation() {
        while (playingDeck.getCurrentPlayingDeckConfig().size() > 0) {

        }
    }

    @Override
    public List<PlayingCard> getCurrentHandCard() {
        List<PlayingCard> currentHandCard = new ArrayList<PlayingCard>();
        currentHandCard.add(hand.getCurrentHandCard());

        return currentHandCard;
    }

    @Override
    public HashMap<Integer, PlayingCard> checkForPlayableCards() throws IOException {
        initCards();
        List<Edge> currentEdges = new ArrayList<>(AdjacencyMatrixHelper.returnEdge(currentMatrix));
        HashMap<Integer, PlayingCard> playableCards = new HashMap<>();
        int edgeSize = currentEdges.size();

        switch (edgeSize) {
            case 1:
                for (Map.Entry entry : currentPlayingCardsDeck.entrySet()) {
                    if (!entry.getKey().equals(currentEdges.get(0).getDest())) {
                        playableCards.put((Integer) entry.getKey(), currentPlayingCardsDeck.get(entry.getKey()));
                    }
                }
                break;
            case 2:
                for (Map.Entry entry : currentPlayingCardsDeck.entrySet()) {
                    if (!entry.getKey().equals(currentEdges.get(0).getDest()) && !entry.getKey().equals(currentEdges.get(1).getDest())) {
                        playableCards.put((Integer) entry.getKey(), currentPlayingCardsDeck.get(entry.getKey()));
                    }
                }
                break;
            default:
                for (Map.Entry entry : currentPlayingCardsDeck.entrySet()) {
                    playableCards.put((Integer) entry.getKey(), currentPlayingCardsDeck.get(entry.getKey()));
                }
                break;
        }

        System.out.println("Current Playing cards are: ");
        System.out.println(playableCards);
        System.out.println("Current edges are: ");
        System.out.println(currentEdges);

        return playableCards;
    }

    @Override
    public HashMap<Integer, PlayingCard> playCard(HashMap playableCards) {
        PlayingCard currentHand = hand.getCurrentHandCard();
        return null;
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
