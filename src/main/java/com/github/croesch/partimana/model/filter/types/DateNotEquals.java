package com.github.croesch.partimana.model.filter.types;

import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.util.Util;
import java.util.Date;

/**
 * Filters all objects that are equal to the given filter value.
 *
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public final class DateNotEquals extends DateFilterType {

  @Override
  public String getShortDescription() {
    return Text.FILTER_TYPE_NOT_EQUALS.text();
  }

  @Override
  public boolean matches(final Date object) {
    return !Util.of(object).getDateWithoutTime().equals(getFilterValue());
  }

  @Override
  public DateNotEquals getCopy() {
    final DateNotEquals copy = new DateNotEquals();
    if (getFilterValue() != null) {
      copy.setFilterValue(new Date(getFilterValue().getTime()));
    }
    return copy;
  }
}
