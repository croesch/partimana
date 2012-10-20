package com.github.croesch.partimana.model.filter.cat.p;

import com.github.croesch.partimana.model.filter.cat.BooleanCategory;
import com.github.croesch.partimana.types.Participant;

/**
 * TODO Comment here ...
 * 
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public class CanBeKitchenCategory extends BooleanCategory<Participant> {

  public CanBeKitchenCategory() {
    super(null);
    // TODO Auto-generated constructor stub
  }

  @Override
  protected Boolean getValue(final Participant element) {
    return element.isPossibleKitchen();
  }

}
