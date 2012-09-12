package com.github.croesch.partimana.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Comparator;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import net.miginfocom.swing.MigLayout;

import com.github.croesch.partimana.actions.ActionObserver;
import com.github.croesch.partimana.actions.UserAction;
import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.types.Participant;
import com.github.croesch.partimana.view.api.IParticipantListView;

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

    this.table = new ParticipantTable(o);

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

  /**
   * Table that displays a set of participants.
   * 
   * @author croesch
   * @since Date: Jul 11, 2011
   */
  private static class ParticipantTable extends JTable {

    /** generated */
    private static final long serialVersionUID = -6694984381634664552L;

    /**
     * Creates a new table of participants.
     * 
     * @since Date: Jul 11, 2011
     * @param o the {@link ActionObserver} that'll be notified on selection changes.
     */
    public ParticipantTable(final ActionObserver o) {
      final DefaultTableModel tm = new DefaultTableModel();
      tm.setColumnIdentifiers(new Object[] { Text.PARTICIPANT_ID.text(),
                                            Text.PARTICIPANT_FORENAME.text(),
                                            Text.PARTICIPANT_LASTNAME.text() });
      setModel(tm);
      setAutoCreateRowSorter(true);
      setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

      final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tm);
      sorter.setComparator(0, new Comparator<Long>() {
        @Override
        public int compare(final Long o1, final Long o2) {
          return (int) (o1 - o2);
        }
      });
      setRowSorter(sorter);

      addMouseListener(new TableMouseListener(o));
    }

    @Override
    public boolean isCellEditable(final int rowIndex, final int colIndex) {
      return false;
    }
  }

  /**
   * Mouse listener for the table that will fire an action to a given observer that a row has been selected.
   * 
   * @author croesch
   * @since Date: Jul 11, 2011
   */
  private static class TableMouseListener extends MouseAdapter {

    /** the observer that should be notified about selection events */
    private final ActionObserver observer;

    /**
     * Creates new mouse listener for the table of participants. Will fire the action if double click happened on the
     * table.
     * 
     * @since Date: Jul 11, 2011
     * @param o the {@link ActionObserver} to fire the action to.
     */
    public TableMouseListener(final ActionObserver o) {
      this.observer = o;
    }

    @Override
    public void mouseClicked(final MouseEvent e) {
      if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
        this.observer.performAction(UserAction.PARTICIPANT_SELECTED);
      }
      super.mouseClicked(e);
    }
  }
}
