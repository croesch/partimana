package com.github.croesch.partimana.model.filter.types;

/**
 * TODO Comment here ...
 * 
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public class GreaterThan extends AFilterType<Integer> {

  @Override
  public String getShortDescription() {
    return null;
  }

  @Override
  public boolean matches(final Integer object) {
    return object > getFilterValue();
  }

}
