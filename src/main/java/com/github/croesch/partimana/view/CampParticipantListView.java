package com.github.croesch.partimana.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;

import com.github.croesch.partimana.actions.ActionObserver;
import com.github.croesch.partimana.actions.UserAction;
import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.types.CampParticipant;
import com.github.croesch.partimana.view.api.ICampParticipantListView;
import com.github.croesch.partimana.view.components.DataTable;

/**
 * Implementation of the table that views the table of participants in a camp.
 * 
 * @author croesch
 * @since Date: Sep 16, 2012
 */
class CampParticipantListView extends JPanel implements ICampParticipantListView {

  /** generated */
  private static final long serialVersionUID = -8804248070325729977L;

  /** the table to display the different participants */
  private final JTable table;

  /** the table model that holds the participants - the data of the table */
  private final MyTableModel tableModel = new MyTableModel();

  /**
   * Constructs a new {@link CampParticipantListView} that is able to visualise a table of participants. The observer
   * will be notified about selection changes.
   * 
   * @since Date: Sep 16, 2012
   * @param o the {@link ActionObserver} that listens for the selection change event.
   */
  public CampParticipantListView(final ActionObserver o) {
    setLayout(new MigLayout("fill"));

    this.table = new DataTable(o, UserAction.PARTICIPANT_SELECTED, this.tableModel);

    add(new JScrollPane(this.table), "grow");
  }

  @Override
  public void update(final List<CampParticipant> participants) {
    this.tableModel.removeAll();
    for (final CampParticipant p : participants) {
      this.tableModel.addRow(p);
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
   * Table model that holds {@link CampParticipant}s as rows.
   * 
   * @author croesch
   * @since Date: Sep 16, 2012
   */
  private static class MyTableModel extends DefaultTableModel {
    /** generated */
    private static final long serialVersionUID = 4361064994767610933L;

    /** the rows of the model */
    private final Map<Integer, CampParticipant> participants = new HashMap<Integer, CampParticipant>();

    /**
     * Creates the table model that holds {@link CampParticipant}s as rows.
     * 
     * @since Date: Sep 16, 2012
     */
    public MyTableModel() {
      setColumnIdentifiers(new Object[] { Text.PARTICIPANT_ID.text(),
                                         Text.PARTICIPANT_FORENAME.text(),
                                         Text.PARTICIPANT_LASTNAME.text(),
                                         Text.PARTICIPANT_AGE.text(),
                                         Text.PARTICIPANT_BOARD.text(),
                                         Text.PARTICIPANT_EXTENDED_BOARD.text(),
                                         Text.PARTICIPANT_CAMP_KITCHEN.text(),
                                         Text.PARTICIPANT_MAK.text(),
                                         Text.PARTICIPANT_MISC.text(),
                                         Text.PARTICIPANT_CAMP_PARTICIPANT.text(),
                                         Text.PARTICIPANT_SEMINAR.text(),
                                         Text.PARTICIPANT_STAFF_GENERAL.text(),
                                         Text.PARTICIPANT_STAFF_YOUTH.text() });
    }

    @Override
    public Class<?> getColumnClass(final int columnIndex) {
      if (columnIndex == 0) {
        return Long.class;
      } else if (columnIndex == 1 || columnIndex == 2) {
        return String.class;
      } else {
        return Boolean.class;
      }
    }

    @Override
    public boolean isCellEditable(final int row, final int column) {
      switch (column) {
        case 3:
          return this.participants.get(row).isPossibleAGE();
        case 4:
          return this.participants.get(row).isPossibleBoard();
        case 5:
          return this.participants.get(row).isPossibleExtendedBoard();
        case 6:
          return this.participants.get(row).isPossibleKitchen();
        case 7:
          return this.participants.get(row).isPossibleMAK();
        case 8:
          return this.participants.get(row).isPossibleMisc();
        case 9:
          return this.participants.get(row).isPossibleParticipant();
        case 10:
          return this.participants.get(row).isPossibleSeminar();
        case 11:
          return this.participants.get(row).isPossibleStaff();
        case 12:
          return this.participants.get(row).isPossibleStaffYouth();
        default:
          return false;
      }
    }

    /**
     * Removes all data from the table.
     * 
     * @since Date: Sep 16, 2012
     */
    public void removeAll() {
      while (getRowCount() > 0) {
        removeRow(0);
      }
    }

    @Override
    public void removeRow(final int row) {
      this.participants.remove(row);
      super.removeRow(row);
    }

    /**
     * Adds the given {@link CampParticipant} as row to the table.
     * 
     * @since Date: Sep 16, 2012
     * @param p the new row for the table.
     */
    public void addRow(final CampParticipant p) {
      this.participants.put(getRowCount(), p);
      super.addRow(new Object[] { p.getId(),
                                 p.getForeName(),
                                 p.getLastName(),
                                 p.isAGE(),
                                 p.isBoard(),
                                 p.isExtendedBoard(),
                                 p.isKitchen(),
                                 p.isMAK(),
                                 p.isMisc(),
                                 p.isParticipant(),
                                 p.isSeminar(),
                                 p.isStaff(),
                                 p.isStaffYouth() });
    }
  }
}
