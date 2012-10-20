package com.github.croesch.partimana.model.api;

/**
 * TODO Comment here ...
 * 
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public interface IFilterType<OT extends Object> {

  String getShortDescription();

  void setFilterValue(OT value);

  OT getFilterValue();

  boolean matches(OT object);

}
