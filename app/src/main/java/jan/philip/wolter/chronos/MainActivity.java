package jan.philip.wolter.chronos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import org.lucasr.twowayview.widget.TwoWayView;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import jan.philip.wolter.chronos.jan.philip.wolter.database.ChronosDataSource;
import jan.philip.wolter.chronos.jan.philip.wolter.database.MyEvent;

public class MainActivity extends AppCompatActivity {

  MyEvent myEvent;
  MyDate myDate;

  HashMap<Integer,MyEvent> listOfEvents = new HashMap<>();
  GlobalDatabase globalDatabase;
  final Calendar calendar = Calendar.getInstance();
  int selectedYear;
  int selectedMonth;
  TwoWayView myRecyclerView;
  MyRecyclerViewAdapter myRecyclerViewAdapter;
  //Selected Month will be the current Month by app start
  List<MyDate> datesOfSelectedMonth = new ArrayList<>();
  View viewForTouchListener;
  float downXValue = 0;
  float downYValue = 0;
  static final String LOG_TAG = "MainActivity";

  ChronosDataSource chronosDataSource;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    globalDatabase = (GlobalDatabase)getApplication();

    myRecyclerView = (TwoWayView) findViewById(R.id.list);

    initializeDate();
    setTouchListenerForRecyclerView();
    getDatesForSelectedMonth();

    chronosDataSource = globalDatabase.getChronosDataSource();
    listOfEvents = chronosDataSource.getEventsForMonth(calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR));
    myRecyclerViewAdapter = new MyRecyclerViewAdapter(datesOfSelectedMonth, listOfEvents, this);
    myRecyclerView.setAdapter(myRecyclerViewAdapter);


    myEvent = new MyEvent(10,10);
    myDate = new MyDate(2016,5,1);
    myDate = new MyDate(2016,5,30);
    chronosDataSource.insertEvent(myEvent,myDate);
    chronosDataSource.getEventsForMonth(5, 2016);
  }

  private void initializeDate () {
    selectedYear = calendar.get(Calendar.YEAR);
    selectedMonth = calendar.get(Calendar.MONTH);
  }

  private void setTouchListenerForRecyclerView () {
    viewForTouchListener = findViewById(R.id.list);
    viewForTouchListener.setOnTouchListener(new View.OnTouchListener() {
      public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
          case MotionEvent.ACTION_DOWN: {
            // store the X value when the user's finger was pressed down
            downXValue = event.getX();
            Log.d(LOG_TAG, "downXValue = " + downXValue);
            downYValue = event.getY();
            Log.d(LOG_TAG, "downYValue = " + downYValue);
            break;
          }
          case MotionEvent.ACTION_UP: {
            // Get the X value when the user released his/her finger
            float currentX = event.getX();
            float currentY = event.getY();
            // check if horizontal or vertical movement was bigger
            if (Math.abs(downXValue - currentX) > Math.abs(downYValue
                    - currentY)) {
              // going backwards: pushing stuff to the right
              if (downXValue < currentX) {
                Log.d(LOG_TAG, "Motion Action = RIGHT");
                Log.d(LOG_TAG, "downXValue = " + downXValue);
                Log.d(LOG_TAG, "downYValue = " + downYValue);


                int savePosition = myRecyclerView.getFirstVisiblePosition();
                Log.d(LOG_TAG, "savePosition = " + savePosition);

                decrementMonth();
                refreshAdapter();

                Log.d(LOG_TAG, "Adapter Refreshed");
                Log.d(LOG_TAG, "downXValue = " + downXValue);
                Log.d(LOG_TAG, "downYValue = " + downYValue);

                myRecyclerView.scrollToPosition(savePosition);
              }
              // going forwards: pushing stuff to the left
              if (downXValue > currentX) {
                Log.d(LOG_TAG, "Motion Action = LEFT");
                Log.d(LOG_TAG, "downXValue = " + downXValue);
                Log.d(LOG_TAG, "downYValue = " + downYValue);

                int savePosition = myRecyclerView.getFirstVisiblePosition();
                Log.d(LOG_TAG, "savePosition = " + savePosition);

                incrementMonth();
                refreshAdapter();

                Log.d(LOG_TAG, "Adapter Refreshed");
                Log.d(LOG_TAG, "downXValue = " + downXValue);
                Log.d(LOG_TAG, "downYValue = " + downYValue);

                myRecyclerView.scrollToPosition(savePosition);
              }
            } else {
              if (downYValue < currentY) {
                Log.d(LOG_TAG, "Motion Action = DOWN ");
                Log.d(LOG_TAG, "downXValue = " + downXValue);
                Log.d(LOG_TAG, "downYValue = " + downYValue);
              }
              if (downYValue > currentY) {
                Log.d(LOG_TAG, "Motion Action = UP");
                Log.d(LOG_TAG, "downXValue = " + downXValue);
                Log.d(LOG_TAG, "downYValue = " + downYValue);
              }
            }
            break;
          }
        }
        // Returning False here enables the ListView to scroll
        return false;
      }
    });
  }

  private void getDatesForSelectedMonth () {
    int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    int month = calendar.get(Calendar.MONTH);
    int year = calendar.get(Calendar.YEAR);
    Log.d(LOG_TAG, "Tage im aktuellem Monat = " + days);
    datesOfSelectedMonth.clear();
    for (int i = 1; i <= days; i++) {
      Log.d(LOG_TAG, "Tage hinzugefügt = " + i);
      MyDate myDate = new MyDate(year,month,i);
      datesOfSelectedMonth.add(myDate);
    }
  }

  private void incrementMonth () {
    selectedMonth++;
    if (selectedMonth > 11) {
      selectedMonth = 0;
      selectedYear++;
    }
    calendar.set(Calendar.YEAR, selectedYear);
    calendar.set(Calendar.MONTH, selectedMonth);
    Log.d(LOG_TAG, "+++ Der aktuelle Monat ist = " + calendar.get(Calendar.MONTH));
  }

  private void decrementMonth () {
    selectedMonth--;
    if (selectedMonth < 0) {
      selectedMonth = 11;
      selectedYear--;
    }
    calendar.set(Calendar.YEAR, selectedYear);
    calendar.set(Calendar.MONTH, selectedMonth);
    Log.d(LOG_TAG, "--- Der aktuelle Monat ist = " + calendar.get(Calendar.MONTH));
  }

  private void refreshAdapter () {
    getDatesForSelectedMonth();
    listOfEvents = chronosDataSource.getEventsForMonth(calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR));
    myRecyclerViewAdapter = new MyRecyclerViewAdapter(datesOfSelectedMonth, listOfEvents, this);
    myRecyclerView.setAdapter(myRecyclerViewAdapter);
  }

}
