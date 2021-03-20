package utils;

import playingcards.Card;
import playingcards.PlayingCard;

import java.io.*;
import java.util.HashMap;
import java.util.List;

public class FileWorker {

    public static void createFile(String fileName) {
        try {
            File simulationStatsFile = new File(fileName);
            if (simulationStatsFile.createNewFile()) {
            } else {
                simulationStatsFile.delete();
                simulationStatsFile.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToResultsFile(String textToWrite) {
        try (FileWriter fw = new FileWriter("simulation_stats.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(textToWrite);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToLogFile(String textToWrite) {
        try (FileWriter fw = new FileWriter("simulation_log.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(textToWrite);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeMatrixToLog(int matrix[][]) {
        int vertexAmount = matrix.length;
        try (FileWriter fw = new FileWriter("simulation_log.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            for (int i = 0; i < vertexAmount; i++) {
                for (int j = 0; j < vertexAmount; j++) {
                    out.print(matrix[i][j] + " ");
                }
                out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeGameStatusInLog(int[][] currentMatrix, Card currentHand, List<PlayingCard> currentDeckCards,
                                            HashMap<Integer, PlayingCard> currentPlayingCardsDeck) {
        FileWorker.writeToLogFile("Current cards: " + currentPlayingCardsDeck);
        FileWorker.writeMatrixToLog(currentMatrix);
        FileWorker.writeToLogFile("Current deck : " + currentDeckCards);
        FileWorker.writeToLogFile("Current hand : " + currentHand);
    }

    public static void writePercentileToLog(List<Integer> boosterUsage, String boosterType) {
        switch (boosterType) {
            case "WILD":
                FileWorker.writeToResultsFile("Wild Card Stats:");
                FileWorker.writeToResultsFile("25th: " + PercentileHelper.Percentile(boosterUsage, 25));
                FileWorker.writeToResultsFile("50th: " + PercentileHelper.Percentile(boosterUsage, 50));
                FileWorker.writeToResultsFile("75th: " + PercentileHelper.Percentile(boosterUsage, 75));
                FileWorker.writeToResultsFile("100th: " + PercentileHelper.Percentile(boosterUsage, 100));
                break;

            case "PLUSFIVE":
                FileWorker.writeToResultsFile("Plus Five Stats:");
                FileWorker.writeToResultsFile("25th: " + PercentileHelper.Percentile(boosterUsage, 25));
                FileWorker.writeToResultsFile("50th: " + PercentileHelper.Percentile(boosterUsage, 50));
                FileWorker.writeToResultsFile("75th: " + PercentileHelper.Percentile(boosterUsage, 75));
                FileWorker.writeToResultsFile("100th: " + PercentileHelper.Percentile(boosterUsage, 100));
        }
    }

}

