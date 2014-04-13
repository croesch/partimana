package com.github.croesch.partimana.view;

import com.github.croesch.partimana.actions.ActionObserver;
import com.github.croesch.partimana.model.filter.FilterModel;
import com.github.croesch.partimana.types.api.IFilterable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * A view to allow users to search the stored data and select <em>one</em> out of it.
 *
 * @param <T> the type of the objects to search for
 * @author croesch
 * @since Date: Apr 13, 2014
 */
public abstract class ASingleSelectSearchView<T extends IFilterable> extends ASearchView<T> {
  public ASingleSelectSearchView(final String name, final FilterModel<T> model, final ActionObserver o) {
    super(name, model, o);
    getListView().addSelectionListener(new ListSelectionListener() {
      @Override
      public void valueChanged(final ListSelectionEvent e) {
        updateSelectButtonState();
      }
    });
  }

  @Override
  protected boolean shouldSelectButtonBeEnabled() {
    return getListView().getSelectedElementId() != 0;
  }
}
