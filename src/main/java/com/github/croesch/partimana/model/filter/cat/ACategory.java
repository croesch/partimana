package com.github.croesch.partimana.model.filter.cat;

import com.github.croesch.annotate.NotNull;
import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.api.IFilterCategory;
import com.github.croesch.partimana.model.api.IFilterType;
import com.github.croesch.partimana.types.api.IFilterable;

/**
 * Abstract class that describes a category to filter.
 *
 * @param <F>  the {@link IFilterable} element that is filtered
 * @param <OT> the type of the objects of this category
 * @author croesch
 * @since Date: Oct 21, 2012
 */
public abstract class ACategory<F extends IFilterable, OT> implements IFilterCategory<F, OT> {

  /** the current filter type */
  private IFilterType<OT> filter = null;

  /** the viewable description of this category */
  @NotNull
  private final Text description;

  /**
   * Constructs this category with the given viewable description.
   *
   * @param desc the short description that can be shown to the user
   * @since Date: Oct 22, 2012
   */
  protected ACategory(final Text desc) {
    description = desc;
  }

  @Override
  @NotNull
  public final String getShortDescription() {
    return description.text();
  }

  @Override
  public final String toString() {
    return getShortDescription();
  }

  @Override
  public final IFilterType<OT> getFilter() {
    return filter;
  }

  @Override
  public final void setFilter(final IFilterType<OT> f) {
    filter = f;
  }

  /**
   * Returns the object value of this category from the given element.
   *
   * @param element the element that will be filtered
   * @return the object value of this category from the given element.
   * @since Date: Oct 22, 2012
   */
  protected abstract OT getValue(F element);

  @Override
  public final boolean isMatchingFilter(final F element) {
    if (filter == null || element == null) {
      return false;
    }
    return getFilter().isMatching(getValue(element));
  }

  @Override
  public final int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result;
    if (filter != null) {
      result += filter.hashCode();
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
    @SuppressWarnings("rawtypes")
    final ACategory other = (ACategory) obj;
    if (filter == null) {
      if (other.filter != null) {
        return false;
      }
    } else if (!filter.equals(other.filter)) {
      return false;
    }
    return true;
  }
}
