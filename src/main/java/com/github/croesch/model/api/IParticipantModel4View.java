package com.github.croesch.model.api;

import com.github.croesch.types.Participant;

/**
 * Interface for the participant-model that contains all methods that the view should know/see.
 * 
 * @author croesch
 * @since Date: Jun 8, 2011 6:13:50 AM
 */
public interface IParticipantModel4View {

  /**
   * Returns the {@link Participant} with the given id.
   * 
   * @author croesch
   * @since Date: Jun 19, 2011
   * @param id the id to lookup
   * @return the participant with the given id, or <code>null</code> if no participant has this id.
   */
  Participant getParticipant(long id);
}
