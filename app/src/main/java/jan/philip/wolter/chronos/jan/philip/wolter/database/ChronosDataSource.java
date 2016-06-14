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
          DBHelper.COLUMN_EVENT_DATE_ID
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
    values.put(DBHelper.COLUMN_EVENT_DATE_ID, myDate.getDateAsInt());
    Log.d(LOG_TAG, "MyDate Als Int = " + myDate.getDateAsInt());
    database.insert(DBHelper.TABLE_EVENT_LIST, null, values);
    Log.d(LOG_TAG, "Event in die Datenbank geschrieben ");
  }

  public HashMap<Integer,MyEvent> getEventsForDate (MyDate minDateInMonth, MyDate maxDateInMonth) {

    HashMap<Integer,MyEvent> mapOfEvents = new HashMap<>();

    Log.d(LOG_TAG, "RAW QUERY = " + database.rawQuery(DBHelper.SQL_QUERY_EVENTS_ON_DATE, new String[]{String.valueOf(minDateInMonth), String.valueOf(maxDateInMonth)}).toString());

    Cursor cursor = database.rawQuery(DBHelper.SQL_QUERY_EVENTS_ON_DATE, new String[]{String.valueOf(minDateInMonth),String.valueOf(maxDateInMonth)});
    cursor.moveToFirst();

    while (cursor.isAfterLast() == false) {

      int idIndex = cursor.getColumnIndex(DBHelper.COLUMN_EVENT_ID);
      int idDate = cursor.getColumnIndex(DBHelper.COLUMN_DATE);
      int idText = cursor.getColumnIndex(DBHelper.COLUMN_EVENT_TEXT);
      int idHour = cursor.getColumnIndex(DBHelper.COLUMN_EVENT_HOUR);
      int idMinute = cursor.getColumnIndex(DBHelper.COLUMN_EVENT_MINUTE);

      int indexId = cursor.getInt(idIndex);
      String text = cursor.getString(idText);
      int date = cursor.getInt(idDate);
      int hour = cursor.getInt(idHour);
      int minute = cursor.getInt(idMinute);

      MyEvent myEvent = new MyEvent(hour,minute);
      myEvent.setEventText(text);
      mapOfEvents.put(date,myEvent);
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
