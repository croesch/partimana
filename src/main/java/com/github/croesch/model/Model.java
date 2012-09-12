package com.github.croesch.model;

import java.util.List;

import com.github.croesch.model.api.ICampModel;
import com.github.croesch.model.api.IModel4View;
import com.github.croesch.model.api.IParticipantModel;
import com.github.croesch.model.api.IPersistenceModel;
import com.github.croesch.types.Camp;
import com.github.croesch.types.Participant;
import com.github.croesch.types.exceptions.RequiredFieldSetToNullException;
import com.github.croesch.view.api.IView;

/**
 * The model of the partimana program.
 * 
 * @author croesch
 * @since Date: May 29, 2011
 */
public final class Model implements ICampModel, IParticipantModel, IModel4View {

  /** the model that is responsible for persistence of data */
  private final IPersistenceModel persistenceModel = new PersistenceModel();

  /** the model that is responsible for actions that have to do with {@link Participant}s */
  private final IParticipantModel participantModel = new ParticipantModel(this.persistenceModel);

  /** the model that is responsible for actions that have to do with {@link Camp}s */
  private final ICampModel campModel = new CampModel(this.persistenceModel);

  /** the connection to the view of the program */
  private IView view;

  /**
   * Sets the view for the model so that it can notify the view to update itself.
   * 
   * @author croesch
   * @since Date: Jun 30, 2011
   * @param v the view to set to the model.
   */
  public void setView(final IView v) {
    this.view = v;
  }

  @Override
  public Participant getParticipant(final long id) {
    return this.participantModel.getParticipant(id);
  }

  @Override
  public void store(final Participant p) throws RequiredFieldSetToNullException {
    this.participantModel.store(p);
    if (this.view != null) {
      this.view.update();
    }
  }

  @Override
  public void deleteParticipant(final long id) {
    this.participantModel.deleteParticipant(id);
    if (this.view != null) {
      this.view.update();
    }
  }

  @Override
  public List<Participant> getListOfParticipants() {
    return this.participantModel.getListOfParticipants();
  }

  @Override
  public Camp getCamp(final long id) {
    return this.campModel.getCamp(id);
  }

  @Override
  public List<Camp> getListOfCamps() {
    return this.campModel.getListOfCamps();
  }

  @Override
  public void store(final Camp c) throws RequiredFieldSetToNullException {
    this.campModel.store(c);
    if (this.view != null) {
      this.view.update();
    }
  }

  @Override
  public void deleteCamp(final long id) {
    this.campModel.deleteCamp(id);
    if (this.view != null) {
      this.view.update();
    }
  }
}
