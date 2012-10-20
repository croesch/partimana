package com.github.croesch.partimana.model.api;

/**
 * TODO Comment here ...
 * 
 * @author croesch
 * @since Date: Oct 20, 2012
 */
public interface IFilterType<OT extends Object> {

  String getShortDescription();

  boolean matches(OT object);

}
