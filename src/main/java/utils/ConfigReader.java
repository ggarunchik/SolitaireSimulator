package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConfigReader {
    private static final String CONFIG_PATH = "src/main/resources/level_config.txt";

    public static int[][] getMatrixConfig() throws IOException {
        int[][] myArray = new int[5][5];
        int count = 1;

        if (count <= 5) {
            for (int j = 0; j < myArray.length; j++) {
                String[] extractedLine = Files.readAllLines(Paths.get(CONFIG_PATH)).get(count).trim().split(",");
                for (int k = 0; k < extractedLine.length; k++) {
                    myArray[j][k] = Integer.parseInt(extractedLine[k]);
                }
                count++;
            }
        }
        System.out.println(Arrays.deepToString(myArray));
        printGraph(myArray);
        return myArray;
    }

    public static String[] getConfigLine(int configLine) {
        String[] configLineArray = new String[0];
        try {
            configLineArray = Files.readAllLines(Paths.get(CONFIG_PATH)).get(configLine).trim().split(",");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return configLineArray;
    }

    public static void printGraph(int matrix[][]) {
        System.out.println("Graph: (Adjacency Matrix)");
        int vertexAmount = matrix.length;
        for (int i = 0; i < vertexAmount; i++) {
            for (int j = 0; j < vertexAmount; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static List<Edge> returnEdge(int matrix[][]) throws IOException {
        int vertexAmount = matrix.length;
        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < vertexAmount; i++) {
            for (int j = 0; j < vertexAmount; j++) {
                if (matrix[i][j] == 1) {
                    edges.add(new Edge(i, j));
                }
            }
        }
        return edges;
    }

    public static void removeEdge(int[][] matrix, int i, int j) {
        matrix[i][j] = -1;
        matrix[j][i] = -1;
    }

    public static class Edge {
        int src, dest;

        Edge(int src, int dest) {
            this.src = src;
            this.dest = dest;
        }

        public int getSrc() {
            return src;
        }

        public int getDest() {
            return dest;
        }

        @Override
        public String toString() {
            return src + "," + dest;
        }
    }

}