package com.github.croesch.partimana.model.filter.types;

import com.github.croesch.partimana.i18n.Text;

/**
 * Filters all objects that are equal to the given filter value.
 * 
 * @param <OT> the type of objects to filter
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public final class Equals<OT extends Object> extends AFilterType<OT> {

  @Override
  public String getShortDescription() {
    return Text.FILTER_TYPE_EQUALS.text();
  }

  @Override
  public boolean matches(final OT object) {
    return object.equals(getFilterValue());
  }
}
