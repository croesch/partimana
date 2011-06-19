package com.github.croesch.model;

import com.github.croesch.model.api.ICampModel;
import com.github.croesch.model.api.IPersistenceModel;
import com.github.croesch.types.Camp;

/**
 * Model responsible for {@link Camp}s
 * 
 * @author croesch
 * @since Date: May 29, 2011 12:24:16 PM
 */
class CampModel implements ICampModel {

  /** model that is responsible to store and load data */
  private final IPersistenceModel persistenceModel;

  /**
   * Creates a {@link CampModel} that will load all {@link Camp}s from the given {@link IPersistenceModel} .
   * 
   * @author croesch
   * @since Date: Jun 19, 2011
   * @param pm the model to load the data from (and synchronize afterwards).
   */
  public CampModel(final IPersistenceModel pm) {
    this.persistenceModel = pm;
  }

}
