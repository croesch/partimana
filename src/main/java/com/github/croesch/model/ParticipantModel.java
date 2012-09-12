package com.github.croesch.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.management.openmbean.KeyAlreadyExistsException;

import com.github.croesch.model.api.IParticipantModel;
import com.github.croesch.model.api.IPersistenceModel;
import com.github.croesch.types.Participant;
import com.github.croesch.types.exceptions.RequiredFieldSetToNullException;

/**
 * Model responsible for participants.
 * 
 * @author croesch
 * @since Date: May 29, 2011
 */
class ParticipantModel implements IParticipantModel {

  /** model that is responsible to store and load data */
  private final IPersistenceModel persistenceModel;

  /** map of all participants that are available */
  private final Map<Long, Participant> mapOfParticipants;

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
    this.mapOfParticipants = this.persistenceModel.getMapOfParticipants();
  }

  @Override
  public Participant getParticipant(final long id) {
    final Participant p = this.mapOfParticipants.get(Long.valueOf(id));
    if (p == null) {
      return null;
    }
    return new Participant(p);
  }

  @Override
  public void store(final Participant p) throws KeyAlreadyExistsException, RequiredFieldSetToNullException {
    final Participant store = new Participant(p);

    if (this.mapOfParticipants.containsKey(store.getId())) {
      this.persistenceModel.update(store);
    } else {
      this.persistenceModel.create(store);
    }
    this.mapOfParticipants.put(store.getId(), store);
  }

  @Override
  public void deleteParticipant(final long id) {
    this.persistenceModel.deleteParticipant(id);
    this.mapOfParticipants.remove(id);
  }

  @Override
  public List<Participant> getListOfParticipants() {
    final List<Participant> list = new ArrayList<Participant>();
    for (final Participant p : this.mapOfParticipants.values()) {
      list.add(new Participant(p));
    }
    return list;
  }
}
