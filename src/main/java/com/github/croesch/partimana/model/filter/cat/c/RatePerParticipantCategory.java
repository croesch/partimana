package com.github.croesch.partimana.model.filter.cat.c;

import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.filter.cat.StringCategory;
import com.github.croesch.partimana.types.Camp;

/**
 * The category that describes the attribute <em>rate per participant</em> of a {@link Camp}.
 *
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public final class RatePerParticipantCategory extends StringCategory<Camp> {

  /**
   * Constructs the category that describes the attribute <em>name</em> of a {@link Camp}.
   *
   * @since Date: Oct 27, 2012
   */
  public RatePerParticipantCategory() {
    super(Text.FILTER_CAT_CAMP_RATE_PER_PART);
  }

  @Override
  protected String getValue(final Camp element) {
    return element.getRatePerParticipant();
  }

  @Override
  public RatePerParticipantCategory getCopy() {
    final RatePerParticipantCategory copy = new RatePerParticipantCategory();
    if (getFilter() != null) {
      copy.setFilter(getFilter().getCopy());
    }
    return copy;
  }
}
