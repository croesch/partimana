package com.github.croesch.partimana.model.filter.cat.p;

import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.filter.cat.IntegerCategory;
import com.github.croesch.partimana.types.Participant;

/**
 * The category that describes the attribute <em>bank account number</em> of a {@link Participant}.
 *
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public final class BankAccountNumberCategory extends IntegerCategory<Participant> {

  /**
   * Constructs the category that describes the attribute <em>bank account number</em> of a {@link Participant}.
   *
   * @since Date: Oct 27, 2012
   */
  public BankAccountNumberCategory() {
    super(Text.FILTER_CAT_PARTICIPANT_BANK_ACC_NUM);
  }

  @Override
  protected Integer getValue(final Participant element) {
    return element.getBankAccountNumber();
  }

  @Override
  public BankAccountNumberCategory getCopy() {
    final BankAccountNumberCategory copy = new BankAccountNumberCategory();
    if (getFilter() != null) {
      copy.setFilter(getFilter().getCopy());
    }
    return copy;
  }
}
