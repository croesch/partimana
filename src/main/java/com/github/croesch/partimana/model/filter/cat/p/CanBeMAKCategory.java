package com.github.croesch.partimana.model.filter.cat.p;

import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.filter.cat.BooleanCategory;
import com.github.croesch.partimana.types.Participant;

/**
 * The category that describes the attribute <em>can be MAK</em> of a {@link Participant}.
 * 
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public final class CanBeMAKCategory extends BooleanCategory<Participant> {

  /**
   * Constructs the category that describes the attribute <em>can be MAK</em> of a {@link Participant}.
   * 
   * @since Date: Oct 27, 2012
   */
  public CanBeMAKCategory() {
    super(Text.FILTER_CAT_PARTICIPANT_CAN_BE_MAK);
  }

  @Override
  protected Boolean getValue(final Participant element) {
    return element.isPossibleMAK();
  }
}
