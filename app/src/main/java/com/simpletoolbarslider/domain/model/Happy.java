package com.simpletoolbarslider.domain.model;

/**
 * A simple model to represent an item of the list.
 */
public class Happy {

  private int id;
  private String title;
  private String image;

  public Happy(int id, String title, String image) {
    this.id = id;
    this.title = title;
    this.image = image;
  }

  public String getTitle() {
    return title;
  }

  public String getImage() {
    return image;
  }

  /**
   * Every model has your won hashCode, in this case can be used
   * the id of the object to be used on a HashMap/Set.
   * @return idt.of the objec.
   */
  @Override public int hashCode() {
    return id;
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
