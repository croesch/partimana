package com.github.croesch.partimana.model;

import com.github.croesch.annotate.NotNull;
import com.github.croesch.partimana.model.api.ICampModel;
import com.github.croesch.partimana.model.api.IPersistenceModel;
import com.github.croesch.partimana.types.Camp;
import com.github.croesch.partimana.types.exceptions.RequiredFieldSetToNullException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Model responsible for {@link Camp}s
 *
 * @author croesch
 * @since Date: May 29, 2011
 */
class CampModel implements ICampModel {

  /** model that is responsible to store and load data */
  @NotNull
  private final IPersistenceModel persistenceModel;

  /** map of all camps that are available */
  @NotNull
  private final Map<Long, Camp> mapOfCamps;

  /**
   * Creates a {@link CampModel} that will load all {@link Camp}s from the given {@link IPersistenceModel} .
   *
   * @param pm the model to load the data from (and synchronize afterwards).
   * @since Date: Jun 19, 2011
   */
  public CampModel(final IPersistenceModel pm) {
    persistenceModel = pm; //FIXME: null check
    mapOfCamps = persistenceModel.getMapOfCamps();
  }

  @Override
  public Camp getCamp(final long id) {
    final Camp c = mapOfCamps.get(Long.valueOf(id));
    if (c == null) {
      return null;
    }
    return new Camp(c);
  }

  @Override
  public List<Camp> getListOfCamps() {
    final List<Camp> list = new ArrayList<Camp>();
    for (final Camp c : mapOfCamps.values()) {
      list.add(c);
    }
    return list;
  }

  @Override
  public void store(final Camp c) throws RequiredFieldSetToNullException {
    final Camp store = new Camp(c);

    if (mapOfCamps.containsKey(store.getId())) {
      persistenceModel.update(store);
    } else {
      persistenceModel.create(store);
    }
    mapOfCamps.put(store.getId(), store);
  }

  @Override
  public void deleteCamp(final long id) {
    persistenceModel.deleteCamp(id);
    mapOfCamps.remove(id);
  }
}
