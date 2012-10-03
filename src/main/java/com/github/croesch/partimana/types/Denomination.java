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

  /** evangelic / protestant */
  EVANGELIC (Text.EVANGELIC, 1),

  /** catholic */
  CATHOLIC (Text.CATHOLIC, 2),

  /** orthodox */
  ORTHODOX (Text.ORTHODOX, 3),

  /** muslim */
  MUSLIM (Text.MUSLIM, 4),

  /** free church */
  FREE_CHURCH (Text.FREE_CHURCH, 5),

  /** jewish */
  JEWISH (Text.JEWISH, 6),

  /** other denomination */
  OTHER (Text.OTHER_DENOMINATION, 7),

  /** no denomination */
  NONE (Text.NO_DENOMINATION, 8);

  /** the i18n representation of this object */
  @NotNull
  private final String t;

  /** the representation of this object */
  private final int s;

  /**
   * Constructs a {@link Denomination} with the given i18n representation of the specific object.
   * 
   * @author croesch
   * @since Date: Jun 21, 2011
   * @param t the {@link Text} that represents this object.
   * @param r the storable representation of that object.
   */
  private Denomination(final Text t, final int r) {
    this.t = t.text(); //FIXME null check!
    this.s = r;
  }

  @Override
  @NotNull
  public String toString() {
    return this.t;
  }

  public int getStorableRepresentation() {
    return this.s;
  }

  public static Denomination of(final int idx) {
    for (final Denomination d : values()) {
      if (d.getStorableRepresentation() == idx) {
        return d;
      }
    }
    return null;
  }
}
