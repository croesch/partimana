package com.github.croesch.partimana.model.filter;

import java.util.ArrayList;
import java.util.List;

import com.github.croesch.partimana.model.api.IFilterCategory;
import com.github.croesch.partimana.model.filter.cat.c.FromCategory;
import com.github.croesch.partimana.model.filter.cat.c.LocationCategory;
import com.github.croesch.partimana.model.filter.cat.c.NameCategory;
import com.github.croesch.partimana.model.filter.cat.c.RatePerDayChildCategory;
import com.github.croesch.partimana.model.filter.cat.c.RatePerParticipantCategory;
import com.github.croesch.partimana.model.filter.cat.c.UntilCategory;
import com.github.croesch.partimana.types.Camp;

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
