package com.github.croesch.partimana.view;

import javax.swing.table.DefaultTableModel;

import com.github.croesch.partimana.actions.ActionObserver;
import com.github.croesch.partimana.actions.UserAction;
import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.types.Participant;
import com.github.croesch.partimana.view.api.IListView;

/**
 * Implementation of the table that views the table of participants.
 * 
 * @author croesch
 * @since Date: Jun 8, 2011
 */
class ParticipantListView extends ListView<Participant> implements IListView<Participant> {

  /** generated */
  private static final long serialVersionUID = -96888415800702415L;

  /**
   * Constructs a new {@link ParticipantListView} that is able to visualise a table of participants. The observer will
   * be notified about selection changes.
   * 
   * @since Date: Jul 10, 2011
   * @param name the name of this component
   * @param o the {@link ActionObserver} that listens for the selection change event.
   */
  public ParticipantListView(final String name, final ActionObserver o) {
    super(name, "participants", o, new ParticipantTableModel(), UserAction.PARTICIPANT_SELECTED);
  }

  /**
   * Model for a table holding participant entries.
   * 
   * @author croesch
   * @since Date: Nov 11, 2012
   */
  private static class ParticipantTableModel extends DefaultTableModel {

    /** generated serial version UID */
    private static final long serialVersionUID = -5057192383604854L;

    /**
     * Constructs a model for a table holding participant entries.
     * 
     * @author croesch
     * @since Date: Nov 11, 2012
     */
    public ParticipantTableModel() {
      setColumnIdentifiers(new Object[] { Text.PARTICIPANT_ID.text(),
                                         Text.PARTICIPANT_FORENAME.text(),
                                         Text.PARTICIPANT_LASTNAME.text() });
    }

    @Override
    public boolean isCellEditable(final int row, final int column) {
      return false;
    }
  }

  @Override
  protected Object[] getRowArray(final Participant p) {
    return new Object[] { p.getId(), p.getForeName(), p.getLastName() };
  }
}
