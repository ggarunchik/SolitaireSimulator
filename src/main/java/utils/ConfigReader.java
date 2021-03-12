package utils;

import utils.adjacencymatrixhelper.AdjacencyMatrixHelper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ConfigReader {
    private static final String CONFIG_PATH = "src/main/resources/level_config.txt";

    public static int[][] getMatrixConfig()  {
        int[][] myArray = new int[5][5];
        int count = 1;

        if (count <= 5) {
            for (int j = 0; j < myArray.length; j++) {
                String[] extractedLine = new String[0];
                try {
                    extractedLine = Files.readAllLines(Paths.get(CONFIG_PATH)).get(count).trim().split(",");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                for (int k = 0; k < extractedLine.length; k++) {
                    myArray[j][k] = Integer.parseInt(extractedLine[k]);
                }
                count++;
            }
        }
        //System.out.println(Arrays.deepToString(myArray));
        //AdjacencyMatrixHelper.printGraph(myArray);
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
}