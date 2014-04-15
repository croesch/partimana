package com.github.croesch.partimana.model.filter.types;

import javax.swing.JTextField;

/**
 * Abstract string filter that filters string-objects based on the filter value.
 *
 * @author croesch
 * @since Date: Nov 17, 2012
 */
public abstract class StringFilterType extends AFilterType<String> {
  @Override
  public final boolean parseFilterValue(final JTextField value) {
    setFilterValue(value.getText());
    return true;
  }
}
