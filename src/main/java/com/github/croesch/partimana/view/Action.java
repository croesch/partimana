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

  /** action to save something */
  @NotNull
  private static Action saveCampAction = new Action(UserAction.SAVE_CAMP, Text.SAVE);

  /** action to save camp to csv */
  @NotNull
  private static Action saveCamp2CSVAction = new Action(UserAction.SAVE_CAMP_TO_CSV, Text.CAMP_TO_CSV);

  /** action to create a new camp */
  @NotNull
  private static Action createCampAction = new Action(UserAction.CREATE_CAMP, Text.CAMP_NEW);

  /** action to delete a camp */
  @NotNull
  private static Action deleteCampAction = new Action(UserAction.DELETE_CAMP, Text.CAMP_DELETE);

  /** action to cancel a camp */
  @NotNull
  private static Action cancelCampAction = new Action(UserAction.CANCEL_CAMP, Text.CAMP_CANCEL);

  /** action to search a camp */
  @NotNull
  private static Action searchCampAction = new Action(UserAction.SEARCH_CAMP, Text.SEARCH_CAMP);

  /** action to search a participant */
  @NotNull
  private static Action searchParticipantAction = new Action(UserAction.SEARCH_PARTICIPANT, Text.SEARCH_PARTICIPANT);

  /** the action to pass to the observer */
  @NotNull
  private final UserAction action;

  /**
   * Constructs an action that will fire the given {@link UserAction} to the {@link ActionObserver} and contains a
   * {@link Text} as description.
   * 
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
   * @since Date: Jul 11, 2011
   * @return the {@link Action} to delete a participant
   */
  @NotNull
  static Action getDeleteParticipantAction() {
    return deleteParticipantAction;
  }

  /**
   * Returns the action to save a camp.
   * 
   * @since Date: Sep 23, 2012
   * @return the {@link Action} to save a camp
   */
  @NotNull
  static Action getSaveCampAction() {
    return saveCampAction;
  }

  /**
   * Returns the action to create a camp.
   * 
   * @since Date: Sep 23, 2012
   * @return the {@link Action} to create a camp
   */
  @NotNull
  static Action getCreateCampAction() {
    return createCampAction;
  }

  /**
   * Returns the action to delete a camp.
   * 
   * @since Date: Sep 23, 2012
   * @return the {@link Action} to delete a camp
   */
  @NotNull
  static Action getDeleteCampAction() {
    return deleteCampAction;
  }

  /**
   * @since Date: Mar 11, 2013
   * @return the {@link Action} to cancel a camp
   */
  @NotNull
  static Action getCancelCampAction() {
    return cancelCampAction;
  }

  /**
   * Returns the action to search a camp.
   * 
   * @since Date: Dec 16, 2012
   * @return the {@link Action} to search a camp.
   */
  @NotNull
  static Action getSearchCampAction() {
    return searchCampAction;
  }

  /**
   * Returns the action to search a participant.
   * 
   * @since Date: Jan 26, 2013
   * @return the {@link Action} to search a participant.
   */
  @NotNull
  static Action getSearchParticipantAction() {
    return searchParticipantAction;
  }

  /**
   * @since Date: Jun 1, 2013
   * @return the {@link Action} to save a camp to csv.
   */
  @NotNull
  static Action getSaveCampToCSVAction() {
    return saveCamp2CSVAction;
  }
}
