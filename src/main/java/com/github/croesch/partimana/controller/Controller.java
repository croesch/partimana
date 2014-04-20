package com.github.croesch.partimana.controller;

import com.github.croesch.annotate.NotNull;
import com.github.croesch.partimana.actions.ActionObserver;
import com.github.croesch.partimana.actions.UserAction;
import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.Model;
import com.github.croesch.partimana.model.api.IPersistenceModel;
import com.github.croesch.partimana.types.Camp;
import com.github.croesch.partimana.view.View;
import java.util.Date;
import org.apache.log4j.Logger;

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
   * @param o      the {@link ActionObserver}
   * @param pModel the persistence model to store participants and camps with
   * @since Date: May 29, 2011
   */
  public Controller(final ActionObserver o, final IPersistenceModel pModel) {
    observer = o; //FIXME null check!
    model = new Model(pModel);
    view = new View("view", model, this);
    model.setView(view);

    view.setVisible(true);

    view.showInformation(Text.VERSION);
  }

  @Override
  public void performAction(final UserAction action) {
    switch (action) {

      case EXIT:
        LOGGER.debug(Text.DEBUG_PROGRAM_EXIT_NOTIFICATION.text());
        observer.performAction(action);
        break;

      case SAVE_PARTICIPANT:
        ParticipantSaver.performSave(model, view.getParticipantEditView(), view);
        break;

      case DELETE_PARTICIPANT:
        model.deleteParticipant(view.getParticipantListView().getSelectedElementId());
        break;

      case CANCEL_CAMP:
        final Camp c = model.getCamp(view.getCampListView().getSelectedElementId());
        c.setCancelDate(new Date());
        model.store(c);
        break;

      case SAVE_CAMP:
        CampSaver.performSave(model, view.getCampEditView(), view);
        break;

      case DELETE_CAMP:
        model.deleteCamp(view.getCampListView().getSelectedElementId());
        break;

      default:
        LOGGER.warn(Text.WARN_UNKNOWN_ACTION.text(action));
        break;
    }
  }
}
