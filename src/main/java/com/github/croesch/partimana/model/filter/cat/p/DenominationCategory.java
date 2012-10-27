package com.github.croesch.partimana.model.filter.cat.p;

import java.util.ArrayList;
import java.util.List;

import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.api.IFilterType;
import com.github.croesch.partimana.model.filter.cat.ACategory;
import com.github.croesch.partimana.model.filter.types.Equals;
import com.github.croesch.partimana.model.filter.types.NotEquals;
import com.github.croesch.partimana.types.Denomination;
import com.github.croesch.partimana.types.Participant;

/**
 * The category that describes the attribute <em>denomination</em> of a {@link Participant}.
 * 
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public final class DenominationCategory extends ACategory<Participant, Denomination> {

  /**
   * Constructs the category that describes the attribute <em>denomination</em> of a {@link Participant}.
   * 
   * @since Date: Oct 27, 2012
   */
  public DenominationCategory() {
    super(Text.FILTER_CAT_PARTICIPANT_DENOMINATION);
  }

  @Override
  public List<IFilterType<Denomination>> getFilterTypes() {
    final List<IFilterType<Denomination>> l = new ArrayList<IFilterType<Denomination>>();
    l.add(new Equals<Denomination>());
    l.add(new NotEquals<Denomination>());
    return l;
  }

  @Override
  protected Denomination getValue(final Participant element) {
    return element.getDenomination();
  }
}
