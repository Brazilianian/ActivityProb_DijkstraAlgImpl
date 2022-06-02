import javax.xml.stream.Location;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static final int COUNT_OF_SCENES = 6;
    private static LocationsMap locationsMap;

    public static void main (String[] args) {
        Scene[] scenesArr = new Scene[COUNT_OF_SCENES];
        fillScenes(scenesArr);

        List<Scene> scenes = EventUtil.createConcertPlan(scenesArr);

        fillRoad();

        int previousScene = 0;
        double priceForTransfer = 0;
        double priceForAllEvents = 0;
        for (Scene scene : scenes) {
            int[] distances = EventUtil.getShortestPathBetweenScenes(locationsMap, scene);
            priceForTransfer += distances[previousScene] * EventUtil.PRICE_FOR_KM;
            priceForAllEvents += scene.event.getPrice();
            System.out.printf("Scene ~ %-10s Event -- band <** %-11s **> price %-6.2f, start - %s, finish - %s, duration: %3d min *** %n",
                    String.format("%-6s #%d", scene.sceneSpecification.getName(), scene.sceneSpecification.getNumber()),
                    scene.event.getTitle(),
                    scene.event.getPrice(),
                    scene.event.getStart().format(DateTimeFormatter.ofPattern("HH:mm")),
                    scene.event.getFinish().format(DateTimeFormatter.ofPattern("HH:mm")),
                    scene.event.getDuration().toMinutes());
        }

        System.out.printf("%-22s %6.2f $%n", "price for all events:", priceForAllEvents);
        System.out.printf("%-22s %6.2f $%n", "price for transfer:", priceForTransfer);
        System.out.printf("%-22s %6.2f $%n", "total price:", priceForAllEvents + priceForTransfer);
    }

    private static void fillRoad() {
        locationsMap = new LocationsMap(COUNT_OF_SCENES);

        locationsMap.addRoad(1, 2, 2);
        locationsMap.addRoad(1, 5, 6);
        locationsMap.addRoad(1, 6, 3);

        locationsMap.addRoad(2, 5, 3);
        locationsMap.addRoad(2, 3, 2);

        locationsMap.addRoad(3, 5, 6);
        locationsMap.addRoad(3, 4, 3);

        locationsMap.addRoad(4, 5, 2);

        locationsMap.addRoad(5, 6, 2);

    }

    private static void fillScenes(Scene[] scenes) {
        scenes[0] = new Scene(SceneSpecification.YELLOW, Event.of(LocalTime.of(16, 0), LocalTime.of(18, 20), "Iron Maiden", 130));
        scenes[1] = new Scene(SceneSpecification.PINK, Event.of(LocalTime.of(17, 0), LocalTime.of(19, 50), "Judas Priest", 85.7));
        scenes[2] = new Scene(SceneSpecification.ORANGE, Event.of(LocalTime.of(18, 50), LocalTime.of(20, 20), "Metallica", 110));
        scenes[3] = new Scene(SceneSpecification.BLACK, Event.of(LocalTime.of(19, 30), LocalTime.of(22, 20), "AC~DC", 116));
        scenes[4] = new Scene(SceneSpecification.GREEN, Event.of(LocalTime.of(20, 50), LocalTime.of(22, 30), "RHCP", 99));
        scenes[5] = new Scene(SceneSpecification.PURPLE, Event.of(LocalTime.of(17, 0), LocalTime.of(19, 30), "Guns N` Roses", 78.5));
    }
}
