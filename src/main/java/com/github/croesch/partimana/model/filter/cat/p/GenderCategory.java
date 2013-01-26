package com.github.croesch.partimana.model.filter.cat.p;

import java.util.ArrayList;
import java.util.List;

import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.api.IFilterType;
import com.github.croesch.partimana.model.filter.cat.ACategory;
import com.github.croesch.partimana.model.filter.types.GenderEquals;
import com.github.croesch.partimana.model.filter.types.GenderNotEquals;
import com.github.croesch.partimana.types.Gender;
import com.github.croesch.partimana.types.Participant;

/**
 * The category that describes the attribute <em>gender</em> of a {@link Participant}.
 * 
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public final class GenderCategory extends ACategory<Participant, Gender> {

  /**
   * Constructs the category that describes the attribute <em>gender</em> of a {@link Participant}.
   * 
   * @since Date: Oct 27, 2012
   */
  public GenderCategory() {
    super(Text.FILTER_CAT_PARTICIPANT_GENDER);
  }

  @Override
  public List<IFilterType<Gender>> getFilterTypes() {
    final List<IFilterType<Gender>> l = new ArrayList<IFilterType<Gender>>();
    l.add(new GenderEquals());
    l.add(new GenderNotEquals());
    return l;
  }

  @Override
  protected Gender getValue(final Participant element) {
    return element.getGender();
  }

  @Override
  public GenderCategory getCopy() {
    final GenderCategory copy = new GenderCategory();
    if (getFilter() != null) {
      copy.setFilter(getFilter().getCopy());
    }
    return copy;
  }
}
