package com.github.croesch.partimana.model.filter.cat.p;

import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.filter.cat.DateCategory;
import com.github.croesch.partimana.types.Participant;
import java.util.Date;

/**
 * The category that describes the attribute <em>birthday</em> of a {@link Participant}.
 *
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public final class BirthdayCategory extends DateCategory<Participant> {

  /**
   * Constructs the category that describes the attribute <em>birthday</em> of a {@link Participant}.
   *
   * @since Date: Oct 27, 2012
   */
  public BirthdayCategory() {
    super(Text.FILTER_CAT_PARTICIPANT_BIRTHDAY);
  }

  @Override
  protected Date getValue(final Participant element) {
    return element.getBirthDate();
  }

  @Override
  public BirthdayCategory getCopy() {
    final BirthdayCategory copy = new BirthdayCategory();
    if (getFilter() != null) {
      copy.setFilter(getFilter().getCopy());
    }
    return copy;
  }
}
