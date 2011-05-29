package com.github.croesch.actions;

/**
 * Observer interface for {@link UserAction}s.
 * 
 * @author croesch
 * @since Date: May 29, 2011 12:29:24 PM
 */
public interface ActionObserver {

  /**
   * Tells the observer that the given action happened and that it should react.
   * 
   * @author croesch
   * @since Date: May 29, 2011 3:19:42 PM
   * @param action the {@link UserAction} that happened.
   */
  void performAction(UserAction action);

}
