package com.github.croesch.partimana.model.filter.types;

import com.github.croesch.partimana.types.Denomination;
import javax.swing.JComponent;
import javax.swing.JTextField;

/**
 * Abstract denomination filter that filters denomination-objects based on the filter value.
 *
 * @author croesch
 * @since Date: Nov 17, 2012
 */
public abstract class DenominationFilterType extends AFilterType<Denomination> {
  @Override
  public final boolean parseFilterValue(final JComponent value) {
    if (value instanceof JTextField) {
      final Denomination den = parse(((JTextField) value).getText());
      if (den != null) {
        setFilterValue(den);
        return true;
      }
    }
    return false;
  }

  /**
   * Parses the denomination from the given string and returns it. If the given string is not a valid denomination, it
   * returns <code>null</code>.
   *
   * @param value the string to parse to an denomination
   * @return the denomination value of the given string,<br> or <code>null</code> if the given string cannot be parsed
   * to a valid denomination
   * @since Date: Nov 17, 2012
   */
  private Denomination parse(final String value) {
    for (final Denomination den : Denomination.values()) {
      if (den.toString().equals(value)) {
        return den;
      }
    }
    return null;
  }
}
