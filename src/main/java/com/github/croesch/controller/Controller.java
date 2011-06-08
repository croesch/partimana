package com.github.croesch.controller;

import org.apache.log4j.Logger;

import com.github.croesch.actions.ActionObserver;
import com.github.croesch.actions.UserAction;

/**
 * TODO Comment here ...
 * 
 * @author croesch
 * @since Date: May 29, 2011 12:15:51 PM
 */
public final class Controller implements Runnable, ActionObserver {

  /** logging class */
  private final Logger log = Logger.getLogger(Controller.class);

  /** the action observer for the main program */
  private final ActionObserver observer;

  /**
   * Constructs the core controller with the given action observer and the arguments from command line.
   * 
   * @author croesch
   * @since Date: May 29, 2011 3:48:41 PM
   * @param o the {@link ActionObserver}
   * @param args the arguments from command line
   */
  public Controller(final ActionObserver o, final String[] args) {
    this.observer = o;
  }

  @Override
  public void run() {
    try {
      Thread.sleep(10000);
    } catch (final InterruptedException e) {
      this.log.warn(e.getMessage(), e);
    }
    this.observer.performAction(UserAction.EXIT);
  }

  @Override
  public void performAction(final UserAction action) {
    // TODO Auto-generated method stub

  }

}
