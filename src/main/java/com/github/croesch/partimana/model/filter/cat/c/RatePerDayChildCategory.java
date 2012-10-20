package com.github.croesch.partimana.model.filter.cat.c;

import com.github.croesch.partimana.model.filter.cat.StringCategory;
import com.github.croesch.partimana.types.Camp;

/**
 * TODO Comment here ...
 * 
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public class RatePerDayChildCategory extends StringCategory<Camp> {

  public RatePerDayChildCategory() {
    super(null);
    // TODO Auto-generated constructor stub
  }

  @Override
  protected String getValue(final Camp element) {
    return element.getRatePerDayChildren();
  }

}
