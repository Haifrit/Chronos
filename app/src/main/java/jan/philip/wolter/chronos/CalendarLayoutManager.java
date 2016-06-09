package jan.philip.wolter.chronos;

import android.support.v7.widget.RecyclerView;

/**
 * Created by J.Wolter on 09.06.2016.
 */
public class CalendarLayoutManager extends RecyclerView.LayoutManager {
  @Override
  public RecyclerView.LayoutParams generateDefaultLayoutParams() {
    return new RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.WRAP_CONTENT,
            RecyclerView.LayoutParams.WRAP_CONTENT);
  }
}
