package com.github.croesch.partimana.view;

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
import com.github.croesch.partimana.types.Camp;
import com.github.croesch.partimana.view.api.ICampListView;
import com.github.croesch.partimana.view.listener.TableMouseListener;

/**
 * Implementation of the table that views the table of camps.
 * 
 * @author croesch
 * @since Date: Jun 8, 2011
 */
class CampListView extends JPanel implements ICampListView {

  /** generated */
  private static final long serialVersionUID = -96888415800702415L;

  /** the table to display the different camps */
  private final JTable table;

  /**
   * Constructs a new {@link CampListView} that is able to visualise a table of camps. The observer will be notified
   * about selection changes.
   * 
   * @since Date: Sep 27, 2011
   * @param o the {@link ActionObserver} that listens for the selection change event.
   */
  public CampListView(final ActionObserver o) {
    setLayout(new MigLayout("fill"));

    this.table = new CampTable(o);

    add(new JScrollPane(this.table), "grow");
  }

  @Override
  public void update(final List<Camp> camps) {
    while (this.table.getRowCount() > 0) {
      ((DefaultTableModel) this.table.getModel()).removeRow(0);
    }
    for (final Camp c : camps) {
      ((DefaultTableModel) this.table.getModel()).addRow(new Object[] { c.getId(), c.getLocation(), c.getName() });
    }
  }

  @Override
  public long getSelectedCampId() {
    if (this.table.getSelectedRowCount() == 0) {
      return 0;
    }
    return Long.parseLong(this.table.getValueAt(this.table.getSelectedRow(), 0).toString());
  }

  /**
   * Table that displays a set of camps.
   * 
   * @author croesch
   * @since Date: Sep 27, 2011
   */
  private static class CampTable extends JTable {

    /** generated */
    private static final long serialVersionUID = -6694984381634664552L;

    /**
     * Creates a new table of camps.
     * 
     * @since Date: Sep 27, 2011
     * @param o the {@link ActionObserver} that'll be notified on selection changes.
     */
    public CampTable(final ActionObserver o) {
      final DefaultTableModel tm = new DefaultTableModel();
      tm.setColumnIdentifiers(new Object[] { Text.CAMP_ID.text(), Text.CAMP_LOCATION.text(), Text.CAMP_NAME.text() });
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

      addMouseListener(new TableMouseListener(o, UserAction.CAMP_SELECTED));
    }

    @Override
    public boolean isCellEditable(final int rowIndex, final int colIndex) {
      return false;
    }
  }
}
