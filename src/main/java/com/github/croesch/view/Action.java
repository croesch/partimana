package com.github.croesch.view;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.github.croesch.actions.ActionObserver;
import com.github.croesch.actions.UserAction;
import com.github.croesch.i18n.Text;

/**
 * An action that will fire the given {@link UserAction} to the {@link ActionObserver} and contains a {@link Text} as
 * description.
 * 
 * @author croesch
 * @since Date: Jun 8, 2011 6:25:47 AM
 */
class Action extends AbstractAction {

  /** generated */
  private static final long serialVersionUID = -2077864085665141085L;

  /** the observer that is able to handle the action */
  private final ActionObserver actionObserver;

  /** the action to pass to the observer */
  private final UserAction action;

  /**
   * Constructs an action that will fire the given {@link UserAction} to the {@link ActionObserver} and contains a
   * {@link Text} as description.
   * 
   * @author croesch
   * @since Date: Jun 30, 2011
   * @param ao the {@link ActionObserver} that will handle the action
   * @param act the {@link UserAction} to pass to the observer
   * @param t the {@link Text} as description of this action
   */
  public Action(final ActionObserver ao, final UserAction act, final Text t) {
    super(t.text());
    this.actionObserver = ao;
    this.action = act;
  }

  @Override
  public void actionPerformed(final ActionEvent ev) {
    this.actionObserver.performAction(this.action);
  }
}
