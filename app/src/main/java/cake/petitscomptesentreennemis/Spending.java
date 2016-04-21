package cake.petitscomptesentreennemis;

import java.util.Date;

public class Spending {
    private int id_spending;
    private String description;
    private Double amount;
    private Date date;
    private int id_event;
    private Event event = null;

    public Spending(int id_spending, String description, Double amount, Date date, int id_event) {
        this.id_spending = id_spending;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.id_event = id_event;
    }

    public Spending(int id_spending, String description, Double amount, Date date, int id_event, Event event) {
        this.id_spending = id_spending;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.id_event = id_event;
        this.event = event;
    }

    public Spending(String description, Double amount, Date date, int id_event) {
        this.id_spending = -1;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.id_event = id_event;
    }

    public Spending(String description, Double amount, Date date, int id_event, Event event) {
        this.id_spending = -1;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.id_event = id_event;
        this.event = event;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public int getId_spending() {
        return id_spending;
    }

    public void setId_spending(int id_spending) {
        this.id_spending = id_spending;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }
}
