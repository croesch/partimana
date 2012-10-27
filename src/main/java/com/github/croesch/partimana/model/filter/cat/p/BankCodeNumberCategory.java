package com.github.croesch.partimana.model.filter.cat.p;

import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.filter.cat.IntegerCategory;
import com.github.croesch.partimana.types.Participant;

/**
 * The category that describes the attribute <em>bank code number</em> of a {@link Participant}.
 * 
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public final class BankCodeNumberCategory extends IntegerCategory<Participant> {

  /**
   * Constructs the category that describes the attribute <em>bank code number</em> of a {@link Participant}.
   * 
   * @since Date: Oct 27, 2012
   */
  public BankCodeNumberCategory() {
    super(Text.FILTER_CAT_PARTICIPANT_BANK_CODE_NUM);
  }

  @Override
  protected Integer getValue(final Participant element) {
    return element.getBankCodeNumber();
  }
}
