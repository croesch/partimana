package com.github.croesch.types;

import com.github.croesch.i18n.Text;

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
  public String toString() {
    return this.s;
  }
}
