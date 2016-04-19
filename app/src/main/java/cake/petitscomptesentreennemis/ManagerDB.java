package cake.petitscomptesentreennemis;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ManagerDB extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "PetitCompteEntreEnnemisDB";

    // Table d'événement
    private static final String TABLE_EVENTS = "Events";
    private static final String KEY_EVENTS_ID = "id_event";
    private static final String KEY_EVENTS_NAME = "name";
    private static final String KEY_EVENTS_DEVICE = "device";
    private static final String KEY_EVENTS_DATE_BEGIN = "date_begin";
    private static final String KEY_EVENTS_DATE_END = "date_end";

    // Table des participation aux événement
    private static final String TABLE_EVENTS_PARTICIPATIONS = "EventsParticipations";
    private static final String KEY_EVENTS_PARTICIPATIONS_ID_EVENT = "id_event";
    private static final String KEY_EVENTS_PARTICIPATIONS_NAME = "name";

    // Table des dépenses
    private static final String TABLE_SPENDINGS = "Spendings";
    private static final String KEY_SPENDINGS_ID = "id_spending";
    private static final String KEY_SPENDINGS_DESCRIPTION = "description";
    private static final String KEY_SPENDINGS_AMOUNT = "amount";
    private static final String KEY_SPENDINGS_DATE = "date";
    private static final String KEY_SPENDINGS_ID_EVENT = "id_event";

    // Table des participations aux dépenses
    private static final String TABLE_SPENDINGS_PARTICIPATION = "SpendingsParticipation";
    private static final String KEY_SPENDINGS_ID_USER = "id_user";
    private static final String KEY_SPENDINGS_ID_SPENDING = "id_spending";
    private static final String KEY_SPENDINGS_WEIGHT = "weight";

    // Création des tables
    private static final String CREATE_TABLE_EVENTS = "CREATE TABLE " +
            TABLE_EVENTS + "(" +
            KEY_EVENTS_ID + " INTEGER PRIMARY KEY, " +
            KEY_EVENTS_NAME + " TEXT, " +
            KEY_EVENTS_DEVICE + " TEXT, " +
            KEY_EVENTS_DATE_BEGIN + " DATETIME, " +
            KEY_EVENTS_DATE_END + " DATETIME " +
            ")";

    private static final String CREATE_TABLE_EVENTS_PARTICIPATIONS = "CREATE TABLE " +
            TABLE_EVENTS_PARTICIPATIONS + "(" +
            KEY_EVENTS_PARTICIPATIONS_ID_EVENT + " INTEGER, " +
            KEY_EVENTS_PARTICIPATIONS_NAME + " TEXT, " +
            "FOREIGN KEY(" + KEY_EVENTS_PARTICIPATIONS_ID_EVENT + ") REFERENCES " + TABLE_EVENTS + "(" + KEY_EVENTS_ID + ") ON DELETE CASCADE, " +
            "PRIMARY KEY (" + KEY_EVENTS_PARTICIPATIONS_ID_EVENT + ", " + KEY_EVENTS_PARTICIPATIONS_NAME + ") " +
            ")";

    private static final String CREATE_TABLE_SPENDINGS = "CREATE TABLE " +
            TABLE_SPENDINGS + "(" +
            KEY_SPENDINGS_ID + " INTEGER PRIMARY KEY, " +
            KEY_SPENDINGS_DESCRIPTION + " TEXT, " +
            KEY_SPENDINGS_AMOUNT + " TEXT, " +
            KEY_SPENDINGS_DATE + " DATETIME, " +
            KEY_SPENDINGS_ID_EVENT + " TEXT, " +
            "FOREIGN KEY(" + KEY_SPENDINGS_ID_EVENT + ") REFERENCES " + TABLE_EVENTS + "(" + KEY_EVENTS_ID + ") ON DELETE CASCADE " +
            ")";

    private static final String CREATE_TABLE_SPENDINGS_PARTICIPATION = "CREATE TABLE " +
            TABLE_SPENDINGS_PARTICIPATION + "(" +
            KEY_SPENDINGS_ID_USER + " TEXT, " +
            KEY_SPENDINGS_ID_SPENDING + " INTEGER, " +
            KEY_SPENDINGS_WEIGHT + " INTEGER, " +
            "FOREIGN KEY(" + KEY_SPENDINGS_ID_USER + ") REFERENCES " + TABLE_EVENTS_PARTICIPATIONS + "(" + KEY_EVENTS_PARTICIPATIONS_NAME + ") ON DELETE CASCADE, " +
            "FOREIGN KEY(" + KEY_SPENDINGS_ID_SPENDING + ") REFERENCES " + TABLE_SPENDINGS + "(" + KEY_SPENDINGS_ID + ") ON DELETE CASCADE, " +
            "PRIMARY KEY (" + KEY_SPENDINGS_ID_USER + ", " + KEY_SPENDINGS_ID_SPENDING + ") " +
            ")";

    SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public ManagerDB(Context context) {
        super(context, DATABASE_NAME, null, 0);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Création des tables
        db.execSQL(CREATE_TABLE_EVENTS);
        db.execSQL(CREATE_TABLE_EVENTS_PARTICIPATIONS);
        db.execSQL(CREATE_TABLE_SPENDINGS);
        db.execSQL(CREATE_TABLE_SPENDINGS_PARTICIPATION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Si nouvelle version, on supprime pour les recréer
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_EVENTS);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_EVENTS_PARTICIPATIONS);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_SPENDINGS);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_SPENDINGS_PARTICIPATION);

        // Création des nouvelles tables
        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Activation foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    /*************
     * Events
     ************/

    public long addEvent(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues new_event = new ContentValues();
        new_event.put(KEY_EVENTS_NAME, event.getName());
        new_event.put(KEY_EVENTS_DEVICE, event.getDevice());
        new_event.put(KEY_EVENTS_DATE_BEGIN, date_format.format(event.getDate_begin()));
        new_event.put(KEY_EVENTS_DATE_END, date_format.format(event.getDate_end()));

        // Ajout dans la table
        long id_event = db.insert(TABLE_EVENTS, null, new_event);

        db.close();
        return id_event;
    }

    public Event getEventById(int id_event) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_EVENTS + " " +
                "WHERE " + KEY_EVENTS_ID + " = " + id_event, null);

        if (cursor != null && cursor.moveToFirst()) {
            Event event = null;
            try {
                event = new Event(
                        cursor.getInt(cursor.getColumnIndex(KEY_EVENTS_ID)),
                        cursor.getString(cursor.getColumnIndex(KEY_EVENTS_NAME)),
                        cursor.getString(cursor.getColumnIndex(KEY_EVENTS_DEVICE)),
                        date_format.parse(cursor.getString(cursor.getColumnIndex(KEY_EVENTS_DATE_BEGIN))),
                        date_format.parse(cursor.getString(cursor.getColumnIndex(KEY_EVENTS_DATE_END)))
                );
            } catch (ParseException e) {
                e.printStackTrace();
            }

            db.close();
            return event;
        } else {
            return null;
        }
    }

    public ArrayList<Event> getEvents() {
        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<Event> events = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT  * FROM " + TABLE_EVENTS, null);

        if (cursor.moveToFirst()) {
            do {
                try {
                    Event event = new Event(
                            cursor.getInt(cursor.getColumnIndex(KEY_EVENTS_ID)),
                            cursor.getString(cursor.getColumnIndex(KEY_EVENTS_NAME)),
                            cursor.getString(cursor.getColumnIndex(KEY_EVENTS_DEVICE)),
                            date_format.parse(cursor.getString(cursor.getColumnIndex(KEY_EVENTS_DATE_BEGIN))),
                            date_format.parse(cursor.getString(cursor.getColumnIndex(KEY_EVENTS_DATE_END)))
                    );
                    events.add(event);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } while (cursor.moveToNext());
        }
        return events;
    }

    public int updateEventById(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues update_event = new ContentValues();
        update_event.put(KEY_EVENTS_NAME, event.getName());
        update_event.put(KEY_EVENTS_DEVICE, event.getDevice());
        update_event.put(KEY_EVENTS_DATE_BEGIN, date_format.format(event.getDate_begin()));
        update_event.put(KEY_EVENTS_DATE_END, date_format.format(event.getDate_end()));

        int number_of_rows_affected = db.update(TABLE_EVENTS, update_event, KEY_EVENTS_ID + " = ?", new String[]{Integer.toString(event.getId_event())});

        db.close();
        return number_of_rows_affected;
    }

    public void deleteEventById(int id_event) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_EVENTS, KEY_EVENTS_ID + " = ?", new String[]{Integer.toString(id_event)});
        db.close();
    }

    public void deleteEvent(Event event) {
        deleteEventById(event.getId_event());
    }
}
