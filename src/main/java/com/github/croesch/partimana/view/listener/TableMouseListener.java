package com.github.croesch.partimana.view.listener;

import com.github.croesch.annotate.NotNull;
import com.github.croesch.partimana.actions.ActionObserver;
import com.github.croesch.partimana.actions.UserAction;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Mouse listener for the table that will fire the given action to the given observer that a row has been selected.
 *
 * @author croesch
 * @since Date: Sep 12, 2012
 */
public final class TableMouseListener extends MouseAdapter {

  /** the observer that should be notified about selection events */
  @NotNull
  private final ActionObserver observer;

  /** the action that will be sent to the observer when the event happened */
  @NotNull
  private final UserAction action;

  /**
   * Creates new mouse listener that will send the given action to the given observer if double click happened on the
   * table.
   *
   * @param o the {@link ActionObserver} to fire the action to.
   * @param a the action to send to the observer
   * @since Date: Sep 12, 2012
   */
  public TableMouseListener(final ActionObserver o, final UserAction a) {
    observer = o; //FIXME null check
    action = a; // FIXME null check
  }

  @Override
  public void mouseClicked(final MouseEvent e) {
    if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
      observer.performAction(action);
    }
    super.mouseClicked(e);
  }
}
