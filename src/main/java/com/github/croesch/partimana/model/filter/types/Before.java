package com.github.croesch.partimana.model.filter.types;

import java.util.Date;

import com.github.croesch.partimana.i18n.Text;

/**
 * Filters all date values that are before the given filter value.
 * 
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public final class Before extends AFilterType<Date> {

  @Override
  public String getShortDescription() {
    return Text.FILTER_TYPE_BEFORE.text();
  }

  @Override
  public boolean matches(final Date object) {
    return object.before(getFilterValue());
  }
}
