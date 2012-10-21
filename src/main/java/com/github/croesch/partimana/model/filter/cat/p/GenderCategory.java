package com.github.croesch.partimana.model.filter.cat.p;

import java.util.List;

import com.github.croesch.partimana.model.api.IFilterType;
import com.github.croesch.partimana.model.filter.cat.ACategory;
import com.github.croesch.partimana.types.Gender;
import com.github.croesch.partimana.types.Participant;

/**
 * TODO Comment here ...
 * 
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public class GenderCategory extends ACategory<Participant, Gender> {

  public GenderCategory() {
    super(null);
  }

  @Override
  public List<IFilterType<Gender>> getFilterTypes() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  protected Gender getValue(final Participant element) {
    return element.getGender();
  }

}
