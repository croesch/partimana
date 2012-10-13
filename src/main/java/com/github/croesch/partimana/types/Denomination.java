package com.github.croesch.partimana.types;

import com.github.croesch.annotate.MayBeNull;
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
  private final String text;

  /** the database representation of this object */
  private final int dbRepresentation;

  /**
   * Constructs a {@link Denomination} with the given i18n representation of the specific object.
   * 
   * @author croesch
   * @since Date: Jun 21, 2011
   * @param t the {@link Text} that represents this object.
   * @param r the storable representation of that object.
   */
  private Denomination(final Text t, final int r) {
    this.text = t.text();
    this.dbRepresentation = r;
  }

  @Override
  @NotNull
  public String toString() {
    return this.text;
  }

  /**
   * Returns the representation of this denomination for a database.
   * 
   * @since Date: Oct 14, 2012
   * @return the representation of this denomination for a database.
   */
  public int getStorableRepresentation() {
    return this.dbRepresentation;
  }

  /**
   * Returns the denomination that is represented by the given database value.
   * 
   * @since Date: Oct 14, 2012
   * @param idx the value of the database that represents a denomination
   * @return the denomination represented by the given value,<br>
   *         or <code>null</code> if no denomination is represented by the given value
   */
  @MayBeNull
  public static Denomination of(final int idx) {
    for (final Denomination d : values()) {
      if (d.getStorableRepresentation() == idx) {
        return d;
      }
    }
    return null;
  }
}
