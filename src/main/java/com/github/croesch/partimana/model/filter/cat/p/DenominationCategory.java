package com.github.croesch.partimana.model.filter.cat.p;

import java.util.List;

import com.github.croesch.partimana.model.api.IFilterType;
import com.github.croesch.partimana.model.filter.cat.ACategory;
import com.github.croesch.partimana.types.Denomination;
import com.github.croesch.partimana.types.Participant;

/**
 * TODO Comment here ...
 * 
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public class DenominationCategory extends ACategory<Participant, Denomination> {

  public DenominationCategory() {
    super(null);
  }

  @Override
  public List<IFilterType<Denomination>> getFilterTypes() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  protected Denomination getValue(final Participant element) {
    return element.getDenomination();
  }

}
