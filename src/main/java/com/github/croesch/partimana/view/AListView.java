package com.github.croesch.partimana.view;

import java.util.List;

import javax.swing.JComponent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import net.miginfocom.swing.MigLayout;

import com.github.croesch.annotate.NotNull;
import com.github.croesch.components.CPanel;
import com.github.croesch.components.CScrollPane;
import com.github.croesch.components.CTable;
import com.github.croesch.partimana.actions.ActionObserver;
import com.github.croesch.partimana.actions.UserAction;
import com.github.croesch.partimana.types.api.IFilterable;
import com.github.croesch.partimana.view.api.IListView;
import com.github.croesch.partimana.view.components.DataTable;

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

  /** the observer that should be informed when a row of the table is selected. */
  @NotNull
  private final ActionObserver observer;

  /** the action that notifies an observer that a row of the table has been selected */
  @NotNull
  private final UserAction selectionAction;

  /**
   * Constructs the view, including the table that can hold the entries.
   * 
   * @since Date: Nov 11, 2012
   * @param name the name of this view
   * @param nameOfComps the name of the constructed table
   * @param o the observer listening for action events
   * @param tableModel the model of the table
   * @param action the action that should be performed, when a row is selected in the table
   */
  public AListView(final String name,
                   final String nameOfComps,
                   final ActionObserver o,
                   final DefaultTableModel tableModel,
                   final UserAction action) {
    super(name);
    this.observer = o; // TODO null check
    this.selectionAction = action; //TODO null check

    setLayout(new MigLayout("fill"));

    this.table = new DataTable(nameOfComps, o, action, tableModel);

    add(new CScrollPane(nameOfComps, this.table), "grow");
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
   * @since Date: Nov 11, 2012
   * @param element the {@link IFilterable} element to convert to a row
   * @return the object array that represents the row holding the data of the given element
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
   * @since Date: Nov 11, 2012
   * @return the model of the table holding the different elements.
   */
  protected final TableModel getTableModel() {
    return this.table.getModel();
  }

  @Override
  public final UserAction getSelectionAction() {
    return this.selectionAction;
  }
}
