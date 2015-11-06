package com.simpletoolbarslider.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.simpletoolbarslider.R;
import com.simpletoolbarslider.ui.model.Happy;
import java.util.ArrayList;

public class HappyAdapter extends RecyclerView.Adapter<HappyViewHolder> {

  private ArrayList<Happy> happies;

  public HappyAdapter(ArrayList<Happy> happies) {
    this.happies = happies;
  }

  /**
   * Create the instance of ViewHolder (instance of HappyViewHolder).
   * @param parent The main view, RecyclerView at this case.
   * @param position The actual position of the binder.
   * @return New instance of ViewHolder.
   */
  @Override public HappyViewHolder onCreateViewHolder(ViewGroup parent, int position) {
    return HappyViewHolder.newInstance(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_happy, parent, false));
  }

  @Override public void onBindViewHolder(HappyViewHolder happyViewHolder, int position) {
    if(happies != null) {
      Happy happy = happies.get(position);
      happyViewHolder.update(happy.getImage(), happy.getTitle());
    }
  }

  @Override public int getItemCount() {
    return happies != null ? happies.size() : 0;
  }

  /**
   * Adds an instance of Happy model at the list
   * @param happy Instance of the item that's will be added.
   */
  public void addHappy(Happy happy) {
    if(happies == null) {
      happies = new ArrayList<>();
    }
    if(!happies.contains(happy)) {
      happies.add(happy);
    }
  }

  /**
   * Removes a determined item of the ArrayList of items.
   * @param happy Instance of the item that's will be removed.
   */
  public void removeHappy(Happy happy) {
    if(happies != null) {
      happies.remove(happy);
    }
  }

  /**
   * Clear the all the items, useful when device is with low memory.
   */
  public void clear() {
    if(happies != null) {
      happies.clear();
      happies = null;
    }
  }

}
