package com.github.croesch.partimana.model.filter.types;

import javax.swing.JComponent;
import javax.swing.JTextField;

/**
 * Abstract boolean filter that filters boolean-objects based on the filter value.
 *
 * @author croesch
 * @since Date: Nov 17, 2012
 */
public abstract class BooleanFilterType extends AFilterType<Boolean> {

  @Override
  public final boolean parseFilterValue(final JComponent value) {
    if (value instanceof JTextField) {
      setFilterValue(Boolean.valueOf(((JTextField) value).getText()));
      return true;
    }
    return false;
  }
}
