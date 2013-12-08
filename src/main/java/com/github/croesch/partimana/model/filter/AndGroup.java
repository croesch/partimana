package com.github.croesch.partimana.model.filter;

import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.api.IFilter;
import com.github.croesch.partimana.model.api.IFilterGroup;
import com.github.croesch.partimana.types.api.IFilterable;
import java.util.ArrayList;
import java.util.List;

/**
 * A group that is able to group other groups or filters. When filtering the result of the different groups and filters
 * are combined with an AND operation.
 *
 * @param <F> the type of the objects the filters this group combines can filter
 * @author croesch
 * @since Date: Oct 20, 2012
 */
class AndGroup<F extends IFilterable> extends AFilterGroup<F> {

  /** the single filters that are grouped via AND operation */
  private final List<IFilter<F>> filters = new ArrayList<IFilter<F>>();

  /**
   * Constructs this group that is able to group other groups or filters. When filtering the result of the different
   * groups and filters are combined with an AND operation.
   *
   * @since Date: Oct 30, 2012
   */
  public AndGroup() {
    super(Text.FILTER_AND);
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

  /**
   * Returns a list containing all elements that are contained in both of the given lists. So to say returns the
   * intersection of both given lists.<br> TODO to utils
   *
   * @param one first list to intersect with the other
   * @param two second list to intersect with the other one
   * @return the intersection of elements of both lists
   * @since Date: Oct 30, 2012
   */
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
