package com.github.croesch.partimana.model.filter.cat.c;

import java.util.Date;

import com.github.croesch.partimana.model.filter.cat.DateCategory;
import com.github.croesch.partimana.types.Camp;

/**
 * The category that describes the attribute <em>from</em> of a {@link Camp}.
 * 
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public final class FromCategory extends DateCategory<Camp> {

  public FromCategory() {
    super(null);
  }

  @Override
  protected Date getValue(final Camp element) {
    return element.getFromDate();
  }
}
