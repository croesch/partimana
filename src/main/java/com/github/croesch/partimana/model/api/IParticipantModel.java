package com.github.croesch.partimana.model.api;

import com.github.croesch.partimana.types.Participant;
import com.github.croesch.partimana.types.exceptions.RequiredFieldSetToNullException;

/**
 * Interface for the participant-model.
 *
 * @author croesch
 * @since Date: Jun 8, 2011
 */
public interface IParticipantModel extends IParticipantModel4View {

  /**
   * Creates the given {@link Participant} and stores it, if it doesn't exist already. If it exists already the existing
   * participant will be updated with the information fetched from the given participant.
   *
   * @param p the {@link Participant} to store
   * @return the instance of the stored participant
   * @throws RequiredFieldSetToNullException if the given {@link Participant} is <code>null</code>
   * @since Date: Jun 21, 2011
   */
  Participant store(Participant p) throws RequiredFieldSetToNullException;

  /**
   * Tries to delete the {@link Participant} from the stored data. If it doesn't exist nothing will be done. So after
   * calling this method the participant with the given number won't be stored, no matter if it existed before.
   *
   * @param id the number of the {@link Participant} to delete
   * @since Date: Jun 21, 2011
   */
  void deleteParticipant(long id);
}
