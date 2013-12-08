package com.github.croesch.partimana.model.filter.types;

import com.github.croesch.partimana.i18n.Text;
import java.util.Date;

/**
 * Filters all objects that are equal to the given filter value.
 *
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public final class DateEquals extends DateFilterType {

  @Override
  public String getShortDescription() {
    return Text.FILTER_TYPE_EQUALS.text();
  }

  @Override
  public boolean matches(final Date object) {
    return object.equals(getFilterValue());
  }

  @Override
  public DateEquals getCopy() {
    final DateEquals copy = new DateEquals();
    if (getFilterValue() != null) {
      copy.setFilterValue(new Date(getFilterValue().getTime()));
    }
    return copy;
  }
}
