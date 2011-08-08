package com.github.croesch.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.github.croesch.model.api.ICampModel;
import com.github.croesch.model.api.IPersistenceModel;
import com.github.croesch.types.Camp;
import com.github.croesch.types.exceptions.RequiredFieldSetToNullException;

/**
 * Model responsible for {@link Camp}s
 * 
 * @author croesch
 * @since Date: May 29, 2011 12:24:16 PM
 */
class CampModel implements ICampModel {

  /** model that is responsible to store and load data */
  private final IPersistenceModel persistenceModel;

  /** map of all camps that are available */
  private final Map<Long, Camp> mapOfCamps;

  /**
   * Creates a {@link CampModel} that will load all {@link Camp}s from the given {@link IPersistenceModel} .
   * 
   * @author croesch
   * @since Date: Jun 19, 2011
   * @param pm the model to load the data from (and synchronize afterwards).
   */
  public CampModel(final IPersistenceModel pm) {
    this.persistenceModel = pm;
    this.mapOfCamps = this.persistenceModel.getMapOfCamps();
  }

  @Override
  public Camp getCamp(final long id) {
    final Camp c = this.mapOfCamps.get(Long.valueOf(id));
    if (c == null) {
      return null;
    }
    return new Camp(c);
  }

  @Override
  public List<Camp> getListOfCamps() {
    final List<Camp> list = new ArrayList<Camp>();
    for (final Camp c : this.mapOfCamps.values()) {
      list.add(c);
    }
    return list;
  }

  @Override
  public void store(final Camp c) throws RequiredFieldSetToNullException {
    final Camp store = new Camp(c);

    if (this.mapOfCamps.containsKey(store.getId())) {
      this.persistenceModel.update(store);
    } else {
      this.persistenceModel.create(store);
    }
    this.mapOfCamps.put(store.getId(), store);
  }

  @Override
  public void deleteCamp(final long id) {
    this.persistenceModel.deleteCamp(id);
    this.mapOfCamps.remove(id);
  }
}
