package com.simpletoolbarslider.ui.model;

/**
 * A simple model to represent an item of the list.
 */
public class Happy {

  private String title;
  private String image;

  public Happy(String title, String image) {
    this.title = title;
    this.image = image;
  }

  public String getTitle() {
    return title;
  }

  public String getImage() {
    return image;
  }

  @Override public int hashCode() {
    return title.hashCode() & image.hashCode();
  }

  /**
   * Verify whether the instance of the model has
   * the params of the compared model.
   * @param object Instance of the compared model.
   * @return If item is equals or not.
   */
  @Override public boolean equals(Object object) {
    if (object instanceof Happy) {
      Happy happy = ((Happy) object);
      return (happy.title.equals(title) && happy.image.equals(image))
          && happy.hashCode() == hashCode();
    }
    return false;
  }

}
