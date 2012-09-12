package com.github.croesch.partimana.types;

import com.github.croesch.annotate.NotNull;
import com.github.croesch.partimana.i18n.Text;

/**
 * Represents the types of denomiation.
 * 
 * @author croesch
 * @since Date: Jun 16, 2011
 */
public enum Denomination {

  //TODO add 'no denomination'

  /** evangelic / protestant */
  EVANGELIC (Text.EVANGELIC),

  /** catholic */
  CATHOLIC (Text.CATHOLIC),

  /** orthodox */
  ORTHODOX (Text.ORTHODOX),

  /** muslim */
  MUSLIM (Text.MUSLIM),

  /** free church */
  FREE_CHURCH (Text.FREE_CHURCH),

  /** jewish */
  JEWISH (Text.JEWISH),

  /** other denomination */
  OTHER (Text.OTHER_DENOMINATION),

  /** no denomination */
  NONE (Text.NO_DENOMINATION);

  /** the i18n representation of this object */
  @NotNull
  private final String s;

  /**
   * Constructs a {@link Denomination} with the given i18n representation of the specific object.
   * 
   * @author croesch
   * @since Date: Jun 21, 2011
   * @param t the {@link Text} that represents this object.
   */
  private Denomination(@NotNull final Text t) {
    this.s = t.text(); //FIXME null check!
  }

  @Override
  @NotNull
  public String toString() {
    return this.s;
  }

}
