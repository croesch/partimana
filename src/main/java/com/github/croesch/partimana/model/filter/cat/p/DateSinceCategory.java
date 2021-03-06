package com.github.croesch.partimana.model.filter.cat.p;

import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.filter.cat.DateCategory;
import com.github.croesch.partimana.types.Participant;
import java.util.Date;

/**
 * The category that describes the attribute <em>date since in db</em> of a {@link Participant}.
 *
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public final class DateSinceCategory extends DateCategory<Participant> {

  /**
   * Constructs the category that describes the attribute <em>date since in db</em> of a {@link Participant}.
   *
   * @since Date: Oct 27, 2012
   */
  public DateSinceCategory() {
    super(Text.FILTER_CAT_PARTICIPANT_DATE_SINCE);
  }

  @Override
  protected Date getValue(final Participant element) {
    return element.getDateSinceInDataBase();
  }

  @Override
  public DateSinceCategory getCopy() {
    final DateSinceCategory copy = new DateSinceCategory();
    if (getFilter() != null) {
      copy.setFilter(getFilter().getCopy());
    }
    return copy;
  }
}
