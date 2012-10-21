package com.github.croesch.partimana.model.filter.types;

import com.github.croesch.partimana.i18n.Text;

/**
 * Filters all strings that are equal (ignoring case) to the given filter value.
 * 
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public final class EqualsIgnoreCase extends AFilterType<String> {

  @Override
  public String getShortDescription() {
    return Text.FILTER_TYPE_EQUALS_IGNORE_CASE.text();
  }

  @Override
  public boolean matches(final String object) {
    return object.equalsIgnoreCase(getFilterValue());
  }
}
