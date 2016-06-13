package jan.philip.wolter.chronos.jan.philip.wolter.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
  public static final String COLUMN_EVENT_HOUR = "event_hour";
  public static final String COLUMN_EVENT_MINUTE = "event_minute";
  public static final String COLUMN_EVENT_TEXT = "event_text";


  //Tabelle für Datum/Daten an denen es Events gibt
  public static final String TABLE_DATE_LIST = "date_list";

  public DBHelper(Context context){
  super(context, DB_NAME, null, DB_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {

  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

  }
}
