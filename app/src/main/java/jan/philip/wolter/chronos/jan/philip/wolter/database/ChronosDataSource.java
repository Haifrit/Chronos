package jan.philip.wolter.chronos.jan.philip.wolter.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import jan.philip.wolter.chronos.MyDate;

/**
 * Created by J.Wolter on 13.06.2016.
 */
public class ChronosDataSource {

  private static final String LOG_TAG = "datasource";

  private SQLiteDatabase database;
  private DBHelper dbHelper;

  private String[] columns_event = {
          DBHelper.COLUMN_EVENT_ID,
          DBHelper.COLUMN_EVENT_TEXT,
          DBHelper.COLUMN_EVENT_HOUR,
          DBHelper.COLUMN_EVENT_MINUTE,
          DBHelper.COLUMN_EVENT_DATE
  };

  private String[] columns_date = {
          DBHelper.COLUMN_DATE
  };

  public ChronosDataSource(Context context) {
    Log.d(LOG_TAG, "Die DataSource erzeugt jetzt den dbHelper.");
    this.dbHelper = new DBHelper(context);
  }

  public void insertDate (int date) {
    ContentValues values = new ContentValues();
    values.put(DBHelper.COLUMN_DATE, date);
    database.insert(DBHelper.TABLE_DATE_LIST, null, values);
    Log.d(LOG_TAG, date  + " in die Datenbank geschrieben ");
  }

  public void insertEvent (MyEvent myEvent, MyDate myDate) {
    ContentValues values = new ContentValues();
    values.put(DBHelper.COLUMN_EVENT_HOUR, myEvent.getHour());
    values.put(DBHelper.COLUMN_EVENT_MINUTE, myEvent.getMinute());
    values.put(DBHelper.COLUMN_EVENT_TEXT, myEvent.getEventText());
    values.put(DBHelper.COLUMN_EVENT_DATE, myDate.getDateAsInt());
    Log.d(LOG_TAG, "MyDate Als Int = " + myDate.getDateAsInt());
    database.insert(DBHelper.TABLE_EVENT_LIST, null, values);
    Log.d(LOG_TAG, "Event in die Datenbank geschrieben ");
  }

  public void open() {
    Log.d(LOG_TAG, "Eine Referenz auf die Datenbank wird jetzt angefragt.");
    database = dbHelper.getWritableDatabase();
    Log.d(LOG_TAG, "Datenbank-Referenz erhalten. Pfad zur Datenbank: " + database.getPath());
  }

  public void close() {
    dbHelper.close();
    Log.d(LOG_TAG, "Datenbank mit Hilfe des DbHelpers geschlossen.");
  }
}
