package com.github.croesch.partimana.model.api;

import com.github.croesch.annotate.NotNull;
import com.github.croesch.partimana.types.api.IDescribable;
import com.github.croesch.partimana.types.api.IFilterable;
import java.util.List;

/**
 * A group that is able to group other groups or filters.
 *
 * @param <F> the type of the objects the filters this group combines can filter
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public interface IFilterGroup<F extends IFilterable> extends IDescribable {

  /**
   * Adds the given filter to the group. Doesn't check, if the filter is already contained.
   *
   * @param filter the filter to be added to the group. Each time the group should filter some values this filter is
   *               invoked, if necessary.
   * @since Date: Oct 30, 2012
   */
  void add(final IFilter<F> filter);

  /**
   * Adds the given group into the group. Doesn't check, if the given group is already contained.
   *
   * @param filterGroup the group to be added to the group. Each time the group should filter some values this group is
   *                    invoked to filter the elements, if necessary.
   * @since Date: Oct 30, 2012
   */
  void add(final IFilterGroup<F> filterGroup);

  /**
   * Returns a list of all filters currently contained in that group. <b>Note:</b> This method does not necessarily
   * return any filter contained in a group inside that group. Use {@link #getFilterGroups()} to find all filters inside
   * the groups.
   *
   * @return a list of all filters currently contained in that group.
   * @since Date: Oct 30, 2012
   */
  List<IFilter<F>> getFilters();

  /**
   * Returns a list of all groups currently contained in that group.
   *
   * @return a list of all groups currently contained in that group.
   * @since Date: Oct 30, 2012
   */
  @NotNull
  List<IFilterGroup<F>> getFilterGroups();

  /**
   * Filters the given list of elements and returns the objects from the list that match the current filters and groups
   * inside this group.
   *
   * @param elements the {@link IFilterable} objects to filter
   * @return the elements from the list that matched the filter settings of the group
   * @since Date: Oct 30, 2012
   */
  List<F> filter(List<F> elements);
}
