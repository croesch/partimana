package com.github.croesch.partimana.view;

import com.github.croesch.annotate.NotNull;
import com.github.croesch.components.CPanel;
import com.github.croesch.components.CScrollPane;
import com.github.croesch.components.CTable;
import com.github.croesch.partimana.actions.ActionObserver;
import com.github.croesch.partimana.actions.UserAction;
import com.github.croesch.partimana.types.api.IFilterable;
import com.github.croesch.partimana.view.api.IListView;
import com.github.croesch.partimana.view.components.DataTable;
import com.github.croesch.partimana.view.components.RowNumberTable;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.JComponent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.miginfocom.swing.MigLayout;

/**
 * An abstract view for showing a list of {@link IFilterable} entries in a table.
 *
 * @param <T> the {@link IFilterable} objects the table can hold
 * @author croesch
 * @since Date: Nov 10, 2012
 */
public abstract class AListView<T extends IFilterable> extends CPanel implements IListView<T> {

  /** generated serial version UID */
  private static final long serialVersionUID = -258243373156061504L;

  /** the table to display the different camps */
  @NotNull
  private final CTable table;

  /** the action that notifies an observer that a row of the table has been selected */
  @NotNull
  private final UserAction selectionAction;

  /**
   * Constructs the view, including the table that can hold the entries.
   *
   * @param name           the name of this view
   * @param nameOfComps    the name of the constructed table
   * @param observer       the observer listening for action events
   * @param tableModel     the model of the table
   * @param action         the action that should be performed, when a row is selected in the table
   * @param showRowNumbers <code>true</code>, if the row numbers should be shown
   * @since Date: Nov 11, 2012
   */
  public AListView(final String name,
                   final String nameOfComps,
                   final ActionObserver observer,
                   final DefaultTableModel tableModel,
                   final UserAction action,
                   boolean showRowNumbers) {
    super(name);
    this.selectionAction = action; //TODO null check

    setLayout(new MigLayout("fill"));

    this.table = new DataTable(nameOfComps, observer, action, tableModel);

    CScrollPane scrollPane = new CScrollPane(nameOfComps, this.table);
    if (showRowNumbers) {
      scrollPane.setRowHeaderView(new RowNumberTable(table));
    }
    add(scrollPane, "grow");
  }

  @Override
  public void update(final List<T> elements) {
    while (this.table.getRowCount() > 0) {
      ((DefaultTableModel) this.table.getModel()).removeRow(0);
    }
    for (final T element : elements) {
      ((DefaultTableModel) this.table.getModel()).addRow(getRowArray(element));
    }
  }

  /**
   * Based on the given element this returns an array - each element representing a column value.
   *
   * @param element the {@link IFilterable} element to convert to a row
   * @return the object array that represents the row holding the data of the given element
   * @since Date: Nov 11, 2012
   */
  protected abstract Object[] getRowArray(T element);

  @Override
  public final long getSelectedElementId() {
    if (this.table.getSelectedRowCount() == 0) {
      return 0;
    }
    return Long.parseLong(this.table.getValueAt(this.table.getSelectedRow(), 0).toString());
  }

  @Override
  @NotNull
  public final JComponent toComponent() {
    return this;
  }

  @Override
  public final void addSelectionListener(final ListSelectionListener lst) {
    this.table.getSelectionModel().addListSelectionListener(lst);
  }

  /**
   * Returns the model of the table holding the different elements.
   *
   * @return the model of the table holding the different elements.
   * @since Date: Nov 11, 2012
   */
  protected final TableModel getTableModel() {
    return this.table.getModel();
  }

  /**
   * Sets the given new cell editor for the given cell.
   *
   * @param column     the id of the column that gets the new cell editor
   * @param cellEditor the cell editor
   * @since Date: Mar 2, 2013
   */
  protected final void setCellEditor(final int column, final DefaultCellEditor cellEditor) {
    this.table.getColumnModel().getColumn(column).setCellEditor(cellEditor);
  }

  @Override
  public final UserAction getSelectionAction() {
    return this.selectionAction;
  }
}
