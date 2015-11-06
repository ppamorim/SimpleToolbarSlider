package com.simpletoolbarslider;

import android.app.Application;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * The main configuration of the application,
 * necessary to config the Fresco ImageLoader.
 */
public class TSApplication extends Application {

  /**
   * Initialized the Fresco ImageLoader Pipeline when the app start.
   */
  @Override public void onCreate() {
    super.onCreate();
    Fresco.initialize(this);
  }

  /**
   * Finish the pipeline of the Fresco ImageLoader.
   */
  @Override public void onTerminate() {
    Fresco.shutDown();
    super.onTerminate();
  }
}
