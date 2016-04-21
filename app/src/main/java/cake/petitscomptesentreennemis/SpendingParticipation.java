package cake.petitscomptesentreennemis;

/**
 * Created by brian on 4/19/16.
 */
public class SpendingParticipation {
    private String id_user;
    private int id_spending;
    private int weight;
    private Spending spending = null;

    public SpendingParticipation(String id_user, int id_spending, int weight) {
        this.id_user = id_user;
        this.id_spending = id_spending;
        this.weight = weight;
    }

    public SpendingParticipation(String id_user, int id_spending, int weight, Spending spending) {
        this.id_user = id_user;
        this.id_spending = id_spending;
        this.weight = weight;
        this.spending = spending;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public int getId_spending() {
        return id_spending;
    }

    public void setId_spending(int id_spending) {
        this.id_spending = id_spending;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Spending getSpending() {
        return spending;
    }

    public void setSpending(Spending spending) {
        this.spending = spending;
    }
}
