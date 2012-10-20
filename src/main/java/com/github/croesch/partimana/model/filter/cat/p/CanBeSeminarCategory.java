package com.github.croesch.partimana.model.filter.cat.p;

import com.github.croesch.partimana.model.filter.cat.BooleanCategory;
import com.github.croesch.partimana.types.Participant;

/**
 * TODO Comment here ...
 * 
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public class CanBeSeminarCategory extends BooleanCategory<Participant> {

  public CanBeSeminarCategory() {
    super(null);
    // TODO Auto-generated constructor stub
  }

  @Override
  protected Boolean getValue(final Participant element) {
    return element.isPossibleSeminar();
  }

}
