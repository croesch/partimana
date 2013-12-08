package com.github.croesch.partimana.actions;

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
   * @param action the {@link UserAction} that happened.
   * @since Date: May 29, 2011
   */
  void performAction(UserAction action);
}
