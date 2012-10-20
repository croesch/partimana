package com.github.croesch.partimana.model.filter.cat.c;

import java.util.Date;

import com.github.croesch.partimana.model.filter.cat.DateCategory;
import com.github.croesch.partimana.types.Camp;

/**
 * TODO Comment here ...
 * 
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public class UntilCategory extends DateCategory<Camp> {

  public UntilCategory() {
    super(null);
    // TODO Auto-generated constructor stub
  }

  @Override
  protected Date getValue(final Camp element) {
    return element.getUntilDate();
  }

}
