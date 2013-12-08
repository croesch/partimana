package com.github.croesch.partimana.model.filter;

import com.github.croesch.annotate.NotNull;
import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.api.IFilterGroup;
import com.github.croesch.partimana.types.api.IFilterable;
import java.util.ArrayList;
import java.util.List;

/**
 * A group that is able to group other groups or filters.
 *
 * @param <F> the type of the objects the filters this group combines can filter
 * @author croesch
 * @since Date: Oct 20, 2012
 */
abstract class AFilterGroup<F extends IFilterable> implements IFilterGroup<F> {

  /** list of groups inside that group */
  @NotNull
  private final List<IFilterGroup<F>> filterGroups = new ArrayList<IFilterGroup<F>>();

  /** the i18n text that describes this group to the user */
  private final Text description;

  /**
   * Constructs a new group with the given description.
   *
   * @param descr the i18n value that describes this group
   * @since Date: Oct 30, 2012
   */
  public AFilterGroup(final Text descr) {
    this.description = descr;
  }

  @Override
  public final String getShortDescription() {
    return this.description.text();
  }

  @Override
  public final void add(final IFilterGroup<F> filterGroup) {
    this.filterGroups.add(filterGroup);
  }

  @Override
  @NotNull
  public final List<IFilterGroup<F>> getFilterGroups() {
    return this.filterGroups;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + this.filterGroups.hashCode();
    return result;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof AFilterGroup)) {
      return false;
    }
    @SuppressWarnings("rawtypes")
    final AFilterGroup other = (AFilterGroup) obj;
    if (!this.filterGroups.equals(other.filterGroups)) {
      return false;
    }
    if (getFilters() == null) {
      if (other.getFilters() != null) {
        return false;
      }
    } else if (!getFilters().equals(other.getFilters())) {
      return false;
    }
    return true;
  }
}
