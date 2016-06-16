package jan.philip.wolter.chronos;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.lucasr.twowayview.widget.SpannableGridLayoutManager;

import java.util.HashMap;
import java.util.List;

import jan.philip.wolter.chronos.jan.philip.wolter.database.ChronosDataSource;
import jan.philip.wolter.chronos.jan.philip.wolter.database.MyEvent;

/**
 * Created by J.Wolter on 09.06.2016.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyDateViewHolder> {

  static final String LOG_TAG = "MyAdapter";
  GlobalDatabase globalDatabase;
  ChronosDataSource chronosDataSource;
  private List<MyDate> listOfmyDates;
  HashMap<Integer,MyEvent> listOfEvents;
  private Context mContext;

  public MyRecyclerViewAdapter (List<MyDate> listOfmyDates, HashMap<Integer,MyEvent> listOfEvents, Context context) {
    this.mContext = context;
    this.listOfmyDates = listOfmyDates;
    this.listOfEvents = listOfEvents;
    globalDatabase = (GlobalDatabase) mContext.getApplicationContext();
    this.chronosDataSource = globalDatabase.getChronosDataSource();
  }

  public static class MyDateViewHolder extends RecyclerView.ViewHolder {

    TextView year;
    TextView month;
    TextView dayOfWeek;
    TextView event;

    public MyDateViewHolder(View itemView) {
      super(itemView);
      year = (TextView)itemView.findViewById(R.id.year);
      month = (TextView)itemView.findViewById(R.id.month);
      dayOfWeek = (TextView)itemView.findViewById(R.id.day);
      event = (TextView)itemView.findViewById(R.id.event);
    }
  }

  @Override
  public MyDateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_date_view_group, parent, false);
    MyDateViewHolder myDateViewHolder = new MyDateViewHolder(view);
    return myDateViewHolder;
  }

  @Override
  public void onBindViewHolder(MyDateViewHolder holder, int position) {
    Log.d(LOG_TAG, "View für Position = " + position + " wird erstellt");
    View itemView = holder.itemView;
    SpannableGridLayoutManager.LayoutParams layoutParams = (SpannableGridLayoutManager.LayoutParams)  itemView.getLayoutParams();
    layoutParams.rightMargin = 4;
    layoutParams.leftMargin = 4;
    layoutParams.topMargin = 4;
    layoutParams.bottomMargin = 4;
    //Colspan muss hier gesetzt werden da der View mit Viewspan 3 wiederverwendet werden kann
    layoutParams.colSpan = 1;
    holder.year.setText("" + listOfmyDates.get(position).getYear());
    holder.month.setText("" + listOfmyDates.get(position).getMonthAsString());
    //holder.dayOfWeek.setText("" + listOfmyDates.get(position).getDayOfMonth());
    holder.dayOfWeek.setText("" + position);

    if (listOfEvents != null) {
      if (listOfEvents.get(position + 1) != null) {
        //Log.d(LOG_TAG, "Colspan für Position = " + position + " erhöht");
        layoutParams.colSpan = 3;
      }
    }

    itemView.setLayoutParams(layoutParams);

//      layoutParams.rowSpan = 2;
  }

  @Override
  public int getItemCount() {
    return listOfmyDates.size();
  }

  @Override
  public void onAttachedToRecyclerView(RecyclerView recyclerView) {
    super.onAttachedToRecyclerView(recyclerView);
  }
}
