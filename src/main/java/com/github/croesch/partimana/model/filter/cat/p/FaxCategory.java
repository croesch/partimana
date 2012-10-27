package com.github.croesch.partimana.model.filter.cat.p;

import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.filter.cat.StringCategory;
import com.github.croesch.partimana.types.Participant;

/**
 * The category that describes the attribute <em>fax</em> of a {@link Participant}.
 * 
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public final class FaxCategory extends StringCategory<Participant> {

  /**
   * Constructs the category that describes the attribute <em>fax</em> of a {@link Participant}.
   * 
   * @since Date: Oct 27, 2012
   */
  public FaxCategory() {
    super(Text.FILTER_CAT_PARTICIPANT_FAX);
  }

  @Override
  protected String getValue(final Participant element) {
    return element.getFax();
  }
}
