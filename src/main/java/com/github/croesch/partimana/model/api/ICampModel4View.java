package com.github.croesch.partimana.model.api;

import com.github.croesch.annotate.MayBeNull;
import com.github.croesch.annotate.NotNull;
import com.github.croesch.partimana.types.Camp;
import java.util.List;

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
   * @param id the id to lookup
   * @return the camp with the given id, or <code>null</code> if no camp has this id.
   * @since Date: Aug 8, 2011
   */
  @MayBeNull
  Camp getCamp(long id);

  /**
   * Returns a {@link List} containing all stored {@link Camp}s.
   *
   * @return a list that contains all camps stored in the model.
   * @since Date: Aug 8, 2011
   */
  @NotNull
  List<Camp> getListOfCamps();
}
