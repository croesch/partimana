package com.github.croesch.partimana.view.api;

/**
 * Interface for the view that implements all methods seen by the model.
 *
 * @author croesch
 * @since Date: May 29, 2011
 */
public interface IView {

  /**
   * Performs an update of the view. Means that the view will fetch all displayed information from the model to get the
   * version up to date.
   *
   * @since Date: Jul 10, 2011
   */
  void update();
}
