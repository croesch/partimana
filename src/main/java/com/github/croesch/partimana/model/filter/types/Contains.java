package com.github.croesch.partimana.model.filter.types;

/**
 * TODO Comment here ...
 * 
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public class Contains extends AFilterType<String> {

  @Override
  public String getShortDescription() {
    return null;
  }

  @Override
  public boolean matches(final String object) {
    return object.contains(getFilterValue());
  }

}
