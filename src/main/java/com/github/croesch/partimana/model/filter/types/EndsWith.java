package com.github.croesch.partimana.model.filter.types;

import com.github.croesch.partimana.i18n.Text;

/**
 * Filters all string values that ends with the given filter value.
 *
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public final class EndsWith extends StringFilterType {

  @Override
  public String getShortDescription() {
    return Text.FILTER_TYPE_ENDS_WITH.text();
  }

  @Override
  public boolean matches(final String object) {
    return object.endsWith(getFilterValue());
  }

  @Override
  public EndsWith getCopy() {
    final EndsWith copy = new EndsWith();
    if (getFilterValue() != null) {
      copy.setFilterValue(getFilterValue());
    }
    return copy;
  }
}
