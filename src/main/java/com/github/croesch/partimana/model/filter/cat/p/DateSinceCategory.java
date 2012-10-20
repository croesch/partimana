package com.github.croesch.partimana.model.filter.cat.p;

import java.util.Date;

import com.github.croesch.partimana.model.filter.cat.DateCategory;
import com.github.croesch.partimana.types.Participant;

/**
 * TODO Comment here ...
 * 
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public class DateSinceCategory extends DateCategory<Participant> {

  public DateSinceCategory() {
    super(null);
    // TODO Auto-generated constructor stub
  }

  @Override
  protected Date getValue(final Participant element) {
    return element.getDateSinceInDataBase();
  }

}
