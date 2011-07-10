package com.github.croesch.view;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import org.apache.log4j.Logger;

import com.github.croesch.actions.ActionObserver;
import com.github.croesch.actions.UserAction;
import com.github.croesch.i18n.Text;
import com.github.croesch.model.api.IParticipantModel4View;
import com.github.croesch.types.exceptions.RequiredFieldSetToNullException;
import com.github.croesch.view.api.IParticipantEditView;
import com.github.croesch.view.api.IParticipantListView;
import com.github.croesch.view.api.IParticipantView;

/**
 * The implementation of {@link IParticipantView} that is able to view participants.
 * 
 * @author croesch
 * @since Date: Jun 8, 2011 6:22:50 AM
 */
class ParticipantView extends JPanel implements IParticipantView, ActionObserver {

  /** generated */
  private static final long serialVersionUID = 3996523912669034625L;

  /** logging class */
  private static final Logger LOGGER = Logger.getLogger(ParticipantView.class);

  /** the view to edit the participants */
  private final IParticipantEditView editView;

  /** the view to show list of participants */
  private final IParticipantListView listView;

  /** the model to fetch participants from */
  private final IParticipantModel4View model;

  /**
   * Constructs the view for participants.
   * 
   * @since Date: Jul 1, 2011
   * @param m the model to fetch participant information from
   * @throws RequiredFieldSetToNullException if the given model is <code>null</code>
   */
  public ParticipantView(final IParticipantModel4View m) throws RequiredFieldSetToNullException {
    if (m == null) {
      throw new RequiredFieldSetToNullException();
    }
    this.model = m;

    setLayout(new MigLayout("fill", "[][grow, fill]", "[grow, fill]"));

    final ParticipantEditView pev = new ParticipantEditView();
    this.editView = pev;

    final ParticipantListView plv = new ParticipantListView(this);
    this.listView = plv;

    add(plv);
    add(pev);
  }

  @Override
  public IParticipantEditView getParticipantEditView() {
    return this.editView;
  }

  @Override
  public IParticipantListView getParticipantListView() {
    return this.listView;
  }

  @Override
  public void performAction(final UserAction action) {
    if (action == UserAction.PARTICIPANT_SELECTED) {
      this.editView.setParticipant(this.model.getParticipant(this.listView.getSelectedParticipantId()));
    } else {
      LOGGER.warn(Text.WARN_UNKNOWN_ACTION.text(action));
    }
  }
}
