package com.github.croesch.partimana.model.filter;

import java.util.ArrayList;
import java.util.List;

import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.api.IFilter;
import com.github.croesch.partimana.model.api.IFilterGroup;
import com.github.croesch.partimana.types.api.IFilterable;

/**
 * A group that is able to group other groups or filters. When filtering the result of the different groups are combined
 * with an OR operation.
 * 
 * @param <F> the type of the objects the filters this group combines can filter
 * @author croesch
 * @since Date: Oct 20, 2012
 */
class OrGroup<F extends IFilterable> extends AFilterGroup<F> {

  /**
   * Constructs this group that is able to group other groups or filters. When filtering the result of the different
   * groups are combined with an OR operation.
   * 
   * @since Date: Nov 1, 2012
   */
  public OrGroup() {
    super(Text.FILTER_OR);
  }

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
