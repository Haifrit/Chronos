package jan.philip.wolter.chronos.jan.philip.wolter.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.HashMap;

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
          DBHelper.COLUMN_EVENT_DAY,
          DBHelper.COLUMN_EVENT_MONTH,
          DBHelper.COLUMN_EVENT_YEAR
  };

  public ChronosDataSource(Context context) {
    Log.d(LOG_TAG, "Die DataSource erzeugt jetzt den dbHelper.");
    this.dbHelper = new DBHelper(context);
  }



  public void insertEvent (MyEvent myEvent, MyDate myDate) {
    ContentValues values = new ContentValues();
    values.put(DBHelper.COLUMN_EVENT_TEXT, myEvent.getEventText());
    values.put(DBHelper.COLUMN_EVENT_MINUTE, myEvent.getMinute());
    values.put(DBHelper.COLUMN_EVENT_HOUR, myEvent.getHour());
    values.put(DBHelper.COLUMN_EVENT_DAY, myDate.getDayOfMonth());
    values.put(DBHelper.COLUMN_EVENT_MONTH, myDate.getMonth());
    values.put(DBHelper.COLUMN_EVENT_YEAR, myDate.getYear());

    database.insert(DBHelper.TABLE_EVENT_LIST, null, values);
    Log.d(LOG_TAG, "Event in die Datenbank geschrieben ");
  }

  public HashMap<Integer,MyEvent> getEventsForMonth (int month, int year) {

    HashMap<Integer,MyEvent> mapOfEvents = new HashMap<>();

    Log.d(LOG_TAG, "month = " + month + " year = " + year);

    Cursor cursor = database.rawQuery(DBHelper.SQL_QUERY_EVENTS_IN_MONTH, new String[]{String.valueOf(month),String.valueOf(year)});
    cursor.moveToFirst();

    while (cursor.isAfterLast() == false) {

      int idIndex = cursor.getColumnIndex(DBHelper.COLUMN_EVENT_ID);
      int idText = cursor.getColumnIndex(DBHelper.COLUMN_EVENT_TEXT);
      int idMinute = cursor.getColumnIndex(DBHelper.COLUMN_EVENT_MINUTE);
      int idHour = cursor.getColumnIndex(DBHelper.COLUMN_EVENT_HOUR);
      int idDay = cursor.getColumnIndex(DBHelper.COLUMN_EVENT_DAY);
      int idMonth = cursor.getColumnIndex(DBHelper.COLUMN_EVENT_MONTH);
      int idYear = cursor.getColumnIndex(DBHelper.COLUMN_EVENT_YEAR);

      int indexId = cursor.getInt(idIndex);
      String text = cursor.getString(idText);
      int minute = cursor.getInt(idMinute);
      int hour = cursor.getInt(idHour);
      int day = cursor.getInt(idDay);
      int monthId = cursor.getInt(idMonth);
      int yearId = cursor.getInt(idYear);

      MyEvent myEvent = new MyEvent(hour,minute);
      myEvent.setEventText(text);
      mapOfEvents.put(day,myEvent);
      Log.d(LOG_TAG, "erfolgreich gelesen " + myEvent.getEventText());

      cursor.moveToNext();
    }
    return  mapOfEvents;
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
