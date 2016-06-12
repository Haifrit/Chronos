package jan.philip.wolter.chronos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


import org.lucasr.twowayview.widget.TwoWayView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


  TwoWayView myRecyclerView;
  MyRecyclerViewAdapter myRecyclerViewAdapter;
  LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
  StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
  CalendarLayoutManager calendarLayoutManager = new CalendarLayoutManager();
  List<MyDate> listOfMyDates = new ArrayList<>();
  MyDate dateOne = new MyDate(2000,1,1);
  MyDate dateTwo = new MyDate(3000,2,2);
  MyDate dateThree = new MyDate(1999,2,2);
  MyDate dateFour = new MyDate(2001,1,1);
  MyDate dateFive = new MyDate(3001,2,2);
  MyDate dateSix = new MyDate(1998,2,2);
  View viewForTouchListener;
  float downXValue = 0;
  float downYValue = 0;
  static final String LOG_TAG = "MainAcrivity";



  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);


    myRecyclerView = (TwoWayView) findViewById(R.id.list);

    fillDummyData();

    myRecyclerViewAdapter = new MyRecyclerViewAdapter(listOfMyDates);
    myRecyclerView.setAdapter(myRecyclerViewAdapter);

    setTouchListenerForRecyclerView();


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
              }
              // going forwards: pushing stuff to the left
              if (downXValue > currentX) {
                Log.d(LOG_TAG, "Motion Action = LEFT");
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
        return true;
      }
    });
  }


  private void fillDummyData () {

    dateFour.setEvent("Dies ist ein Test");
    listOfMyDates.add(dateOne);
    listOfMyDates.add(dateTwo);
    listOfMyDates.add(dateFour);
    listOfMyDates.add(dateFive);
    listOfMyDates.add(dateSix);
    listOfMyDates.add(dateThree);

  }


}
