package jan.philip.wolter.chronos;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by J.Wolter on 09.06.2016.
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyDateViewHolder> {

  private List<MyDate> listOfmyDates;

  public MyRecyclerViewAdapter (List<MyDate> listOfmyDates) {
    this.listOfmyDates = listOfmyDates;
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
    holder.year.setText("Year = " + listOfmyDates.get(position).getYear());
    holder.month.setText("Month =" + listOfmyDates.get(position).getMonth());
    holder.dayOfWeek.setText("Day = " + listOfmyDates.get(position).getDayOfWeek());
    if (listOfmyDates.get(position).getEvent() != null) {
      holder.event.setText("Event = " + listOfmyDates.get(position).getEvent());
    }
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
