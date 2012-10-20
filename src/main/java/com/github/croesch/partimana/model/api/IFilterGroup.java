package com.github.croesch.partimana.model.api;

import java.util.List;

import com.github.croesch.partimana.types.api.IFilterable;

/**
 * TODO Comment here ...
 * 
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public interface IFilterGroup<F extends IFilterable> {

  void add(final IFilter<F> filter);

  void add(final IFilterGroup<F> filterGroup);

  List<IFilter<F>> getFilters();

  List<IFilterGroup<F>> getFilterGroups();

  List<F> filter(List<F> elements);

  String getShortDescription();
}
