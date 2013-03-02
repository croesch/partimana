package com.github.croesch.partimana.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import com.github.croesch.partimana.actions.ActionObserver;
import com.github.croesch.partimana.actions.UserAction;
import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.types.CampParticipant;
import com.github.croesch.partimana.types.Role;
import com.github.croesch.partimana.view.api.IListView;

/**
 * Implementation of the table that views the table of participants in a camp.
 * 
 * @author croesch
 * @since Date: Sep 16, 2012
 */
class CampParticipantListView extends AListView<CampParticipant> implements IListView<CampParticipant> {

  /** generated */
  private static final long serialVersionUID = -8804248070325729977L;

  /** the table model that holds the participants - the data of the table */
  private final CampParticipantTableModel tableModel;

  /**
   * Constructs a new {@link CampParticipantListView} that is able to visualise a table of participants. The observer
   * will be notified about selection changes.
   * 
   * @since Date: Sep 16, 2012
   * @param name the name of this component
   * @param o the {@link ActionObserver} that listens for the selection change event.
   */
  public CampParticipantListView(final String name, final ActionObserver o) {
    super(name, "campParticipants", o, new CampParticipantTableModel(), UserAction.CAMP_PARTICIPANT_SELECTED);
    setCellEditor(3, new DefaultCellEditor(new JComboBox(Role.values())));
    this.tableModel = (CampParticipantTableModel) getTableModel();
  }

  @Override
  public void update(final List<CampParticipant> participants) {
    this.tableModel.removeAll();
    for (final CampParticipant p : participants) {
      this.tableModel.addRow(p);
    }
  }

  /**
   * Table model that holds {@link CampParticipant}s as rows.
   * 
   * @author croesch
   * @since Date: Sep 16, 2012
   */
  private static class CampParticipantTableModel extends DefaultTableModel implements TableModelListener {
    /** generated */
    private static final long serialVersionUID = 4361064994767610933L;

    /** the rows of the model */
    private final Map<Integer, CampParticipant> participants = new HashMap<Integer, CampParticipant>();

    /**
     * Creates the table model that holds {@link CampParticipant}s as rows.
     * 
     * @since Date: Sep 16, 2012
     */
    public CampParticipantTableModel() {
      setColumnIdentifiers(new Object[] { Text.PARTICIPANT_ID.text(),
                                         Text.PARTICIPANT_FORENAME.text(),
                                         Text.PARTICIPANT_LASTNAME.text(),
                                         Text.CAMP_PARTICIPANT_ROLE.text() });
      addTableModelListener(this);
    }

    @Override
    public boolean isCellEditable(final int row, final int column) {
      return column == 3;
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
      super.addRow(new Object[] { p.getId(), p.getForeName(), p.getLastName(), p.getRole() });
    }

    @Override
    public void tableChanged(final TableModelEvent e) {
      if (e.getType() == TableModelEvent.UPDATE) {
        if (e.getFirstRow() != e.getLastRow()) {
          System.out.println("oooops!");
        }

        if (e.getColumn() == 3) {
          final CampParticipant participant = this.participants.get(e.getFirstRow());
          final Role value = (Role) getValueAt(e.getFirstRow(), e.getColumn());

          participant.setRole(value);
        }
      }
    }
  }

  @Override
  protected Object[] getRowArray(final CampParticipant element) {
    return null;
  }
}
