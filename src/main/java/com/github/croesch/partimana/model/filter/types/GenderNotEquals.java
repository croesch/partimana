package com.github.croesch.partimana.model.filter.types;

import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.types.Gender;

/**
 * Filters all objects that are equal to the given filter value.
 *
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public final class GenderNotEquals extends GenderFilterType {

  @Override
  public String getShortDescription() {
    return Text.FILTER_TYPE_NOT_EQUALS.text();
  }

  @Override
  public boolean matches(final Gender object) {
    return !object.equals(getFilterValue());
  }

  @Override
  public GenderNotEquals getCopy() {
    final GenderNotEquals copy = new GenderNotEquals();
    if (getFilterValue() != null) {
      copy.setFilterValue(getFilterValue());
    }
    return copy;
  }
}
