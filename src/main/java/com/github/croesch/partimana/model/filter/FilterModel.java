package com.github.croesch.partimana.model.filter;

import java.util.List;

import com.github.croesch.partimana.model.api.IFilter;
import com.github.croesch.partimana.model.api.IFilterModel;
import com.github.croesch.partimana.types.api.IFilterable;

/**
 * The model for filtering {@link IFilterable} objects.
 * 
 * @param <F> the type of the objects this model can filter
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public final class FilterModel<F extends IFilterable> implements IFilterModel<F> {

  /** the group that contains the different filters */
  private final OrGroup<F> filters = new OrGroup<F>();

  /** the list of elements to be filtered */
  private final List<F> originalElements;

  /**
   * Constructs the model for filtering the given {@link IFilterable} objects.
   * 
   * @since Date: Nov 1, 2012
   * @param elements a list of elements that should be filtered
   */
  public FilterModel(final List<F> elements) {
    this.originalElements = elements; //TODO reference
  }

  @Override
  public void and(final IFilter<F> filter) {
    this.filters.add(filter);
  }

  @Override
  public void or(final IFilter<F> filter) {
    final AndGroup<F> group = new AndGroup<F>();
    group.add(filter);
    this.filters.add(group);
  }

  @Override
  public List<F> getFilterMatchingElements() {
    return this.filters.filter(this.originalElements);
  }
}
