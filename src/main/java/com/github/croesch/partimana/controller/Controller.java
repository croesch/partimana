package com.github.croesch.partimana.controller;

import org.apache.log4j.Logger;

import com.github.croesch.annotate.NotNull;
import com.github.croesch.partimana.actions.ActionObserver;
import com.github.croesch.partimana.actions.UserAction;
import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.Model;
import com.github.croesch.partimana.view.View;

/**
 * The controller of the program.
 * 
 * @author croesch
 * @since Date: May 29, 2011
 */
public final class Controller implements ActionObserver {

  /** logging class */
  private static final Logger LOGGER = Logger.getLogger(Controller.class);

  /** the action observer for the main program */
  @NotNull
  private final ActionObserver observer;

  /** the connector to the model of the program */
  @NotNull
  private final Model model;

  /** the connector to the view of the program */
  @NotNull
  private final View view;

  /**
   * Constructs the core controller with the given action observer and the arguments from command line.
   * 
   * @author croesch
   * @since Date: May 29, 2011
   * @param o the {@link ActionObserver}
   * @param args the arguments from command line
   */
  public Controller(final ActionObserver o, final String[] args) {
    this.observer = o; //FIXME null check!
    this.model = new Model();
    this.view = new View(this.model, this);
    this.model.setView(this.view);

    this.view.setVisible(true);

    this.view.showInformation(Text.VERSION);
  }

  @Override
  public void performAction(final UserAction action) {
    switch (action) {

      case EXIT:
        LOGGER.debug(Text.DEBUG_PROGRAM_EXIT_NOTIFICATION.text());
        this.observer.performAction(action);
        break;

      case SAVE_PARTICIPANT:
        ParticipantSaver.performSave(this.model, this.view.getParticipantEditView(), this.view);
        break;

      case DELETE_PARTICIPANT:
        this.model.deleteParticipant(this.view.getParticipantListView().getSelectedParticipantId());
        break;

      default:
        LOGGER.warn(Text.WARN_UNKNOWN_ACTION.text(action));
        break;
    }
  }

}
