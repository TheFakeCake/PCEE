package cake.petitscomptesentreennemis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView text_debug = (TextView) findViewById(R.id.debug);

        ManagerDB test = new ManagerDB(this);
        long id = test.addEvent(new Event("Evenement5", "CHF", new Date(), new Date()));
        test.addEvent(new Event("Evenement2", "CHF", new Date(), new Date()));
        test.addEvent(new Event("Evenement3", "CHF", new Date(), new Date()));

        ArrayList<Event> lists = test.getEvents();

        String msg = "";
        for (Event ev : lists) {
            msg += "\n"+ev.getName();
        }
        text_debug.setText(msg);

        Event lel = test.getEventById((int)id);
        text_debug.setText(lel.getName());

        lel.setName("New name");
        test.updateEventById(lel);
        text_debug.setText(lel.getName());

        test.addEventParticipation(new EventParticipation(lel.getId_event(), "Brian"));
        test.addEventParticipation(new EventParticipation(lel.getId_event(), "Benjamin"));
        test.addEventParticipation(new EventParticipation(lel.getId_event(), "Moi"));
        test.addEventParticipation(new EventParticipation(lel.getId_event(), "Lui"));

        test.deleteEventParticipationByName("Brian");

        ArrayList<EventParticipation> list2 = test.getEventParticipationByIdEvent(lel.getId_event());

        String msg2 = "";
        for (EventParticipation ev : list2) {
            msg2 += "\n"+ ev.getName();
        }
        text_debug.setText(msg2);


        long id_spending = test.addSpendig(new Spending("Description, ma gueule", 350d, new Date(), lel.getId_event()));
        Spending spending = test.getSpendingById((int) id_spending);

        text_debug.setText(spending.getDescription());

        test.addSpendingParticipation(new SpendingParticipation("Benjamin", (int) id_spending, 1));

        int id_event = lel.getId_event();

        test.deleteEvent(lel);
        lel = test.getEventById(lel.getId_event());
        if (lel != null)
            text_debug.setText(lel.getName());
        else
            text_debug.setText(".");

        list2 = test.getEventParticipationByIdEvent(id_event);
        String msg3 = "";
        for (EventParticipation ev : list2) {
            msg3 += "\n"+ ev.getName();
        }
        text_debug.setText(msg3);

    }
}
