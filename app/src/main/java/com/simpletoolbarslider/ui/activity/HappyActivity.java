package com.simpletoolbarslider.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.animation.AccelerateInterpolator;
import com.simpletoolbarslider.R;
import com.simpletoolbarslider.domain.model.Happy;
import com.simpletoolbarslider.ui.adapter.HappyAdapter;
import com.simpletoolbarslider.ui.listener.ToolbarSliderListener;
import java.util.ArrayList;

/**
 * This is the most simple and fast way to implement a scrollable Toolbar,
 * that verify the position of the RecyclerView and notifies if the
 * Toolbar needs to be animated or not.
 */
public class HappyActivity extends AppCompatActivity {

  private static final String URL =
      "http://cdn-www.airliners.net/aviation-photos/middle/3/8/1/0243183.jpg";
  private static final String TITLE = "Concorde and Boing 747";

  private Toolbar toolbar;
  private RecyclerView recyclerView;

  /**
   * Set the content of the view, then, config the toolbar
   * and adapter of RecyclerView.
   *
   * @param savedInstanceState Can be used to store instances of data
   * at the memory, not used right now.
   */
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_happy);
    bindViews();
    configToolbar();
    configAdapter();
  }

  @Override public void onLowMemory() {
    ((HappyAdapter) recyclerView.getAdapter()).clear();
    super.onLowMemory();
  }

  /**
   * Bind the views of the XML to instances of classes.
   */
  private void bindViews() {
    this.toolbar = (Toolbar) findViewById(R.id.toolbar);
    this.recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
  }

  /**
   * Set the actionBar as Toolbar in this activity,
   * after set the title of the application.
   */
  private void configToolbar() {
    setSupportActionBar(toolbar);
    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setTitle(R.string.app_name);
    }
  }

  /**
   * Config the layoutManger(vertical), add the scroll listener
   * (ToolbarSliderListener) to hide the toolbar and set the
   * adapter(HappyAdapter) of the RecyclerView.
   */
  private void configAdapter() {
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.addOnScrollListener(new ToolbarSliderListener(20, toolbarSliderCallback));
    recyclerView.setAdapter(new HappyAdapter(stubData()));
  }

  /**
   * Create a stub data to use with the RecyclerView.
   * @return Array with 100 instances of the Happy model.
   */
  private ArrayList<Happy> stubData() {
    ArrayList<Happy> happies = new ArrayList<>(100);
    for (int i = 0; i < 100; i++) {
      happies.add(new Happy(i, TITLE, URL));
    }
    return happies;
  }

  /**
   * Status perform the animation of the toolbar, this is the most
   * simple way to implement this feature. @param hidden means that
   * list can be hidden or not
   *
   * positionFirstItemVisible returns if recyclerView is at the
   * first item. It can be useful if the slide is very slow at the
   * first item.
   */
  private ToolbarSliderListener.ToolbarSliderCallback toolbarSliderCallback =
      new ToolbarSliderListener.ToolbarSliderCallback() {
    @Override public void status(boolean hidden) {
      ViewCompat.animate(toolbar)
          .translationY(hidden ? 0 : -toolbar.getHeight())
          .setDuration(200)
          .setInterpolator(new AccelerateInterpolator());
    }

    @Override public int positionFirstItemVisible() {
      return recyclerView == null ? 0 : ((LinearLayoutManager) recyclerView.getLayoutManager())
          .findFirstVisibleItemPosition();
    }
  };

}
