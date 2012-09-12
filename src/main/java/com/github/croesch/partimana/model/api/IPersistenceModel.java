package com.github.croesch.partimana.model.api;

import java.util.Map;

import com.github.croesch.annotate.NotNull;
import com.github.croesch.partimana.types.Camp;
import com.github.croesch.partimana.types.Participant;

/**
 * The interface that defines the methods for a persistence model.
 * 
 * @author croesch
 * @since Date: May 29, 2011
 */
public interface IPersistenceModel {

  /**
   * Updates the stored data of the given {@link Participant}.
   * 
   * @author croesch
   * @since Date: Jun 19, 2011
   * @param p the participant that will be searched in the stored data and updated to the given data.
   */
  void update(Participant p);

  /**
   * Updates the stored data of the given {@link Camp}.
   * 
   * @author croesch
   * @since Date: Jun 19, 2011
   * @param c the camp that will be searched in the stored data and updated to the given data.
   */
  void update(Camp c);

  /**
   * Returns a map of all saved {@link Camp}s.
   * 
   * @author croesch
   * @since Date: Jun 19, 2011
   * @return a {@link Map} of all available {@link Camp}s.
   */
  @NotNull
  Map<Long, Camp> getMapOfCamps();

  /**
   * Returns a map of all saved {@link Participant}s.
   * 
   * @author croesch
   * @since Date: Jun 19, 2011
   * @return a {@link Map} of all available {@link Participant}s.
   */
  @NotNull
  Map<Long, Participant> getMapOfParticipants();

  /**
   * Deletes the {@link Participant} from data.
   * 
   * @author croesch
   * @since Date: Jun 19, 2011
   * @param id the number of the {@link Participant} to delete.
   */
  void deleteParticipant(long id);

  /**
   * Deletes the {@link Camp} from data.
   * 
   * @author croesch
   * @since Date: Jun 19, 2011
   * @param id the number of the {@link Camp} to delete.
   */
  void deleteCamp(long id);

  /**
   * Creates the given {@linkp Participant} in the stored data.
   * 
   * @author croesch
   * @since Date: Jun 19, 2011
   * @param p the participant to create.
   */
  void create(Participant p);

  /**
   * Creates the given {@linkp Camp} in the stored data.
   * 
   * @author croesch
   * @since Date: Jun 19, 2011
   * @param c the camp to create.
   */
  void create(Camp c);

}
