package com.github.croesch.partimana.model.api;

import com.github.croesch.annotate.MayBeNull;
import com.github.croesch.annotate.NotNull;

/**
 * Interface that defines a filter for a specific data type.
 * 
 * @param <OT> the type of the objects that'll be filtered
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public interface IFilterType<OT extends Object> {

  /**
   * Returns a short description. May be shown to the user.
   * 
   * @since Date: Oct 21, 2012
   * @return the string representation of this filter
   */
  @NotNull
  String getShortDescription();

  /**
   * Sets the value for the filter. Each object to filter will be checked against this value.
   * 
   * @since Date: Oct 21, 2012
   * @param value the object this filter should check objects against - may be null
   */
  void setFilterValue(OT value);

  /**
   * Returns the value of the filter. Each object to filter is checked against this value.
   * 
   * @since Date: Oct 21, 2012
   * @return the object this filter checks objects against
   */
  @MayBeNull
  OT getFilterValue();

  /**
   * Returns whether the given object matches the filter or should be removed.
   * 
   * @since Date: Oct 21, 2012
   * @param object the object to check if it should be filtered, mustn't be <code>null</code>
   * @return <code>true</code> if the given object matches the filter,<br>
   *         <code>false</code> otherwise - the given object should not be viewed/added to the result list
   */
  boolean isMatching(OT object);

}
