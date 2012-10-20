package com.github.croesch.partimana.model.api;

import java.util.List;

import com.github.croesch.partimana.types.api.IFilterable;

/**
 * TODO Comment here ...
 * 
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public interface IFilterModel<F extends IFilterable> {

  List<IFilter<F>> getFilter();

  void and(IFilter<F> filter);

  void or(IFilter<F> filter);

  List<F> getFilterMatchingElements();
}
