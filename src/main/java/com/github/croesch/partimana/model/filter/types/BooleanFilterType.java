package com.github.croesch.partimana.model.filter.types;

import javax.swing.JTextField;

/**
 * Abstract boolean filter that filters boolean-objects based on the filter value.
 *
 * @author croesch
 * @since Date: Nov 17, 2012
 */
public abstract class BooleanFilterType extends AFilterType<Boolean> {

  @Override
  public final boolean parseFilterValue(final JTextField value) {
    setFilterValue(Boolean.valueOf(value.getText()));
    return true;
  }
}
