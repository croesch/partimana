package com.github.croesch.partimana.view.api;

import com.github.croesch.annotate.NotNull;
import com.github.croesch.partimana.types.Participant;

/**
 * Interface to provide functionality of the view for {@link com.github.croesch.partimana.types.Participant}s.
 *
 * @author croesch
 * @since Date: Jun 8, 2011
 */
public interface IParticipantView {

  /**
   * Returns the instance of {@link IParticipantEditView} that is responsible for editing the {@link
   * com.github.croesch.partimana.types.Participant}s.
   *
   * @return the {@link IParticipantEditView}
   * @since Date: Jun 30, 2011
   */
  @NotNull
  IParticipantEditView getParticipantEditView();

  /**
   * Returns the instance of {@link IListView} that is responsible for viewing the {@link
   * com.github.croesch.partimana.types.Participant}s.
   *
   * @return the {@link IListView}
   * @since Date: Jul 10, 2011
   */
  @NotNull
  IListView<Participant> getParticipantListView();

  /**
   * Performs creation of a new {@link com.github.croesch.partimana.types.Participant}. Means that the view will clear
   * all fields and give the user the possibility to fill the fields and store the information as a new participant.
   * Won't have an effect on the data.
   *
   * @since Date: Jul 11, 2011
   */
  void createParticipant();
}
