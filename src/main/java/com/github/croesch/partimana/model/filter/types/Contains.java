package com.github.croesch.partimana.model.filter.types;

import com.github.croesch.partimana.i18n.Text;

/**
 * Filters all string values that contain the given filter value.
 * 
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public final class Contains extends StringFilterType {

  @Override
  public String getShortDescription() {
    return Text.FILTER_TYPE_CONTAINS.text();
  }

  @Override
  public boolean matches(final String object) {
    return object.contains(getFilterValue());
  }
}
