package com.github.croesch.partimana.model.filter.types;

import java.util.Date;

import com.github.croesch.partimana.i18n.Text;

/**
 * Filters all date values that are after the given filter value.
 * 
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public final class After extends DateFilterType {

  @Override
  public String getShortDescription() {
    return Text.FILTER_TYPE_AFTER.text();
  }

  @Override
  public boolean matches(final Date object) {
    return object.after(getFilterValue());
  }

  @Override
  public After getCopy() {
    final After copy = new After();
    if (getFilterValue() != null) {
      copy.setFilterValue(new Date(getFilterValue().getTime()));
    }
    return copy;
  }
}
