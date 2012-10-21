package com.github.croesch.partimana.model.filter.types;

import com.github.croesch.partimana.i18n.Text;

/**
 * Filters all string values that starts with the given filter value.
 * 
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public final class StartsWith extends AFilterType<String> {

  @Override
  public String getShortDescription() {
    return Text.FILTER_TYPE_STARTS_WITH.text();
  }

  @Override
  public boolean matches(final String object) {
    return object.startsWith(getFilterValue());
  }
}
