package com.github.croesch.partimana.view;

import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;

import com.github.croesch.partimana.actions.ActionObserver;
import com.github.croesch.partimana.actions.UserAction;
import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.types.Participant;
import com.github.croesch.partimana.view.api.IParticipantListView;
import com.github.croesch.partimana.view.components.DataTable;

/**
 * Implementation of the table that views the table of participants.
 * 
 * @author croesch
 * @since Date: Jun 8, 2011
 */
class ParticipantListView extends JPanel implements IParticipantListView {

  /** generated */
  private static final long serialVersionUID = -96888415800702415L;

  /** the table to display the different participants */
  private final JTable table;

  /**
   * Constructs a new {@link ParticipantListView} that is able to visualise a table of participants. The observer will
   * be notified about selection changes.
   * 
   * @since Date: Jul 10, 2011
   * @param o the {@link ActionObserver} that listens for the selection change event.
   */
  public ParticipantListView(final ActionObserver o) {
    setLayout(new MigLayout("fill"));

    final Object[] columnIdentifiers = new Object[] { Text.PARTICIPANT_ID.text(),
                                                     Text.PARTICIPANT_FORENAME.text(),
                                                     Text.PARTICIPANT_LASTNAME.text() };
    this.table = new DataTable(o, columnIdentifiers, UserAction.PARTICIPANT_SELECTED);
    this.table.setName("participants");

    add(new JScrollPane(this.table), "grow");
  }

  @Override
  public void update(final List<Participant> participants) {
    while (this.table.getRowCount() > 0) {
      ((DefaultTableModel) this.table.getModel()).removeRow(0);
    }
    for (final Participant p : participants) {
      ((DefaultTableModel) this.table.getModel()).addRow(new Object[] { p.getId(), p.getForeName(), p.getLastName() });
    }
  }

  @Override
  public long getSelectedParticipantId() {
    if (this.table.getSelectedRowCount() == 0) {
      return 0;
    }
    return Long.parseLong(this.table.getValueAt(this.table.getSelectedRow(), 0).toString());
  }
}
