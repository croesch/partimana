package com.github.croesch.partimana.types.api;

/**
 * Interface to mark types that should provide descriptions for showing to the user.
 * 
 * @author croesch
 * @since Date: Oct 22, 2012
 */
public interface IDescribable {

  /**
   * Returns a short description of this element. Typically this is shown as text of a GUI component.
   * 
   * @since Date: Oct 21, 2012
   * @return the string representation of this element
   */
  String getShortDescription();

}
