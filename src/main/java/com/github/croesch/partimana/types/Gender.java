package com.github.croesch.partimana.types;

import com.github.croesch.partimana.annotation.NotNull;
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
  private Gender(@NotNull final Text t) {
    this.s = t.text(); //FIXME null check
  }

  @Override
  @NotNull
  public String toString() {
    return this.s;
  }
}
