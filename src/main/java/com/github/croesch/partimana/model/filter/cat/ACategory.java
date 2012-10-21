package com.github.croesch.partimana.model.filter.cat;

import com.github.croesch.partimana.model.api.IFilterCategory;
import com.github.croesch.partimana.model.api.IFilterType;
import com.github.croesch.partimana.types.api.IFilterable;

/**
 * Abstract class that describes a category to filter.
 * 
 * @author croesch
 * @param <F> the {@link IFilterable} element that is filtered
 * @param <OT> the type of the objects of this category
 * @since Date: Oct 21, 2012
 */
public abstract class ACategory<F extends IFilterable, OT extends Object> implements IFilterCategory<F, OT> {

  /** the current filter type */
  private IFilterType<OT> filter = null;

  /** the viewable description of this category */
  private final String description;

  /**
   * Constructs this category with the given viewable description.
   * 
   * @since Date: Oct 22, 2012
   * @param descr the short description that can be shown to the user
   */
  protected ACategory(final String descr) {
    this.description = descr;
  }

  @Override
  public final String getShortDescription() {
    return this.description;
  }

  @Override
  public final IFilterType<OT> getFilter() {
    return this.filter;
  }

  @Override
  public final void setFilter(final IFilterType<OT> f) {
    this.filter = f;
  }

  /**
   * Returns the object value of this category from the given element.
   * 
   * @since Date: Oct 22, 2012
   * @param element the element that'll be filtered
   * @return the object value of this category from the given element.
   */
  protected abstract OT getValue(F element);

  @Override
  public final boolean isMatchingFilter(final F element) {
    if (this.filter == null || element == null) {
      return false;
    }
    return getFilter().isMatching(getValue(element));
  }
}
