package com.github.croesch.partimana.view.components;

import com.github.croesch.components.CTable;
import com.github.croesch.partimana.actions.ActionObserver;
import com.github.croesch.partimana.actions.UserAction;
import com.github.croesch.partimana.view.listener.TableMouseListener;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 * Table that visualizes data. Originally participants or camps.
 *
 * @author croesch
 * @since Date: Sep 12, 2012
 */
public class DataTable extends CTable {

  /** generated */
  private static final long serialVersionUID = -6694984381634664552L;

  /**
   * Creates a new table.
   *
   * @param name        the name of this table
   * @param o           the {@link ActionObserver} that will be notified on selection changes.
   * @param identifiers the column identifiers
   * @param action      the action that will be send to the observer, when a row is selected (via double click) by the
   *                    user
   * @since Date: Sep 12, 2012
   */
  public DataTable(final String name, final ActionObserver o, final Object[] identifiers, final UserAction action) {
    this(name, o, identifiers, action, null);
  }

  /**
   * Creates a new table.
   *
   * @param name   the name of this table
   * @param o      the {@link ActionObserver} that will be notified on selection changes.
   * @param model  the table model
   * @param action the action that will be send to the observer, when a row is selected (via double click) by the user
   * @since Date: Sep 12, 2012
   */
  public DataTable(final String name, final ActionObserver o, final UserAction action, final TableModel model) {
    this(name, o, null, action, model);
  }

  /**
   * Creates a new table.
   *
   * @param name        the name of this table
   * @param o           the {@link ActionObserver} that will be notified on selection changes.
   * @param identifiers the column identifiers
   * @param action      the action that will be send to the observer, when a row is selected (via double click) by the
   *                    user
   * @param model       the table model
   * @since Date: Sep 12, 2012
   */
  private DataTable(final String name,
                    final ActionObserver o,
                    final Object[] identifiers,
                    final UserAction action,
                    final TableModel model) {
    super(name);

    if (model == null) {
      final DefaultTableModel tm = new DefaultTableModel() {
        /** generated */
        private static final long serialVersionUID = 619183013847304143L;

        @Override
        public boolean isCellEditable(final int row, final int column) {
          return false;
        }
      };
      tm.setColumnIdentifiers(identifiers);
      setModel(tm);
    } else {
      setModel(model);
    }

    setAutoCreateRowSorter(true);
    setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(getModel());
    sorter.setComparator(0, new LongComparator());
    sorter.toggleSortOrder(0);
    setRowSorter(sorter);

    addMouseListener(new TableMouseListener(o, action));
  }
}
