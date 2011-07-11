package com.github.croesch.model.api;

import com.github.croesch.types.Participant;
import com.github.croesch.types.exceptions.RequiredFieldSetToNullException;

/**
 * Interface for the participant-model.
 * 
 * @author croesch
 * @since Date: Jun 8, 2011 6:12:52 AM
 */
public interface IParticipantModel extends IParticipantModel4View {

  /**
   * Creates the given {@link Participant} and stores it, if it doesn't exist already. If it exists already the existing
   * participant will be updated with the information fetched from the given participant.
   * 
   * @author croesch
   * @since Date: Jun 21, 2011
   * @param p the {@link Participant} to store
   * @throws RequiredFieldSetToNullException if the given {@link Participant} is <code>null</code>
   */
  void store(Participant p) throws RequiredFieldSetToNullException;

  /**
   * Tries to delete the {@link Participant} from the stored data. If it doesn't exist nothing will be done. So after
   * calling this method the participant with the given number won't be stored, no matter if it existed before.
   * 
   * @author croesch
   * @since Date: Jun 21, 2011
   * @param id the number of the {@link Participant} to delete
   */
  void deleteParticipant(long id);
}
