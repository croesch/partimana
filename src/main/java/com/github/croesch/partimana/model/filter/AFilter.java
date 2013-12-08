package com.github.croesch.partimana.model.filter;

import com.github.croesch.partimana.model.api.IFilter;
import com.github.croesch.partimana.model.api.IFilterCategory;
import com.github.croesch.partimana.types.api.IFilterable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a generic filter for {@link IFilterable}-objects. Can have different {@link IFilterCategory}s to filter
 * the objects by different columns.
 *
 * @param <F> the type of {@link IFilterable} this filter filters.
 * @author croesch
 * @since Date: Oct 21, 2012
 */
public abstract class AFilter<F extends IFilterable> implements IFilter<F> {

  /** the category - a specific attribute of the {@link IFilterable} - the objects will be filtered by */
  private IFilterCategory<F, ?> category = null;

  @Override
  public final List<F> filter(final List<F> elements) {
    final List<F> result = new ArrayList<F>();

    if (this.category != null) {
      for (final F element : elements) {
        if (this.category.isMatchingFilter(element)) {
          result.add(element);
        }
      }
    }

    return result;
  }

  @Override
  public final IFilterCategory<F, ?> getCategory() {
    return this.category;
  }

  @Override
  public final void setCategory(final IFilterCategory<F, ?> cat) {
    this.category = cat;
  }

  @Override
  public final int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result;
    if (this.category != null) {
      result += this.category.hashCode();
    }

    return result;
  }

  @Override
  public final boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof AFilter)) {
      return false;
    }
    @SuppressWarnings("rawtypes")
    final AFilter other = (AFilter) obj;
    if (this.category == null) {
      if (other.category != null) {
        return false;
      }
    } else if (!this.category.equals(other.category)) {
      return false;
    }
    return true;
  }
}
