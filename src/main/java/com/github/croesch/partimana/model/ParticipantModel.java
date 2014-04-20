package com.github.croesch.partimana.model;

import com.github.croesch.annotate.NotNull;
import com.github.croesch.partimana.model.api.IParticipantModel;
import com.github.croesch.partimana.model.api.IPersistenceModel;
import com.github.croesch.partimana.types.Participant;
import com.github.croesch.partimana.types.exceptions.RequiredFieldSetToNullException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.management.openmbean.KeyAlreadyExistsException;

/**
 * Model responsible for participants.
 *
 * @author croesch
 * @since Date: May 29, 2011
 */
class ParticipantModel implements IParticipantModel {

  /** model that is responsible to store and load data */
  @NotNull
  private final IPersistenceModel persistenceModel;

  /** map of all participants that are available */
  @NotNull
  private final Map<Long, Participant> mapOfParticipants;

  /**
   * Creates a {@link ParticipantModel} that will load all {@link Participant}s from the given {@link IPersistenceModel}
   * .
   *
   * @param pm the model to load the data from (and synchronize afterwards).
   * @since Date: Jun 19, 2011
   */
  public ParticipantModel(final IPersistenceModel pm) {
    persistenceModel = pm; //FIXME null check!
    mapOfParticipants = persistenceModel.getMapOfParticipants();
  }

  @Override
  public Participant getParticipant(final long id) {
    final Participant p = mapOfParticipants.get(Long.valueOf(id));
    if (p == null) {
      return null;
    }
    return new Participant(p);
  }

  @Override
  public Participant store(final Participant p) throws KeyAlreadyExistsException, RequiredFieldSetToNullException {
    final Participant store = new Participant(p);

    if (mapOfParticipants.containsKey(store.getId())) {
      persistenceModel.update(store);
    } else {
      persistenceModel.create(store);
    }
    mapOfParticipants.put(store.getId(), store);

    return store;
  }

  @Override
  public void deleteParticipant(final long id) {
    persistenceModel.deleteParticipant(id);
    mapOfParticipants.remove(id);
  }

  @Override
  public List<Participant> getListOfParticipants() {
    final List<Participant> list = new ArrayList<Participant>();
    for (final Participant p : mapOfParticipants.values()) {
      list.add(new Participant(p));
    }
    return list;
  }
}
