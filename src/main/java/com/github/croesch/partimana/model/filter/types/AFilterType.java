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
public abstract class AFilterType<OT extends Object> implements IFilterType<OT> {

  /** the value this filter is filtering */
  @MayBeNull
  private OT filterValue = null;

  @Override
  public final void setFilterValue(final OT value) {
    this.filterValue = value;
  }

  @Override
  public final OT getFilterValue() {
    return this.filterValue;
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
   * @since Date: Oct 21, 2012
   * @param object the object to check if it should be filtered, mustn't be <code>null</code>
   * @return <code>true</code> if the given object matches the filter,<br>
   *         <code>false</code> otherwise - the given object should not be viewed/added to the result list
   */
  protected abstract boolean matches(OT object);
}