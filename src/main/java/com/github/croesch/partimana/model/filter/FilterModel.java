package com.github.croesch.partimana.model.filter;

import com.github.croesch.partimana.model.api.IFilter;
import com.github.croesch.partimana.model.api.IFilterModel;
import com.github.croesch.partimana.types.api.IFilterable;
import java.util.ArrayList;
import java.util.List;

/**
 * The model for filtering {@link IFilterable} objects. This can be used to connect more than one filter with each
 * other. Currently it's just used to filter with one filter at a time.
 *
 * @param <F> the type of the objects this model can filter
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public final class FilterModel<F extends IFilterable> implements IFilterModel<F> {

  /** the filter this model filters the elements currently with. */
  private IFilter<F> filter;

  /** the list of elements to be filtered */
  private final List<F> originalElements;

  /**
   * Constructs the model for filtering the given {@link IFilterable} objects.
   *
   * @param elements a list of elements that should be filtered
   * @since Date: Nov 1, 2012
   */
  public FilterModel(final List<F> elements) {
    this.originalElements = elements; //TODO reference
  }

  @Override
  public void setFilter(IFilter<F> filter) {
    this.filter = filter;
  }

  @Override
  public List<F> getFilterMatchingElements() {
    if (filter == null) {
      return new ArrayList<F>(originalElements);
    }
    return this.filter.filter(this.originalElements);
  }
}
