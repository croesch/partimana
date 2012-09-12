package com.github.croesch.partimana.model.api;

import java.util.List;

import com.github.croesch.partimana.annotation.MayBeNull;
import com.github.croesch.partimana.annotation.NotNull;
import com.github.croesch.partimana.types.Participant;

/**
 * Interface for the participant-model that contains all methods that the view should know/see.
 * 
 * @author croesch
 * @since Date: Jun 8, 2011
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
  @MayBeNull
  Participant getParticipant(long id);

  /**
   * Returns a {@link List} containing all stored {@link Participant}s.
   * 
   * @author croesch
   * @since Date: Jun 21, 2011
   * @return a list that contains all participants stored in the model.
   */
  @NotNull
  List<Participant> getListOfParticipants();
}
