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
final class Action extends AbstractAction {

  /** generated */
  private static final long serialVersionUID = -2077864085665141085L;

  /** the observer that is able to handle the action */
  private static ActionObserver observer = null;

  /** action to close the program */
  private static Action exitAction = new Action(UserAction.EXIT, Text.EXIT);

  /** action to save something */
  private static Action saveParticipantAction = new Action(UserAction.SAVE_PARTICIPANT, Text.SAVE);

  /** action to create a new participant */
  private static Action createParticipantAction = new Action(UserAction.CREATE_PARTICIPANT, Text.PARTICIPANT_NEW);

  /** action to delete a participant */
  private static Action deleteParticipantAction = new Action(UserAction.DELETE_PARTICIPANT, Text.PARTICIPANT_DELETE);

  /** the action to pass to the observer */
  private final UserAction action;

  /**
   * Constructs an action that will fire the given {@link UserAction} to the {@link ActionObserver} and contains a
   * {@link Text} as description.
   * 
   * @author croesch
   * @since Date: Jun 30, 2011
   * @param act the {@link UserAction} to pass to the observer
   * @param t the {@link Text} as description of this action
   */
  private Action(final UserAction act, final Text t) {
    super(t.text());
    this.action = act;
  }

  /**
   * Sets the observer that is able to handle the actions.
   * 
   * @author croesch
   * @since Date: Jul 1, 2011
   * @param ao the observer to handle actions
   */
  static void setObserver(final ActionObserver ao) {
    observer = ao;
  }

  @Override
  public void actionPerformed(final ActionEvent ev) {
    if (observer != null) {
      observer.performAction(this.action);
    }
  }

  /**
   * Returns the action to close the program.
   * 
   * @author croesch
   * @since Date: Jul 1, 2011
   * @return the {@link Action} to close the program
   */
  static Action getExitAction() {
    return exitAction;
  }

  /**
   * Returns the action to save a participant.
   * 
   * @author croesch
   * @since Date: Jul 1, 2011
   * @return the {@link Action} to save a participant
   */
  static Action getSaveParticipantAction() {
    return saveParticipantAction;
  }

  /**
   * Returns the action to create a participant.
   * 
   * @author croesch
   * @since Date: Jul 11, 2011
   * @return the {@link Action} to create a participant
   */
  static Action getCreateParticipantAction() {
    return createParticipantAction;
  }

  /**
   * Returns the action to delete a participant.
   * 
   * @author croesch
   * @since Date: Jul 11, 2011
   * @return the {@link Action} to delete a participant
   */
  static Action getDeleteParticipantAction() {
    return deleteParticipantAction;
  }
}
