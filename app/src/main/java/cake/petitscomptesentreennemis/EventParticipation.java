package cake.petitscomptesentreennemis;

/**
 * Created by brian on 4/19/16.
 */
public class EventParticipation {
    private int id_event;
    private String name;
    private Event event = null;

    public EventParticipation(int id_event, String name) {
        this.id_event = id_event;
        this.name = name;
    }

    public EventParticipation(int id_event, String name, Event event) {
        this.id_event = id_event;
        this.name = name;
        this.event = event;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
