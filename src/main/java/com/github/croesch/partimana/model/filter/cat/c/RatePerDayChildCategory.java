package com.github.croesch.partimana.model.filter.cat.c;

import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.filter.cat.StringCategory;
import com.github.croesch.partimana.types.Camp;

/**
 * The category that describes the attribute <em>rate per day</em> of a {@link Camp}.
 *
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public final class RatePerDayChildCategory extends StringCategory<Camp> {

  /**
   * Constructs the category that describes the attribute <em>name</em> of a {@link Camp}.
   *
   * @since Date: Oct 27, 2012
   */
  public RatePerDayChildCategory() {
    super(Text.FILTER_CAT_CAMP_RATE_PER_DAY);
  }

  @Override
  protected String getValue(final Camp element) {
    return element.getRatePerDayChildren();
  }

  @Override
  public RatePerDayChildCategory getCopy() {
    final RatePerDayChildCategory copy = new RatePerDayChildCategory();
    if (getFilter() != null) {
      copy.setFilter(getFilter().getCopy());
    }
    return copy;
  }
}
