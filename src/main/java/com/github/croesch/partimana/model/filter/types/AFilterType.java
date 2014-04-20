package com.github.croesch.partimana.model.filter.types;

import com.github.croesch.annotate.MayBeNull;
import com.github.croesch.partimana.model.api.IFilterType;

/**
 * Abstract filter that filters objects based on the filter value.
 *
 * @param <OT> the type of the objects to filter
 * @author croesch
 * @since Date: Oct 21, 2012
 */
public abstract class AFilterType<OT> implements IFilterType<OT> {

  /** the value this filter is filtering */
  @MayBeNull
  private OT filterValue = null;

  @Override
  public final String toString() {
    return getShortDescription();
  }

  @Override
  public final void setFilterValue(final OT value) {
    filterValue = value;
  }

  @Override
  public final OT getFilterValue() {
    return filterValue;
  }

  @Override
  public final boolean isMatching(final OT object) {
    if (getFilterValue() == null || object == null) {
      return false;
    }
    return matches(object);
  }

  /**
   * Returns whether the given object matches the filter or should be removed.
   *
   * @param object the object to check if it should be filtered, mustn't be <code>null</code>
   * @return <code>true</code> if the given object matches the filter,<br> <code>false</code> otherwise - the given
   * object should not be viewed/added to the result list
   * @since Date: Oct 21, 2012
   */
  protected abstract boolean matches(OT object);

  @Override
  public final int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result;
    if (filterValue != null) {
      result += filterValue.hashCode();
    }
    return result;
  }

  @Override
  public final boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }

    final AFilterType<?> other = (AFilterType<?>) obj;
    if (filterValue == null) {
      if (other.filterValue != null) {
        return false;
      }
    } else if (!filterValue.equals(other.filterValue)) {
      return false;
    }
    return true;
  }
}
