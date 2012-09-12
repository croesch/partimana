package com.github.croesch.partimana.view.api;

import java.util.List;

import com.github.croesch.annotate.NotNull;
import com.github.croesch.partimana.types.Camp;

/**
 * Interface for the list view that defines methods that need to be implemented.
 * 
 * @author croesch
 * @since Date: Sep 27, 2011
 */
public interface ICampListView {

  /**
   * Performs an update of the list view and will visualise the information of the given list.
   * 
   * @since Date: Sep 27, 2011
   * @param camps the list of camps that is up to date.
   */
  @NotNull
  void update(List<Camp> camps);

  /**
   * Returns the id of the selected camp.
   * 
   * @since Date: Sep 27, 2011
   * @return id of the {@link Camp} that is currently selected.<br>
   *         <code>0</code>, if no row is selected.
   */
  long getSelectedCampId();
}
