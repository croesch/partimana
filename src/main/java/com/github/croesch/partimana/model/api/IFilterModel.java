package com.github.croesch.partimana.model.api;

import com.github.croesch.partimana.types.api.IFilterable;
import java.util.List;

/**
 * A model for filtering {@link IFilterable} objects.
 *
 * @param <F> the type of the objects this model can filter
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public interface IFilterModel<F extends IFilterable> {

  /**
   * Adds the given filter to the list and connects it with an AND operation to all filters since the last OR
   * operation.
   *
   * @param filter the new filter
   * @since Date: Nov 1, 2012
   */
  void and(IFilter<F> filter);

  /**
   * Adds the given filter to the list and connects it with an OR operation to the result of current filtering.
   *
   * @param filter the new filter
   * @since Date: Nov 1, 2012
   */
  void or(IFilter<F> filter);

  /**
   * Returns all objects matching the current filters.
   *
   * @return the <F> objects that match the filter settings
   * @since Date: Nov 1, 2012
   */
  List<F> getFilterMatchingElements();
}
