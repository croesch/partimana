package com.github.croesch.partimana.model.filter.types;

import com.github.croesch.partimana.i18n.Text;

/**
 * Filters all objects that are equal to the given filter value.
 * 
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public final class StringEquals extends StringFilterType {

  @Override
  public String getShortDescription() {
    return Text.FILTER_TYPE_EQUALS.text();
  }

  @Override
  public boolean matches(final String object) {
    return object.equals(getFilterValue());
  }

  @Override
  public StringEquals getCopy() {
    final StringEquals copy = new StringEquals();
    if (getFilterValue() != null) {
      copy.setFilterValue(getFilterValue());
    }
    return copy;
  }
}
