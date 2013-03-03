package com.github.croesch.partimana.view;

import net.miginfocom.swing.MigLayout;

import org.apache.log4j.Logger;

import com.github.croesch.annotate.NotNull;
import com.github.croesch.components.CButton;
import com.github.croesch.components.CPanel;
import com.github.croesch.partimana.actions.ActionObserver;
import com.github.croesch.partimana.actions.UserAction;
import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.api.IParticipantModel4View;
import com.github.croesch.partimana.types.Participant;
import com.github.croesch.partimana.types.exceptions.RequiredFieldSetToNullException;
import com.github.croesch.partimana.view.api.IListView;
import com.github.croesch.partimana.view.api.IParticipantEditView;
import com.github.croesch.partimana.view.api.IParticipantView;

/**
 * The implementation of {@link IParticipantView} that is able to view participants.
 * 
 * @author croesch
 * @since Date: Jun 8, 2011
 */
class ParticipantView extends CPanel implements IParticipantView, ActionObserver {

  /** generated */
  private static final long serialVersionUID = 3996523912669034625L;

  /** logging class */
  private static final Logger LOGGER = Logger.getLogger(ParticipantView.class);

  /** the view to edit the participants */
  @NotNull
  private final IParticipantEditView editView;

  /** the view to show list of participants */
  @NotNull
  private final IListView<Participant> listView;

  /** the model to fetch participants from */
  @NotNull
  private final IParticipantModel4View model;

  /**
   * Constructs the view for participants.
   * 
   * @since Date: Jul 1, 2011
   * @param name the name of this component
   * @param m the model to fetch participant information from
   * @throws RequiredFieldSetToNullException if the given model is <code>null</code>
   */
  public ParticipantView(final String name, final IParticipantModel4View m) throws RequiredFieldSetToNullException {
    super(name);
    if (m == null) {
      throw new RequiredFieldSetToNullException();
    }
    this.model = m;

    setLayout(new MigLayout("fill", "[][grow, fill]", "[grow, fill]"));

    // sic! because of the add methods below ..
    final ParticipantEditView pev = new ParticipantEditView("participantEdit");
    final ParticipantListView plv = new ParticipantListView("participantList", this);
    plv.update(m.getListOfParticipants());
    this.editView = pev;
    this.listView = plv;

    final CPanel buttonPanel = new CPanel("buttons", new MigLayout("fill"));
    final CButton createButton = new CButton("newParticipant", Action.getCreateParticipantAction());
    final CButton deleteButton = new CButton("deleteParticipant", Action.getDeleteParticipantAction());
    buttonPanel.add(createButton, "sg one");
    buttonPanel.add(deleteButton, "sg one");

    final CPanel leftPanel = new CPanel("participantList", new MigLayout("fill", "[grow, fill]", "[][grow, fill]"));
    leftPanel.add(buttonPanel, "wrap");
    leftPanel.add(plv);

    add(leftPanel);
    add(pev);
  }

  @Override
  @NotNull
  public IParticipantEditView getParticipantEditView() {
    return this.editView;
  }

  @Override
  @NotNull
  public IListView<Participant> getParticipantListView() {
    return this.listView;
  }

  @Override
  public void performAction(final UserAction action) {
    if (action == UserAction.PARTICIPANT_SELECTED) {
      this.editView.setParticipant(this.model.getParticipant(this.listView.getSelectedElementId()));
    } else {
      LOGGER.warn(Text.WARN_UNKNOWN_ACTION.text(action));
    }
  }

  @Override
  public void createParticipant() {
    this.editView.clear();
  }
}
