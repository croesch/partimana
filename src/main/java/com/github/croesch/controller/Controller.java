package com.github.croesch.controller;

import org.apache.log4j.Logger;

import com.github.croesch.actions.ActionObserver;
import com.github.croesch.actions.UserAction;
import com.github.croesch.i18n.Text;
import com.github.croesch.model.Model;
import com.github.croesch.view.View;

/**
 * The controller of the program.
 * 
 * @author croesch
 * @since Date: May 29, 2011 12:15:51 PM
 */
public final class Controller implements ActionObserver {

  /** logging class */
  private static final Logger LOGGER = Logger.getLogger(Controller.class);

  /** the action observer for the main program */
  private final ActionObserver observer;

  /** the connector to the model of the program */
  private final Model model;

  /** the connector to the view of the program */
  private final View view;

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

      default:
        LOGGER.warn(Text.WARN_UNKNOWN_ACTION.text(action));
        break;
    }
  }

}
