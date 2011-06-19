package com.github.croesch.model;

import com.github.croesch.model.api.ICampModel;
import com.github.croesch.model.api.IModel4View;
import com.github.croesch.model.api.IParticipantModel;
import com.github.croesch.model.api.IPersistenceModel;
import com.github.croesch.types.Camp;
import com.github.croesch.types.Participant;

/**
 * The model of the partimana program.
 * 
 * @author croesch
 * @since Date: May 29, 2011 12:19:05 PM
 */
public final class Model implements ICampModel, IParticipantModel, IModel4View {

  /** the model that is responsible for persistency of data */
  private final IPersistenceModel persistenceModel = new PersistenceModel();

  /** the model that is responsible for actions that have to do with {@link Participant}s */
  private final IParticipantModel participantModel = new ParticipantModel(this.persistenceModel);

  /** the model that is responsible for actions that have to do with {@link Camp}s */
  private final ICampModel campModel = new CampModel(this.persistenceModel);

  @Override
  public Participant getParticipant(final long id) {
    return this.participantModel.getParticipant(id);
  }

}
