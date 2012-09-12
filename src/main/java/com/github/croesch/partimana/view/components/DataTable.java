package com.github.croesch.partimana.view.components;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.github.croesch.partimana.actions.ActionObserver;
import com.github.croesch.partimana.actions.UserAction;
import com.github.croesch.annotate.MayBeNull;
import com.github.croesch.annotate.NotNull;
import com.github.croesch.partimana.view.listener.TableMouseListener;

/**
 * Table that visualizes data. Originally participants or camps.
 * 
 * @author croesch
 * @since Date: Sep 12, 2012
 */
public class DataTable extends JTable {

  /** generated */
  private static final long serialVersionUID = -6694984381634664552L;

  /**
   * Creates a new table.
   * 
   * @since Date: Sep 12, 2012
   * @param o the {@link ActionObserver} that'll be notified on selection changes.
   * @param identifiers the column identifiers
   * @param action the action that'll be send to the observer, when a row is selected (via double click) by the user
   */
  public DataTable(@NotNull final ActionObserver o,
                   @MayBeNull final Object[] identifiers,
                   @NotNull final UserAction action) {
    final DefaultTableModel tm = new DefaultTableModel();
    tm.setColumnIdentifiers(identifiers);
    setModel(tm);
    setAutoCreateRowSorter(true);
    setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tm);
    sorter.setComparator(0, new LongComparator());
    setRowSorter(sorter);

    addMouseListener(new TableMouseListener(o, action));
  }

  @Override
  public final boolean isCellEditable(final int rowIndex, final int colIndex) {
    return false;
  }
}
