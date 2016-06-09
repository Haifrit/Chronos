package jan.philip.wolter.chronos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  RecyclerView myRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
  LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
  List<MyDate> listOfMyDates = new ArrayList<>();
  MyDate dateOne = new MyDate(2000,1,1);
  MyDate dateTwo = new MyDate(3000,2,2);
  MyDate dateThree = new MyDate(1999,2,2);


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    myRecyclerView.setLayoutManager(linearLayoutManager);
    listOfMyDates.add(dateOne);
    listOfMyDates.add(dateTwo);
    listOfMyDates.add(dateThree);
  }
}
