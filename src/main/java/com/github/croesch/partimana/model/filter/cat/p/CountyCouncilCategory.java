package com.github.croesch.partimana.model.filter.cat.p;

import java.util.ArrayList;
import java.util.List;

import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.api.IFilterType;
import com.github.croesch.partimana.model.filter.cat.ACategory;
import com.github.croesch.partimana.model.filter.types.Equals;
import com.github.croesch.partimana.model.filter.types.NotEquals;
import com.github.croesch.partimana.types.CountyCouncil;
import com.github.croesch.partimana.types.Participant;

/**
 * The category that describes the attribute <em>county council</em> of a {@link Participant}.
 * 
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public final class CountyCouncilCategory extends ACategory<Participant, CountyCouncil> {

  /**
   * Constructs the category that describes the attribute <em>county council</em> of a {@link Participant}.
   * 
   * @since Date: Oct 27, 2012
   */
  public CountyCouncilCategory() {
    super(Text.FILTER_CAT_PARTICIPANT_COUNTY_COUNCIL);
  }

  @Override
  public List<IFilterType<CountyCouncil>> getFilterTypes() {
    final List<IFilterType<CountyCouncil>> l = new ArrayList<IFilterType<CountyCouncil>>();
    l.add(new Equals<CountyCouncil>());
    l.add(new NotEquals<CountyCouncil>());
    return l;
  }

  @Override
  protected CountyCouncil getValue(final Participant element) {
    return element.getCountyCouncil();
  }
}
