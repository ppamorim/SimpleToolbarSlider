package com.simpletoolbarslider.ui.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.simpletoolbarslider.R;

/**
 * A simple ViewHolder that is used to handle the recycle
 * of the RecyclerView. Uses the model Happy.
 */
public class HappyViewHolder extends RecyclerView.ViewHolder {

  private SimpleDraweeView image;
  private TextView title;

  public HappyViewHolder(View parent, SimpleDraweeView image, TextView title) {
    super(parent);
    this.image = image;
    this.title = title;
  }

  /**
   * A static method to create a new instance of HappyViewHolder
   * and perform the bind of the views.
   * @param parent Layout of the adapter
   * @return New Instance of the ViewHolder(HappyViewHolder)
   */
  public static HappyViewHolder newInstance(View parent) {
    SimpleDraweeView image = (SimpleDraweeView) parent.findViewById(R.id.image);
    TextView title = (TextView) parent.findViewById(R.id.title);
    return new HappyViewHolder(parent, image, title);
  }

  /**
   * Updates the data of the ViewHolder
   * @param url The url of the image
   * @param title The title of the model
   */
  public void update(String url, String title) {
    loadImage(image, url);
    this.title.setText(title);
  }

  /**
   * Perform the load of the image with a async way,
   * using the Fresco library.
   *
   * @param view Instance of the SimpleDraweeView
   * @param image The url of the image
   */
  private void loadImage(SimpleDraweeView view, String image) {
    if (image != null && !image.isEmpty()) {
      ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse(image))
          .setImageType(ImageRequest.ImageType.SMALL)
          .build();
      DraweeController draweeController = Fresco.newDraweeControllerBuilder()
          .setImageRequest(imageRequest)
          .setOldController(view.getController())
          .build();
      view.setController(draweeController);
    }
  }

}