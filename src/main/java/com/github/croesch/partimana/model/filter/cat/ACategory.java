package com.github.croesch.partimana.model.filter.cat;

import com.github.croesch.partimana.model.api.IFilterCategory;
import com.github.croesch.partimana.model.api.IFilterType;
import com.github.croesch.partimana.types.api.IFilterable;

/**
 * TODO Comment here ...
 * 
 * @author croesch
 * @since Date: Oct 21, 2012
 */
public abstract class ACategory<F extends IFilterable, OT extends Object> implements IFilterCategory<F, OT> {

  private IFilterType<OT> filter = null;

  @Override
  public IFilterType<OT> getFilter() {
    return this.filter;
  }

  @Override
  public void setFilter(final IFilterType<OT> f) {
    this.filter = f;
  }

  protected abstract OT getValue(F element);

  @Override
  public boolean isMatchingFilter(final F element) {
    return getFilter().matches(getValue(element));
  }
}
