package com.github.croesch.view;

import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;

import com.github.croesch.actions.ActionObserver;
import com.github.croesch.types.Participant;
import com.github.croesch.view.api.IParticipantListView;

/**
 * Implementation of the table that views the table of participants.
 * 
 * @author croesch
 * @since Date: Jun 8, 2011 6:23:36 AM
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

    final DefaultTableModel tm = new DefaultTableModel();
    tm.setColumnIdentifiers(new Object[] { "ID", "Vorname", "Nachname" });
    this.table = new JTable(tm) {
      /** generated */
      private static final long serialVersionUID = -6694984381634664552L;

      @Override
      public boolean isCellEditable(final int rowIndex, final int colIndex) {
        return false;
      }
    };
    this.table.setAutoCreateRowSorter(true);

    add(new JScrollPane(this.table));
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
  public Participant getSelected() {
    // TODO Auto-generated method stub
    return null;
  }

}
