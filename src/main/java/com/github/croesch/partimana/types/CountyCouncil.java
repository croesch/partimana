package com.github.croesch.partimana.types;

import com.github.croesch.annotate.MayBeNull;
import com.github.croesch.annotate.NotNull;
import com.github.croesch.partimana.i18n.Text;

/**
 * Represents the available county councils.
 * 
 * @author croesch
 * @since Date: Jun 16, 2011
 */
public enum CountyCouncil {

  /** unknown county council */
  UNKNOWN (Text.UNKNOWN, 0),

  /** county of alzey */
  COUNTY_ALZEY (Text.COUNTY_ALZEY, 1),

  /** county of bad kreuznach */
  COUNTY_BAD_KREUZNACH (Text.COUNTY_BAD_KREUZNACH, 2),

  /** county of bad duerkheim */
  COUNTY_BAD_DUERKHEIM (Text.COUNTY_BAD_DUERKHEIM, 3),

  /** city of frankenthal */
  CITY_FRANKENTHAL (Text.CITY_FRANKENTHAL, 4),

  /** county of germersheim */
  COUNTY_GERMERSHEIM (Text.COUNTY_GERMERSHEIM, 5),

  /** city of kaiserslautern */
  CITY_KAISERSLAUTERN (Text.CITY_KAISERSLAUTERN, 6),

  /** county of kaiserslautern */
  COUNTY_KAISERSLAUTERN (Text.COUNTY_KAISERSLAUTERN, 7),

  /** county of kirchheimbolanden */
  COUNTY_KIRCHHEIMBOLANDEN (Text.COUNTY_KIRCHHEIMBOLANDEN, 8),

  /** county of kusel */
  COUNTY_KUSEL (Text.COUNTY_KUSEL, 9),

  /** city of landau */
  CITY_LANDAU (Text.CITY_LANDAU, 10),

  /** city of ludwigshafen */
  CITY_LUDWIGSHAFEN (Text.CITY_LUDWIGSHAFEN, 11),

  /** county of rhein-pfalz */
  COUNTY_RHEIN_PFALZ (Text.COUNTY_RHEIN_PFALZ, 12),

  /** city of neustadt */
  CITY_NEUSTADT (Text.CITY_NEUSTADT, 13),

  /** city of pirmasens */
  CITY_PIRMASENS (Text.CITY_PIRMASENS, 14),

  /** city of speyer */
  CITY_SPEYER (Text.CITY_SPEYER, 15),

  /** county of suedliche weinstrasse */
  COUNTY_SUEDLICHE_WEINSTRASSE (Text.COUNTY_SUEDLICHE_WEINSTRASSE, 16),

  /** county of */
  COUNTY_SUEDWESTPFALZ (Text.COUNTY_SUEDWESTPFALZ, 17),

  /** city of zweibruecken */
  CITY_ZWEIBRUECKEN (Text.CITY_ZWEIBRUECKEN, 18),

  /** another county council */
  OTHER (Text.OTHER, 19);

  /** the i18n representation of this county council */
  @NotNull
  private final String s;

  /** the database representation of this county council */
  private final int dbRepresentation;

  /**
   * Constructs a {@link CountyCouncil} with the given i18n representation of the specific county council.
   * 
   * @author croesch
   * @since Date: Jun 21, 2011
   * @param t the {@link Text} that represents this council.
   * @param r the storable representation of this council.
   */
  private CountyCouncil(final Text t, final int r) {
    this.s = t.text();
    this.dbRepresentation = r;
  }

  @Override
  @NotNull
  public String toString() {
    return this.s;
  }

  /**
   * Returns the representation of this county council for a database.
   * 
   * @since Date: Oct 14, 2012
   * @return the representation of this county council for a database.
   */
  public int getStorableRepresentation() {
    return this.dbRepresentation;
  }

  /**
   * Returns the county council that is represented by the given database value.
   * 
   * @since Date: Oct 14, 2012
   * @param idx the value of the database that represents a county council
   * @return the county council represented by the given value,<br>
   *         or <code>null</code> if no county council is represented by the given value
   */
  @MayBeNull
  public static CountyCouncil of(final int idx) {
    for (final CountyCouncil cc : values()) {
      if (cc.getStorableRepresentation() == idx) {
        return cc;
      }
    }
    return null;
  }
}
