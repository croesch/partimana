package com.github.croesch.partimana.model.filter.cat.p;

import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.filter.cat.StringCategory;
import com.github.croesch.partimana.types.Participant;

/**
 * The category that describes the attribute <em>phone of parents</em> of a {@link Participant}.
 * 
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public final class PhoneOfParentsCategory extends StringCategory<Participant> {

  /**
   * Constructs the category that describes the attribute <em>phone of parents</em> of a {@link Participant}.
   * 
   * @since Date: Oct 27, 2012
   */
  public PhoneOfParentsCategory() {
    super(Text.FILTER_CAT_PARTICIPANT_PHONE_OF_PARENTS);
  }

  @Override
  protected String getValue(final Participant element) {
    return element.getPhoneOfParents();
  }
}
