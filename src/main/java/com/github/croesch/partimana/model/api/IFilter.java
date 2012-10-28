package com.github.croesch.partimana.model.api;

import java.util.List;

import com.github.croesch.partimana.types.api.IFilterable;

/**
 * Represents a filter for {@link IFilterable}-objects. Can have different {@link IFilterCategory}s to filter the
 * objects by different columns.
 * 
 * @param <F> the type of {@link IFilterable} this filter filters.
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public interface IFilter<F extends IFilterable> {

  /**
   * Returns all possible {@link IFilterCategory}s this filter can filter by.
   * 
   * @since Date: Oct 28, 2012
   * @return a list of all possible {@link IFilterCategory}s this filter can filter by.
   */
  List<IFilterCategory<F, ?>> getCategories();

  /**
   * Filters the given list of elements and returns the objects from the list that match the current filter settings.
   * 
   * @since Date: Oct 28, 2012
   * @param elements the {@link IFilterable} objects to filter
   * @return the elements from the list that matched the filter settings
   */
  List<F> filter(List<F> elements);

  /**
   * Returns the current {@link IFilterCategory} this filter filters by.
   * 
   * @since Date: Oct 28, 2012
   * @return the current {@link IFilterCategory} this filter filters by.
   */
  IFilterCategory<F, ?> getCategory();

  /**
   * Sets the new {@link IFilterCategory} this filter will filter by.
   * 
   * @since Date: Oct 28, 2012
   * @param cat the new {@link IFilterCategory} this filter will filter by.
   */
  void setCategory(IFilterCategory<F, ?> cat);
}
