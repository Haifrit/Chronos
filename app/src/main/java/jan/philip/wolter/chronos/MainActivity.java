package jan.philip.wolter.chronos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import org.lucasr.twowayview.widget.TwoWayView;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

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

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    myRecyclerView = (TwoWayView) findViewById(R.id.list);

    initializeDate();
    setTouchListenerForRecyclerView();
    getDatesForSelectedMonth();

    myRecyclerViewAdapter = new MyRecyclerViewAdapter(datesOfSelectedMonth);
    myRecyclerView.setAdapter(myRecyclerViewAdapter);
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
            downYValue = event.getY();
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
                decrementMonth();
                refreshAdapter();
              }
              // going forwards: pushing stuff to the left
              if (downXValue > currentX) {
                Log.d(LOG_TAG, "Motion Action = LEFT");
                incrementMonth();
                refreshAdapter();
              }
            } else {
              if (downYValue < currentY) {
                Log.d(LOG_TAG, "Motion Action = DOWN ");
              }
              if (downYValue > currentY) {
                Log.d(LOG_TAG, "Motion Action = UP");
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
    myRecyclerViewAdapter = new MyRecyclerViewAdapter(datesOfSelectedMonth);
    myRecyclerView.setAdapter(myRecyclerViewAdapter);
  }

}
