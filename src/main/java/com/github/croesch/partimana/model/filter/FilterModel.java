package com.github.croesch.partimana.model.filter;

import java.util.List;

import com.github.croesch.partimana.model.api.IFilter;
import com.github.croesch.partimana.model.api.IFilterModel;
import com.github.croesch.partimana.types.api.IFilterable;

/**
 * TODO Comment here ...
 * 
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public class FilterModel<F extends IFilterable> implements IFilterModel<F> {

  private final OrGroup<F> filters = new OrGroup<F>();

  private final List<F> originalElements;

  public FilterModel(final List<F> elements) {
    this.originalElements = elements;//TODO reference
  }

  @Override
  public List<IFilter<F>> getFilter() {
    return this.filters.getFilters();
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
