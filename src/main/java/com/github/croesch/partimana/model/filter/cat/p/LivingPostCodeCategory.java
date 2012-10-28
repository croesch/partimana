package com.github.croesch.partimana.model.filter.cat.p;

import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.filter.cat.IntegerCategory;
import com.github.croesch.partimana.types.Participant;

/**
 * The category that describes the attribute <em>living postal code</em> of a {@link Participant}.
 * 
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public final class LivingPostCodeCategory extends IntegerCategory<Participant> {

  /**
   * Constructs the category that describes the attribute <em>living postal code</em> of a {@link Participant}.
   * 
   * @since Date: Oct 27, 2012
   */
  public LivingPostCodeCategory() {
    super(Text.FILTER_CAT_PARTICIPANT_LIVING_POSTAL_CODE);
  }

  @Override
  protected Integer getValue(final Participant element) {
    return element.getPostCode();
  }
}
