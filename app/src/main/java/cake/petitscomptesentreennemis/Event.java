package cake.petitscomptesentreennemis;

import java.util.Date;

public class Event {
    private int id_event;
    private String name;
    private String device;
    private Date date_begin;
    private Date date_end;

    public Event(String name, String device, Date date_begin, Date date_end) {
        this.id_event = -1;
        this.name = name;
        this.device = device;
        this.date_begin = date_begin;
        this.date_end = date_end;
    }

    public Event(int id_event, String name, String device, Date date_begin, Date date_end) {
        this.id_event = id_event;
        this.name = name;
        this.device = device;
        this.date_begin = date_begin;
        this.date_end = date_end;
    }

    public Date getDate_end() {
        return date_end;
    }

    public void setDate_end(Date date_end) {
        this.date_end = date_end;
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

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public Date getDate_begin() {
        return date_begin;
    }

    public void setDate_begin(Date date_begin) {
        this.date_begin = date_begin;
    }
}
