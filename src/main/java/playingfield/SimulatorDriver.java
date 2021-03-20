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
import utils.FileWorker;
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
    private int count = 1;
    private int countMove = 0;


    public SimulatorDriver() throws InvalidCardException {
        initCards();
        FileWorker.createFile("simulation_stats.txt");
        FileWorker.createFile("simulation_log.txt");
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
        FileWorker.writeToLogFile("#" + count);
        FileWorker.writeToLogFile("Power-up type: Wild Card; ");
        FileWorker.writeGameStatusInLog(currentMatrix, hand.getCurrentHandCard(), currentDeckCards, currentPlayingCardsDeck);
        for (int i = 0; i < amountOfRounds; i++) {
            simulateWildCard();
            restartSimulation();
            boosterUsageCount = 0;
            countMove = 0;
            count++;
            FileWorker.writeToLogFile("#" + count);
        }

        restartSimulation();
        boosterUsageCount = 0;
        countMove = 0;
        count = 1;
        FileWorker.writeToLogFile("#" + count);
        FileWorker.writeToLogFile("Power-up type: +5; ");
        FileWorker.writeGameStatusInLog(currentMatrix, hand.getCurrentHandCard(), currentDeckCards, currentPlayingCardsDeck);

        for (int i = 0; i < amountOfRounds; i++) {
            simulatePlusFive();
            restartSimulation();
            boosterUsageCount = 0;
            countMove = 0;
            count++;
            FileWorker.writeToLogFile("#" + count);
        }
        FileWorker.writeToResultsFile("Amount of rounds: " +  amountOfRounds);
        FileWorker.writePercentileToLog(wildCardUsed, "WILD");
        FileWorker.writePercentileToLog(plusFiveUsed, "PLUSFIVE");

        long endTime = System.nanoTime();
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

                FileWorker.writeToLogFile("#" + count + "." + ++countMove + " I played : " + currentHand);
                FileWorker.writeGameStatusInLog(currentMatrix, currentHand, currentDeckCards, currentPlayingCardsDeck);
                break;
            } else if (currentHand.getSuit() == BoosterType.WILD) {
                Random random = new Random();
                List<PlayingCard> keys = new ArrayList<PlayingCard>(currentPlayingCardsDeck.values());
                PlayingCard randomKey = keys.get(random.nextInt(currentPlayingCardsDeck.size()));
                currentHand = hand.setCurrentHandCard(randomKey);

                currentPlayingCardsDeck.remove(entry.getKey());
                updateEdges(entry.getKey());

                FileWorker.writeToLogFile("#" + count + "." + ++countMove + " I played (W): " + currentHand);
                FileWorker.writeGameStatusInLog(currentMatrix, currentHand, currentDeckCards, currentPlayingCardsDeck);
                break;
            }
        }
    }

    public void updateEdges(int value) {
        for (Edge edge : currentEdges) {
            if (edge.getSrc() == value) {
                AdjacencyMatrixHelper.removeEdge(currentMatrix, edge.getSrc(), edge.getDest());
                currentEdges = AdjacencyMatrixHelper.returnEdge(currentMatrix);
            }
        }
    }

    @Override
    public void takeCardFromDeck() throws InvalidCardException {
        if (!deck.isDeckEmpty(currentDeckCards)) {
            Card playingCard = hand.setCurrentHandCard(deck.getNextCard(currentDeckCards));

            FileWorker.writeToLogFile("#" + count + "." + ++countMove + " I get card from deck: " + playingCard);
            FileWorker.writeGameStatusInLog(currentMatrix, hand.getCurrentHandCard(), currentDeckCards, currentPlayingCardsDeck);
        }
    }

    @Override
    public void playPlusFive() throws InvalidCardException {
        BoosterPlusFiveFactory boosterPlusFiveFactory = new BoosterPlusFiveFactory();
        currentDeckCards = boosterPlusFiveFactory.createBooster();
        boosterUsageCount++;

        FileWorker.writeToLogFile("#" + count + "." + ++countMove + " I activated +5");
        FileWorker.writeGameStatusInLog(currentMatrix, hand.getCurrentHandCard(), currentDeckCards, currentPlayingCardsDeck);
    }

    @Override
    public void playWildCard() {
        Card currentHand = hand.getCurrentHandCard();
        BoosterWildCardFactory boosterWildCardFactory = new BoosterWildCardFactory();
        currentHand = hand.setCurrentBoosterCard(boosterWildCardFactory.createBooster());

        FileWorker.writeToLogFile("#" + count + "." + ++countMove + " I Played Wild Card");
        FileWorker.writeGameStatusInLog(currentMatrix, currentHand, currentDeckCards, currentPlayingCardsDeck);
        boosterUsageCount++;
    }
}
