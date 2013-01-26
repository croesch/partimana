package com.github.croesch.partimana.model.filter.cat.p;

import java.util.Date;

import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.filter.cat.DateCategory;
import com.github.croesch.partimana.types.Participant;

/**
 * The category that describes the attribute <em>date up to in db</em> of a {@link Participant}.
 * 
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public final class DateUntilCategory extends DateCategory<Participant> {

  /**
   * Constructs the category that describes the attribute <em>date up to in db</em> of a {@link Participant}.
   * 
   * @since Date: Oct 27, 2012
   */
  public DateUntilCategory() {
    super(Text.FILTER_CAT_PARTICIPANT_DATE_UNTIL);
  }

  @Override
  protected Date getValue(final Participant element) {
    return element.getDateUpToInSystem();
  }

  @Override
  public DateUntilCategory getCopy() {
    final DateUntilCategory copy = new DateUntilCategory();
    if (getFilter() != null) {
      copy.setFilter(getFilter().getCopy());
    }
    return copy;
  }
}
