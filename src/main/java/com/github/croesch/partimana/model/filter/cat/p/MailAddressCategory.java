package com.github.croesch.partimana.model.filter.cat.p;

import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.filter.cat.StringCategory;
import com.github.croesch.partimana.types.Participant;

/**
 * The category that describes the attribute <em>mail address</em> of a {@link Participant}.
 *
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public final class MailAddressCategory extends StringCategory<Participant> {

  /**
   * Constructs the category that describes the attribute <em>mail address</em> of a {@link Participant}.
   *
   * @since Date: Oct 27, 2012
   */
  public MailAddressCategory() {
    super(Text.FILTER_CAT_PARTICIPANT_MAIL_ADDRESS);
  }

  @Override
  protected String getValue(final Participant element) {
    return element.getMailAddress();
  }

  @Override
  public MailAddressCategory getCopy() {
    final MailAddressCategory copy = new MailAddressCategory();
    if (getFilter() != null) {
      copy.setFilter(getFilter().getCopy());
    }
    return copy;
  }
}
