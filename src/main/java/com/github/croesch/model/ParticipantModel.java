package com.github.croesch.model;

import java.util.Map;

import com.github.croesch.model.api.IParticipantModel;
import com.github.croesch.model.api.IPersistenceModel;
import com.github.croesch.types.Participant;

/**
 * Model responsible for participants.
 * 
 * @author croesch
 * @since Date: May 29, 2011 12:19:44 PM
 */
class ParticipantModel implements IParticipantModel {

  /** model that is responsible to store and load data */
  private final IPersistenceModel persistenceModel;

  /** list of all participants that are available */
  private final Map<Long, Participant> listOfParticipants;

  /**
   * Creates a {@link ParticipantModel} that will load all {@link Participant}s from the given {@link IPersistenceModel}
   * .
   * 
   * @author croesch
   * @since Date: Jun 19, 2011
   * @param pm the model to load the data from (and synchronize afterwards).
   */
  public ParticipantModel(final IPersistenceModel pm) {
    this.persistenceModel = pm;
    this.listOfParticipants = this.persistenceModel.getListOfParticipants();
  }

  @Override
  public Participant getParticipant(final long id) {
    return this.listOfParticipants.get(Long.valueOf(id));
  }
}
