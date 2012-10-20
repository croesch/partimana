package com.github.croesch.partimana.model.filter;

import java.util.ArrayList;
import java.util.List;

import com.github.croesch.partimana.model.api.IFilter;
import com.github.croesch.partimana.model.api.IFilterGroup;
import com.github.croesch.partimana.types.api.IFilterable;

/**
 * TODO Comment here ...
 * 
 * @author croesch
 * @since Date: Oct 20, 2012
 */
class AndGroup<F extends IFilterable> extends AFilterGroup<F> {

  private final List<IFilter<F>> filters = new ArrayList<IFilter<F>>();

  @Override
  public String getShortDescription() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void add(final IFilter<F> filter) {
    this.filters.add(filter);
  }

  @Override
  public List<IFilter<F>> getFilters() {
    return this.filters;
  }

  @Override
  public List<F> filter(final List<F> elements) {
    List<F> result = new ArrayList<F>(elements);
    for (final IFilterGroup<F> group : getFilterGroups()) {
      result = intersect(result, group.filter(elements));
    }
    for (final IFilter<F> filter : getFilters()) {
      result = intersect(result, filter.filter(elements));
    }
    return result;
  }

  // TODO to utils
  private List<F> intersect(final List<F> one, final List<F> two) {
    final List<F> result = new ArrayList<F>();
    for (final F element : two) {
      if (one.contains(element)) {
        result.add(element);
      }
    }
    return result;
  }
}
