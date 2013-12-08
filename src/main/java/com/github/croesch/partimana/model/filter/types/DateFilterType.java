package com.github.croesch.partimana.model.filter.types;

import com.github.croesch.partimana.i18n.Text;
import java.util.Date;
import org.apache.log4j.Logger;

/**
 * Abstract date filter that filters date-objects based on the filter value.
 *
 * @author croesch
 * @since Date: Nov 17, 2012
 */
public abstract class DateFilterType extends AFilterType<Date> {

  /** logging class */
  private static final Logger LOGGER = Logger.getLogger(DateFilterType.class);

  @Override
  public final boolean parseFilterValue(final String value) {
    Date date = null;
    try {
      date = new Date(Long.parseLong(value));
    } catch (final NumberFormatException nfe) {
      LOGGER.debug(Text.ERROR_EXCEPTION.text(nfe.getClass().getName()), nfe);
      // TODO information for the user
      return false;
    }
    if (date != null) {
      setFilterValue(date);
    }
    return true;
  }
}
