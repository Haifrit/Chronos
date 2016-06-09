package jan.philip.wolter.chronos;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;

/**
 * Created by J.Wolter on 09.06.2016.
 */
public class CalendarLayoutManager extends RecyclerView.LayoutManager {
  private static final int DEFAULT_COUNT = 1;
  /* Fill Direction Constants */
  private static final int DIRECTION_NONE = -1;
  private static final int DIRECTION_START = 0;
  private static final int DIRECTION_END = 1;
  private static final int DIRECTION_UP = 2;
  private static final int DIRECTION_DOWN = 3;
  /* First (top-left) position visible at any point */
  private int mFirstVisiblePosition;
  private int mDecoratedChildWidth;
  private int mDecoratedChildHeight;
  /* Metrics for the visible window of our data */
  private int mVisibleColumnCount;
  private int mVisibleRowCount;
  /* Number of columns that exist in the grid */
  private int mTotalColumnCount = DEFAULT_COUNT;

  @Override
  public RecyclerView.LayoutParams generateDefaultLayoutParams() {
    return new RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.WRAP_CONTENT,
            RecyclerView.LayoutParams.WRAP_CONTENT);
  }

  @Override
  public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
    //Scrap measure one child
    View scrap = recycler.getViewForPosition(0);
    addView(scrap);
    measureChildWithMargins(scrap, 0, 0);
    //All Children are the same size
    mDecoratedChildWidth = getDecoratedMeasuredWidth(scrap);
    mDecoratedChildHeight = getDecoratedMeasuredHeight(scrap);
    detachAndScrapView(scrap, recycler);

    updateWindowSizing();
    int childLeft;
    int childTop;

     /*
     * Reset the visible and scroll positions
     */
    mFirstVisiblePosition = 0;
    childLeft = childTop = 0;

    //Clear all attached views into the recycle bin
    detachAndScrapAttachedViews(recycler);
    //Fill the grid for the initial layout of views
    fillGrid(DIRECTION_NONE, childLeft, childTop, recycler);
  }

  private void fillGrid (int direction, int childLeft, int childTop,  RecyclerView.Recycler recycler ) {
    
  }


  private void updateWindowSizing() {
    mVisibleColumnCount = (getHorizontalSpace() / mDecoratedChildWidth) + 1;
    if (getHorizontalSpace() % mDecoratedChildWidth > 0) {
      mVisibleColumnCount++;
    }

    //Allow minimum value for small data sets
    if (mVisibleColumnCount > getTotalColumnCount()) {
      mVisibleColumnCount = getTotalColumnCount();
    }


    mVisibleRowCount = (getVerticalSpace()/ mDecoratedChildHeight) + 1;
    if (getVerticalSpace() % mDecoratedChildHeight > 0) {
      mVisibleRowCount++;
    }

    if (mVisibleRowCount > getTotalRowCount()) {
      mVisibleRowCount = getTotalRowCount();
    }
  }

  private int getTotalColumnCount() {
    if (getItemCount() < mTotalColumnCount) {
      return getItemCount();
    }

    return mTotalColumnCount;
  }

  private int getTotalRowCount() {
    if (getItemCount() == 0 || mTotalColumnCount == 0) {
      return 0;
    }
    int maxRow = getItemCount() / mTotalColumnCount;
    //Bump the row count if it's not exactly even
    if (getItemCount() % mTotalColumnCount != 0) {
      maxRow++;
    }

    return maxRow;
  }

  private int getHorizontalSpace() {
    return getWidth() - getPaddingRight() - getPaddingLeft();
  }

  private int getVerticalSpace() {
    return getHeight() - getPaddingBottom() - getPaddingTop();
  }
}
