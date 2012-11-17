package com.github.croesch.partimana.view;

import java.util.List;

import com.github.croesch.partimana.actions.ActionObserver;
import com.github.croesch.partimana.model.api.IFilter;
import com.github.croesch.partimana.model.filter.CampFilter;
import com.github.croesch.partimana.model.filter.FilterModel;
import com.github.croesch.partimana.model.filter.cat.c.FromCategory;
import com.github.croesch.partimana.model.filter.cat.c.LocationCategory;
import com.github.croesch.partimana.model.filter.cat.c.NameCategory;
import com.github.croesch.partimana.model.filter.cat.c.RatePerDayChildCategory;
import com.github.croesch.partimana.model.filter.cat.c.RatePerParticipantCategory;
import com.github.croesch.partimana.model.filter.cat.c.UntilCategory;
import com.github.croesch.partimana.types.Camp;

/**
 * The view that allows users to search the stored camps.
 * 
 * @author croesch
 * @since Date: Nov 11, 2012
 */
public final class CampSearchView extends ASearchView<Camp> {

  /** generated serial version UID */
  private static final long serialVersionUID = -7644046114952605154L;

  /** the view that visualizes the filter matching camps */
  private AListView<Camp> lView;

  /** contains the possible categories the camps can be searched for */
  private Object[] objects;

  /**
   * Constructs the camp search view that allows the user to search stored camps.
   * 
   * @since Date: Nov 11, 2012
   * @param name the name of this view
   * @param elements the elements that can be searched
   * @param o the observer that will be notified, when a camp selection has been made
   */
  public CampSearchView(final String name, final List<Camp> elements, final ActionObserver o) {
    super(name, new FilterModel<Camp>(elements), o);
  }

  @Override
  protected AListView<Camp> getListView() {
    if (this.lView == null) {
      this.lView = new CampListView("list", getObserver());
    }
    return this.lView;
  }

  @Override
  protected Object[] getPossibleCategories() {
    if (this.objects == null) {
      this.objects = new Object[] { new NameCategory(),
                                   new LocationCategory(),
                                   new FromCategory(),
                                   new UntilCategory(),
                                   new RatePerParticipantCategory(),
                                   new RatePerDayChildCategory() };
    }
    return this.objects;
  }

  @Override
  protected IFilter<Camp> createEmptyFilter() {
    return new CampFilter();
  }
}