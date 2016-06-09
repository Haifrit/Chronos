package jan.philip.wolter.chronos;

import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  RecyclerView myRecyclerView;
  MyRecyclerViewAdapter myRecyclerViewAdapter;
  LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
  StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
  List<MyDate> listOfMyDates = new ArrayList<>();
  MyDate dateOne = new MyDate(2000,1,1);
  MyDate dateTwo = new MyDate(3000,2,2);
  MyDate dateThree = new MyDate(1999,2,2);
  MyDate dateFour = new MyDate(2001,1,1);
  MyDate dateFive = new MyDate(3001,2,2);
  MyDate dateSix = new MyDate(1998,2,2);



  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    myRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
    myRecyclerView.setLayoutManager(staggeredGridLayoutManager);
    dateFour.setEvent("Dies ist ein Test");
    listOfMyDates.add(dateOne);
    listOfMyDates.add(dateTwo);
    listOfMyDates.add(dateFour);
    listOfMyDates.add(dateFive);
    listOfMyDates.add(dateSix);
    listOfMyDates.add(dateThree);
    myRecyclerViewAdapter = new MyRecyclerViewAdapter(listOfMyDates);
    myRecyclerView.setAdapter(myRecyclerViewAdapter);
  }
}
