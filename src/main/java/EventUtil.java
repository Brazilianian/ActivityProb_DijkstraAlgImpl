import org.w3c.dom.events.Event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class EventUtil {
    public static final double PRICE_FOR_KM = 2.5;

    public static List<Scene> createConcertPlan(Scene[] scenes) {
        scenes = Arrays.stream(scenes).toList().stream()
                .sorted(Comparator.comparing(x -> x.event.getStart()))
                .toArray(Scene[]::new);

        List<Scene> finalScenes = new ArrayList<>();
        Scene scene = scenes[0];
        finalScenes.add(scene);
        do {
            scene = getNearestSceneAfter(scenes, scene);
            if (scene != null) {
                finalScenes.add(scene);
            }
        } while (scene != null);

        return finalScenes;
    }

    private static Scene getNearestSceneAfter(Scene[] scenes, Scene scene) {
        scenes = Arrays.stream(scenes).toList().stream()
                .sorted(Comparator.comparing(x -> x.event.getFinish()))
                .toArray(Scene[]::new);
        for (Scene s :
                scenes) {
            if (s.event.getStart().isAfter(scene.event.getFinish()) && s != scene) {
                return s;
            }
        }
        return null;
    }

    public static int[] getShortestPathBetweenScenes(LocationsMap locationsMap, Scene scene) {
        int[] distance = new int[locationsMap.getNumOfScenes()];
        Arrays.fill(distance, Integer.MAX_VALUE);
        boolean[] visited = new boolean[locationsMap.getNumOfScenes()];

        distance[scene.sceneSpecification.getNumber() - 1] = 0;
        for (int i = 0; i < locationsMap.getNumOfScenes(); i++) {
            int closestLocation = getClosestLocation(distance, visited);
            visited[closestLocation] = true;
            for (int j = 0; j < locationsMap.getNumOfScenes(); j++) {
                if (!visited[j]) {
                    if (locationsMap.getAdjointMatrix()[closestLocation][j] != 0) {
                        int d = distance[closestLocation] + locationsMap.getAdjointMatrix()[closestLocation][j];
                        if (d < distance[j]) {
                            distance[j] = d;
                        }
                    }
                }
            }
        }
        return distance;
    }

    private static int getClosestLocation(int[] distance, boolean[] visited) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int i = 0; i < distance.length; i++) {
            if (distance[i] < min && !visited[i]) {
                min = distance[i];
                minIndex = i;
            }
        }

        return minIndex;
    }
}