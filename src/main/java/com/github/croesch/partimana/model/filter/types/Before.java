package com.github.croesch.partimana.model.filter.types;

import java.util.Date;

/**
 * TODO Comment here ...
 * 
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public class Before extends AFilterType<Date> {

  @Override
  public String getShortDescription() {
    return null;
  }

  @Override
  public boolean matches(final Date object) {
    // TODO Auto-generated method stub
    return false;
  }

}
