package com.github.croesch.partimana.model.api;

import java.util.List;

import com.github.croesch.partimana.types.api.IFilterable;

/**
 * TODO Comment here ...
 * 
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public interface IFilter<F extends IFilterable> {

  List<IFilterCategory<F, ?>> getCategories();

  List<F> filter(List<F> elements);

  IFilterCategory<F, ?> getCategory();

  void setCategory(IFilterCategory<F, ?> cat);
}
