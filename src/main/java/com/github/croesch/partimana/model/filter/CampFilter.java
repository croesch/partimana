package com.github.croesch.partimana.model.filter;

import com.github.croesch.partimana.model.api.IFilterCategory;
import com.github.croesch.partimana.model.filter.cat.c.*;
import com.github.croesch.partimana.types.Camp;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a filter for {@link Camp}-objects. Can have different {@link IFilterCategory}s to filter the camps by
 * different columns.
 *
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public final class CampFilter extends AFilter<Camp> {

  @Override
  public List<IFilterCategory<Camp, ?>> getCategories() {
    final List<IFilterCategory<Camp, ?>> categories = new ArrayList<IFilterCategory<Camp, ?>>();
    categories.add(new FromCategory());
    categories.add(new LocationCategory());
    categories.add(new NameCategory());
    categories.add(new RatePerDayChildCategory());
    categories.add(new RatePerParticipantCategory());
    categories.add(new UntilCategory());
    return categories;
  }
}
