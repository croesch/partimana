package com.github.croesch.partimana.model.filter.types;

import org.apache.log4j.Logger;

import com.github.croesch.partimana.i18n.Text;

/**
 * Abstract integer filter that filters integer-objects based on the filter value.
 * 
 * @author croesch
 * @since Date: Nov 17, 2012
 */
public abstract class IntegerFilterType extends AFilterType<Integer> {

  /** logging class */
  private static final Logger LOGGER = Logger.getLogger(IntegerFilterType.class);

  @Override
  public final boolean parseFilterValue(final String value) {
    Integer integer = null;
    try {
      integer = Integer.valueOf(value);
    } catch (final NumberFormatException nfe) {
      LOGGER.debug(Text.ERROR_EXCEPTION.text(nfe.getClass().getName()), nfe);
      // TODO information for the user
      return false;
    }
    if (integer != null) {
      setFilterValue(integer.intValue());
    }
    return true;
  }
}
