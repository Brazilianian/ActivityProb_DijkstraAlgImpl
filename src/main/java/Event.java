import java.time.Duration;
import java.time.LocalTime;

public class Event {
    private LocalTime start;
    private LocalTime finish;
    private String title;
    private double price;

    public Duration getDuration(){
        return Duration.between(start, finish);
    }

    public static Event of (LocalTime start, LocalTime finish, String title, double price) {
        Event event = new Event();
        event.start = start;
        event.finish = finish;
        event.title = title;
        event.price = price;

        return event;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getFinish() {
        return finish;
    }

    public void setFinish(LocalTime finish) {
        this.finish = finish;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
