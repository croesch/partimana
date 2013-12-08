package com.github.croesch.partimana.model;

import com.github.croesch.annotate.MayBeNull;
import com.github.croesch.annotate.NotNull;
import com.github.croesch.partimana.model.api.ICampModel;
import com.github.croesch.partimana.model.api.IModel4View;
import com.github.croesch.partimana.model.api.IParticipantModel;
import com.github.croesch.partimana.model.api.IPersistenceModel;
import com.github.croesch.partimana.types.Camp;
import com.github.croesch.partimana.types.Participant;
import com.github.croesch.partimana.types.exceptions.RequiredFieldSetToNullException;
import com.github.croesch.partimana.view.api.IView;
import java.util.List;

/**
 * The model of the partimana program.
 *
 * @author croesch
 * @since Date: May 29, 2011
 */
public final class Model implements ICampModel, IParticipantModel, IModel4View {

  /** the model that is responsible for actions that have to do with {@link Participant}s */
  @NotNull
  private final IParticipantModel participantModel;

  /** the model that is responsible for actions that have to do with {@link Camp}s */
  @NotNull
  private final ICampModel campModel;

  /** the connection to the view of the program */
  @MayBeNull
  private IView view;

  /**
   * Creates this model using the given persistence model to store participants and camps.
   *
   * @param persistenceModel the model to store participants and camps.
   * @since Date: Oct 13, 2012
   */
  public Model(final IPersistenceModel persistenceModel) {
    this.participantModel = new ParticipantModel(persistenceModel);
    this.campModel = new CampModel(persistenceModel);
  }

  /**
   * Sets the view for the model so that it can notify the view to update itself.
   *
   * @param v the view to set to the model.
   * @since Date: Jun 30, 2011
   */
  public void setView(final IView v) {
    this.view = v;
  }

  @Override
  public Participant getParticipant(final long id) {
    return this.participantModel.getParticipant(id);
  }

  @Override
  public Participant store(final Participant p) throws RequiredFieldSetToNullException {
    final Participant participant = this.participantModel.store(p);
    if (this.view != null) {
      this.view.update();
    }
    return participant;
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
