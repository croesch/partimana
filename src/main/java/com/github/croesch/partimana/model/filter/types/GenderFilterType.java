package com.github.croesch.partimana.model.filter.types;

import com.github.croesch.partimana.types.Gender;
import javax.swing.JComponent;
import javax.swing.JTextField;

/**
 * Abstract gender filter that filters gender-objects based on the filter value.
 *
 * @author croesch
 * @since Date: Nov 17, 2012
 */
public abstract class GenderFilterType extends AFilterType<Gender> {

  @Override
  public final boolean parseFilterValue(final JComponent value) {
    if (value instanceof JTextField) {
      final Gender gen = parse(((JTextField) value).getText());
      if (gen != null) {
        setFilterValue(gen);
        return true;
      }
    }
    return false;
  }

  /**
   * Parses the gender from the given string and returns it. If the given string is not a valid gender, it returns
   * <code>null</code>.
   *
   * @param value the string to parse to an gender
   * @return the gender value of the given string,<br> or <code>null</code> if the given string cannot be parsed to a
   * valid gender
   * @since Date: Nov 17, 2012
   */
  private Gender parse(final String value) {
    for (final Gender cc : Gender.values()) {
      if (cc.toString().equals(value)) {
        return cc;
      }
    }
    return null;
  }
}
