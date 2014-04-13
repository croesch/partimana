package com.github.croesch.partimana.view;

import com.github.croesch.partimana.actions.ActionObserver;
import com.github.croesch.partimana.model.filter.FilterModel;
import com.github.croesch.partimana.types.api.IFilterable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 * A view to allow users to search the stored data and select <em>a subset</em> out of it.
 *
 * @param <T> the type of the objects to search for
 * @author croesch
 * @since Date: Apr 13, 2014
 */
public abstract class AMultiSelectSearchView<T extends IFilterable> extends ASearchView<T> {
  public AMultiSelectSearchView(final String name, final FilterModel<T> model, final ActionObserver o) {
    super(name, model, o);
    getListView().getTableModel().addTableModelListener(new TableModelListener() {
      @Override
      public void tableChanged(TableModelEvent tableModelEvent) {
        updateSelectButtonState();
      }
    });
  }

  @Override
  protected boolean shouldSelectButtonBeEnabled() {
    return getListView().getTableModel().getRowCount() > 0;
  }
}
