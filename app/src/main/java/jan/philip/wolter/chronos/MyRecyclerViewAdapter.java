package jan.philip.wolter.chronos;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by J.Wolter on 09.06.2016.
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyDateViewHolder> {

  public static class MyDateViewHolder extends RecyclerView.ViewHolder {

    TextView year;
    TextView month;
    TextView dayOfWeek;

    public MyDateViewHolder(View itemView) {
      super(itemView);
      year = (TextView)itemView.findViewById(R.id.year);
      month = (TextView)itemView.findViewById(R.id.month);
      dayOfWeek = (TextView)itemView.findViewById(R.id.day);
    }
  }

  @Override
  public MyDateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return null;
  }

  @Override
  public void onBindViewHolder(MyDateViewHolder holder, int position) {

  }

  @Override
  public int getItemCount() {
    return 0;
  }




}
