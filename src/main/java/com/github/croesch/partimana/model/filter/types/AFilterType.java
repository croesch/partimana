package com.github.croesch.partimana.model.filter.types;

import com.github.croesch.partimana.model.api.IFilterType;

/**
 * TODO Comment here ...
 * 
 * @author croesch
 * @since Date: Oct 21, 2012
 */
public abstract class AFilterType<OT extends Object> implements IFilterType<OT> {

  private OT filterValue = null;

  @Override
  public void setFilterValue(final OT value) {
    this.filterValue = value;
  }

  @Override
  public OT getFilterValue() {
    return this.filterValue;
  }
}
