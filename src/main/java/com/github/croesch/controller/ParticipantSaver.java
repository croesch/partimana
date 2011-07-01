package com.github.croesch.controller;

import org.apache.log4j.Logger;

import com.github.croesch.i18n.Text;
import com.github.croesch.model.api.IParticipantModel;
import com.github.croesch.types.Participant;
import com.github.croesch.view.api.IParticipantEditView;
import com.github.croesch.view.api.IStatusView;

/**
 * Component that is able to fetch the information from the {@link IParticipantEditView} and to store it in the model.
 * 
 * @author croesch
 * @since Date: Jul 1, 2011
 */
final class ParticipantSaver {

  /** logging class */
  private static final Logger LOGGER = Logger.getLogger(ParticipantSaver.class);

  /**
   * Hidden constructor.
   * 
   * @author croesch
   * @since Date: Jul 1, 2011
   */
  private ParticipantSaver() {
    // not needed
  }

  /**
   * Tries saving the participant currently being edited in the {@link IParticipantEditView}.
   * 
   * @author croesch
   * @since Date: Jul 1, 2011
   * @param model the {@link IParticipantModel} to store the data with
   * @param editView the {@link IParticipantEditView} that has been editing the participant
   * @param statusView the {@link IStatusView} that is responsible for displaying the status
   */
  static void performSave(final IParticipantModel model,
                          final IParticipantEditView editView,
                          final IStatusView statusView) {

    // TODO implement test case
    try {
      Participant p = null;
      if (editView.getId() <= 0) {
        p = new Participant(editView.getLastName(),
                            editView.getFirstName(),
                            editView.getGender(),
                            editView.getDenomination(),
                            editView.getBirthDate(),
                            editView.getLivingStreet(),
                            editView.getLivingPostCode(),
                            editView.getLivingCity(),
                            editView.getCountyCouncil());
      } else {
        p = model.getParticipant(editView.getId());
        p.setLastName(editView.getLastName());
        p.setForeName(editView.getFirstName());
        p.setGender(editView.getGender());
        p.setDenomination(editView.getDenomination());
        p.setBirthDate(editView.getBirthDate());
        p.setStreet(editView.getLivingStreet());
        p.setPostCode(editView.getLivingPostCode());
        p.setCity(editView.getLivingCity());
        p.setCountyCouncil(editView.getCountyCouncil());
      }
      editView.setParticipant(p);
      model.store(p);
      statusView.showInformation(Text.INFO_PARTICIPANT_SAVED, p.getId());
    } catch (final Exception e) {
      LOGGER.debug(Text.ERROR_EXCEPTION.text(e.getClass().getName()), e);
      statusView.showError(Text.ERROR_PARTICIPANT_NOT_SAVED);
    }
  }

}
