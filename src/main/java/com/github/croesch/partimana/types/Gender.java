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
  MALE(Text.MALE, "m"),

  /** the female gender */
  FEMALE(Text.FEMALE, "w");

  /** the i18n representation of this gender */
  @NotNull
  private final String s;

  /** the technical representation of this gender. */
  @NotNull
  private final String representation;

  /**
   * Constructs a {@link Gender} with the given i18n representation of the specific object.
   *
   * @param rep the representation of this object. This is ensured to be not changed frequently as it may be stored in
   *            the database or something like that.
   * @param t   the {@link Text} that represents this object.
   * @since Date: Jun 21, 2011
   */
  private Gender(final Text t, String rep) {
    s = t.text();
    representation = rep;
  }

  @Override
  @NotNull
  public String toString() {
    return s;
  }


  /**
   * Returns the representation of this gender for a database.
   *
   * @return the representation of this gender for a database.
   * @since Date: Oct 14, 2012
   */
  @NotNull
  public String getStorableString() {
    return name().substring(0, 1);
  }

  /**
   * Returns the gender that is represented by the given database value.
   *
   * @param c the value of the database that represents a gender
   * @return the gender represented by the given value,<br> or <code>null</code> if no gender is represented by the
   * given value
   * @since Date: Oct 14, 2012
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

  /**
   * @return the technical representation of this gender.
   * @since Date: Dec 8, 2013
   */
  @NotNull
  public String getRepresentation() {
    return representation;
  }
}
