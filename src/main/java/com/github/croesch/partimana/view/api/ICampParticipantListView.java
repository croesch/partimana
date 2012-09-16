package com.github.croesch.partimana.view.api;

import java.util.List;

import com.github.croesch.partimana.types.CampParticipant;

/**
 * Interface for the list view of {@link CampParticipant}s that defines methods that need to be implemented.
 * 
 * @author croesch
 * @since Date: Sep 16, 2012
 */
public interface ICampParticipantListView {

  /**
   * Performs an update of the list view and will visualise the information of the given list.
   * 
   * @since Date: Jul 10, 2011
   * @param participants the list of participants that is up to date.
   */
  void update(List<CampParticipant> participants);

  /**
   * Returns the id of the selected participant.
   * 
   * @since Date: Jul 10, 2011
   * @return id of the participant that is currently selected.<br>
   *         <code>0</code>, if no row is selected.
   */
  long getSelectedParticipantId();

}
