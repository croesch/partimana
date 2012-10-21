package com.github.croesch.partimana.model.filter.cat.p;

import java.util.List;

import com.github.croesch.partimana.model.api.IFilterType;
import com.github.croesch.partimana.model.filter.cat.ACategory;
import com.github.croesch.partimana.types.CountyCouncil;
import com.github.croesch.partimana.types.Participant;

/**
 * TODO Comment here ...
 * 
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public class CountyCouncilCategory extends ACategory<Participant, CountyCouncil> {

  public CountyCouncilCategory() {
    super(null);
  }

  @Override
  public List<IFilterType<CountyCouncil>> getFilterTypes() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  protected CountyCouncil getValue(final Participant element) {
    return element.getCountyCouncil();
  }
}
