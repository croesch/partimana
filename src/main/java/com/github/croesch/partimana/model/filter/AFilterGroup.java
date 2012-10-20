package com.github.croesch.partimana.model.filter;

import java.util.ArrayList;
import java.util.List;

import com.github.croesch.partimana.model.api.IFilterGroup;
import com.github.croesch.partimana.types.api.IFilterable;

/**
 * TODO Comment here ...
 * 
 * @author croesch
 * @since Date: Oct 20, 2012
 */
abstract class AFilterGroup<F extends IFilterable> implements IFilterGroup<F> {

  private final List<IFilterGroup<F>> filterGroups = new ArrayList<IFilterGroup<F>>();

  @Override
  public void add(final IFilterGroup<F> filterGroup) {
    this.filterGroups.add(filterGroup);
  }

  @Override
  public List<IFilterGroup<F>> getFilterGroups() {
    return this.filterGroups;
  }
}
