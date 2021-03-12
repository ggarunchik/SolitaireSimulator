package playingfield;

import org.apache.commons.lang3.ArrayUtils;
import playingcards.InvalidCardException;
import playingcards.PlayingCard;
import playingfield.deck.Deck;
import playingfield.hand.Hand;
import playingfield.playingdeck.PlayingDeck;
import utils.adjacencymatrixhelper.AdjacencyMatrixHelper;
import utils.ConfigReader;
import utils.adjacencymatrixhelper.Edge;

import java.util.*;

public class SimulatorDriver implements StateCases {
    private final Deck deck = new Deck();
    private final Hand hand = new Hand();
    private final PlayingDeck playingDeck = new PlayingDeck();

    private HashMap<Integer, PlayingCard> currentPlayingCardsDeck = new HashMap<>();
    private List<PlayingCard> currentHandCard = new ArrayList<>();
    private List<PlayingCard> currentDeckCards = new ArrayList<>();
    private int[][] currentMatrix;
    private List<Edge> currentEdges = new ArrayList<>();


    public SimulatorDriver() throws InvalidCardException {
        initCards();
    }


    public void initCards() throws InvalidCardException {
        if (currentPlayingCardsDeck.isEmpty()) {
            currentPlayingCardsDeck = new HashMap(playingDeck.getCurrentPlayingDeckConfig());
        }
        if (ArrayUtils.isEmpty(currentMatrix)) {
            currentMatrix = ConfigReader.getMatrixConfig();
        }
        if (currentHandCard.isEmpty()) {
            currentHandCard = hand.initStartHand();
        }
        if (currentDeckCards.isEmpty()) {
            currentDeckCards = deck.initDeck();
        }
        if (currentEdges.isEmpty()) {
            currentEdges = AdjacencyMatrixHelper.returnEdge(currentMatrix);
        }
    }

    public void simulation() throws InvalidCardException {
        while (currentPlayingCardsDeck.size() > 0) {
            playCard();
        }
    }

    @Override
    public PlayingCard getCurrentHandCard() {
        PlayingCard currentCardInHand = currentHandCard.get(0);

        return currentCardInHand;
    }

    @Override
    public HashMap<Integer, PlayingCard> checkForPlayableCards() {
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
        System.out.print("Current Cards are: ");
        System.out.println(currentPlayingCardsDeck);
        System.out.print("Current Playable cards are: ");
        System.out.println(playableCards);

        return playableCards;
    }

    @Override
    public void playCard() throws InvalidCardException {
        PlayingCard currentHand = hand.getCurrentHandCard();

        for (Map.Entry<Integer, PlayingCard> entry : checkForPlayableCards().entrySet()) {
            int value = entry.getValue().getFaceNum();
            if (currentHand.getFaceNum() == value - 1 || currentHand.getFaceNum() == value + 1) {
                currentHand = hand.setCurrentHandCard(new PlayingCard(entry.getValue().getFaceNum() +
                        entry.getValue().getSuitChar(entry.getValue().getSuit())));
                currentPlayingCardsDeck.remove(entry.getKey());
                updateEdges(entry.getKey());
                System.out.print("I played : ");
                System.out.println(currentHand);
                break;
            }else {
                takeCardFromDeck();
                break;
            }
        }
    }

    public void updateEdges(int value) {
        for (Edge edge : currentEdges) {
            if (edge.getSrc() == value) {
                AdjacencyMatrixHelper.removeEdge(currentMatrix, edge.getSrc(), edge.getDest());
                currentEdges = AdjacencyMatrixHelper.returnEdge(currentMatrix);
                AdjacencyMatrixHelper.printGraph(currentMatrix);
                System.out.print("Current edges are: ");
                System.out.println(currentEdges);
            }
        }
    }

    @Override
    public void takeCardFromDeck() {
        if (!deck.isDeckEmpty(currentDeckCards)) {
            PlayingCard playingCard = hand.setCurrentHandCard(deck.getNextCard(currentDeckCards));
            System.out.println("I took a card from the deck: " +playingCard );
            System.out.println("Current deck is: " + currentDeckCards);
        }else {
            System.out.println("Deck is empty");
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public void playPlusFive() {

    }

    @Override
    public void playWildCard() {

    }
}
