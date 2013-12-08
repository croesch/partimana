package com.github.croesch.partimana.model.filter.cat.p;

import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.filter.cat.StringCategory;
import com.github.croesch.partimana.types.Participant;

/**
 * The category that describes the attribute <em>living city</em> of a {@link Participant}.
 *
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public final class LivingCityCategory extends StringCategory<Participant> {

  /**
   * Constructs the category that describes the attribute <em>living city</em> of a {@link Participant}.
   *
   * @since Date: Oct 27, 2012
   */
  public LivingCityCategory() {
    super(Text.FILTER_CAT_PARTICIPANT_LIVING_CITY);
  }

  @Override
  protected String getValue(final Participant element) {
    return element.getCity();
  }

  @Override
  public LivingCityCategory getCopy() {
    final LivingCityCategory copy = new LivingCityCategory();
    if (getFilter() != null) {
      copy.setFilter(getFilter().getCopy());
    }
    return copy;
  }
}
