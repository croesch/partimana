package com.github.croesch.partimana.model.api;

import java.util.List;

import com.github.croesch.partimana.types.Camp;

/**
 * Interface for the camp-model that contains all methods that the view should know/see.
 * 
 * @author croesch
 * @since Date: Jun 8, 2011
 */
public interface ICampModel4View {

  /**
   * Returns the {@link Camp} with the given id.
   * 
   * @author croesch
   * @since Date: Aug 8, 2011
   * @param id the id to lookup
   * @return the camp with the given id, or <code>null</code> if no camp has this id.
   */
  Camp getCamp(long id);

  /**
   * Returns a {@link List} containing all stored {@link Camp}s.
   * 
   * @author croesch
   * @since Date: Aug 8, 2011
   * @return a list that contains all camps stored in the model.
   */
  List<Camp> getListOfCamps();
}
