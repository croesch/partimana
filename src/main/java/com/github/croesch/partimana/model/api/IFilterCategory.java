package com.github.croesch.partimana.model.api;

import java.util.List;

import com.github.croesch.partimana.types.api.IFilterable;

/**
 * TODO Comment here ...
 * 
 * @author croesch
 * @param <OT>
 * @since Date: Oct 20, 2012
 */
public interface IFilterCategory<F extends IFilterable, OT> {

  String getShortDescription();

  List<IFilterType<OT>> getFilterTypes();

  IFilterType<OT> getFilter();

  void setFilter(IFilterType<OT> filter);

  boolean isMatchingFilter(F element);

}
