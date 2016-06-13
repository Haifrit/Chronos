package jan.philip.wolter.chronos.jan.philip.wolter.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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
