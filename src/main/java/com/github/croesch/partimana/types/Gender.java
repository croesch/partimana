package com.github.croesch.partimana.types;

import com.github.croesch.annotate.MayBeNull;
import com.github.croesch.annotate.NotNull;
import com.github.croesch.partimana.i18n.Text;

/**
 * Represents a Gender.
 * 
 * @author croesch
 * @since Date: Jun 16, 2011
 */
public enum Gender {

  /** the male gender */
  MALE (Text.MALE),

  /** the female gender */
  FEMALE (Text.FEMALE);

  /** the i18n representation of this gender */
  @NotNull
  private final String s;

  /**
   * Constructs a {@link Gender} with the given i18n representation of the specific object.
   * 
   * @author croesch
   * @since Date: Jun 21, 2011
   * @param t the {@link Text} that represents this object.
   */
  private Gender(final Text t) {
    this.s = t.text();
  }

  @Override
  @NotNull
  public String toString() {
    return this.s;
  }

  /**
   * Returns the representation of this gender for a database.
   * 
   * @since Date: Oct 14, 2012
   * @return the representation of this gender for a database.
   */
  @NotNull
  public String getStorableString() {
    return name().substring(0, 1);
  }

  /**
   * Returns the gender that is represented by the given database value.
   * 
   * @since Date: Oct 14, 2012
   * @param c the value of the database that represents a gender
   * @return the gender represented by the given value,<br>
   *         or <code>null</code> if no gender is represented by the given value
   */
  @MayBeNull
  public static Gender of(final String c) {
    if (c != null && !c.isEmpty()) {
      for (final Gender g : values()) {
        if (g.name().startsWith(c)) {
          return g;
        }
      }
    }
    return null;
  }
}
