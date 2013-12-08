package com.github.croesch.partimana.model.api;

import com.github.croesch.annotate.MayBeNull;
import com.github.croesch.annotate.NotNull;
import com.github.croesch.partimana.types.Participant;
import java.util.List;

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
   * @param id the id to lookup
   * @return the participant with the given id, or <code>null</code> if no participant has this id.
   * @since Date: Jun 19, 2011
   */
  @MayBeNull
  Participant getParticipant(long id);

  /**
   * Returns a {@link List} containing all stored {@link Participant}s.
   *
   * @return a list that contains all participants stored in the model.
   * @since Date: Jun 21, 2011
   */
  @NotNull
  List<Participant> getListOfParticipants();
}
