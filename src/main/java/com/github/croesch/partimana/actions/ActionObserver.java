package com.github.croesch.partimana.actions;

import com.github.croesch.annotate.NotNull;

/**
 * Observer interface for {@link UserAction}s.
 * 
 * @author croesch
 * @since Date: May 29, 2011
 */
public interface ActionObserver {

  /**
   * Tells the observer that the given action happened and that it should react.
   * 
   * @author croesch
   * @since Date: May 29, 2011
   * @param action the {@link UserAction} that happened.
   */
  void performAction(@NotNull UserAction action);

}
