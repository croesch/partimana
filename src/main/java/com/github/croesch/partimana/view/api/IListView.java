package com.github.croesch.partimana.view.api;

import java.util.List;

import javax.swing.JComponent;
import javax.swing.event.ListSelectionListener;

import com.github.croesch.annotate.NotNull;
import com.github.croesch.partimana.types.api.IFilterable;

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
   * @since Date: Nov 10, 2012
   * @return the component representing this list view.
   */
  @NotNull
  JComponent toComponent();

  /**
   * Performs an update of the list view and will visualise the information of the given list.
   * 
   * @since Date: Sep 27, 2011
   * @param elements the list of elements that is up to date.
   */
  void update(List<T> elements);

  /**
   * Returns the id of the selected element.
   * 
   * @since Date: Sep 27, 2011
   * @return id of the element that is currently selected.<br>
   *         <code>0</code>, if no row is selected.
   */
  long getSelectedElementId();

  /**
   * Adds the given listener to listen for table selection changes.
   * 
   * @since Date: Nov 10, 2012
   * @param lst the listener that is notified when table selection changes.
   */
  void addSelectionListener(ListSelectionListener lst);
}
