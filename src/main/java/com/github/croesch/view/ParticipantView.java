package com.github.croesch.view;

import com.github.croesch.view.api.IParticipantEditView;
import com.github.croesch.view.api.IParticipantView;

/**
 * The implementation of {@link IParticipantView} that is able to view participants.
 * 
 * @author croesch
 * @since Date: Jun 8, 2011 6:22:50 AM
 */
class ParticipantView implements IParticipantView {

  /** the view to edit the participants */
  private final IParticipantEditView editView = new ParticipantEditView();

  @Override
  public IParticipantEditView getParticipantEditView() {
    return this.editView;
  }

}
