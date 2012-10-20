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
class OrGroup<F extends IFilterable> extends AFilterGroup<F> {

  @Override
  public List<F> filter(final List<F> originalElements) {
    final List<F> elements = new ArrayList<F>();
    for (final IFilterGroup<F> group : getFilterGroups()) {
      for (final F element : group.filter(originalElements)) {
        if (!elements.contains(element)) {
          elements.add(element);
        }
      }
    }
    return elements;
  }

  @Override
  public String getShortDescription() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void add(final IFilter<F> filter) {
    if (getFilterGroups() == null || getFilterGroups().size() == 0) {
      add(new AndGroup<F>());
    }
    getFilterGroups().get(getFilterGroups().size() - 1).add(filter);
  }

  @Override
  public List<IFilter<F>> getFilters() {
    return null;
  }
}
