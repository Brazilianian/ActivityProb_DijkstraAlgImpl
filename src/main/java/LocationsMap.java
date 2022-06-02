import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;

public class LocationsMap {
    private int[][] adjointMatrix;
    private int numOfScenes;

    public LocationsMap(int countOfScenes) {
        numOfScenes = countOfScenes;
        adjointMatrix = new int[numOfScenes][numOfScenes];
    }

    public int[][] getAdjointMatrix() {
        return adjointMatrix;
    }

    public int getNumOfScenes() {
        return numOfScenes;
    }

    public void addRoad(int src, int dest, int distance) {
        adjointMatrix[src - 1][dest - 1] = distance;
        adjointMatrix[dest - 1][src - 1] = distance;
    }

    public void print () {
        for (int i = 0; i < numOfScenes; i++) {
            StringBuilder str = new StringBuilder();
            for (int j = 0; j < numOfScenes; j++) {
                str.append(adjointMatrix[i][j]).append(" ");
            }
            System.out.println(str);
        }
    }
}
