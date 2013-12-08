package com.github.croesch.partimana.model.filter.types;

import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.types.CountyCouncil;

/**
 * Filters all objects that are equal to the given filter value.
 *
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public final class CountyCouncilEquals extends CountyCouncilFilterType {

  @Override
  public String getShortDescription() {
    return Text.FILTER_TYPE_EQUALS.text();
  }

  @Override
  public boolean matches(final CountyCouncil object) {
    return object.equals(getFilterValue());
  }

  @Override
  public CountyCouncilEquals getCopy() {
    final CountyCouncilEquals copy = new CountyCouncilEquals();
    if (getFilterValue() != null) {
      copy.setFilterValue(getFilterValue());
    }
    return copy;
  }
}
