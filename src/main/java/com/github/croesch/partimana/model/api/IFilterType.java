package com.github.croesch.partimana.model.api;

import com.github.croesch.annotate.MayBeNull;
import com.github.croesch.partimana.types.api.IDescribable;
import javax.swing.JComponent;

/**
 * Interface that defines a filter for a specific data type.
 *
 * @param <OT> the type of the objects that will be filtered
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public interface IFilterType<OT> extends IDescribable {

  /**
   * Sets the value for the filter. Each object to filter will be checked against this value.
   *
   * @param value the object this filter should check objects against - may be null
   * @since Date: Oct 21, 2012
   */
  void setFilterValue(OT value);

  /**
   * Parses and sets the value for the filter. Each object to filter will be checked against this value. Returns whether
   * the given value could be parsed to a valid value.
   *
   * @param field the value this filter should filter by
   * @return <code>true</code>, if the given value could be parsed to a valid value,<br> or <code>false</code> otherwise
   * @since Date: Oct 21, 2012
   */
  boolean parseFilterValue(JComponent field);

  /**
   * Returns the value of the filter. Each object to filter is checked against this value.
   *
   * @return the object this filter checks objects against
   * @since Date: Oct 21, 2012
   */
  @MayBeNull
  OT getFilterValue();

  /**
   * Returns whether the given object matches the filter or should be removed.
   *
   * @param object the object to check if it should be filtered
   * @return <code>false</code> if the filter value or the given object is <code>null</code>,<br> <code>true</code> if
   * the given object matches the filter,<br> <code>false</code> otherwise - the given object should not be viewed/added
   * to the result list
   * @since Date: Oct 21, 2012
   */
  boolean isMatching(OT object);

  /**
   * Returns a copy of this filter type. It is equal to this filter type, but doesn't share references.
   *
   * @return a copy of this filter type.
   * @since Date: Jan 26, 2013
   */
  IFilterType<OT> getCopy();
}
