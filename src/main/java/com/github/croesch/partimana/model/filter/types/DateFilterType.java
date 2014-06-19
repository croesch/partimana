package com.github.croesch.partimana.model.filter.types;

import com.github.croesch.components.CDateField;
import java.util.Date;
import javax.swing.JComponent;

/**
 * Abstract date filter that filters date-objects based on the filter value.
 *
 * @author croesch
 * @since Date: Nov 17, 2012
 */
public abstract class DateFilterType extends AFilterType<Date> {
  @Override
  public final boolean parseFilterValue(final JComponent value) {
    if (value instanceof CDateField) {
      setFilterValue(((CDateField) value).getDateWithoutTime());
      return true;
    }
    return false;
  }
}
