package com.github.croesch.partimana.model.filter.types;

import com.github.croesch.partimana.i18n.Text;

/**
 * Filters all integers that are less than the given filter value.
 *
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public final class LessThan extends IntegerFilterType {

  @Override
  public String getShortDescription() {
    return Text.FILTER_TYPE_LESS_THAN.text();
  }

  @Override
  public boolean matches(final Integer object) {
    return object < getFilterValue();
  }

  @Override
  public LessThan getCopy() {
    final LessThan copy = new LessThan();
    if (getFilterValue() != null) {
      copy.setFilterValue(getFilterValue());
    }
    return copy;
  }
}
