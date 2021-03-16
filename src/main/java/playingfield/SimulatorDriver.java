package playingfield;

import boosters.plusfive.BoosterPlusFiveFactory;
import boosters.wildcard.BoosterType;
import boosters.wildcard.BoosterWildCardFactory;
import org.apache.commons.lang3.ArrayUtils;
import playingcards.Card;
import playingcards.InvalidCardException;
import playingcards.PlayingCard;
import playingfield.deck.Deck;
import playingfield.hand.Hand;
import playingfield.playingdeck.PlayingDeck;
import utils.PercentileHelper;
import utils.adjacencymatrixhelper.AdjacencyMatrixHelper;
import utils.ConfigReader;
import utils.adjacencymatrixhelper.Edge;

import java.util.*;

public class SimulatorDriver implements StateCases {
    private final Deck deck = new Deck();
    private final Hand hand = new Hand();
    private final PlayingDeck playingDeck = new PlayingDeck();

    private HashMap<Integer, PlayingCard> currentPlayingCardsDeck = new HashMap<>();
    private List currentHandCard = new ArrayList<>();
    private List<PlayingCard> currentDeckCards = new ArrayList<>();
    private int[][] currentMatrix;
    private List<Edge> currentEdges = new ArrayList<>();
    private List<Integer> plusFiveUsed = new ArrayList<>();
    private List<Integer> wildCardUsed = new ArrayList<>();
    private int boosterUsageCount;


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

    public void restartSimulation() throws InvalidCardException {
        currentPlayingCardsDeck = new HashMap(playingDeck.getCurrentPlayingDeckConfig());
        currentMatrix = ConfigReader.getMatrixConfig();
        currentHandCard = hand.initStartHand();
        currentDeckCards = deck.initDeck();
        currentEdges = AdjacencyMatrixHelper.returnEdge(currentMatrix);
    }



    public void simulation(int amountOfRounds) throws InvalidCardException {
        long startTime = System.nanoTime();
        int count = 0;
        for (int i = 0; i < amountOfRounds; i++) {
            simulateWildCard();
            restartSimulation();
            boosterUsageCount = 0;
            count++;
        }

        restartSimulation();
        boosterUsageCount = 0;

        for (int i = 0; i < amountOfRounds; i++) {
            simulatePlusFive();
            restartSimulation();
            boosterUsageCount = 0;
            count++;
        }
        System.out.println("Amount of rounds: " + count/2);
        System.out.println("Wild Card Stat");
        System.out.println("25th: " + PercentileHelper.Percentile(wildCardUsed,25));
        System.out.println("50th: " + PercentileHelper.Percentile(wildCardUsed,50));
        System.out.println("75th: " + PercentileHelper.Percentile(wildCardUsed,75));
        System.out.println("100th: " + PercentileHelper.Percentile(wildCardUsed,100));

        System.out.println("Plus Five Stat");
        System.out.println("25th: " + PercentileHelper.Percentile(plusFiveUsed,25));
        System.out.println("50th: " + PercentileHelper.Percentile(plusFiveUsed,50));
        System.out.println("75th: " + PercentileHelper.Percentile(plusFiveUsed,75));
        System.out.println("100th: " + PercentileHelper.Percentile(plusFiveUsed,100));
        long endTime = System.nanoTime();

        // get difference of two nanoTime values
        long timeElapsed = endTime - startTime;
        System.out.println("Execution time in milliseconds : " +
                timeElapsed / 1000000);
    }

    public void simulateWildCard() throws InvalidCardException {
        while (currentPlayingCardsDeck.size() > 0) {
            if (currentDeckCards.size() != 0) {
                playCard();
                takeCardFromDeck();
            } else {
                playWildCard();
                playCard();
            }
        }
        wildCardUsed.add(boosterUsageCount);
    }

    public void simulatePlusFive() throws InvalidCardException {
        while (currentPlayingCardsDeck.size() > 0) {
            if (currentDeckCards.size() != 0) {
                playCard();
                takeCardFromDeck();
            } else {
                playPlusFive();
                playCard();
            }
        }
        plusFiveUsed.add(boosterUsageCount);
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
        Card currentHand = hand.getCurrentHandCard();

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
            } else if (currentHand.getSuit() == BoosterType.WILD) {
                Random random = new Random();
                List<PlayingCard> keys = new ArrayList<PlayingCard>(currentPlayingCardsDeck.values());
                PlayingCard randomKey = keys.get(random.nextInt(currentPlayingCardsDeck.size()));
                currentHand = hand.setCurrentHandCard(randomKey);

                currentPlayingCardsDeck.remove(entry.getKey());
                updateEdges(entry.getKey());
                System.out.print("I played because it was WILD card in hand: ");
                System.out.println(currentHand);
                break;
            } else {
                //    takeCardFromDeck();
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
    public void takeCardFromDeck() throws InvalidCardException {
        if (!deck.isDeckEmpty(currentDeckCards)) {
            Card playingCard = hand.setCurrentHandCard(deck.getNextCard(currentDeckCards));
            System.out.println("I took a card from the deck: " + playingCard);
            System.out.println("Current deck is: " + currentDeckCards);
        } else {
            System.out.println("Deck is empty");
            //  playWildCard();
            //playPlusFive();
        }
    }

    @Override
    public void playPlusFive() throws InvalidCardException {
        System.out.println("I activated +5 booster");
        BoosterPlusFiveFactory boosterPlusFiveFactory = new BoosterPlusFiveFactory();
        currentDeckCards = boosterPlusFiveFactory.createBooster();
        System.out.println("Current deck is: " + currentDeckCards);
        boosterUsageCount++;
    }

    @Override
    public void playWildCard() {
        Card currentHand = hand.getCurrentHandCard();
        System.out.println("I used wild booster");
        BoosterWildCardFactory boosterWildCardFactory = new BoosterWildCardFactory();
        currentHand = hand.setCurrentBoosterCard(boosterWildCardFactory.createBooster());
        System.out.println("Cuurent hand is " + currentHand);
        boosterUsageCount++;
    }
}
