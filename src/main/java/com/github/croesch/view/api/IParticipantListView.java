package com.github.croesch.view.api;

import java.util.List;

import com.github.croesch.types.Participant;

/**
 * Interface for the list view that defines methods that need to be implemented.
 * 
 * @author croesch
 * @since Date: Jun 8, 2011 6:19:45 AM
 */
public interface IParticipantListView {

  /**
   * Performs an update of the list view and will visualise the information of the given list.
   * 
   * @since Date: Jul 10, 2011
   * @param participants the list of participants that is up to date.
   */
  void update(List<Participant> participants);

  /**
   * Returns the selected participant from the list.
   * 
   * @since Date: Jul 10, 2011
   * @return {@link Participant}, that is currently selected.
   */
  Participant getSelected();
}
