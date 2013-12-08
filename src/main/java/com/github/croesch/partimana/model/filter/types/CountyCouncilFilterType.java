package com.github.croesch.partimana.model.filter.types;

import com.github.croesch.partimana.types.CountyCouncil;

/**
 * Abstract county council filter that filters county-council-objects based on the filter value.
 *
 * @author croesch
 * @since Date: Nov 17, 2012
 */
public abstract class CountyCouncilFilterType extends AFilterType<CountyCouncil> {
  @Override
  public final boolean parseFilterValue(final String value) {
    final CountyCouncil cc = parse(value);
    if (cc != null) {
      setFilterValue(cc);
      return true;
    }
    return false;
  }

  /**
   * Parses the county council from the given string and returns it. If the given string is not a valid county council,
   * it returns <code>null</code>.
   *
   * @param value the string to parse to an county council
   * @return the county council value of the given string,<br> or <code>null</code> if the given string cannot be parsed
   * to a valid county council
   * @since Date: Nov 17, 2012
   */
  private CountyCouncil parse(final String value) {
    for (final CountyCouncil cc : CountyCouncil.values()) {
      if (cc.toString().equals(value)) {
        return cc;
      }
    }
    return null;
  }
}
