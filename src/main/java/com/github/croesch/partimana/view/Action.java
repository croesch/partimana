package com.github.croesch.partimana.view;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.github.croesch.annotate.MayBeNull;
import com.github.croesch.annotate.NotNull;
import com.github.croesch.partimana.actions.ActionObserver;
import com.github.croesch.partimana.actions.UserAction;
import com.github.croesch.partimana.i18n.Text;

/**
 * An action that will fire the given {@link UserAction} to the {@link ActionObserver} and contains a {@link Text} as
 * description.
 * 
 * @author croesch
 * @since Date: Jun 8, 2011
 */
final class Action extends AbstractAction {

  /** generated */
  private static final long serialVersionUID = -2077864085665141085L;

  /** the observer that is able to handle the action */
  @MayBeNull
  private static ActionObserver observer = null;

  /** action to close the program */
  @NotNull
  private static Action exitAction = new Action(UserAction.EXIT, Text.EXIT);

  /** action to save something */
  @NotNull
  private static Action saveParticipantAction = new Action(UserAction.SAVE_PARTICIPANT, Text.SAVE);

  /** action to create a new participant */
  @NotNull
  private static Action createParticipantAction = new Action(UserAction.CREATE_PARTICIPANT, Text.PARTICIPANT_NEW);

  /** action to delete a participant */
  @NotNull
  private static Action deleteParticipantAction = new Action(UserAction.DELETE_PARTICIPANT, Text.PARTICIPANT_DELETE);

  /** the action to pass to the observer */
  @NotNull
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
    this.action = act; // FIXME null check
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
  @NotNull
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
  @NotNull
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
  @NotNull
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
  @NotNull
  static Action getDeleteParticipantAction() {
    return deleteParticipantAction;
  }
}
