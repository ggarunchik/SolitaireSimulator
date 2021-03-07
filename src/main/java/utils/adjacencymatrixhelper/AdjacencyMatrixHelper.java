package utils.adjacencymatrixhelper;

import java.util.ArrayList;
import java.util.List;

public class AdjacencyMatrixHelper {

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

    public static List<Edge> returnEdge(int matrix[][]){
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
}
