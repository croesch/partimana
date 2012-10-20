package com.github.croesch.partimana.model.filter.types;

import com.github.croesch.partimana.model.api.IFilterType;

/**
 * TODO Comment here ...
 * 
 * @author croesch
 * @since Date: Oct 21, 2012
 */
public abstract class AFilterType<OT extends Object> implements IFilterType<OT> {
  @Override
  public void setFilterValue(final OT value) {
    // TODO Auto-generated method stub

  }

  @Override
  public OT getFilterValue() {
    // TODO Auto-generated method stub
    return null;
  }
}
