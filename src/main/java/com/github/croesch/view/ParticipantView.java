package com.github.croesch.view;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import com.github.croesch.view.api.IParticipantEditView;
import com.github.croesch.view.api.IParticipantListView;
import com.github.croesch.view.api.IParticipantView;

/**
 * The implementation of {@link IParticipantView} that is able to view participants.
 * 
 * @author croesch
 * @since Date: Jun 8, 2011 6:22:50 AM
 */
class ParticipantView extends JPanel implements IParticipantView {

  /** generated */
  private static final long serialVersionUID = 3996523912669034625L;

  /** the view to edit the participants */
  private final IParticipantEditView editView;

  /** the view to show list of participants */
  private final IParticipantListView listView;

  /**
   * Constructs the view for participants.
   * 
   * @since Date: Jul 1, 2011
   */
  public ParticipantView() {
    setLayout(new MigLayout("fill", "[][grow, fill]", "[grow, fill]"));

    final ParticipantEditView pev = new ParticipantEditView();
    this.editView = pev;

    final ParticipantListView plv = new ParticipantListView();
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

}
