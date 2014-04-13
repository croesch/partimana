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
   * Sets the given filter as the main filter. This will have an effect on the result returned by {@link
   * #getFilterMatchingElements()}.
   *
   * @param filter the new filter
   * @since Date: Apr 13, 2014
   */
  void setFilter(IFilter<F> filter);

  /**
   * Returns all objects matching the current filters.
   *
   * @return the <F> objects that match the filter settings
   * @since Date: Nov 1, 2012
   */
  List<F> getFilterMatchingElements();
}
