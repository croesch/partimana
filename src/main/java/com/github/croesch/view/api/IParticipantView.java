package com.github.croesch.view.api;

/**
 * Interface to provide functionality of the view for {@link com.github.croesch.types.Participant}s.
 * 
 * @author croesch
 * @since Date: Jun 8, 2011 6:19:28 AM
 */
public interface IParticipantView {

  /**
   * Returns the instance of {@link IParticipantEditView} that is responsible for editing the
   * {@link com.github.croesch.types.Participant}s.
   * 
   * @author croesch
   * @since Date: Jun 30, 2011
   * @return the {@link IParticipantEditView}
   */
  IParticipantEditView getParticipantEditView();

}
