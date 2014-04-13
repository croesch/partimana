package com.github.croesch.partimana.view.api;

import com.github.croesch.annotate.NotNull;
import com.github.croesch.partimana.actions.UserAction;
import com.github.croesch.partimana.types.api.IFilterable;
import java.util.Collection;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.event.ListSelectionListener;

/**
 * Interface for the list view that defines methods that need to be implemented.
 *
 * @param <T> the type of the elements this list can view
 * @author croesch
 * @since Date: Sep 27, 2011
 */
public interface IListView<T extends IFilterable> {

  /**
   * Returns the component representing this list view.
   *
   * @return the component representing this list view.
   * @since Date: Nov 10, 2012
   */
  @NotNull
  JComponent toComponent();

  /**
   * Performs an update of the list view and will visualise the information of the given list.
   *
   * @param elements the list of elements that is up to date.
   * @since Date: Sep 27, 2011
   */
  void update(List<T> elements);

  /**
   * @return id of the element that is currently selected.<br> <code>0</code>, if no row is selected.
   * @since Date: Sep 27, 2011
   */
  long getSelectedElementId();

  /**
   * @return ids of all elements contained in this view.
   * @since Date: Apr 13, 2014
   */
  @NotNull
  Collection<Long> getElementIds();

  /**
   * Adds the given listener to listen for table selection changes.
   *
   * @param lst the listener that is notified when table selection changes.
   * @since Date: Nov 10, 2012
   */
  void addSelectionListener(ListSelectionListener lst);

  /**
   * Returns the action that notifies the observer that one row has been selected.
   *
   * @return the action that notifies the observer that one row has been selected.
   * @since Date: Nov 11, 2012
   */
  UserAction getSelectionAction();
}
