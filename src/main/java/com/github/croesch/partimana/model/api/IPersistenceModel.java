package com.github.croesch.partimana.model.api;

import com.github.croesch.annotate.NotNull;
import com.github.croesch.partimana.types.Camp;
import com.github.croesch.partimana.types.Participant;
import java.io.Closeable;
import java.util.Map;

/**
 * The interface that defines the methods for a persistence model.
 *
 * @author croesch
 * @since Date: May 29, 2011
 */
public interface IPersistenceModel extends Closeable {

  /**
   * Updates the stored data of the given {@link Participant}.
   *
   * @param p the participant that will be searched in the stored data and updated to the given data.
   * @since Date: Jun 19, 2011
   */
  void update(Participant p);

  /**
   * Updates the stored data of the given {@link Camp}.
   *
   * @param c the camp that will be searched in the stored data and updated to the given data.
   * @since Date: Jun 19, 2011
   */
  void update(Camp c);

  /**
   * Returns a map of all saved {@link Camp}s.
   *
   * @return a {@link Map} of all available {@link Camp}s.
   * @since Date: Jun 19, 2011
   */
  @NotNull
  Map<Long, Camp> getMapOfCamps();

  /**
   * Returns a map of all saved {@link Participant}s.
   *
   * @return a {@link Map} of all available {@link Participant}s.
   * @since Date: Jun 19, 2011
   */
  @NotNull
  Map<Long, Participant> getMapOfParticipants();

  /**
   * Deletes the {@link Participant} from data.
   *
   * @param id the number of the {@link Participant} to delete.
   * @since Date: Jun 19, 2011
   */
  void deleteParticipant(long id);

  /**
   * Deletes the {@link Camp} from data.
   *
   * @param id the number of the {@link Camp} to delete.
   * @since Date: Jun 19, 2011
   */
  void deleteCamp(long id);

  /**
   * Creates the given {@linkp Participant} in the stored data.
   *
   * @param p the participant to create.
   * @since Date: Jun 19, 2011
   */
  void create(Participant p);

  /**
   * Creates the given {@linkp Camp} in the stored data.
   *
   * @param c the camp to create.
   * @since Date: Jun 19, 2011
   */
  void create(Camp c);
}
