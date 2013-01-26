package com.github.croesch.partimana.model.filter.cat.p;

import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.filter.cat.StringCategory;
import com.github.croesch.partimana.types.Participant;

/**
 * The category that describes the attribute <em>mobile phone</em> of a {@link Participant}.
 * 
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public final class MobilePhoneCategory extends StringCategory<Participant> {

  /**
   * Constructs the category that describes the attribute <em>mobile phone</em> of a {@link Participant}.
   * 
   * @since Date: Oct 27, 2012
   */
  public MobilePhoneCategory() {
    super(Text.FILTER_CAT_PARTICIPANT_MOBILE_PHONE);
  }

  @Override
  protected String getValue(final Participant element) {
    return element.getMobilePhone();
  }

  @Override
  public MobilePhoneCategory getCopy() {
    final MobilePhoneCategory copy = new MobilePhoneCategory();
    if (getFilter() != null) {
      copy.setFilter(getFilter().getCopy());
    }
    return copy;
  }
}
