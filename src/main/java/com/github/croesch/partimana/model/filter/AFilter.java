package com.github.croesch.partimana.model.filter;

import java.util.ArrayList;
import java.util.List;

import com.github.croesch.partimana.model.api.IFilter;
import com.github.croesch.partimana.model.api.IFilterCategory;
import com.github.croesch.partimana.types.api.IFilterable;

/**
 * TODO Comment here ...
 * 
 * @author croesch
 * @since Date: Oct 21, 2012
 */
public abstract class AFilter<F extends IFilterable> implements IFilter<F> {

  private IFilterCategory<F, ?> category = null;

  @Override
  public List<F> filter(final List<F> elements) {
    final List<F> result = new ArrayList<F>();
    for (final F element : elements) {
      if (this.category.isMatchingFilter(element)) {
        result.add(element);
      }
    }
    return result;
  }

  @Override
  public IFilterCategory<F, ?> getCategory() {
    return this.category;
  }

  @Override
  public void setCategory(final IFilterCategory<F, ?> cat) {
    this.category = cat;
  }
}
