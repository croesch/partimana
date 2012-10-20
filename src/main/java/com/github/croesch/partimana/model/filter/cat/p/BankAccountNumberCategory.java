package com.github.croesch.partimana.model.filter.cat.p;

import com.github.croesch.partimana.model.filter.cat.IntegerCategory;
import com.github.croesch.partimana.types.Participant;

/**
 * TODO Comment here ...
 * 
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public class BankAccountNumberCategory extends IntegerCategory<Participant> {

  public BankAccountNumberCategory() {
    super(null);
    // TODO Auto-generated constructor stub
  }

  @Override
  protected Integer getValue(final Participant element) {
    return element.getBankAccountNumber();
  }

}
