package jan.philip.wolter.chronos;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.lucasr.twowayview.widget.SpannableGridLayoutManager;

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
    final View itemView = holder.itemView;
    final SpannableGridLayoutManager.LayoutParams layoutParams = (SpannableGridLayoutManager.LayoutParams)  itemView.getLayoutParams();
    layoutParams.rightMargin = 4;
    layoutParams.leftMargin = 4;
    layoutParams.topMargin = 4;
    layoutParams.bottomMargin = 4;
    holder.year.setText("" + listOfmyDates.get(position).getYear());
    holder.month.setText("" + listOfmyDates.get(position).getMonthAsString());
    holder.dayOfWeek.setText("" + listOfmyDates.get(position).getDayOfWeek());
    //holder.imageView.setImageResource(R.drawable.may15);
//    if (listOfmyDates.get(position).getEvent() != null) {
//
//      holder.event.setText("Event = " + listOfmyDates.get(position).getEvent());
//
//      layoutParams.colSpan = 2;
//      layoutParams.rowSpan = 2;
//
//      itemView.setLayoutParams(layoutParams);
//    }
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
