package jan.philip.wolter.chronos.jan.philip.wolter.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by J.Wolter on 13.06.2016.
 */
public class DBHelper extends SQLiteOpenHelper {

  private static final String LOG_TAG = "db_helper";

  public static final String DB_NAME = "witenso.db";
  public static final int DB_VERSION = 1;


  //Tabelle für Events
  public static final String TABLE_EVENT_LIST = "event_list";

  //Spalten für die Tabelle Events
  public static final String COLUMN_EVENT_ID = "event_id";
  public static final String COLUMN_EVENT_MINUTE = "event_minute";
  public static final String COLUMN_EVENT_HOUR = "event_hour";
  public static final String COLUMN_EVENT_DAY = "event_day";
  public static final String COLUMN_EVENT_MONTH = "event_month";
  public static final String COLUMN_EVENT_YEAR = "event_year";
  public static final String COLUMN_EVENT_TEXT = "event_text";

  //Raw SQL Querys ( benutzen mit database.rawQuery )

  //Raw SQL Query um alle events zu erhalten die es zu einem vorher spezifiziertem Datum gibt
  public static final String SQL_QUERY_EVENTS_IN_MONTH = "SELECT event_list." + COLUMN_EVENT_ID
          + ", event_list." + COLUMN_EVENT_MONTH
          + ", event_list." + COLUMN_EVENT_TEXT
          + ", event_list." + COLUMN_EVENT_HOUR
          + ", event_list." + COLUMN_EVENT_MINUTE
          + ", event_list." + COLUMN_EVENT_DAY
          + ", event_list." + COLUMN_EVENT_YEAR
          + " FROM " + TABLE_EVENT_LIST
          + " WHERE event_list." + COLUMN_EVENT_MONTH + " =? AND " + COLUMN_EVENT_YEAR + " =?";

  //SQL Create String für die Event Tabelle
  public static final String SQL_CREATE_EVENT =
          "CREATE TABLE " + TABLE_EVENT_LIST +
                  "(" + COLUMN_EVENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                  COLUMN_EVENT_TEXT + " TEXT NOT NULL, " +
                  COLUMN_EVENT_MINUTE + " INTEGER NOT NULL, " +
                  COLUMN_EVENT_HOUR + " INTEGER NOT NULL, " +
                  COLUMN_EVENT_DAY + " INTEGER NOT NULL, " +
                  COLUMN_EVENT_MONTH + " INTEGER NOT NULL," +
                  COLUMN_EVENT_YEAR + " INTEGER NOT NULL);";


  public DBHelper(Context context){
  super(context, DB_NAME, null, DB_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {

    Log.d(LOG_TAG, "Die Tabelle Event wird mit SQL-Befehl: " + SQL_CREATE_EVENT + " angelegt.");
    db.execSQL(SQL_CREATE_EVENT);

  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

  }
}
