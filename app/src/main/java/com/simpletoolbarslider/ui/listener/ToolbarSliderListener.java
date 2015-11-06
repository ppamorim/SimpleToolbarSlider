package com.simpletoolbarslider.ui.listener;

import android.support.v7.widget.RecyclerView;

/**
 * A simple extension of the base scroll listener, to know the position
 * of the RecyclerView and notify to hide or show the Toolbar if needed.
 */
public class ToolbarSliderListener extends RecyclerView.OnScrollListener {

  private int scrolledDistance = 0;
  private boolean contentVisible = true;
  private int maxDistance = 0;
  private ToolbarSliderCallback toolbarSliderCallback;

  public ToolbarSliderListener(int maxDistance, ToolbarSliderCallback toolbarSliderCallback) {
    this.maxDistance = maxDistance;
    this.toolbarSliderCallback = toolbarSliderCallback;
  }

  /**
   * This method is called every time when the user scrolls the list.
   *
   * Verify whether the scrolledDistance is bigger than the maxDistance
   * and the toolbar is visible. Notify the params that the Toolbar
   * is hidden and notify the activity to animate the Toolbar.
   *
   * Else, verify otherwise the scrolled distance is smaller than of the
   * inverted maxDistance and if the Toolbar isn't visible, or,
   * if the RecyclerView is at the top and first item, then,
   * show the Toolbar.
   *
   * After this interaction, it's necessary to save the total value
   * of the scrolled list to know where is the RecyclerView on
   * every scroll.
   *
   * @param recyclerView instance of the RecyclerView
   * @param distanceX Scrolled distance at the x axis
   * @param distanceY Scrolled distance at the Y axis
   */
  @Override public void onScrolled(RecyclerView recyclerView, int distanceX, int distanceY) {
    super.onScrolled(recyclerView, distanceX, distanceY);

    if (scrolledDistance > maxDistance && contentVisible) {
      contentVisible = false;
      scrolledDistance = 0;
      toolbarSliderCallback.status(false);
    } else if ((scrolledDistance < -maxDistance && !contentVisible)
        || toolbarSliderCallback.positionFirstItemVisible() == 0) {
      contentVisible = true;
      scrolledDistance = 0;
      toolbarSliderCallback.status(true);
    }

    boolean distanceBiggerThanZero = distanceY > 0;
    if ((contentVisible && distanceBiggerThanZero)
        || (!contentVisible && !distanceBiggerThanZero)) {
      scrolledDistance += distanceY;
    }

  }

  /**
   * Status notifies the activity to hide or not the Toolbar;
   * positionFirstItemVisible returns the position of the first
   * visible item at the RecyclerView
   */
  public interface ToolbarSliderCallback {
    void status(boolean hidden);
    int positionFirstItemVisible();
  }

}
