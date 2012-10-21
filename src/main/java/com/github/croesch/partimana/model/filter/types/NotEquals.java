package com.github.croesch.partimana.model.filter.types;

/**
 * TODO Comment here ...
 * 
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public class NotEquals<OT extends Object> extends AFilterType<OT> {

  @Override
  public String getShortDescription() {
    return null;
  }

  @Override
  public boolean matches(final OT object) {
    return !object.equals(getFilterValue());
  }

}