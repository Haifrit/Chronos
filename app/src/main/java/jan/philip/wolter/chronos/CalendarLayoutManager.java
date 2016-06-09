package jan.philip.wolter.chronos;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;

/**
 * Created by J.Wolter on 09.06.2016.
 */
public class CalendarLayoutManager extends RecyclerView.LayoutManager {
  /* View Removal Constants */
  private static final int REMOVE_VISIBLE = 0;
  private static final int REMOVE_INVISIBLE = 1;
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
    fillGrid(DIRECTION_NONE, childLeft, childTop, recycler, state, null);
  }

  private void fillGrid (int direction, int childLeft, int childTop,  RecyclerView.Recycler recycler,RecyclerView.State state, SparseIntArray removedPositions) {


    if (mFirstVisiblePosition < 0) {
      mFirstVisiblePosition = 0;
    }
    if (mFirstVisiblePosition >= getItemCount()) {
      mFirstVisiblePosition = (getItemCount() - 1);
    }

    SparseArray<View> viewCache = new SparseArray<View>(getChildCount());
    int startLeftOffset = childLeft;
    int startTopOffset = childTop;
    //TODO: detach all exsisting views

         /*
         * Next, we advance the visible position based on the fill direction.
         * DIRECTION_NONE doesn't advance the position in any direction.
         */
    switch (direction) {
      case DIRECTION_START:
        mFirstVisiblePosition--;
        break;
      case DIRECTION_END:
        mFirstVisiblePosition++;
        break;
      case DIRECTION_UP:
        mFirstVisiblePosition -= getTotalColumnCount();
        break;
      case DIRECTION_DOWN:
        mFirstVisiblePosition += getTotalColumnCount();
        break;
    }

       /*
         * Next, we supply the grid of items that are deemed visible.
         * If these items were previously there, they will simply be
         * re-attached. New views that must be created are obtained
         * from the Recycler and added.
         */
    int leftOffset = startLeftOffset;
    int topOffset = startTopOffset;

    for (int i = 0; i < getVisibleChildCount(); i++) {
      int nextPosition = positionOfIndex(i);
      int offsetPositionDelta = 0;

      if (state.isPreLayout()) {
        int offsetPosition = nextPosition;

        for (int offset = 0; offset < removedPositions.size(); offset++) {
          //Look for off-screen removals that are less-than this
          if (removedPositions.valueAt(offset) == REMOVE_INVISIBLE
                  && removedPositions.keyAt(offset) < nextPosition) {
            //Offset position to match
            offsetPosition--;
          }
        }
        offsetPositionDelta = nextPosition - offsetPosition;
        nextPosition = offsetPosition;
      }

    }
  }

  private int getVisibleChildCount() {
    return mVisibleColumnCount * mVisibleRowCount;
  }

  /*
 * Mapping between child view indices and adapter data
 * positions helps fill the proper views during scrolling.
 */
  private int positionOfIndex(int childIndex) {
    int row = childIndex / mVisibleColumnCount;
    int column = childIndex % mVisibleColumnCount;

    return mFirstVisiblePosition + (row * getTotalColumnCount()) + column;
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
