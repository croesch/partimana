package com.github.croesch.partimana.model.filter.types;

import com.github.croesch.partimana.i18n.Text;

/**
 * Filters all objects that are equal to the given filter value.
 * 
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public final class IntegerEquals extends IntegerFilterType {

  @Override
  public String getShortDescription() {
    return Text.FILTER_TYPE_EQUALS.text();
  }

  @Override
  public boolean matches(final Integer object) {
    return object.equals(getFilterValue());
  }

  @Override
  public IntegerEquals getCopy() {
    final IntegerEquals copy = new IntegerEquals();
    if (getFilterValue() != null) {
      copy.setFilterValue(getFilterValue());
    }
    return copy;
  }
}
