package com.github.croesch.partimana.model.filter;

import java.util.ArrayList;
import java.util.List;

import com.github.croesch.partimana.i18n.Text;
import com.github.croesch.partimana.model.api.IFilterGroup;
import com.github.croesch.partimana.types.api.IFilterable;

/**
 * A group that is able to group other groups or filters.
 * 
 * @param <F> the type of the objects the filters this group combines can filter
 * @author croesch
 * @since Date: Oct 20, 2012
 */
abstract class AFilterGroup<F extends IFilterable> implements IFilterGroup<F> {

  /** list of groups inside that group */
  private final List<IFilterGroup<F>> filterGroups = new ArrayList<IFilterGroup<F>>();

  /** the i18n text that describes this group to the user */
  private final Text description;

  /**
   * Constructs a new group with the given description.
   * 
   * @since Date: Oct 30, 2012
   * @param descr the i18n value that describes this group
   */
  public AFilterGroup(final Text descr) {
    this.description = descr;
  }

  @Override
  public String getShortDescription() {
    return this.description.text();
  }

  @Override
  public void add(final IFilterGroup<F> filterGroup) {
    this.filterGroups.add(filterGroup);
  }

  @Override
  public List<IFilterGroup<F>> getFilterGroups() {
    return this.filterGroups;
  }
}
