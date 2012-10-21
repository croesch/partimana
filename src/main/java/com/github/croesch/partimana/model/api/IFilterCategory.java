package com.github.croesch.partimana.model.api;

import java.util.List;

import com.github.croesch.partimana.types.api.IDescribable;
import com.github.croesch.partimana.types.api.IFilterable;

/**
 * Interface that describes a category to filter. A category is a specific attribute of an {@link IFilterable} element.
 * 
 * @author croesch
 * @param <F> the {@link IFilterable} element that is filtered
 * @param <OT> the type of the objects of this category
 * @since Date: Oct 20, 2012
 */
public interface IFilterCategory<F extends IFilterable, OT> extends IDescribable {

  /**
   * Returns all possible types of filtering this category of the filterable object.
   * 
   * @since Date: Oct 21, 2012
   * @return all possible types of filtering this category of the filterable object.
   */
  List<IFilterType<OT>> getFilterTypes();

  /**
   * Returns the current type of filtering this category of the filterable object.<br>
   * TODO can be null?
   * 
   * @since Date: Oct 21, 2012
   * @return the current type of filtering this category of the filterable object.
   */
  IFilterType<OT> getFilter();

  /**
   * Sets the current type of filtering this category of the filterable object.
   * 
   * @since Date: Oct 21, 2012
   * @param filter the current type of filtering this category of the filterable object.
   */
  void setFilter(IFilterType<OT> filter);

  /**
   * Returns whether the given element matches the filter or should be removed.
   * 
   * @since Date: Oct 21, 2012
   * @param element the object to check if it should be filtered
   * @return <code>false</code> if the filter type, the filter value, the given element or the object of this category
   *         is <code>null</code>,<br>
   *         <code>true</code> if the given element matches the filter,<br>
   *         <code>false</code> otherwise - the given element should not be viewed/added to the result list
   */
  boolean isMatchingFilter(F element);

}
