package com.github.croesch.partimana.model.filter;

import com.github.croesch.partimana.model.api.IFilterCategory;
import com.github.croesch.partimana.model.filter.cat.cp.SignedInCategory;
import com.github.croesch.partimana.types.CampParticipant;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a filter for {@link CampParticipant}-objects. Can have different {@link IFilterCategory}s to filter the
 * camp participants by different columns.
 *
 * @author croesch
 * @since Date: Mar 16, 2014
 */
public class CampParticipantFilter extends AFilter<CampParticipant> {

  @Override
  public final List<IFilterCategory<CampParticipant, ?>> getCategories() {
    final List<IFilterCategory<CampParticipant, ?>> categories = new ArrayList<IFilterCategory<CampParticipant, ?>>();
    categories.add(new SignedInCategory());
    return categories;
  }
}
